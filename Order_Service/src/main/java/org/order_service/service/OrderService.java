package org.order_service.service;

import org.order_service.client.ProductClient;
import org.order_service.dto.OrderRequest;
import org.order_service.dto.OrderResponse;
import org.order_service.entity.Order;
import org.order_service.exception.OrderNotFoundException;
import org.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    public OrderService(RestTemplate restTemplate, RestClient restClient, ProductClient productClient, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.productClient = productClient;
        this.orderRepository = orderRepository;
    }

    // existing method preserved
    public String placeOrder(Long productId) {
        String response = productClient.getProduct(productId);
        return "in stock".equals(response)?"order placed":"Product out of stock";
    }

    // CRUD methods
    public OrderResponse createOrder(OrderRequest req) {
        Order o = new Order();
        o.setProductId(req.getProductId());
        o.setQuantity(req.getQuantity());
        o.setTotalAmount(req.getTotalAmount());
        o.setStatus(req.getStatus());
        Order saved = orderRepository.save(o);
        return toResponse(saved);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<Order> reproduceNPlusOne() {
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId() + ", Items: " + order.getOrderItems().size());
        }

        return orders;
    }

    public List<Order> getAllOrdersWithOrderItems() {
        List<Order> orders = orderRepository.findAllWithOrderItems();

        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId() + ", Items: " + order.getOrderItems().size());
        }

        return orders;
    }

    public OrderResponse getOrderById(Long id) {
        Order o = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return toResponse(o);
    }

    public OrderResponse updateOrder(Long id, OrderRequest req) {
        Order o = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        o.setProductId(req.getProductId());
        o.setQuantity(req.getQuantity());
        o.setTotalAmount(req.getTotalAmount());
        o.setStatus(req.getStatus());
        Order saved = orderRepository.save(o);
        return toResponse(saved);
    }

    public void deleteOrder(Long id) {
        Order o = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(o);
    }

    private OrderResponse toResponse(Order o) {
        return new OrderResponse(o.getId(), o.getProductId(), o.getQuantity(), o.getTotalAmount(), o.getStatus());
    }
}