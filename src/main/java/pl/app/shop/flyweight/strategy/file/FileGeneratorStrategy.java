package pl.app.shop.flyweight.strategy.file;

import pl.app.shop.flyweight.strategy.GenericStrategy;
import pl.app.shop.generator.model.FileType;

public interface FileGeneratorStrategy extends GenericStrategy <FileType> {

    byte[] generateFile();
}
