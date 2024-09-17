package bootcamp.authenticationservice.domain.model;

import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void userConstructor_SetsAllFieldsCorrectly() {
        LocalDate birthDate = LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE);
        User user = new User(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, birthDate, TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_ROLE);

        assertEquals(TestConsts.USER_VALID_NAME, user.getName());
        assertEquals(TestConsts.USER_VALID_LAST_NAME, user.getLastName());
        assertEquals(TestConsts.USER_VALID_EMAIL, user.getEmail());
        assertEquals(TestConsts.USER_VALID_PASSWORD, user.getPassword());
        assertEquals(TestConsts.USER_VALID_DOCUMENT, user.getDocument());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(TestConsts.USER_VALID_PHONE, user.getPhone());
        assertEquals(TestConsts.USER_VALID_ROLE, user.getRole());
    }

    @Test
    void setName_UpdatesNameCorrectly() {
        User user = new User();
        user.setName(TestConsts.USER_VALID_NAME);

        assertEquals(TestConsts.USER_VALID_NAME, user.getName());
    }

    @Test
    void setLastName_UpdatesLastNameCorrectly() {
        User user = new User();
        user.setLastName(TestConsts.USER_VALID_LAST_NAME);

        assertEquals(TestConsts.USER_VALID_LAST_NAME, user.getLastName());
    }

    @Test
    void setEmail_UpdatesEmailCorrectly() {
        User user = new User();
        user.setEmail(TestConsts.USER_VALID_EMAIL);

        assertEquals(TestConsts.USER_VALID_EMAIL, user.getEmail());
    }

    @Test
    void setPassword_UpdatesPasswordCorrectly() {
        User user = new User();
        user.setPassword(TestConsts.USER_VALID_PASSWORD);

        assertEquals(TestConsts.USER_VALID_PASSWORD, user.getPassword());
    }

    @Test
    void setDocument_UpdatesDocumentCorrectly() {
        User user = new User();
        user.setDocument(TestConsts.USER_VALID_DOCUMENT);

        assertEquals(TestConsts.USER_VALID_DOCUMENT, user.getDocument());
    }

    @Test
    void setBirthDate_UpdatesBirthDateCorrectly() {
        LocalDate newBirthDate = LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE);
        User user = new User();
        user.setBirthDate(newBirthDate);

        assertEquals(newBirthDate, user.getBirthDate());
    }

    @Test
    void setPhone_UpdatesPhoneCorrectly() {
        User user = new User();
        user.setPhone(TestConsts.USER_VALID_PHONE);

        assertEquals(TestConsts.USER_VALID_PHONE, user.getPhone());
    }

    @Test
    void setRole_UpdatesRoleCorrectly() {
        User user = new User();
        user.setRole(TestConsts.USER_VALID_ROLE);

        assertEquals(TestConsts.USER_VALID_ROLE, user.getRole());
    }
    @Test
    void userConstructor_SetsAllFieldsCorrectlyWithId() {
        LocalDate birthDate = LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE);
        User user = new User(1L, TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, birthDate, TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_ROLE);

        assertEquals(1L, user.getId());
        assertEquals(TestConsts.USER_VALID_NAME, user.getName());
        assertEquals(TestConsts.USER_VALID_LAST_NAME, user.getLastName());
        assertEquals(TestConsts.USER_VALID_EMAIL, user.getEmail());
        assertEquals(TestConsts.USER_VALID_PASSWORD, user.getPassword());
        assertEquals(TestConsts.USER_VALID_DOCUMENT, user.getDocument());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(TestConsts.USER_VALID_PHONE, user.getPhone());
        assertEquals(TestConsts.USER_VALID_ROLE, user.getRole());
    }

    @Test
    void setId_UpdatesIdCorrectly() {
        User user = new User();
        user.setId(1L);

        assertEquals(1L, user.getId());
    }

    @Test
    void setName_UpdatesNameToNull() {
        User user = new User();
        user.setName(null);

        assertNull(user.getName());
    }

    @Test
    void setEmail_UpdatesEmailToEmpty() {
        User user = new User();
        user.setEmail("");

        assertEquals("", user.getEmail());
    }

    @Test
    void setBirthDate_UpdatesBirthDateToNull() {
        User user = new User();
        user.setBirthDate(null);

        assertNull(user.getBirthDate());
    }

    @Test
    void setRole_UpdatesRoleToNull() {
        User user = new User();
        user.setRole(null);

        assertNull(user.getRole());
    }
}