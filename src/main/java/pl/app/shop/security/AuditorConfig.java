package pl.app.shop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorConfig implements AuditorAware<String> { //Stworzenie audytu w klasie User createBy i LastModifiedBy

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityUtils.getCurrentUserEmail());
    }
}
