package pl.app.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;
import pl.app.shop.domain.dao.User;
import pl.app.shop.domain.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserHistoryMapper { //zwracanie audytu

    @Mapping(source = "entity.firstname",target = "firstname")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "entity.lastname",target = "lastname")
    @Mapping(source = "entity.email",target = "email")
    @Mapping(source = "requiredRevisionNumber", target = "revisionNumber")
    UserDto toDto(Revision<Integer, User> revision);
}
