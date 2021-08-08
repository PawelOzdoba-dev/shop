package pl.app.shop.flyweight.strategy.email;

import pl.app.shop.flyweight.strategy.GenericStrategy;
import pl.app.shop.flyweight.strategy.email.model.EmailType;

public interface SenderStrategy extends GenericStrategy <EmailType> {
    void send();
}
