package org.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "PRODUCT-SERVICE"
     //   url = "http://localhost:8080"
)
public interface ProductClient {
    @GetMapping("/products/{id}")
    String getProduct(@PathVariable Long id);
}
