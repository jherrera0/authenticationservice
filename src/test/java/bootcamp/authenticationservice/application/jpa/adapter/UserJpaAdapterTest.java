package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.entity.UserEntity;
import bootcamp.authenticationservice.application.jpa.mapper.IRoleEntityMapper;
import bootcamp.authenticationservice.application.jpa.mapper.IUserEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IUserRepository;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Mock
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        roleEntityMapper = mock(IRoleEntityMapper.class);
        userEntityMapper = mock(IUserEntityMapper.class);
        userJpaAdapter = new UserJpaAdapter(userRepository, roleEntityMapper, userEntityMapper);
    }

    @Test
    void createUserWarehouse_SavesUserWithRole() {
        User user = new User(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE), TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_ROLE);
        Role role = new Role();
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = new RoleEntity();
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(roleEntityMapper.toEntity(role)).thenReturn(roleEntity);

        userJpaAdapter.createUserWarehouse(user, role);

        verify(userRepository, times(1)).save(userEntity);
        assertEquals(roleEntity, userEntity.getRole());
    }

    @Test
    void getUserByEmail_ReturnsUser_WhenEmailExists() {
        UserEntity userEntity = new UserEntity();
        User user = new User();
        when(userRepository.findByEmail(TestConsts.USER_VALID_NAME)).thenReturn(userEntity);
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        User result = userJpaAdapter.getUserByEmail(TestConsts.USER_VALID_NAME);

        assertEquals(user, result);
    }

    @Test
    void getUserByEmail_ReturnsNull_WhenEmailDoesNotExist() {
        when(userRepository.findByEmail(TestConsts.USER_VALID_EMAIL_INVALID)).thenReturn(null);
        when(userEntityMapper.toDomain(null)).thenReturn(null);

        User result = userJpaAdapter.getUserByEmail("nonexistent@example.com");

        assertNull(result);
    }

    @Test
    void getUserByDocument_ReturnsUser_WhenDocumentExists() {
        UserEntity userEntity = new UserEntity();
        User user = new User();
        when(userRepository.findByDocument(TestConsts.USER_VALID_DOCUMENT)).thenReturn(userEntity);
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        User result = userJpaAdapter.getUserByDocument("123456789");

        assertEquals(user, result);
    }

    @Test
    void getUserByDocument_ReturnsNull_WhenDocumentDoesNotExist() {
        when(userRepository.findByDocument(TestConsts.INVALID)).thenReturn(null);
        when(userEntityMapper.toDomain(null)).thenReturn(null);

        User result = userJpaAdapter.getUserByDocument("nonexistent");

        assertNull(result);
    }
    @Test
    void createUserWarehouse_ThrowsException_WhenUserIsNull() {
        Role role = new Role();

        assertThrows(NullPointerException.class, () -> userJpaAdapter.createUserWarehouse(null, role));
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void createUserWarehouse_ThrowsException_WhenRoleIsNull() {
        User user = new User(TestConsts.USER_VALID_NAME, TestConsts.USER_VALID_LAST_NAME, TestConsts.USER_VALID_EMAIL, TestConsts.USER_VALID_PASSWORD, TestConsts.USER_VALID_DOCUMENT, LocalDate.parse(TestConsts.USER_VALID_BIRTH_DATE), TestConsts.USER_VALID_PHONE, TestConsts.USER_VALID_ROLE);
        assertThrows(NullPointerException.class, () -> userJpaAdapter.createUserWarehouse(user, null));
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void createUserWarehouse_ThrowsException_WhenUserAndRoleAreNull() {
        assertThrows(NullPointerException.class, () -> userJpaAdapter.createUserWarehouse(null, null));
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}