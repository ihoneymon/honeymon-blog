/**
 * 
 */
package io.honeymon.blog.security;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author honeymon
 *
 */
public class UserDetailImpl extends User {
    private static final long serialVersionUID = 7757978736817069174L;
    @Getter
    @Setter
    private String nickname;

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public UserDetailImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * @param username
     * @param password
     * @param authorities
     * @param nickname
     */
    public UserDetailImpl(String username, String password, Collection<? extends GrantedAuthority> authorities,
            String nickname) {
        this(username, password, authorities);
        this.nickname = nickname;
    }
}
