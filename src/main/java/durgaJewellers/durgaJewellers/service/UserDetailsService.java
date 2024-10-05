package durgaJewellers.durgaJewellers.service;

import durgaJewellers.durgaJewellers.dto.Response;
import durgaJewellers.durgaJewellers.model.User.User;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    Boolean loadUserByUsername(User user);

    Boolean validateUser(String username, String password);
}
