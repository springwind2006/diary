package cn.stwms.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.stwms.dao.UserDao;
import cn.stwms.model.User;
import cn.stwms.utils.BaseUtils;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> list(String kw){
		List<User> users=userDao.getAllUser(kw);
		return users;
	}
	
	public User find(int id){
		User user=userDao.getUserById(id);
		return user;
	}
	
	public int save(User user){
		user.setAddtime(BaseUtils.getTime());
		user.setPassword(BaseUtils.md5(user.getPassword()));
		int result=userDao.addUser(user);
		return result;
	}
}
