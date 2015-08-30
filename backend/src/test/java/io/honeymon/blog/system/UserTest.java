/**
 * 
 */
package io.honeymon.blog.system;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author honeymon
 *
 */
public class UserTest {

    /**
     * 사용자 정보 최초생성시 무엇을 필요로 할까나...
     * 
     */
    @Test
    public void 사용자생성시유저명이_null이면_예외발() {
        User user = new User();
    }

}
