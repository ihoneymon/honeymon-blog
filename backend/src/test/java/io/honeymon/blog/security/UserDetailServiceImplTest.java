/**
 * 
 */
package io.honeymon.blog.security;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import io.honeymon.blog.BlogApplication;
import io.honeymon.blog.system.Account;
import io.honeymon.blog.system.AccountService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honeymon
 *
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlogApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class UserDetailServiceImplTest {

    @Autowired
    private AccountService userService;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    private String username;
    private String password;
    private String nickname;
    private Account user;
    private Account savedUser;

    @Before
    public void setUp() {
        username = "honeymon";
        password = "pssword";
        nickname = "nickname";
        user = new Account(username, password, nickname);
        savedUser = userService.save(user);
    }

    @Test
    public void test등록된계정조회() {
        // given

        // when
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

        // then
        assertThat(userDetails.getUsername(), is(username));
        assertThat(userDetails.getPassword(), is(savedUser.getPassword()));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void test등록되지않은계정조회() throws Exception {
        // given
        String notExistUserName = "notExistUserName";

        // when
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(notExistUserName);

        // then
        fail();
    }
}
