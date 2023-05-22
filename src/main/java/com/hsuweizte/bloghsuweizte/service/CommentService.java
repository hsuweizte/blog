package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
