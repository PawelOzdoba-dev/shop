//package pl.app.shop.mapper.impl;
//
//import org.springframework.stereotype.Component;
//import pl.app.shop.domain.dao.User;
//import pl.app.shop.domain.dto.UserDto;
//import pl.app.shop.mapper.UserMapper;
//
//@Component
//public class UserMapperImpl implements UserMapper {
//
//    @Override
//    public User toDao(UserDto userDto) {
//        return User.builder()
//                .id(userDto.getId())
//                .firstname(userDto.getFirstname())
//                .lastname(userDto.getLastname())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .build();
//    }
//
//    @Override
//    public UserDto toDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .firstname(user.getFirstname())
//                .lastname(user.getLastname())
//                .email(user.getEmail())
//                .build();
//    }
//}
