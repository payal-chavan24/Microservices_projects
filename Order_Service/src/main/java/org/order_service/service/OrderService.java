package org.order_service.service;

import org.order_service.client.ProductClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final RestTemplate restTemplate;
     private final RestClient restClient;
    private final ProductClient productClient;
    public OrderService(RestTemplate restTemplate, RestClient restClient,ProductClient productClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.productClient = productClient;
    }

    public String placeOrder(Long productId) {

        // Rest Template Example
//        String response = restTemplate.getForObject(
//                "http://localhost:8080/products/" + productId,
//                String.class
//        );
//        String response = restClient.get()
//                .uri("http://localhost:8080/products/{productId}", productId)
//                .retrieve()
//                .body(String.class);
        String response = productClient.getProduct(productId);

        return "in stock".equals(response)?"order placed":"Product out of stock";
    }
}