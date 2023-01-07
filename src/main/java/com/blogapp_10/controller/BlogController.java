package com.blogapp_10.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blogapp_10.dto.CommentDto;
import com.blogapp_10.dto.PostDto;
import com.blogapp_10.services.PostService;


@Controller
public class BlogController {

	private PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	// handler method to handle http://localhost:8080/
	@GetMapping("/")
	public String viewBlogPosts(Model model) {
		List<PostDto> postResponse = postService.findAllPosts();
		model.addAttribute("postResponse", postResponse);
		return "blog/view_posts";
	}
	
	//handler method to handle view post request
	@GetMapping("/post/{postUrl}")
	public String showPost(@PathVariable("postUrl") String postUrl, Model model) {
		PostDto post = postService.findPostByUrl(postUrl);
		CommentDto commentDto = new CommentDto();
		model.addAttribute("comment", commentDto);
		model.addAttribute("post", post);
		return "blog/blog_post";
	}
	
	

}
