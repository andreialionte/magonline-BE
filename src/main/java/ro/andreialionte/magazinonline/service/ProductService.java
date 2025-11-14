package ro.andreialionte.magazinonline.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import ro.andreialionte.magazinonline.model.Product;
import ro.andreialionte.magazinonline.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // cache entire list under a fixed key 'all'
    @Cacheable(cacheNames = "products", key = "'all'")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // cache individual products by id
    @Cacheable(cacheNames = "product", key = "#id")
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    // when updating, we just updated product into product cache and evict the list cache
    @Caching(put = { @CachePut(cacheNames = "product", key = "#result.id") }, evict = { @CacheEvict(cacheNames = "products", key = "'all'") })
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // on delete, we evict both the product entry and the list
    @Caching(evict = { @CacheEvict(cacheNames = "product", key = "#id"), @CacheEvict(cacheNames = "products", key = "'all'") })
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    // on create, we saved product into cache and evict the list
    @Caching(put = { @CachePut(cacheNames = "product", key = "#result.id") }, evict = { @CacheEvict(cacheNames = "products", key = "'all'") })
    public Product saveProduct(Product product) {
        Product saved = productRepository.save(product);
        return saved;
    }
}