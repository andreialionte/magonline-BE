package ro.andreialionte.magazinonline.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    // private UUID id; 
    private String name;
    private String description;
    private String category;
    private String subcategory;
    private String sellerName;
    private BigDecimal price;
    private Integer quantity;
}
