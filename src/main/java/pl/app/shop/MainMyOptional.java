package pl.app.shop;

import java.util.ArrayList;
import java.util.List;

public class MainMyOptional {
    public static void main(String[] args) {

        MyOptional<Integer> myOptional = MyOptional.of(1);
        Integer orElseGet = myOptional.orElseGet(() -> 2);
        System.out.println(orElseGet);
        MyOptional<Integer> myOptional2 = MyOptional.empty();
        Integer orElseGet2 = myOptional2.orElseGet(() -> 2);//musimy zainizjalicować wartość w orElse, w orElseGet jest inicjalizowany gdy jest potrzebny
        System.out.println(orElseGet2);
        MyOptional<String> map = myOptional.map(String::valueOf);
        System.out.println(map.get());
        System.out.println("IFPRESENT: ");
        myOptional.ifPresent(i -> System.out.println(i));

        Integer orElseThrow = myOptional.orElseThrow(IllegalArgumentException::new);
        System.out.println(orElseThrow);

//        Integer orElseThrow2 = myOptional2.orElseThrow(IllegalArgumentException::new);
//        System.out.println(orElseThrow2);

//        PECS
        List<? super Number> numbers = new ArrayList<>();
//        List<? extends Number> numbers = new ArrayList<>(); nie zadziała
        numbers.add(1);
        numbers.add(2L);
        numbers.add(4d);
    }
}
