package com.msclient1.msclient1.endpoint;

import com.msclient1.msclient1.BO.ProductBOImpl;
import com.msclient1.msclient1.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/pim")
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public List<Product> prodList;

    @Autowired
    private ProductBOImpl productBO;

    /*public ProductService() {
        prodList = new ArrayList<Product>();
        prodList.add(new Product(1, "Apple"));
        prodList.add(new Product(2, "Samsung"));
        prodList.add(new Product(3, "Sony"));
    }*/

    @SuppressWarnings("unchecked")
    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        log.info("ms-client1 - getAllProducts - before");
        List<Product> prodList = productBO.getAllProducts();
        log.info("ms-client1 - getAllProducts - after");
        return prodList;
    }

}
