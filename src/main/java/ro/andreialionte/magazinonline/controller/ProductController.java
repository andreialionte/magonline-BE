package ro.andreialionte.magazinonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.andreialionte.magazinonline.dto.ProductDto;
import ro.andreialionte.magazinonline.mapper.ProductMapper;
import ro.andreialionte.magazinonline.model.Product;
import ro.andreialionte.magazinonline.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/getproducts")
    public List<Product> getAllProducts() {
    return productService.getAllProducts();
}

    @GetMapping("/getproduct")
public Optional<Product> getProduct(@RequestParam UUID id) {
    return productService.getProductById(id);
}

    @PostMapping("/createproduct")
    public Product createProduct(@RequestBody ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        return productService.saveProduct(product);
    }

    @PutMapping("/updateproduct")
    public Product updateProduct(@RequestBody ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
