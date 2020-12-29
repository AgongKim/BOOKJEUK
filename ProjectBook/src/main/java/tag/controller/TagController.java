package tag.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import book.bean.BookDTO;
import book.controller.BookService;

@Controller
public class TagController {
	@Autowired
	TagService tagService;
	
	@Autowired
	BookService bookService;
	
	
	//내가해야되는것==>> 해당 장르에 맞는 추천 목록 점수 내림차순 
	@RequestMapping(value = "/genre_index_list.do")
	public ModelAndView genreIndexList(HttpServletRequest request) throws Exception{
		// 데이터
		int pg = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		
		// DB 
		// 1페이지당 1~15
		int endNum = pg * size;					// 1 * 15 = 15
		int startNum = endNum - (size - 1);		// 15 - (15 -1) = 1
		//장르 풀네임 삽입한다.ex genre_art
		String genre = request.getParameter("genre");
		
		List<BookDTO> list = tagService.genreIndexList(startNum, endNum, genre);
			
		// json
		String rt= "FAIL";
		int total = list.size();
				
		if(total > 0) {
			rt = "OK";
		}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		if(total > 0) {
			JSONArray item = new JSONArray();
			
			for(int i=0; i<total; i++) {
				BookDTO bookDTO = list.get(i);
				
				// 파일이름 얻어오기
				String filename = bookService.getBook_img(bookDTO.getBook_id());
				// 파일 URL
				String fileURL = "";
				if(filename != null) {
					fileURL = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath()
						+ "/storage/" + filename;
					//fileURL = "http://192.168.0.96:8080/finalProject/storage/" + filename;
				}
				
				JSONObject temp = new JSONObject();
				temp.put("book_id", bookDTO.getBook_id());
				temp.put("book_name", bookDTO.getBook_name());
				temp.put("writer", bookDTO.getWriter());
				temp.put("publisher", bookDTO.getPublisher());
				temp.put("grade", bookDTO.getGrade());
				temp.put("book_summary", bookDTO.getBook_summary());
				temp.put("book_link", bookDTO.getBook_link());
				temp.put("book_img", fileURL);
				// JSONArray에 추가
				item.put(i, temp);
			}
			json.put("item", item);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/tag_select.do")
	public ModelAndView tagSelect(HttpServletRequest request) throws Exception{
		
		request.setCharacterEncoding("UTF-8"); // 한글 설정
		String tag = request.getParameter("tag");
		List<HashMap<String,Object>> list = tagService.tagSelect(tag);
		
		String rt = "FAIL";		
		int total = 0;
	    if(list.size()>0) {rt="OK"; total=list.size();}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		JSONArray item = new JSONArray();
		for(int i=0;i<list.size();i++) {
			HashMap<String, Object> map = list.get(i);
			Iterator<String> keys = map.keySet().iterator();
			
			JSONObject temp = new JSONObject();
			while(keys.hasNext()) {
				String key = keys.next();
				temp.put(key.toLowerCase(), map.get(key));
			}
			item.put(temp);
		}
		json.put("item", item);
		
		System.out.println(item);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
}
