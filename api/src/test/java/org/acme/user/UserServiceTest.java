package org.acme.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.user.app.UserService;
import org.acme.user.domain.Role;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.input.UpdateUser;
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

    @BeforeEach
    @Transactional
    public void setup() {
        userData.setup();
    }

    @AfterEach
    @Transactional
    public void clear() {
        try {
            userService.deleteUserByTrigramme(userData.getUserOutput().getTrigramme());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        var users = userService.findAllUsers();

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testUpdateByTrigramme() throws UserNotFoundException {
        var user = userData.getUserOutput();
        var data = new UpdateUser("namemiova", "OMM", Role.admin);
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

}
