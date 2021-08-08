package pl.app.shop.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.domain.dto.ProductDto;

@Mapper(componentModel = "spring", uses = CategoryMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

//    @Mapping(source = "name", target = "name")
    Product toDao(ProductDto productDto);

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

}
