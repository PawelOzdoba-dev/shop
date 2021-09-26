package pl.app.shop;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyOptional<T> {

    private T value;

    private MyOptional(T value) {
        this.value = value;
    }

    private MyOptional() {
    }

    public static <T> MyOptional<T> empty() { //diamenty gdy parametryzujemy klasę lub funkcję.
        return new MyOptional<>();
    }

    public MyOptional<T> test() {
        return new MyOptional<>();
    }

    public static <T> MyOptional<T> of(T t) {
        return new MyOptional<>(t);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("");
        }
        return value;
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public <V> MyOptional<V> map(Function<T, V> function) {
        if (value == null) {
            return empty();
        }
        return of(function.apply(value));
    }

    public T orElseGet(Supplier<T> supplier) {
        if (value != null) {
            return value;
        }
        return supplier.get();
    }

    public T orElse(T t) {
        if (value == null) {
            return t;
        }
        return value;
    }

    public MyOptional<T> filter(Predicate<T> predicate) {
        if (value == null) {
            return this;
        } else if (predicate.test(value)) {
            return this;
        }
        return empty();
    }

    public <X extends Throwable> T orElseThrow(Supplier<X> supplier) throws X {
        if (value != null) {
            return value;
        }
        throw supplier.get();
    }

    //PECSS
    //    przetestować metody z MyOptional
//    napisać funkcję ifPresentorElse (przyjmuje consumera i Runnable) -> jeżeli istnieje wartość z optionala to consumera a jak nie to runnable
}
