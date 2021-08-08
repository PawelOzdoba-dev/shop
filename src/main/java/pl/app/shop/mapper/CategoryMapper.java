package pl.app.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mapping(source = "parent", target = "parent")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "categoryDto", target = "categoryName")
    Category toDao(CategoryDto categoryDto);

//    @Mapping(source = "categoryName", target = "name")
//    @Mapping( source = "name", target = "name")
//    @Mapping(source = "parent", target = "parent")
    CategoryDto toDto(Category category);

}
