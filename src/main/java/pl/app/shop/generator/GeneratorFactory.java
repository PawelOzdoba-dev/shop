package pl.app.shop.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.app.shop.generator.model.FileType;
import pl.app.shop.generator.strategy.GeneratorStrategy;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Component
@RequiredArgsConstructor
public class GeneratorFactory {
//PYLEK
    private final List<GeneratorStrategy> generatorStrategyList;
    private Map<FileType, GeneratorStrategy> strategyMap;

    @PostConstruct
        //automatycznie po konstruktorze inizjalizujemy mapę
    void init() {
        strategyMap = generatorStrategyList.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, identity()));
    }

    public GeneratorStrategy getStrategy(FileType fileType) {
        return strategyMap.get(fileType);
    }

}

//flatMap vs Map
/*
User user = new User("JAN",List.of("JAN@@wp.pl"));
User user2 = new User("KAROL",List.of("KAROL@@wp.pl", "KAROL2@wp.pl"));

List<User> lists = List.of(user, user2);

List<List<String>> users = lists.stream().map(s -> s.getEmail()).collect(toList());  //.flatMap();
List<String> users = lists.stream().map(s -> s.getEmail()).flatMap(List::stream).collect(toList());

 */
