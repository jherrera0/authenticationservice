package bootcamp.authenticationservice.infrastructure.controller;


import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.http.handler.UserHandler;
import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

class UserRestControllerTest {
    @Mock
    private UserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createAssWarehouse_CreatesWarehouse_WhenRequestIsValid() {
        CreateUserRequest createUserRequest = new CreateUserRequest(TestConsts.USER_VALID_NAME,TestConsts.USER_VALID_LAST_NAME,TestConsts.USER_VALID_PASSWORD,TestConsts.USER_VALID_DOCUMENT,TestConsts.USER_VALID_PHONE,TestConsts.USER_VALID_EMAIL, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        userRestController.createAssWarehouse(createUserRequest);

        verify(userHandler).createUserWarehouse(createUserRequest);
    }


}