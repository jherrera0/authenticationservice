package bootcamp.authenticationservice.application.http.handler;

import bootcamp.authenticationservice.application.http.dto.CreateUserRequest;
import bootcamp.authenticationservice.application.http.mapper.ICreateUserRequestMapper;
import bootcamp.authenticationservice.domain.api.IUserServicePort;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserHandlerTest {
    @Mock
    private ICreateUserRequestMapper createUserRequestMapper;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserHandler userHandler;


    @BeforeEach
    void setUp() {
        createUserRequestMapper = mock(ICreateUserRequestMapper.class);
        userServicePort = mock(IUserServicePort.class);
        userHandler = new UserHandler(createUserRequestMapper, userServicePort);
    }

    @Test
    void createUserWarehouse_CreatesUserWithWarehouseRole() {
        CreateUserRequest request = new CreateUserRequest(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_EMAIL,LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        User user = new User(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE), TestConsts.USER_VALID_PHONE, EntityConst.WAREHOUSE_ROLE);
        when(createUserRequestMapper.toUser(request)).thenReturn(user);

        userHandler.createUserWarehouse(request);

        verify(userServicePort, times(1)).createUser(user);
        assertEquals(EntityConst.WAREHOUSE_ROLE, user.getRole());
    }

    @Test
    void createUserWarehouse_ThrowsException_WhenRequestIsNull() {
        assertThrows(NullPointerException.class, () -> userHandler.createUserWarehouse(null));
        verify(userServicePort, never()).createUser(any(User.class));
    }


    @Test
    void createUserWarehouse_ThrowsException_WhenMapperReturnsNull() {
        CreateUserRequest request = new CreateUserRequest(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_EMAIL, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        when(createUserRequestMapper.toUser(request)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> userHandler.createUserWarehouse(request));
        verify(userServicePort, never()).createUser(any(User.class));
    }

    @Test
    void createUserWarehouse_SetsRoleToWarehouse_WhenRoleIsNull() {
        CreateUserRequest request = new CreateUserRequest(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_EMAIL, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));
        User user = new User(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE), TestConsts.USER_VALID_PHONE, null);
        when(createUserRequestMapper.toUser(request)).thenReturn(user);

        userHandler.createUserWarehouse(request);

        verify(userServicePort, times(1)).createUser(user);
        assertEquals(EntityConst.WAREHOUSE_ROLE, user.getRole());
    }

}