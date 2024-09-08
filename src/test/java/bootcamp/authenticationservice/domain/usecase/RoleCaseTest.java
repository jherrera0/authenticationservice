package bootcamp.authenticationservice.domain.usecase;

import bootcamp.authenticationservice.domain.exception.RoleEmptyException;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.spi.IRolePersistencePort;
import bootcamp.authenticationservice.until.EntityConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @Mock
    private RoleCase roleCase;
    @BeforeEach
    void setUp() {
        rolePersistencePort = Mockito.mock(IRolePersistencePort.class);
        roleCase = new RoleCase(rolePersistencePort);
    }

    @Test
    void getRoleByName_ThrowsRoleEmptyException_WhenNameIsEmpty() {
        assertThrows(RoleEmptyException.class, () -> roleCase.getRoleByName(""));
        verify(rolePersistencePort, never()).getRoleByName(anyString());
    }

    @Test
    void getRoleByName_ReturnsNull_WhenRoleDoesNotExist() {
        when(rolePersistencePort.getRoleByName("nonexistent")).thenReturn(null);

        Role result = roleCase.getRoleByName("nonexistent");

        assertNull(result);
        verify(rolePersistencePort, times(1)).getRoleByName("nonexistent");
    }
    @Test
    void getRoleByName_ThrowsRoleEmptyException_WhenNameIsNull() {
        assertThrows(RoleEmptyException.class, () -> roleCase.getRoleByName(null));
        verify(rolePersistencePort, never()).getRoleByName(anyString());
    }

    @Test
    void getRoleByName_ThrowsRoleEmptyException_WhenNameIsEmptyString() {
        assertThrows(RoleEmptyException.class, () -> roleCase.getRoleByName(""));
        verify(rolePersistencePort, never()).getRoleByName(anyString());
    }
}