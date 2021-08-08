package pl.app.shop.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    jedna kategoria do produktu, wiele produktów do jednej kategori : jednokierunkowa relacja
//
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

}
