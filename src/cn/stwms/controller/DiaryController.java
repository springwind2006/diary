package cn.stwms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.stwms.model.Diary;
import cn.stwms.service.DiaryService;

@Controller
@RequestMapping("/diary")	
public class DiaryController{
	@Autowired
	private DiaryService diaryService;
	 //声明请求的方法，默认为GET方法
	@RequestMapping(value="/list/*",params={"!page"})
	public String list(HttpServletRequest request,ModelMap model){
		String page=request.getParameter("page");
		System.out.println(page);
		model.addAttribute("message", "handleRequest");	
		model.addAttribute("page",page);
		return "diary/list";	
	}
	
	@RequestMapping(value="/save",method = RequestMethod.GET)
	public ModelAndView save(ModelMap model){
		Diary diary=new Diary();
		diaryService.save(diary);
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("message", "handleRequests 111");
		mv.setViewName("diary/save");
		return mv;	
	}
	
	@RequestMapping("sync")
	public String sync(ModelMap model){
		model.addAttribute("message", "handleRequest");	
		return "diary/sync";	
	}

}
