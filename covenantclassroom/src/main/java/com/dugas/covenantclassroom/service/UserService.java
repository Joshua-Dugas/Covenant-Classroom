package com.dugas.covenantclassroom.service;
import com.dugas.covenantclassroom.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void registerUser(String username, String email, String password, String role) {
        String encodedPassword = passwordEncoder.encode(password);
        userDao.createUser(username, email, encodedPassword, role);
        LOGGER.info("User registered: {}", username);
    }

    public com.dugas.covenantclassroom.model.User findUserByUsername(String username) {
        var user = userDao.selectUser(username);
        LOGGER.info("User lookup: {}", username);
        return user;
    }
}