package com.example.pp_3_1_3.DAO;

import com.example.pp_3_1_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByEmail(String username);
}
