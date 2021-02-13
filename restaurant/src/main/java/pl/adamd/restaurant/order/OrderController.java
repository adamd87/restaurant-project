package pl.adamd.restaurant.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/by-table/{table}")
    public List<Order> getOrderByTable(@PathVariable Integer table) {
        return orderRepository.findByTable(table);
    }

    @GetMapping("/by-id/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id);
    }

    @PostMapping
    public Order createNewOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
    }

}
