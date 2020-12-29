package search.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import book.bean.BookDTO;
import search.bean.SearchDTO;
import tag.bean.TagDTO;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/searchInsert.do")
	public ModelAndView searchInsert(HttpServletRequest request)throws Exception{
		
		String member_id = request.getParameter("member_id"); 
		String search = request.getParameter("search");


		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setMember_id(member_id);
		searchDTO.setSearch(search);
		
		int su = searchService.searchInsert(searchDTO);
		String rt = "FAIL";
		
		if(su>0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchList.do")
	public ModelAndView searchList(HttpServletRequest request)throws Exception{
		
		String member_id = request.getParameter("member_id"); 

		List<SearchDTO> list = searchService.searchList(member_id);
		
		String rt = "FAIL";
		int total = list.size();
		if(total > 0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		JSONArray item = new JSONArray();
		json.put("item",list);
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
}
