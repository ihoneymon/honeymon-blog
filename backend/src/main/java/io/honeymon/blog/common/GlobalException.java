/**
 * 
 */
package io.honeymon.blog.common;

/**
 * @author honeymon
 *
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = -6495037612837058038L;

    public GlobalException(String msg) {
        super(msg);
    }
    
    public GlobalException(Throwable throwable) {
        super(throwable);
    }
    
    public GlobalException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
