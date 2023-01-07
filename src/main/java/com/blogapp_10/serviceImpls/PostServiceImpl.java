package com.blogapp_10.serviceImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogapp_10.dto.PostDto;
import com.blogapp_10.entity.Post;
import com.blogapp_10.mapper.PostMapper;
import com.blogapp_10.repositories.PostRepository;
import com.blogapp_10.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepo;

	public PostServiceImpl(PostRepository postRepo) {
		this.postRepo = postRepo;
	}


	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts = postRepo.findAll();
		return posts.stream().map(PostMapper :: mapToPostDto)
				.collect(Collectors.toList());
	}


	@Override
	public void createPost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		postRepo.save(post);
	}


	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepo.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}


	@Override
	public void updatePost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		postRepo.save(post);
	}


	@Override
	public void deletePost(Long postId) {
		postRepo.deleteById(postId);
	}


	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepo.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}


	@Override
	public List<PostDto> searchPosts(String query) {
		List<Post> posts = postRepo.searchPosts(query);
		return posts.stream()
				.map(PostMapper :: mapToPostDto)
				.collect(Collectors.toList());
	}

}
