package com.msclient1.msclient1.BO;

import com.msclient1.msclient1.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBOImpl {

    private static final Logger log = LoggerFactory.getLogger(ProductBOImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(groupKey = "ProductBOImpl", commandKey = "getAllProducts", fallbackMethod = "getAllProductsFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })
    public List<Product> getAllProducts() {
        log.info("ms-client1 - getAllProducts BO - before");
        List<Product> prodList = restTemplate.getForObject("http://db-service1/rest/pim/db/products", List.class);
        log.info("ms-client1 - getAllProducts BO - after");
        return prodList;
    }

    public List<Product> getAllProductsFallback() {
        log.info("ms-client1 - getAllProducts BO Fallback");
        return new ArrayList<>();
    }

}
