package io.honeymon.blog.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 블로그 주인
 * 
 * @author honeymon
 *
 */
@Entity
@ToString(of = { "id", "username", "nickname", "email" }, callSuper = true)
@EqualsAndHashCode(of = { "username", "email" })
public class User implements UserDetails {
    private static final long serialVersionUID = 7019782042185736325L;

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Column(nullable = false)
    private String username;

    /**
     * 암호화된 비밀번호 저장
     */
    @Getter
    private String password;

    @Getter
    private String nickname;

    @Getter
    private String email;

    @Getter
    private String introduction;

    @Getter
    private String saltKey;

    @ElementCollection(fetch=FetchType.EAGER)
    private List<UserAuthority> authorities;

    protected User() {
        generateSaltKeyAndSet();
        registryDefaultUserAuthority();
    }

    /**
	 * 
	 */
    private void registryDefaultUserAuthority() {
        this.authorities = new ArrayList<>();
        this.authorities.add(UserAuthority.USER);
    }

    /**
     * 암호화하는데 사용하는 속성으로 사용
     */
    private void generateSaltKeyAndSet() {
        this.saltKey = "" + new Random().nextLong();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(this.authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
