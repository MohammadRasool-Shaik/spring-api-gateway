package org.rash.micro.controller;

import org.rash.micro.entity.Product;
import org.rash.micro.exception.ProductNotFoundException;
import org.rash.micro.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    protected ProductRepository productRepository;

    @GetMapping
    public @ResponseBody
    List<Product> fetchAllProducts() {
        logger.info("Fetch all products");
        return productRepository.findAll();
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

}
