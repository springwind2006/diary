package cn.stwms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.stwms.model.User;

public interface UserDao {

	public int addUser(User user);
	
	public int updateAvatar(User user);
	
	public int updatePassword(User user);
	
	public int updateEmail(User user);
	
	public int updateToken(User user);

	public int deleteUser(int id);
	
	public User getUserById(int id);
	
	public Integer getIdByName(String name);
	
	public Integer getIdByAuth(@Param("username") String username,@Param("password") String password);
	
	public List<User> getAllUser(String kw);
}
