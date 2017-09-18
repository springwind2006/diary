package cn.stwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.stwms.model.User;
import cn.stwms.utils.BaseUtils;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<?> select(String where) {
		String sql = "select * from m_user "+where;
		List<?> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public User find(int id) {
		String sql = "select * from m_user where id = ?";
		final User user = new User();
		final Object[] params = new Object[] { id };
		// 调用jdbcTemplate的query方法
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				// 向user对象中添加各个属性值
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAddtime(rs.getInt("addtime"));
				user.setLastlogin(rs.getInt("lastlogin"));
			}
		});
		return user;
	}

	public int add(User user) {
		String sql = "insert into m_user (username, password, email, addtime) values (?, ?, ?, ?)";
		Object[] params = new Object[] { user.getUsername(), user.getPassword(), user.getEmail(), BaseUtils.getTime() }; 
		return jdbcTemplate.update(sql, params);
	}

	public int update(User user) {
		String sql = "update user set username = ?, password = ?, email = ? where id = ?";
		Object[] params = new Object[] { user.getUsername(), user.getPassword(), user.getEmail(), user.getId() };
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from m_user where id = ?"; 
		Object[] params = new Object[] { id }; // 存放SQL语句中的参数
		return jdbcTemplate.update(sql, params); // 调用jdbcTemplate的update方法
	}
}
