package com.blogapp_10.mapper;

import java.util.stream.Collectors;

import com.blogapp_10.dto.PostDto;
import com.blogapp_10.entity.Post;

public class PostMapper {
	
	public static PostDto mapToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.comment(post.getComments().stream().map((comment)-> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
				.build();
		}
	
	public static Post mapToPost(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setUrl(postDto.getUrl());     
		post.setContent(postDto.getContent());
		post.setShortDescription(postDto.getShortDescription());
		post.setCreatedOn(postDto.getCreatedOn());
		post.setUpdatedOn(postDto.getUpdatedOn());
		return post;
	}

}
