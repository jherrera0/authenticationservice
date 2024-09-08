package bootcamp.authenticationservice.domain.model;

import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void roleConstructor_SetsAllFieldsCorrectly() {
        Role role = new Role(TestConsts.ROLE_VALID_ID, TestConsts.ROLE_VALID_NAME, TestConsts.ROLE_VALID_DESCRIPTION);

        assertEquals(TestConsts.ROLE_VALID_ID, role.getId());
        assertEquals(TestConsts.ROLE_VALID_NAME, role.getName());
        assertEquals(TestConsts.ROLE_VALID_DESCRIPTION, role.getDescription());
    }

    @Test
    void setId_UpdatesIdCorrectly() {
        Role role = new Role();
        role.setId(TestConsts.ROLE_VALID_ID);

        assertEquals(TestConsts.ROLE_VALID_ID, role.getId());
    }

    @Test
    void setName_UpdatesNameCorrectly() {
        Role role = new Role();
        role.setName(TestConsts.ROLE_VALID_NAME);

        assertEquals(TestConsts.ROLE_VALID_NAME, role.getName());
    }

    @Test
    void setDescription_UpdatesDescriptionCorrectly() {
        Role role = new Role();
        role.setDescription(TestConsts.ROLE_VALID_DESCRIPTION);

        assertEquals(TestConsts.ROLE_VALID_DESCRIPTION, role.getDescription());
    }

}