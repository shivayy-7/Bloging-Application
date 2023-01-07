package com.blogapp_10.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.Comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
    private Long id;
    
    @NotEmpty(message = "Title cannot be empty")
	private String title;
    
	private String url;
	
	@NotEmpty(message = "Post content should not be empty")
	private String content;
	
	@NotEmpty(message = "Post short description should not be empty")
	private String shortDescription;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	private Set<CommentDto> comment;
	
	
	


}
