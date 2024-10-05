package durgaJewellers.durgaJewellers.service.Impl;

import durgaJewellers.durgaJewellers.dao.UserDao;
import durgaJewellers.durgaJewellers.dto.CustomLogin;
import durgaJewellers.durgaJewellers.dto.Response;
import durgaJewellers.durgaJewellers.model.User.User;
import durgaJewellers.durgaJewellers.service.UserDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @NonNull private final UserDao userDao;

    public Boolean loadUserByUsername(User user) {
        User userDetails= userDao.findByUsername(user.getUsername());
        if(user.getPassword().equals(userDetails.getPassword())){
            return true;
        }
        return false;
    }

    public Boolean validateUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User userDetails= userDao.findByUsername(user.getUsername());
        if(userDetails==null) return false;
        if(user.getPassword().equals(userDetails.getPassword())){
            return true;
        }
        return false;
    }
}
