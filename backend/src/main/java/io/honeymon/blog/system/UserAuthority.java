/**
 * 
 */
package io.honeymon.blog.system;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author honeymon
 *
 */
public enum UserAuthority implements GrantedAuthority {
    /**
     * 주인장: 모든 권한부여
     */
    HOST("host", "code.accessAuthority.host", "주인장"),

    /**
     * 협력자: 글작성, 수정, 삭제가능
     */
    COWORKER("host", "code.accessAuthority.host", "협력자"),

    /**
     * 기본권한: 읽기
     */
    USER("user", "code.accessAuthority.user", "사용자");

    private String code;
    private String msgKey;
    private String description;

    private UserAuthority(String code, String msgKey, String description) {
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
