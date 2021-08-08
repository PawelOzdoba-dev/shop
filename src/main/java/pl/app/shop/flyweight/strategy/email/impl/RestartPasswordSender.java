package pl.app.shop.flyweight.strategy.email.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.app.shop.flyweight.strategy.email.SenderStrategy;
import pl.app.shop.flyweight.strategy.email.model.EmailType;

import static pl.app.shop.flyweight.strategy.email.model.EmailType.*;

@Slf4j
@Component
public class RestartPasswordSender implements SenderStrategy {
    @Override
    public EmailType getType() {
        return RESTART_PASSWORD;
    }

    @Override
    public void send() {
        log.info("Restart password sender generic:");
    }
}
