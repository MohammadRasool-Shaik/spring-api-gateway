package org.rash.micro.apigateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class CartService {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/cart/{userName}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getCartInfo(@PathVariable(name = "userName") String userName) {
        log.info("GET /cart/" + userName);

        String customerResponse = restTemplate.getForObject("http://user-service/" + userName, String.class);
        String productResponse = restTemplate.getForObject("http://product-service?userName=" + userName, String.class);

        return customerResponse + productResponse;
    }

    public String fallbackMethod(String userName) {

        return "Fallback response:: No User details available temporarily";
    }

}
