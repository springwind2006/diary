package cn.stwms.dao;

import java.util.List;

import cn.stwms.model.Diary;

public interface DiaryMapper {

	public Diary getDiaryById(int id);

	public int addDiary(Diary diary);

	public int updateDiary(Diary diary);

	public int deleteDiary(int id);
	
	public List<Diary> getAllDiary(String kw);
}
