package pl.app.shop.flyweight.strategy.email.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.app.shop.flyweight.strategy.email.SenderStrategy;
import pl.app.shop.flyweight.strategy.email.model.EmailType;

import static pl.app.shop.flyweight.strategy.email.model.EmailType.*;

@Component
@Slf4j
public class RegisterSender implements SenderStrategy {
    @Override
    public EmailType getType() {
        return REGISTER;
    }

    @Override
    public void send() {
        log.info("Register gerneric:");
    }
}
