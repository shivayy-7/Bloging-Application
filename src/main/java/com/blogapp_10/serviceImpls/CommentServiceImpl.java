package com.blogapp_10.serviceImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogapp_10.dto.CommentDto;
import com.blogapp_10.entity.Comment;
import com.blogapp_10.entity.Post;
import com.blogapp_10.mapper.CommentMapper;
import com.blogapp_10.mapper.PostMapper;
import com.blogapp_10.repositories.CommentRepository;
import com.blogapp_10.repositories.PostRepository;
import com.blogapp_10.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepo;
	private PostRepository postRepo;
	
	public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo) {
		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
	}



	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		Post post = postRepo.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
        commentRepo.save(comment);
	}



	@Override
	public List<CommentDto> getComments() {
		List<Comment> comments = commentRepo.findAll();
		return comments.stream().map(CommentMapper :: mapToCommentDto)
				.collect(Collectors.toList());
	}



	@Override
	public void deleteComment(long commentId) {
		commentRepo.deleteById(commentId);
	}

}
