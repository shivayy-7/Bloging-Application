package com.blogapp_10.mapper;

import com.blogapp_10.dto.CommentDto;
import com.blogapp_10.entity.Comment;

public class CommentMapper {
	
	//map Comment to commentDto
	public static CommentDto mapToCommentDto(Comment comment) {
		return CommentDto.builder()
		          .id(comment.getId())
		          .name(comment.getName())
		          .email(comment.getEmail())
		          .content(comment.getContent())
		          .createdOn(comment.getCreatedOn())
		          .updatedOn(comment.getUpdatedOn())
		          .build();
	}
	
	//map CommentDto to Comment
	public static Comment mapToComment(CommentDto commentDto) {
		return Comment.builder()
				      .id(commentDto.getId())
				      .name(commentDto.getName())
				      .email(commentDto.getEmail())
				      .content(commentDto.getContent())
				      .createdOn(commentDto.getCreatedOn())
				      .updatedOn(commentDto.getUpdatedOn())
				      .build();
				      
	}
	

}
