package com.alwan.cimbTest.BlogApps.config;

import com.alwan.cimbTest.BlogApps.filter.JwtAuthFilter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private JwtAuthFilter authFilter;

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        String password = "alwan123";
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
    }

    @Test
    public void testAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = securityConfig.authenticationProvider();
        Assertions.assertNotNull(authenticationProvider);
        Assertions.assertEquals(DaoAuthenticationProvider.class, authenticationProvider.getClass());
        Assertions.assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
    }



}
