package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentJpaRepository commentJpaRepository;

    public boolean saveComment(Comment comment){
        Comment result = commentJpaRepository.save(comment);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return isSuccess;
    }

    public List<Comment> getCommentList(Long postId){
        List<Comment> result = commentJpaRepository.findAllByPostIdOrderByRegDateDesc(postId);
        return result;
    }

    public Comment getComment(Long id){
        Comment result = commentJpaRepository.findOneById(id);
        return result;
    }

    public boolean deleteComment(Long id){
        Comment result = commentJpaRepository.findOneById(id);
        if(result == null){
            return false;
        }

        commentJpaRepository.deleteById(id);
        return true;
    }

    public List<Comment> getCommentList(Long postId, String query){
        List<Comment> result = commentJpaRepository.findAllByPostIdAndCommentContainingOrderByRegDateDesc(postId, query);
        return result;
    }
    
}
