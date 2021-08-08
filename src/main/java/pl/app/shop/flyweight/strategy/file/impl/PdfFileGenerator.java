package pl.app.shop.flyweight.strategy.file.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.app.shop.flyweight.strategy.file.FileGeneratorStrategy;
import pl.app.shop.generator.model.FileType;

import static pl.app.shop.generator.model.FileType.PDF;

@Slf4j
@Component
public class PdfFileGenerator implements FileGeneratorStrategy {

    @Override
    public FileType getType() {
        return PDF;
    }

    @Override
    public byte[] generateFile() {
        log.info("generic PDF: ");
        return new byte[0];
    }
}
