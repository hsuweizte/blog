package com.hsuweizte.bloghsuweizte.service;

import com.hsuweizte.bloghsuweizte.dao.CommentRepository;
import com.hsuweizte.bloghsuweizte.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //找沒有父評論的評論（頂級）評論
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, Sort.by("createTime"));
        return eachComment(comments);
    }

    //循環每個頂級評論節點
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }

        //合併評論的各層子代到第一級子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();


    // @param comments root根節點，blog不為空的物件集合
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                //循環迭代，找出子代，存放在tempReplys中
                recursively(reply);
            }
            //修改頂集結點的reply集合為迭代處理後的集合
            comment.setReplyComments(tempReplys);
            //清除臨時存放區
            tempReplys = new ArrayList<>();
        }
    }

    //遞歸迭代
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getReferenceById(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
