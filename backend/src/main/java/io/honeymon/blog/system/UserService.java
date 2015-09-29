/**
 * 
 */
package io.honeymon.blog.system;

import java.util.List;

/**
 * 사용자관리 서비스
 * 
 * @author honeymon
 *
 */
public interface UserService {

    /**
     * @param user
     *            new {@link User}
     * @return
     */
    User save(User user);

    /**
     * FindAll {@link User}
     * 
     * @return
     */
    List<User> findAll();

    /**
     * Delete {@link User}
     * 
     * @param user
     */
    void delete(User user);

}
