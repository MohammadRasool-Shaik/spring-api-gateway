package org.rash.micro.controller;

import lombok.extern.slf4j.Slf4j;
import org.rash.micro.entity.Product;
import org.rash.micro.exception.ProductNotFoundException;
import org.rash.micro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
@RefreshScope
public class ProductController {

    @Autowired
    protected ProductRepository productRepository;

    @Value("${msg:Config Server is not working. Please check...}")
    private String msg;

    @GetMapping
    public @ResponseBody
    List<Product> fetchAllProducts() {
        log.info("Fetch all products");
        return productRepository.findAll();
    }

    @GetMapping(value = "/msg")
    public @ResponseBody
    String getMsg() {
        log.info("Fetch all products");
        return msg;
    }

    @GetMapping(value = "/{productId}")
    public @ResponseBody
    Product fetchByProductId(@PathVariable Integer productId) {
        Product product = productRepository.findByProductId(productId);
        if (product == null)
            throw new ProductNotFoundException(productId);
        else {
            return product;
        }
    }

    @GetMapping(params = {"lowPrice", "highPrice"})
    public @ResponseBody
    List<Product> fetchByProductPrice(@RequestParam("lowPrice") Double lowPrice,

                                      @RequestParam("highPrice") Double highPrice) {
        return productRepository.findByProductPrice(lowPrice, highPrice);
    }

    @GetMapping(params = "ptype")
    public @ResponseBody
    List<Product> fetchByProductType(@RequestParam("ptype") String ptype) {
        return productRepository.findByProductType(ptype);
    }

    @GetMapping(params = "productName")
    public @ResponseBody
    List<Product> fetchByProductName(@RequestParam("productName") String productName) {
        return productRepository.findByProductNameIgnoreCase(productName);
    }

    @PostMapping
    public @ResponseBody
    Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping(value = "/{productId}")
    public @ResponseBody
    Integer removeProduct(@PathVariable Integer productId) {
        return productRepository.removeByProductId(productId);
    }

    @GetMapping(params = "userName")
    public @ResponseBody
    List<Product> fetchByUserName(@RequestParam("userName") String userName) {
        List<Product> products = fetchAllProducts();
        Map<String, List<Product>> productsByType = products.stream().collect(Collectors.groupingBy(Product::getProductType));
        if (userName.equals("user")) {
            return productsByType.get("soap");
        } else if (userName.equals("admin")) {
            return products;
        } else {
            return productsByType.get("mobile");
        }
    }
}
