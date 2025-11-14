package ro.andreialionte.magazinonline.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.andreialionte.magazinonline.mapper.AuthMapper;
import ro.andreialionte.magazinonline.mapper.ProductMapper;
import ro.andreialionte.magazinonline.mapper.UserMapper;

@Configuration
public class MapperConfig {
   @Bean
   public AuthMapper authMapper() {
       return Mappers.getMapper(AuthMapper.class);
   }

   @Bean
   public UserMapper userMapper() {
       return Mappers.getMapper(UserMapper.class);
   }

   @Bean
   public ProductMapper productMapper(){
       return Mappers.getMapper(ProductMapper.class);
   }
}
