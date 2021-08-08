package pl.app.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FileDto {

    private String version;

    public FileDto(String version) {
        this.version = version;
    }
    private LocalDate startFrom;
}
