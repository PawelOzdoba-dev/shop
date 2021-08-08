package pl.app.shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.app.shop.security.JwtAuthenticationFilter;
import pl.app.shop.security.JwtAuthorizationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)//umożliwia nam to włączenie adnotacji preAuthority
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override//ctrl + o
    /*
    Mówimy co podstawiamy za istniejącą implementację
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/**")//na wszystkich endpoint
                .and()
                .cors()//jeżeli aplikacje są na różnych serwerach to pozwalamy na połączenie między nimi
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), objectMapper))//logowanie
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))//sprawdzamy co to za user
                .sessionManagement()//potrzebna do ustawienia sessionCreationPolicy
                .sessionCreationPolicy(STATELESS);//tylko w czasie przetwarzania requestu jest przechowywana sesja (dla zalogowanego usera).

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); //rejestruje dostarczyciela userów do logowania z bazy danych, dostarczam obiekt passwordEncoder który określa jak zahaszowane jest hasło
    }
}
