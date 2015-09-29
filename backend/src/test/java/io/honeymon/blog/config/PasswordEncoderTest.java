/**
 * 
 */
package io.honeymon.blog.config;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import io.honeymon.blog.BlogApplication;
import io.honeymon.blog.category.CategoryRepositoryTest;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honeymon
 *
 */
@Slf4j
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test문자열암호화확인() throws Exception {
        //given
        String password = "password";
        
        
        //then
        String encodedPassword = passwordEncoder.encode(password);
        String encodedpassword2 = passwordEncoder.encode(password);
        
        assertThat(password, is(not(encodedPassword)));
        assertThat(encodedPassword, is(not(encodedpassword2)));
        assertThat(passwordEncoder.matches(password, encodedPassword), is(true));
        assertThat(passwordEncoder.matches(encodedPassword, encodedpassword2), is(false));
    }
}
