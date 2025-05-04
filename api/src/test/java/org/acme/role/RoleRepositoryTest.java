package org.acme.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acme.role.domain.exception.RoleActif;
import org.acme.role.domain.exception.RoleNotFoundException;
import org.acme.role.domain.port.out.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class RoleRepositoryTest {
    @Inject
    RoleData roleData;

    @Inject
    ServiceData serviceData;

    @Inject
    RoleRepository roleRepository;

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
        var roles = roleRepository.findAll();

        assertNotNull(roles);
        assertTrue(roles.size() > 0);
    }

    @Test
    @TestTransaction
    public void testCreateRole() {
        var role = roleData.getCreateRoleExample();
        var created = roleRepository.createRole(role);

        assertNotNull(created);
        assertEquals(role.getRoleName(), created.getRoleName());
    }

    @Test
    @TestTransaction
    public void testFindByName() throws RoleNotFoundException, RoleActif {
        var role = roleData.getReadCategoryOnly();
        var find = roleRepository.findRoleByName(role.getRoleName());

        assertNotNull(find);
        assertEquals(role.getRoleName(), find.getRoleName());
    }

    @Test
    @TestTransaction
    public void testFindByNameNotFound() throws RoleNotFoundException {
        assertThrows(RoleNotFoundException.class,
                () -> roleRepository.findRoleByName("asdfas"));
    }

    @Test
    @TestTransaction
    public void testUpdateRole() throws RoleNotFoundException {
        var roleToUpdate = roleData.getReadCategoryOnly();
        var role = roleData.getUpdateRoleExample();
        var updated = roleRepository.updateRole(roleToUpdate.getId(), role);

        assertNotNull(updated);
        assertEquals(roleToUpdate.getRoleName(), updated.getRoleName());
        assertEquals(roleToUpdate.getPermissions().size(), updated.getPermissions().size());
    }

    @Test
    @TestTransaction
    public void testUpdateRoleNotFound() {
        var role = roleData.getUpdateRoleExample();

        assertThrows(RoleNotFoundException.class,
                () -> roleRepository.updateRole(999L, role));
    }

    @Test
    @TestTransaction
    public void testDeleteByName() throws RoleNotFoundException, RoleActif {
        var role = roleData.getReadCategoryOnly();
        var deleted = roleRepository.deleteByName(role.getRoleName());

        assertNotNull(deleted);
        assertEquals(role.getRoleName(), deleted.getRoleName());

        assertThrows(RoleNotFoundException.class,
                () -> roleRepository.deleteByName(role.getRoleName()));
    }

    @Test
    @TestTransaction
    public void testDeleteByNameNotFound() throws RoleNotFoundException {
        assertThrows(RoleNotFoundException.class,
                () -> roleRepository.deleteByName("asdfas"));
    }
}
