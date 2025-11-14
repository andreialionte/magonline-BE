package ro.andreialionte.magazinonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.andreialionte.magazinonline.dto.ProductDto;
import ro.andreialionte.magazinonline.model.Product;

@Mapper//  componentModel = "spring" - pentru DI ( Autowired, etc)
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class  );

    ProductDto toDTO(Product product);
    Product toEntity(ProductDto dto);
}
