package bootcamp.authenticationservice.application.jpa.adapter;

import bootcamp.authenticationservice.application.jpa.entity.RoleEntity;
import bootcamp.authenticationservice.application.jpa.mapper.IRoleEntityMapper;
import bootcamp.authenticationservice.application.jpa.repository.IRoleRepository;
import bootcamp.authenticationservice.domain.model.Role;
import bootcamp.authenticationservice.until.EntityConst;
import bootcamp.authenticationservice.until.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleJpaAdapterTest {
    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    @BeforeEach
    void setUp() {
        roleRepository = org.mockito.Mockito.mock(IRoleRepository.class);
        roleEntityMapper = org.mockito.Mockito.mock(IRoleEntityMapper.class);
        roleJpaAdapter = new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Test
    void getRoleByName_ReturnsRole_WhenRoleExists() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(EntityConst.ADMIN_ROLE);
        Role role = new Role();
        role.setName(EntityConst.ADMIN_ROLE);
        when(roleRepository.findByName(EntityConst.ADMIN_ROLE)).thenReturn(roleEntity);
        when(roleEntityMapper.toDomain(roleEntity)).thenReturn(role);

        Role result = roleJpaAdapter.getRoleByName(EntityConst.ADMIN_ROLE);

        assertEquals("ADMIN", result.getName());
    }

    @Test
    void getRoleByName_ReturnsNull_WhenRoleDoesNotExist() {
        when(roleRepository.findByName(TestConsts.INVALID)).thenReturn(null);
        when(roleEntityMapper.toDomain(null)).thenReturn(null);

        Role result = roleJpaAdapter.getRoleByName("NON_EXISTENT");

        assertNull(result);
    }

}