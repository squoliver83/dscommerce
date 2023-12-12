package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;

import java.time.LocalDate;

public class UserFactory {

    public static User createClient() {
        User user = new User(1L, "Maria", "maria@gmail.com", "987654321", LocalDate.parse("2001-07-25"), "$2a$10$FoKqfTplP3pZY48k/8Y5S.GU4lUODSab/9w697seYUAIcPb0kWy0.");
        user.addRole(new Role(1L, "ROLE_CLIENT"));
        return user;
    }

    public static User createAdmin() {
        User user = new User(2L, "Alex", "alex@gmail.com", "965498732", LocalDate.parse("1987-12-13"), "$2a$10$FoKqfTplP3pZY48k/8Y5S.GU4lUODSab/9w697seYUAIcPb0kWy0.");
        user.addRole(new Role(2L, "ROLE_ADMIN"));
        return user;
    }

    public static User createCustomClient(Long id, String username) {
        User user = new User(id, "Maria", username, "987654321", LocalDate.parse("2001-07-25"), "$2a$10$FoKqfTplP3pZY48k/8Y5S.GU4lUODSab/9w697seYUAIcPb0kWy0.");
        user.addRole(new Role(1L, "ROLE_CLIENT"));
        return user;
    }

    public static User createCustomAdmin(Long id, String username) {
        User user = new User(id, "Alex", username, "965498732", LocalDate.parse("1987-12-13"), "$2a$10$FoKqfTplP3pZY48k/8Y5S.GU4lUODSab/9w697seYUAIcPb0kWy0.");
        user.addRole(new Role(2L, "ROLE_ADMIN"));
        return user;
    }
}
