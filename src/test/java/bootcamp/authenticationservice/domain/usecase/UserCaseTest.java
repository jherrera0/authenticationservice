package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.exception.UserDocumentAlreadyExistsException;
import bootcamp.authenticationservice.domain.exception.UserEmailAlreadyExistException;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.domain.spi.IEncoderPersistencePort;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.domain.spi.IUserPersistencePort;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserCaseTest {

    private IUserPersistencePort userPersistencePort;
    private IRolePersistencePort rolePersistencePort;
    private IEncoderPersistencePort encoderPersistencePort;
    private UserCase userCase;

    @BeforeEach
    void setUp() {
        userPersistencePort = mock(IUserPersistencePort.class);
        rolePersistencePort = mock(IRolePersistencePort.class);
        encoderPersistencePort = mock(IEncoderPersistencePort.class);
        userCase = new UserCase(userPersistencePort, rolePersistencePort, encoderPersistencePort);
    }

    @Test
    void createUserShouldThrowExceptionWhenDocumentExists() {
        User user = new User();
        user.setDocument("existingDocument");

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(user);

        assertThrows(UserDocumentAlreadyExistsException.class, () -> userCase.createUser(user, EntityConst.USER_ROLE));
    }

    @Test
    void createUserShouldThrowExceptionWhenEmailExists() {
        User user = new User();
        user.setEmail("existingEmail");

        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(user);

        assertThrows(UserEmailAlreadyExistException.class, () -> userCase.createUser(user,EntityConst.USER_ROLE));
    }


    @Test
    void createUserShouldCreateUserWhenValid() {
        User user = new User();
        user.setDocument(TestConsts.USER_VALID_DOCUMENT);
        user.setEmail(TestConsts.USER_VALID_EMAIL);
        user.setName(TestConsts.USER_VALID_NAME);
        user.setLastName(TestConsts.USER_VALID_LAST_NAME);
        user.setPassword(TestConsts.USER_VALID_PASSWORD);
        user.setPhone(TestConsts.USER_VALID_PHONE);
        user.setRole(TestConsts.USER_VALID_ROLE);
        user.setBirthDate(LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE));

        Role role = new Role();
        role.setName("userRole");

        when(userPersistencePort.getUserByDocument(user.getDocument())).thenReturn(null);
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(null);
        when(rolePersistencePort.getRoleByName(user.getRole())).thenReturn(role);

        userCase.createUser(user);

        verify(userPersistencePort, times(1)).createUserWarehouse(user, role);
    }
}