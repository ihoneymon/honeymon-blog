/**
 * 
 */
package io.honeymon.blog.system;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import io.honeymon.blog.BlogApplication;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class AccountServiceTest {

    @Autowired
    private AccountService userService;
    private String username;
    private String password;
    private String nickname;
    private Account user;

    @Before
    public void setUp() {
        username = "honeymon";
        password = "pssword";
        nickname = "nickname";
        user = new Account(username, password, nickname);
    }

    @Test
    public void test사용자저장() {
        // given

        // when
        Account savedUser = userService.save(user);

        // then
        assertThat(savedUser, is(user));
        assertThat(savedUser.isNew(), is(false));
        assertThat(savedUser.getPassword(), is(not(password)));
    }

    @Test
    public void test사용자조회() throws Exception {
        // given
        Account savedUser = userService.save(user);

        // when
        List<Account> users = userService.findAll();

        // then
        assertThat(users.contains(savedUser), is(true));
    }

    @Test
    public void test사용자삭제() throws Exception {
        // given
        Account savedUser = userService.save(user);

        // when
        userService.delete(user);

        // when
        List<Account> users = userService.findAll();
        assertThat(users.contains(savedUser), is(false));
    }
}
