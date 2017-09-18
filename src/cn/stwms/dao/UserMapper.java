package cn.stwms.dao;

import java.util.List;

import cn.stwms.model.User;

public interface UserMapper {

	public User getUserById(int id);

	public int addUser(User user);

	public int updateUser(User user);

	public int deleteUser(int id);
	
	public List<User> getAllUser(String kw);
}
