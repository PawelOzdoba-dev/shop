package pl.app.shop.generator.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.app.shop.generator.model.FileType;
import pl.app.shop.generator.strategy.GeneratorStrategy;

import static pl.app.shop.generator.model.FileType.PDF;

@Component
@Slf4j
public class PdfGenerator implements GeneratorStrategy {

    @Override
    public FileType getFileType() {
        return PDF;
    }

    @Override
    public byte[] generateFile() {
        log.info("PDF: ");
        return new byte[0];
    }

}
