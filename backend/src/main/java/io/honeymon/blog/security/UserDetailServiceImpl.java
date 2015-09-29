/**
 * 
 */
package io.honeymon.blog.security;

import io.honeymon.blog.system.User;
import io.honeymon.blog.system.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author honeymon
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserame(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailImpl(user.getUsername(), user.getPassword(), user.getAuthorities(), user.getNickname());
    }

}
