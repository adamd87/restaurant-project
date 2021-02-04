package pl.adamd.restaurant.products;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/get-all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/by-name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productRepository.findByName(name);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@Validated @RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void delete(@PathVariable Long id){
        productRepository.deleteById(id);
    }

    @PatchMapping("/{name}")
    public Product patchProduct(@PathVariable("name") String name, @RequestBody Product patch) {
        Product product = productRepository.findOneByName(name);
        if (patch.getName() != null) {
            product.setName(patch.getName());
        }
        if (patch.getCost() != null) {
            product.setCost(patch.getCost());
        }
        if (patch.getWeight() != null){
            product.setWeight(product.getWeight().add(patch.getWeight()));
        }
        return productRepository.save(product);
    }


}
