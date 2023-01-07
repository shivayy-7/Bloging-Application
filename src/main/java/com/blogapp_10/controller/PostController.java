package com.blogapp_10.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogapp_10.dto.CommentDto;
import com.blogapp_10.dto.PostDto;
import com.blogapp_10.services.CommentService;
import com.blogapp_10.services.PostService;

import jakarta.validation.Valid;


@Controller
public class PostController {
	
	private PostService postService;
	private CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}
	
	//http://localhost:8080/admin/posts
	@GetMapping("/admin/posts")
	public String posts(ModelMap model) {
		List<PostDto> posts = postService.findAllPosts();
		model.put("posts", posts);
		return "/admin/posts";
	}
	
	//http://localhost:8080/admin/posts/newpost
	@GetMapping("/admin/posts/newpost")
	public String newPostForm(Model model) {
		PostDto postDto = new PostDto();
		model.addAttribute("post", postDto);
		return "admin/create_post";
	}
	
	@PostMapping("/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model ) {
		if(result.hasErrors()) {
			model.addAttribute("post", postDto);
			return "admin/create_post";
		}
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
	}

	private String getUrl(String postTitle) {
		//OOPS Concepts Explained in java is convert to oops-concepts-explained-in-java
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url=url.replaceAll("[^A-Za-z0-9]", "-");
		return url;
	}
	
	//http://localhost:8080/posts/1/edit
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long postId, Model model) {
		PostDto postDto = postService.findPostById(postId);
		model.addAttribute("post", postDto);
		return "admin/edit_posts";
	}
	
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
							@Valid @ModelAttribute("post") PostDto post,
							BindingResult result,
							Model model){
		if(result.hasErrors()) {
			model.addAttribute("post", post);
			return "admin/edit_post";
		}
		
		post.setId(postId);
		postService.updatePost(post);
		return "redirect:/admin/posts";
		
	}

	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId) {
		postService.deletePost(postId);
		return "redirect:/admin/posts";
	}
	
	// handler method to handle view post request
	@GetMapping("/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post", postDto);
		return "admin/view_post";
	}
	
	@GetMapping("/admin/posts/search")
	public String searchPosts(@RequestParam(value="query") String query, Model model) {
		List<PostDto> posts = postService.searchPosts(query);
		model.addAttribute("posts", posts);
		return "admin/posts";
	}
	
	@GetMapping("/admin/posts/comments")
	public String getComments(Model model) {
		List<CommentDto> comments = commentService.getComments();
		model.addAttribute("comments", comments);
		return "admin/comments";
	}
	
	@GetMapping("/admin/comments/{commentId}/delete")
	public String deleteComment(@PathVariable("commentId") long commentId, Model model) {
		commentService.deleteComment(commentId);
		List<CommentDto> comments = commentService.getComments();
		model.addAttribute("comments", comments);
		return "admin/comments";
	}
	
	

}
