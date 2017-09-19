package cn.stwms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.stwms.dao.DiaryDao;
import cn.stwms.model.Diary;
import cn.stwms.utils.BaseUtils;

@Service("diaryService")
public class DiaryService {
	
	@Autowired
	private DiaryDao diaryDao;
	
	public List<Diary> list(String kw){
		List<Diary> diarys=diaryDao.getAllDiary(kw);
		return diarys;
	}
	
	public Diary find(int id){
		Diary diary=diaryDao.getDiaryById(id);
		return diary;
	}
	
	public int save(Diary diary){
		int time=BaseUtils.getTime();
		diary.setAddtime(time);
		diary.setEdittime(time);
		int result=diaryDao.addDiary(diary);
		return result;
	}
}
