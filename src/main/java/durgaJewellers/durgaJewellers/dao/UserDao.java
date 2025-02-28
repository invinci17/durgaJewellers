package durgaJewellers.durgaJewellers.dao;

import durgaJewellers.durgaJewellers.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {


    User findByUsername(String username);
}

