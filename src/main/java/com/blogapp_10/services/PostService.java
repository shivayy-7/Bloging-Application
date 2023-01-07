package com.blogapp_10.services;

import java.util.List;

import com.blogapp_10.dto.PostDto;

public interface PostService {
	
	List<PostDto> findAllPosts();

	void createPost(PostDto postDto);

	PostDto findPostById(Long postId);
	
	void updatePost(PostDto postDto);

	void deletePost(Long postId);

	PostDto findPostByUrl(String postUrl);

	List<PostDto> searchPosts(String query);

}
