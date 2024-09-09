package bootcamp.authenticationservice.application.http.dto;

import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {
    @Test
    void authRequestConstructor_SetsAllFieldsCorrectly() {
        AuthRequest authRequest = new AuthRequest(TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD);

        assertEquals(TestConsts.USER_VALID_EMAIL, authRequest.getUsername());
        assertEquals(TestConsts.USER_VALID_PASSWORD, authRequest.getPassword());
    }

    @Test
    void setUsername_UpdatesUsernameCorrectly() {
        AuthRequest authRequest = new AuthRequest(TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD);
        authRequest.setUsername(TestConsts.USER_VALID_EMAIL_UPDATE);

        assertEquals(TestConsts.USER_VALID_EMAIL_UPDATE, authRequest.getUsername());
    }

    @Test
    void setPassword_UpdatesPasswordCorrectly() {
        AuthRequest authRequest = new AuthRequest(TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD);
        authRequest.setPassword(TestConsts.USER_VALID_PASSWORD_UPDATE);

        assertEquals(TestConsts.USER_VALID_PASSWORD_UPDATE, authRequest.getPassword());
    }

}