package cn.yy.elastic.mapper;

import cn.yy.elastic.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(name,account_Id,token,gmt_Create,gmt_Modified) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("SELECT * FROM  User where token = #{token}")
    User findByToken(@Param("token") String token);
}
