package cn.stwms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.stwms.dao.DiaryMapper;
import cn.stwms.model.Diary;
import cn.stwms.utils.BaseUtils;

@Service("diaryService")
public class DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;
	
	public List<Diary> list(String kw){
		List<Diary> diarys=diaryMapper.getAllDiary(kw);
		return diarys;
	}
	
	public Diary find(int id){
		Diary diary=diaryMapper.getDiaryById(id);
		return diary;
	}
	
	public int save(Diary diary){
		int time=BaseUtils.getTime();
		diary.setAddtime(time);
		diary.setEdittime(time);
		int result=diaryMapper.addDiary(diary);
		return result;
	}
}
