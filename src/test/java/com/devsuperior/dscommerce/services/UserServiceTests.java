package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.tests.UserDetailsFactory;
import com.devsuperior.dscommerce.tests.UserFactory;
import com.devsuperior.dscommerce.util.CustomUserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private CustomUserUtil userUtil;
    private String existingUsername;
    private String nonExistingUsername;
    private User user;
    private List<UserDetailsProjection> userDetails;

    @BeforeEach
    void setUp() throws Exception {
        existingUsername = "maria@gmail.com";
        nonExistingUsername = "user@gmail.com";

        user = UserFactory.createCustomClient(1L, existingUsername);
        userDetails = UserDetailsFactory.createCustomAdminUser(existingUsername);

        Mockito.when(repository.searchUserAndRolesByEmail(existingUsername)).thenReturn(userDetails);
        Mockito.when(repository.searchUserAndRolesByEmail(nonExistingUsername)).thenReturn(new ArrayList<>());

        Mockito.when(repository.findByEmail(existingUsername)).thenReturn(Optional.of(user));
        Mockito.when(repository.findByEmail(nonExistingUsername)).thenReturn(Optional.empty());
    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
        UserDetails result = service.loadUserByUsername(existingUsername);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUsername);
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(nonExistingUsername);
        });
    }

    @Test
    public void authenticatedShouldReturnUserWhenUserExists() {
        Mockito.when(userUtil.getLoggedUserName()).thenReturn(existingUsername);

        User result = service.authenticated();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUsername);
    }

    @Test
    public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {
        Mockito.doThrow(ClassCastException.class).when(userUtil).getLoggedUserName();

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            service.authenticated();
        });
    }

    @Test
    public void getMeShouldReturnUserDtoWhenUserIsAuthenticated() {
        UserService spyUserService = Mockito.spy(service);
        Mockito.doReturn(user).when(spyUserService).authenticated();

        UserDTO result = spyUserService.getMe();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getEmail(), existingUsername);
    }

    @Test
    public void getMeShouldThrowUsernameNotFoundExceptionWhenUserIsNotAuthenticated() {
        UserService spyUserService = Mockito.spy(service);
        Mockito.doThrow(UsernameNotFoundException.class).when(spyUserService).authenticated();

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            spyUserService.authenticated();
        });
    }
}
