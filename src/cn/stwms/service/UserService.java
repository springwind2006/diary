package cn.stwms.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	
	/**
	 * 注册用户
	 * @param user 注册的用户数据模型
	 * @return 返回错误代码
	 * 错误代码说明：
	 * 	0=>注册成功,
	 *  1=>用户名或密码为空, 
	 *  2=>用户名已存在,
	 *  3=>系统繁忙，稍候再试
	 * */
	public int register(User user){
		System.out.println(user.getUsername());
		String username=user.getUsername();
		String password=user.getPassword();
		
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
			Integer id=userDao.getIdByName(username);
			if(id!=null){
				return 2;
			}
			int time=BaseUtils.getTime();
			user.setId(0);
			user.setPassword(BaseUtils.md5(password));
			user.setAddtime(time);
			user.setLastlogin(time);
			user.setToken(makeToken(user));
			userDao.addUser(user);
			return user.getId() > 0 ? 0 : 3;
		}
		return 1;
	}

	public int updateEmail(User user){
		if(!StringUtils.isEmpty(user.getEmail())){
			return userDao.updateEmail(user);
		}
		return 0;
	}
	
	public int updatePassword(User cuser){
		int id=cuser.getId();
		String password=cuser.getPassword();
		if(id>=0 && !StringUtils.isEmpty(password)){
			User user=userDao.getUserById(id);
			if(user!=null){
				user.setPassword(BaseUtils.md5(password));
				user.setLastlogin(BaseUtils.getTime());
				user.setToken(makeToken(user));
				return userDao.updateToken(user);
			}
		}
		return 0;
	}
	
	public int login(User user){
		String username=user.getUsername();
		String password=user.getPassword();
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
			int time=BaseUtils.getTime();
			password=BaseUtils.md5(password);
			Integer id=userDao.getIdByAuth(username, password);
			if(id==null){
				return 2;
			}
			user.setId(id.intValue());
			user.setLastlogin(time);
			user.setPassword(password);
			user.setToken(makeToken(user));
			userDao.updateToken(user);
			return 0;
		}
		return 1;
	}
	
	/**
	 * 生成Token文件
	 * */
	public static String makeToken(User user){
		StringBuilder sb=new StringBuilder(user.getUsername());
		sb.append(user.getPassword());
		sb.append(user.getLastlogin());
		sb.append(BaseUtils.getConfig("diary.token.key"));
		return BaseUtils.md5(sb.toString());
	}
}
