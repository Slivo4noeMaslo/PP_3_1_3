package com.example.pp_3_1_3.service;

import com.example.pp_3_1_3.DAO.UserDAO;
import com.example.pp_3_1_3.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User getUser(long id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userDAO.findUserByEmail(username);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword(userDAO.getById(user.getId()).getPassword());
        }
        if (!user.getPassword().equals(userDAO.getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }
}
