package com.hsuweizte.bloghsuweizte.dao;
import com.hsuweizte.bloghsuweizte.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
