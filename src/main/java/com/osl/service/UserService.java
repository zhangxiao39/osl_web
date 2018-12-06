package com.osl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.osl.model.UserModel;

@Mapper
public interface UserService {
	@Select("SELECT t_user.*,t_business.name as bname FROM t_user,t_business where t_user.business_id = t_business.id and t_business.url = #{url} and t_user.user_id = #{user_id} and t_user.password = #{password} and t_user.status = 0 and t_business.type=0")
	UserModel findByUserName_admin(String user_id, String password, String url);

	@Select("SELECT t_user.*,t_business.name as bname FROM t_user,t_business where t_user.business_id = t_business.id and t_business.url = #{url} and t_user.user_id = #{user_id} and t_user.password = #{password} and t_user.status = 0 and t_business.type=1")
	UserModel findByUserName_business(String user_id, String password, String url);
	
	@Select("SELECT * FROM t_user where id = #{id}")
	UserModel findById(@Param("id") String id);

	@Select("SELECT * FROM t_user where user_id = #{user_id} and password = #{password} and status = 0")
	UserModel findByUserName(@Param("userid") String userid, @Param("password") String password);

	@Select("SELECT id,user_id,username FROM t_user where business_id=#{bid} and status = 0;")
	List<Map> getAllUsers(@Param("bid") String bid);


	@Insert("INSERT INTO t_user (`user_id`, `password`, `username`, `business_id`,`addtime`) VALUES (#{user_id}, #{password}, #{username}, #{business_id},now());")
	int insert(UserModel users);

	@Update("UPDATE t_user SET user_id = #{user_id}, username = #{username} WHERE id = #{id};")
	int updateUserById(UserModel user);

	@Delete("DELETE FROM t_user WHERE id  = #{id}")
	int deleteById(int id);
}
