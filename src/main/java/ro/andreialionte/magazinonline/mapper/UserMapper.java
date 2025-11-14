package ro.andreialionte.magazinonline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.andreialionte.magazinonline.dto.UserDto;
import ro.andreialionte.magazinonline.model.User;
import ro.andreialionte.magazinonline.request.RegisterRequest;

@Mapper(uses = {AuthMapper.class})
public interface UserMapper {

    UserDto toDto(User user); // remove @Mapping(source = "auth", target = "auth")

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "auth", ignore = true)
    User fromRegisterRequest(RegisterRequest request);
}

