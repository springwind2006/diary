package cn.stwms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.stwms.model.Diary;

@Repository
public class DiaryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<?> select(String where) {
		String sql = "select * from m_diary "+where;
		List<?> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public Diary find(int id) {
		String sql = "select * from m_diary where id = ?";
		final Diary diary = new Diary();
		final Object[] params = new Object[] { id };
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				// 向diary对象中添加各个属性值
				diary.setId(rs.getInt("id"));
				diary.setTitle(rs.getString("title"));
				diary.setSubtitle(rs.getString("subtitle"));
				diary.setContent(rs.getString("content"));
				diary.setWeather(rs.getString("weather"));
				diary.setEmote(rs.getShort("emote"));
				diary.setAddtime(rs.getInt("addtime"));
				diary.setEdittime(rs.getInt("edittime"));
				diary.setSync_id(rs.getInt("sync_id"));
				diary.setUserid(rs.getInt("userid"));
			}
		});
		return diary;
	}

	public int add(Diary diary) {
		String sql = "insert into m_diary (title,subtitle,content,weather,emote,addtime,edittime,sync_id,userid) values (?, ?, ?, ?, ?, ?, ?, ?,?)";
		Object[] params = new Object[] {
				diary.getTitle(), 
				diary.getSubtitle(), 
				diary.getContent(), 
				diary.getWeather(),
				diary.getEmote(),
				diary.getAddtime(),
				diary.getEdittime(),
				diary.getSync_id(),
				diary.getUserid(),
		}; 
		// 存放SQL语句中的参数
		return jdbcTemplate.update(sql, params);
	}

	public int update(Diary diary) {
		String sql = "update diary set title = ?, subtitle = ?, content = ?, weather = ?, emote = ?, edittime = ? where id = ?";
		// 存放SQL语句中的参数
		Object[] params = new Object[] {
					diary.getTitle(), 
					diary.getSubtitle(), 
					diary.getContent(), 
					diary.getWeather(),
					diary.getEmote(),
					diary.getEdittime(),
					diary.getId() 
				};
		return jdbcTemplate.update(sql, params);
	}

	public int delete(int id) {
		String sql = "delete from m_diary where id = ?"; 
		Object[] params = new Object[] { id };
		return jdbcTemplate.update(sql, params);
	}
}
