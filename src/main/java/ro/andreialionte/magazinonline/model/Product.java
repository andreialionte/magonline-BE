package ro.andreialionte.magazinonline.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    @NotBlank
    private String subcategory;
    @NotBlank
    private String sellerName;
    private BigDecimal price;
    private Integer quantity;
}
