package bootcamp.authenticationservice.application.http.dto;

import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {
    @Test
    void authResponseConstructor_SetsTokenCorrectly() {
        AuthResponse authResponse = new AuthResponse(TestConsts.TOKEN_EXAMPLE);

        assertEquals(TestConsts.TOKEN_EXAMPLE, authResponse.getToken());
    }

    @Test
    void setToken_UpdatesTokenCorrectly() {
        AuthResponse authResponse = new AuthResponse(TestConsts.TOKEN_EXAMPLE);
        authResponse.setToken(TestConsts.TOKEN_UPDATE);

        assertEquals(TestConsts.TOKEN_UPDATE, authResponse.getToken());
    }

}