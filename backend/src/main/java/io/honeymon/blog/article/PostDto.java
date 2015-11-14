/**
 * 
 */
package io.honeymon.blog.article;

import java.util.List;

import lombok.Data;

/**
 * @author honeymon
 *
 */
@Data
public class PostDto {

    private String title;

    private String contents;

    private List<String> tags;
}
