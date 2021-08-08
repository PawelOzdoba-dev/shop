package pl.app.shop.generator.strategy;

import pl.app.shop.generator.model.FileType;

public interface GeneratorStrategy {

    FileType getFileType();
    byte[] generateFile();

}
