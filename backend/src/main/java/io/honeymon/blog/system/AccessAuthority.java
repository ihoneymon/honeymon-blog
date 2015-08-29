/**
 * 
 */
package io.honeymon.blog.system;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author honeymon
 *
 */
public enum AccessAuthority implements GrantedAuthority {
    /**
     * 주인장, 모든 권한을 
     */
    HOST("host", "code.accessAuthority.host", "주인장"), 
    
    /**
     * 글작성, 수정, 삭제가능한 협업자
     */
    COWORKER("host", "code.accessAuthority.host", "협력자"),
    
    /**
     * 읽기권한
     */
    NOVICE("novice", "code.accessAuthority.novice", "노비");

    private String code;
    private String msgKey;
    private String description;

    private AccessAuthority(String code, String msgKey, String description) {
        this.code = code;
        this.msgKey = msgKey;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }

    public String getCode() {
        return code;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public String getDescription() {
        return description;
    }

}
