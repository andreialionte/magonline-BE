package ro.andreialionte.magazinonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.andreialionte.magazinonline.dto.AuthDto;
import ro.andreialionte.magazinonline.model.Auth;
import ro.andreialionte.magazinonline.request.RegisterRequest;

@Mapper
public interface AuthMapper {
   AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    AuthDto toDto(Auth auth);

    @Mapping(target = "id", ignore = true)
    Auth toEntity(AuthDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    Auth fromRegisterRequest(RegisterRequest request);
}
