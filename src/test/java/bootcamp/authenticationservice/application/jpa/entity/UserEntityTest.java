package bootcamp.authenticationservice.application.jpa.entity;

import bootcamp.authenticationservice.until.EntityConst;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
    @Test
    void getAuthorities_ReturnsCorrectAuthorities() {
        RoleEntity role = new RoleEntity();
        role.setName(EntityConst.ADMIN_ROLE);
        UserEntity user = new UserEntity();
        user.setRole(role);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(EntityConst.ROLE + EntityConst.ADMIN_ROLE)));
    }

    @Test
    void getUsername_ReturnsEmail() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");

        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    void isAccountNonExpired_ReturnsTrue() {
        UserEntity user = new UserEntity();

        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_ReturnsTrue() {
        UserEntity user = new UserEntity();

        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired_ReturnsTrue() {
        UserEntity user = new UserEntity();

        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_ReturnsTrue() {
        UserEntity user = new UserEntity();

        assertTrue(user.isEnabled());
    }

}