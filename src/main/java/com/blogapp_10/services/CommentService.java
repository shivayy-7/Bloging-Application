package com.blogapp_10.services;

import java.util.List;

import com.blogapp_10.dto.CommentDto;

public interface CommentService {
	
	void createComment(String postUrl, CommentDto commentDto);

	List<CommentDto> getComments();

	void deleteComment(long commentId);

}
