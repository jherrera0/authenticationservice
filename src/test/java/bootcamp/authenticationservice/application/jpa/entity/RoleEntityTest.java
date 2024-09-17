package bootcamp.authenticationservice.application.jpa.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {
    @Test
    void roleEntityConstructor_SetsAllFieldsCorrectly() {
        RoleEntity roleEntity = new RoleEntity(1L, "Admin role", "ADMIN");

        assertEquals(1L, roleEntity.getId());
        assertEquals("Admin role", roleEntity.getDescription());
        assertEquals("ADMIN", roleEntity.getName());
    }

    @Test
    void setId_UpdatesIdCorrectly() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);

        assertEquals(1L, roleEntity.getId());
    }

    @Test
    void setName_UpdatesNameCorrectly() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("USER");

        assertEquals("USER", roleEntity.getName());
    }

    @Test
    void setDescription_UpdatesDescriptionCorrectly() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setDescription("User role");

        assertEquals("User role", roleEntity.getDescription());
    }

    @Test
    void setUsers_UpdatesUsersCorrectly() {
        RoleEntity roleEntity = new RoleEntity();
        List<UserEntity> users = List.of(new UserEntity(), new UserEntity());
        roleEntity.setUsers(users);

        assertEquals(users, roleEntity.getUsers());
    }

    @Test
    void roleEntityConstructor_SetsEmptyFieldsCorrectly() {
        RoleEntity roleEntity = new RoleEntity();

        assertNull(roleEntity.getId());
        assertNull(roleEntity.getDescription());
        assertNull(roleEntity.getName());
        assertNull(roleEntity.getUsers());
    }
}