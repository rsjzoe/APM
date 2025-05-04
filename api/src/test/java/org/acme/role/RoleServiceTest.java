package org.acme.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.role.app.RoleService;
import org.acme.role.domain.exception.ConflitRoleException;
import org.acme.role.domain.exception.RoleActif;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class RoleServiceTest {
    @Inject
    RoleData roleData;

    @Inject
    ServiceData serviceData;

    @Inject
    RoleService roleService;

    @BeforeEach
    @Transactional
    public void setup() {
        roleData.setup();
    }

    @AfterEach
    @Transactional
    public void clear() {
        roleData.clear();
    }

    @Test
    @TestTransaction
    public void testFindAll() {
        var roles = roleService.findAll();

        assertNotNull(roles);
        assertTrue(roles.size() > 0);
    }

    @Test
    @TestTransaction
    public void testCreateRole() throws ConflitRoleException {
        var role = roleData.getCreateRoleExample();
        var created = roleService.createRole(role);

        assertNotNull(created);
        assertEquals(role.getRoleName(), created.getRoleName());
    }

    @Test
    @TestTransaction
    public void testCreateRoleAlreadyExists() {
        var role = roleData.getCreateRoleExample();
        role.setRoleName(roleData.getReadCategoryOnly().getRoleName());

        assertThrows(ConflitRoleException.class,
                () -> roleService.createRole(role));
    }

    @Test
    @TestTransaction
    public void testFindByName() throws RoleNotFoundException, RoleActif {
        var role = roleData.getReadCategoryOnly();
        var find = roleService.findByName(role.getRoleName());

        assertNotNull(find);
        assertEquals(role.getRoleName(), find.getRoleName());
    }

    @Test
    @TestTransaction
    public void testFindByNameNotFound() throws RoleNotFoundException {
        assertThrows(RoleNotFoundException.class,
                () -> roleService.findByName("asdfas"));
    }

    @Test
    @TestTransaction
    public void testUpdateRole() throws RoleNotFoundException {
        var roleToUpdate = roleData.getReadCategoryOnly();
        var role = roleData.getUpdateRoleExample();
        var updated = roleService.updateRole(roleToUpdate.getId(), role);

        assertNotNull(updated);
        assertEquals(roleToUpdate.getRoleName(), updated.getRoleName());
        assertEquals(roleToUpdate.getPermissions().size(), updated.getPermissions().size());
    }

    @Test
    @TestTransaction
    public void testUpdateRoleNotFound() {
        var role = roleData.getUpdateRoleExample();

        assertThrows(RoleNotFoundException.class,
                () -> roleService.updateRole(999L, role));
    }

    @Test
    @TestTransaction
    public void testDeleteByName() throws RoleNotFoundException, RoleActif {
        var role = roleData.getReadCategoryOnly();
        var deleted = roleService.deleteByName(role.getRoleName());

        assertNotNull(deleted);
        assertEquals(role.getRoleName(), deleted.getRoleName());

        assertThrows(RoleNotFoundException.class,
                () -> roleService.deleteByName(role.getRoleName()));
    }

    @Test
    @TestTransaction
    public void testDeleteByNameNotFound() throws RoleNotFoundException {
        assertThrows(RoleNotFoundException.class,
                () -> roleService.deleteByName("asdfas"));
    }

}
