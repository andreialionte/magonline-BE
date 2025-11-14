package ro.andreialionte.magazinonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andreialionte.magazinonline.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
