package pl.app.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
//    obiekt wykorzystywany do kodowania haseł

    @Bean//nie bedziemy tworzyc za każdą razo tego obiektu, rejestrujemy bean'a, adnotacja dla metod
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

}
