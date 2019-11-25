package cn.yy.elastic.mapper;

import cn.yy.elastic.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title,descriotion,gmt_create,gmt_modified,creator,tag) VALUES (#{title},#{descriotion},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
