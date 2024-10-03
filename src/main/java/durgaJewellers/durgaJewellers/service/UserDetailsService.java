package durgaJewellers.durgaJewellers.service;

import durgaJewellers.durgaJewellers.model.User.User;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    ResponseEntity<String> loadUserByUsername(User user);

    Boolean validateUser(String username, String password);
}
