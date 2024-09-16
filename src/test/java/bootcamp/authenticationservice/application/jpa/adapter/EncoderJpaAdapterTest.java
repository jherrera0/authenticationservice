package bootcamp.authenticationservice.application.jpa.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EncoderJpaAdapterTest {
    @Mock
    private EncoderJpaAdapter encoderJpaAdapter;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encoderJpaAdapter = new EncoderJpaAdapter(passwordEncoder);
    }
    @Test
    void encoder_ReturnsEncodedPassword_WhenPasswordIsValid() {
        String rawPassword = "validPassword";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = encoderJpaAdapter.encoder(rawPassword);

        assertEquals(encodedPassword, result);
    }

}