package cn.yy.elastic.mapper;

import cn.yy.elastic.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}