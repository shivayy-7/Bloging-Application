package com.blogapp_10.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
    private Long id;
	
    @NotEmpty(message="Name should not be empty")
	private String name;
	
    @NotEmpty(message="Email should not be empty")
    @Email
	private String email;
	
    @NotEmpty(message="Content should not be empty")
	private String content;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	

}
