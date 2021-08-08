package pl.app.shop.flyweight.strategy.file.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.exception.FileCreateException;
import pl.app.shop.flyweight.strategy.file.FileGeneratorStrategy;
import pl.app.shop.generator.model.FileType;
import pl.app.shop.repository.ProductRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static pl.app.shop.generator.model.FileType.XLS;

@Slf4j
@Component
@RequiredArgsConstructor
public class XlsFileGenerator implements FileGeneratorStrategy {

    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return XLS;
    }

    @Override
    public byte[] generateFile() {
        log.info("generic XLS: ");

        try {
            Workbook workbook = WorkbookFactory.create(false); //tworze excel
            Sheet sheet = workbook.createSheet("PIERWSZY ARKUSZ");//tworze arkusz
            Row row = sheet.createRow(0); //tworze wiersz
            row.createCell(0).setCellValue("ID");  //tworzone są nagłówki
            row.createCell(1).setCellValue("NAME");
            row.createCell(2).setCellValue("CATEGORY NAME");

            List<Product> products = productRepository.findAll();

            for (int i = 0; i < products.size(); i++) {
//tworzenie wiersza
                row = sheet.createRow(i+1);
// Tworzenie kolumny na podstawie pol z products
                Product product = products.get(i);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getCategory() != null ? product.getCategory().getName() : null);
            }
//tworzone jest filtrowanie i sortowanie
            sheet.setAutoFilter(new CellRangeAddress(0, products.size(), 0, 2));//od którego wiersza literujemy, do ktorego wiersza literujemy, od 0 kolumnny , do ostatatniej kolumny

//            Tworzymy wynikowy strumień danych
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            do wynikowego strumienia danych zapisujemy cały plik
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error during creating product excel report: ", e);
        }
        throw new FileCreateException("Error during creating product excel report");
    }
}
