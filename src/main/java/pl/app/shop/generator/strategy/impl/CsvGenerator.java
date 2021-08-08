package pl.app.shop.generator.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.app.shop.generator.model.FileType;
import pl.app.shop.generator.strategy.GeneratorStrategy;

import static pl.app.shop.generator.model.FileType.*;

@Component
@Slf4j
public class CsvGenerator implements GeneratorStrategy {

    @Override
    public FileType getFileType() {
        return CSV;
    }

    @Override
    public byte[] generateFile() {
        log.info("CSV: ");
        return new byte[0];
    }
}
