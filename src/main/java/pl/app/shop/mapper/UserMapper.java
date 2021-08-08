package pl.app.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.app.shop.domain.dao.User;
import pl.app.shop.domain.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)//ignorujemy pole
    UserDto toDto(User user);
}
