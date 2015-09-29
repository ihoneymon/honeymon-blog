/**
 * 
 */
package io.honeymon.blog.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author honeymon
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    /*
     * (non-Javadoc)
     * 
     * @see
     * io.honeymon.blog.system.UserService#save(io.honeymon.blog.system.User)
     */
    @Transactional
    @Override
    public User save(User user) {
        changePasswordToEncodedPassword(user);
        return userRepository.saveAndFlush(user);
    }

    /**
     * @param user
     */
    private void changePasswordToEncodedPassword(User user) {
        user.changePassword(passwordEncoder.encode(user.getPassword()));
    }

    /* (non-Javadoc)
     * @see io.honeymon.blog.system.UserService#findAll()
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /* (non-Javadoc)
     * @see io.honeymon.blog.system.UserService#delete(io.honeymon.blog.system.User)
     */
    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
        userRepository.flush();
    }

    /* (non-Javadoc)
     * @see io.honeymon.blog.system.UserService#findByUserame(java.lang.String)
     */
    @Override
    public User findByUserame(String username) {
        return userRepository.findByUsername(username);
    }

}
