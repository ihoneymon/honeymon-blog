/**
 * 
 */
package io.honeymon.blog.system;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author honeymon
 *
 */
public class AccountTest {

    /**
     * 사용자 정보 최초생성시 무엇을 필요로 할까나...
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void 사용자생성시_유저명이_null이면_예외발생() {
        // given
        String username = null;
        String password = "password";
        String nickname = "nickname";

        // when
        Account user = new Account(username, password, nickname);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test사용자생성시_비밀번호가_null인경우_예외발생() throws Exception {
        // given
        String username = "honeymon";
        String password = null;
        String nickname = "nickname";

        // when
        Account user = new Account(username, password, nickname);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test사용자생성시_별명이_null인경우_예외발생() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = null;

        // when
        Account user = new Account(username, password, nickname);

        // then
        fail();
    }

    @Test
    public void test사용자계정생성() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";

        // when
        Account user = new Account(username, password, nickname);

        // then
        assertThat(user.getUsername(), is(username));
        assertThat(user.getPassword(), is(password));
        assertThat(user.getNickname(), is(nickname));
        assertThat(user.getAuthorities().contains(AccountAuthority.USER), is(true));
    }

    @Test
    public void test사용자권한변경_사용자권한은_기본등록() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);
        List<AccountAuthority> changeAuthority = new ArrayList<>();
        changeAuthority.add(AccountAuthority.HOST);

        // when
        user.changeAuthority(changeAuthority);

        // then
        assertThat(user.getAuthorities().contains(AccountAuthority.USER), is(true));
        assertThat(user.getAuthorities().contains(AccountAuthority.HOST), is(true));
    }

    @Test
    public void test사용자권한_보유확인() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        // then
        assertThat(user.hasAuthority(AccountAuthority.USER), is(true));
        assertThat(user.hasAuthority(AccountAuthority.HOST), is(false));
    }

    @Test
    public void test사용자별명_변경확인() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        String changeNickname = "Change nickname";

        // when
        user.changeName(changeNickname);

        // then
        assertThat(user.getNickname(), is(not(nickname)));
        assertThat(user.getNickname(), is(changeNickname));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test사용자별명_null로변경시_예외발생() {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        // when
        user.changeName(null);

        // then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test사용자별명이_빈공백문자인경우_예외발생() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        // when
        user.changeName("");

        // then
        fail();
    }

    @Test
    public void test비밀번호변경() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        String changePassword = "Change password";

        // when
        user.changePassword(changePassword);

        // then
        assertThat(user.getPassword(), is(not(password)));
        assertThat(user.getPassword(), is(changePassword));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test비밀번호변경시_null인경우_예외발생() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        String changePassword = null;

        // when
        user.changePassword(changePassword);

        // then
        fail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test비밀번호변경시_빈공백인경우_예외발생() throws Exception {
        // given
        String username = "honeymon";
        String password = "pssword";
        String nickname = "nickname";
        Account user = new Account(username, password, nickname);

        String changePassword = "";

        // when
        user.changePassword(changePassword);

        // then
        fail();
    }
}