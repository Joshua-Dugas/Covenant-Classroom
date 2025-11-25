package com.dugas.covenantclassroom.config;

import com.dugas.covenantclassroom.service.UserService;
import com.dugas.covenantclassroom.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Creates a test user when the property `app.create-dev-user=true` is set.
 * This uses the application's PasswordEncoder via UserService.registerUser so the
 * stored password is bcrypt-encoded and compatible with authentication.
 *
 * Enable in Docker Compose or locally by setting
 * `app.create-dev-user=true` in application.properties 
 */
@Component
@ConditionalOnProperty(prefix = "app", name = "create-dev-user", havingValue = "true")
public class DevUserRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevUserRunner.class);

    private final UserService userService;

    public DevUserRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        final String username = "yesh";
        final String email = "yesh@yesh.com";
        final String rawPassword = "password"; 
        final String role = "Parent";

        try {
            User existing = userService.findUserByUsername(username);
            if (existing != null) {
                logger.info("Dev user '{}' already exists - skipping creation", username);
                return;
            }
        } catch (Exception e) {
            // expected when user not found - proceed to create
        }

        userService.registerUser(username, email, rawPassword, role);
        logger.info("Dev user '{}' created (dev-only).", username);
    }
}
