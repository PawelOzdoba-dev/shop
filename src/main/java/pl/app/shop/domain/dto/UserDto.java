package pl.app.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.app.shop.validator.PasswordValid;
import pl.app.shop.validator.group.CreateUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //jeśli pole będzie z wartością null to json wygeneruje się bez tego pola
@PasswordValid(groups = CreateUser.class)// walidujemy hasło tylko dla grupy CreateUser
public class UserDto {
    @Null
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String email;
    @NotBlank(groups = CreateUser.class)
    private String password;

    private String confirmPassword;

    private Integer revisionNumber;
}
