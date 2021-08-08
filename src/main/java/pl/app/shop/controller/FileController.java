package pl.app.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.app.shop.domain.dto.FileDto;
import pl.app.shop.flyweight.GenericFactory;
import pl.app.shop.flyweight.strategy.email.SenderStrategy;
import pl.app.shop.flyweight.strategy.email.model.EmailType;
import pl.app.shop.flyweight.strategy.file.FileGeneratorStrategy;
import pl.app.shop.generator.GeneratorFactory;
import pl.app.shop.generator.model.FileType;
import pl.app.shop.generator.strategy.GeneratorStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final GeneratorFactory generatorFactory;
    private final GenericFactory<FileType, FileGeneratorStrategy> genericFactory;
    private final GenericFactory<EmailType, SenderStrategy> emailFactory;

    @GetMapping
    public void generateFile(@RequestParam FileType fileType) {

        GeneratorStrategy strategy = generatorFactory.getStrategy(fileType);
        strategy.generateFile();
    }

    @GetMapping("/generic")

    public ResponseEntity<byte[]> genericStrategyFile(@RequestParam FileType fileType) {

        FileGeneratorStrategy strategy = genericFactory.getStrategy(fileType);
        byte[] file = strategy.generateFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);//Medi type jaki zwrocimy
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));//Aby przeglądarka wiedziała ile danych ma do pobrania
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report."+fileType.toString().toLowerCase());//nazwa pliku
        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }

    @PostMapping("/upload")

    public void genericStrategyFile(@RequestPart MultipartFile file, @RequestPart FileDto fileDto/*, @RequestHeader("") String httpRequest*/) throws IOException {
        System.out.println(fileDto);
        System.out.println(file);
        Files.copy(file.getInputStream(), Paths.get("C:\\Users\\48609\\Desktop", "test1.pdf"));

//        FileGeneratorStrategy strategy = genericFactory.getStrategy(fileType);
//        byte[] file = strategy.generateFile();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);//Medi type jaki zwrocimy
//        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));//Aby przeglądarka wiedziała ile danych ma do pobrania
//        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report."+fileType.toString().toLowerCase());//nazwa pliku
//        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }

    @PostMapping("/upload2")

    public void genericStrategyFile2(@RequestParam("file") MultipartFile file, @RequestParam("version") String version, @RequestParam("startFrom") String startFrom) throws IOException {
        System.out.println(version);
        System.out.println(startFrom);
        System.out.println(file);
        Files.copy(file.getInputStream(), Paths.get("C:\\Users\\48609\\Desktop", "test2b.pdf"));

//        FileGeneratorStrategy strategy = genericFactory.getStrategy(fileType);
//        byte[] file = strategy.generateFile();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);//Medi type jaki zwrocimy
//        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));//Aby przeglądarka wiedziała ile danych ma do pobrania
//        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report."+fileType.toString().toLowerCase());//nazwa pliku
//        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }

    @PostMapping("/upload3")

    public void genericStrategyFile3(@RequestParam MultipartFile file, @RequestParam String version, @RequestParam LocalDate startDate) throws IOException {
        System.out.println(version);
        System.out.println(startDate);
        System.out.println(file);
        Files.copy(file.getInputStream(), Paths.get("C:\\Users\\48609\\Desktop", "test3.pdf"));

//        FileGeneratorStrategy strategy = genericFactory.getStrategy(fileType);
//        byte[] file = strategy.generateFile();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);//Medi type jaki zwrocimy
//        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, Integer.toString(file.length));//Aby przeglądarka wiedziała ile danych ma do pobrania
//        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report."+fileType.toString().toLowerCase());//nazwa pliku
//        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }

    @GetMapping("/emails")
    public void genericStrategyEmails(@RequestParam EmailType emailType) {

        SenderStrategy strategy = emailFactory.getStrategy(emailType);
        strategy.send();
    }

}
