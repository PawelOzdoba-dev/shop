package pl.app.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.repository.UserRepository;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService { //zwraca użytkownika z bazy danych

    private final UserRepository userRepository;

    @Override
    @Transactional //findByEmail zamyka transakcje
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new User(user.getEmail(), user.getPassword(), user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}



//Na początku jest attemptAuthentication który pobiera body od użytkownika
/*
te dane przekazywane są do userDetailsImpl, w której to klase jest pobierany użytkownik z bazy danych i konwertowany do User z security
następnie jest włączany mechanizm security i sprawdzane są poprawne dane logowania.
Gdy użytkownik jest poprawnie zalogowany wywołuje się metoda succesFullAuthentication w której generowany jest token i zwracany użytkownikowi.

 */