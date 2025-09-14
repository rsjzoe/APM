package org.acme.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.auth.app.AuthService;
import org.acme.auth.domain.exception.LoginException;
import org.acme.auth.domain.input.Login;
import org.acme.role.RoleData;
import org.acme.user.app.UserService;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.WrongPasswordException;
import org.acme.user.domain.input.ChangePassword;
import org.acme.user.domain.input.UpdateUser;
import org.acme.user.domain.query.UserQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class UserServiceTest {
    @Inject
    UserService userService;

    @Inject
    UserData userData;

    @Inject
    RoleData roleData;

    @Inject
    AuthService authService;

    @BeforeEach
    @Transactional
    public void setup() {
        userData.setup();
    }

    @AfterEach
    @Transactional
    public void clear() {
            userData.clear();
    }

    @Test
    public void testFindAll() {
        var users = userService.findAllUsers(new UserQuery());

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testUpdateByTrigramme() throws UserNotFoundException {
        var user = userData.getUserOutput();
        var data = new UpdateUser("namemiova", "OMM", roleData.getApmsuperadmin().getRoleName());
        var updated = userService.updateUserByTrigramme(user.getTrigramme(), data);

        assertNotNull(updated);
        assertEquals(data.getName(), updated.getName());
        assertEquals(data.getDepartement(), updated.getDepartement());
        assertEquals(data.getRole(), updated.getRole());
    }

    @Test
    public void testUpdatePartialByTrigramme() throws UserNotFoundException {
        var user = userData.getUserOutput();
        var data = new UpdateUser("namemiova", null, null);
        var updated = userService.updateUserByTrigramme(user.getTrigramme(), data);

        assertNotNull(updated);
        assertEquals(data.getName(), updated.getName());
    }

    @Test
    public void testDeleteByTrigramme() throws UserNotFoundException {
        var user = userData.getUserOutput();
        var deleted = userService.deleteUserByTrigramme(user.getTrigramme());

        assertNotNull(deleted);
        assertEquals(user.getName(), deleted.getName());

        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUserByTrigramme(user.getTrigramme()));
    }

    @Test
    public void testUpdateThrowsException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.updateUserByTrigramme("dsafasdf", new UpdateUser()));
    }

    @Test
    public void testDeleteThrowsException() {
        assertThrows(UserNotFoundException.class,
                () -> userService.deleteUserByTrigramme("dsafasdf"));
    }

    @Test
    public void testChangePassword() throws UserNotFoundException, WrongPasswordException, LoginException {
        var user = userData.getUserOutput();
        var password = new ChangePassword("0000", "1234");
        var changedPassword = userService.changePassword(user.getTrigramme(), password);

        assertNotNull(changedPassword);
        assertEquals(user.getName(), changedPassword.getName());

        authService.login(new Login(user.getTrigramme(), password.getNewPassword()));
    }

    @Test
    public void testWrongPassword() throws UserNotFoundException, WrongPasswordException {
        var user = userData.getUserOutput();
        var password = new ChangePassword("789", "1234");

        assertThrows(WrongPasswordException.class,
                () -> userService.changePassword(user.getTrigramme(), password));
    }

}
