package member.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import book.bean.BookandGrade;
import member.bean.FavoriteDTO;
import member.bean.MemberDTO;
import member.bean.RecordDTO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//맴버 프로필 사진은 아이디별 폴더에 각각 따로 저장한다.
	@RequestMapping(value = "/getMember.do")
	public ModelAndView getMember(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		
		String member_id = request.getParameter("member_id");
		MemberDTO memberDTO = memberService.getMember(member_id);
		
		String rt = "FAIL";
		int total = 0;
		if(memberDTO!=null) rt = "OK"; total =1; 
		 
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		JSONArray item = new JSONArray();
		JSONObject temp = new JSONObject();
		temp.put("member_nick",memberDTO.getMember_nick());
		temp.put("member_id", memberDTO.getMember_id());
		temp.put("member_pw", memberDTO.getMember_pw());
		temp.put("member_genre", memberDTO.getMember_genre());
		temp.put("member_goal",memberDTO.getMember_goal());
		String fileURL = "";
		//경로 다른애들이랑 다름 각자 아이디명으로 만든 폴더에저장
		if(memberDTO.getMember_photo() != null||memberDTO.getMember_photo().equals("")) {
			fileURL = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/storage/"+memberDTO.getMember_id()+"/"+memberDTO.getMember_photo();
			//fileURL = "http://192.168.0.96:8080/finalProject/storage/" + filename;
		}
		temp.put("member_photo", fileURL);
		temp.put("member_phone", memberDTO.getMember_phone());
		// JSONArray에 추가
		item.put(0,temp);
		json.put("item", item);
		//화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		//src/main/webapp/ProjectBook.jsp를 호출한다.
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getMonthData.do")
	public ModelAndView getMonthData(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		
		String member_id = request.getParameter("member_id");
		int month = Integer.parseInt(request.getParameter("month"));
		int lastDay = Integer.parseInt(request.getParameter("lastDay"));
		int year = Integer.parseInt(request.getParameter("year"));
		List<RecordDTO> list = memberService.getMonthData(member_id, month,lastDay,year);
		String rt = "FAIL";
		int total = 0;
		if(list.size()>0) {
			rt="OK";
			total = list.size();
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		if(total > 0) {
			JSONArray item = new JSONArray();
			
			for(int i=0; i<total; i++) {
				RecordDTO recordDTO = list.get(i);
				JSONObject temp = new JSONObject();
				temp.put("book_id", recordDTO.getBook_id());
				temp.put("member_id", recordDTO.getMember_id());
				temp.put("book_name",recordDTO.getBook_name());
//				System.out.println(recordDTO.getStart_time());
//				System.out.println(recordDTO.getEnd_time());
				temp.put("start_time", recordDTO.getStart_time());
				temp.put("end_time",recordDTO.getEnd_time());
				temp.put("isreading", recordDTO.getIsreading());
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
	@RequestMapping(value = "/getBookRecord")
	public ModelAndView getBookRecord(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		
		String member_id = request.getParameter("member_id");
		List<RecordDTO> list = memberService.getBookRecord(member_id);
		
		String rt = "FAIL";
		int total = 0;
		if(list.size()>0) {
			rt="OK";
			total = list.size();
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		if(total > 0) {
			JSONArray item = new JSONArray();
			
			for(int i=0; i<total; i++) {
				RecordDTO recordDTO = list.get(i);
				JSONObject temp = new JSONObject();
				temp.put("book_id", recordDTO.getBook_id());
				temp.put("member_id", recordDTO.getMember_id());
				temp.put("book_name",recordDTO.getBook_name());
				temp.put("start_time", recordDTO.getStart_time());
				temp.put("end_time",recordDTO.getEnd_time());
				temp.put("isreading", recordDTO.getIsreading());
				// 파일이름 얻어오기
				String filename = recordDTO.getBook_img();
				// 파일 URL
				String fileURL = "";
				if(filename != null) {
					fileURL = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath()
						+ "/storage/" + filename;
					//fileURL = "http://192.168.0.96:8080/finalProject/storage/" + filename;
				}
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
	
	@RequestMapping(value = "/setGoal.do")
	public ModelAndView setGoal(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 

		String member_id = request.getParameter("member_id");
		int goal = Integer.parseInt(request.getParameter("goal"));
		int result = memberService.setGoal(goal, member_id);
		
		String rt = "FAIL";
		int total = 0;
		if(result>0) {
			rt="OK";
			total = result;
		}
		System.out.println("진입테스트2");
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getFavorite.do")
	public ModelAndView getFavorite(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		String member_id = request.getParameter("member_id");
		List<FavoriteDTO> list = memberService.getFavorite(member_id);
		
		String rt = "FAIL";
		int total = 0;
		if(list.size()>0) {
			rt="OK";
			total = list.size();
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		if(total > 0) {
			JSONArray item = new JSONArray();
			
			for(int i=0; i<total; i++) {
				FavoriteDTO favoriteDTO = list.get(i);
				JSONObject temp = new JSONObject();
				temp.put("book_id", favoriteDTO.getBook_id());
				temp.put("member_id", favoriteDTO.getMember_id());
				temp.put("book_name",favoriteDTO.getBook_name());
				temp.put("writer", favoriteDTO.getWriter());
				temp.put("publisher", favoriteDTO.getPublisher());
				temp.put("grade",favoriteDTO.getGrade());
				temp.put("book_summary",favoriteDTO.getBook_summary());
				temp.put("book_link", favoriteDTO.getBook_link());
				
				// 파일이름 얻어오기
				String filename = favoriteDTO.getBook_img();
				// 파일 URL
				String fileURL = "";
				if(filename != null) {
					fileURL = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath()
						+ "/storage/" + filename;
					//fileURL = "http://192.168.0.96:8080/finalProject/storage/" + filename;
				}
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
	
	@RequestMapping(value = "/addMember.do")
	public ModelAndView addMember(HttpServletRequest request,MultipartFile photo)throws Exception{

		request.setCharacterEncoding("UTF-8"); 
		String member_nick = request.getParameter("member_nick");
		String member_id = request.getParameter("member_id");
		String member_pw = request.getParameter("member_pw");
		String member_genre = request.getParameter("member_genre");
		String member_phone = request.getParameter("member_phone");
		int member_goal = Integer.parseInt(request.getParameter("member_goal"));
		String member_photo;		
		String member_email = request.getParameter("member_email");
		
		String dir = "C:\\android_James (2)\\spring\\workspace\\ProjectBook\\src\\main\\webapp\\storage\\"+member_id;
		//폴더가 없으면 생성
		File folder = new File(dir);
		if(!folder.isDirectory()) {
			try{
				folder.mkdir();
			}catch (Exception e) {
				e.getStackTrace();
			}
		}
		if(photo != null) {
			member_photo = photo.getOriginalFilename();
			File file = new File(dir,member_photo);
			FileCopyUtils.copy(photo.getInputStream(), new FileOutputStream(file));
		}
		else {
			member_photo = "profile.jpg";
		}
				
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMember_genre(member_genre);
		memberDTO.setMember_goal(member_goal);
		memberDTO.setMember_id(member_id);
		memberDTO.setMember_nick(member_nick);
		memberDTO.setMember_phone(member_phone);
		memberDTO.setMember_photo(member_photo);
		memberDTO.setMember_pw(member_pw);
		memberDTO.setMember_email(member_email);
	
		JSONObject json = new JSONObject();
		String rt= "FAIL";
		int su = memberService.addMember(memberDTO);
		if(su>0) {
			rt="OK";
		}
		json.put("rt",rt);
		
		//화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		//src/main/webapp/ProjectBook.jsp를 호출한다.
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/chkLogin.do")
	public ModelAndView chkLogin(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		String member_pw = request.getParameter("member_pw");
		String member_id = request.getParameter("member_id");
		
		MemberDTO memberDTO = memberService.chkLogin(member_id, member_pw);
		String rt = "FAIL";
		int total = 0;
		if(memberDTO!=null) rt = "OK"; total =1; 
		 
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		
		JSONArray item = new JSONArray();
		JSONObject temp = new JSONObject();
		temp.put("member_nick",memberDTO.getMember_nick());
		temp.put("member_id", memberDTO.getMember_id());
		temp.put("member_pw", memberDTO.getMember_pw());
		temp.put("member_genre", memberDTO.getMember_genre());
		temp.put("member_goal",memberDTO.getMember_goal());
		temp.put("member_email",memberDTO.getMember_email());
		String fileURL = "";
		//경로 다른애들이랑 다름 각자 아이디명으로 만든 폴더에저장
		if(memberDTO.getMember_photo() != null||memberDTO.getMember_photo().equals("")) {
			if(memberDTO.getMember_photo().equals("profile.jpg")) {
				fileURL = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/storage/"+memberDTO.getMember_photo();
			}else {
				fileURL = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/storage/"+memberDTO.getMember_id()+"/"+memberDTO.getMember_photo();
			}
			
			//fileURL = "http://192.168.0.96:8080/finalProject/storage/" + filename;
		}
		temp.put("member_photo", fileURL);
		temp.put("member_phone", memberDTO.getMember_phone());
		// JSONArray에 추가
		item.put(0,temp);
		json.put("item", item);
		//화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		//src/main/webapp/ProjectBook.jsp를 호출한다.
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/start_reading.do")
	public ModelAndView startReading(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String book_name = request.getParameter("book_name");
		
		int su = memberService.startReading(member_id, book_id, book_name);
		
		String rt = "FAIL";
		if(su>0) rt = "OK";
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/end_reading.do")
	public ModelAndView endReading(HttpServletRequest request)throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		int su = memberService.endReading(member_id, book_id);
		
		String rt = "FAIL";
		if(su>0) rt = "OK";
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/is_reading.do")
	public ModelAndView isReading(HttpServletRequest request) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		RecordDTO recordDTO = memberService.isReading(member_id, book_id);
		
		String isReading="";
		String rt ="OK";
		JSONArray item = new JSONArray();
		if(recordDTO!=null) {
			int su = recordDTO.getIsreading();
			if(su==1) {
				isReading="READING";
				JSONObject temp = new JSONObject();
				temp.put("start_time", recordDTO.getStart_time());
				item.put(temp);
			}else {
				isReading="DONE";
				JSONObject temp = new JSONObject();
				temp.put("start_time", recordDTO.getStart_time());
				temp.put("end_time", recordDTO.getEnd_time());
				item.put(temp);
			}
		}else {
			isReading="NONE";
		}
		
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("isreading", isReading);
		json.put("item",item);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping( value = "/update_reading.do")
	public ModelAndView updateReading(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		//YYYY-MM-DD형식의 스트링이어야함
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String member_id =request.getParameter("member_id");
		
		int su = memberService.updateReading(start_time, end_time, book_id,member_id);
		String rt = "FAIL";
		if(su>0) {
			rt = "OK";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/set_favorite.do")
	public ModelAndView setFavorite(HttpServletRequest request) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		int su = memberService.setFavorite(member_id, book_id);
		String rt = "FAIL";
		if(su>0) {
			rt = "OK";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/delete_favorite.do")
	public ModelAndView deleteFavorite(HttpServletRequest request) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		int su = memberService.deleteFavorite(member_id, book_id);
		String rt = "FAIL";
		if(su>0) {
			rt = "OK";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	@RequestMapping(value = "/is_favorite.do")
	public ModelAndView isFavorite(HttpServletRequest request) throws Exception{

		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		FavoriteDTO favoriteDTO = memberService.isFavorite(member_id, book_id);
		
		String rt = "FAIL";
		String result = "false";
		if(favoriteDTO!=null) {
			rt = "OK";
			 result = "true";
			
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("result", result);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/get_bookshelf.do")
	public ModelAndView getBookShelf(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		List<BookandGrade> list = memberService.getBookShelf(member_id);
		
		JSONObject json = new JSONObject();
		String rt = "FAIL";
		if(list.size()>0) rt="OK";
		
		
		
		json.put("item",list);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete_member.do")
	public ModelAndView deleteMember(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		int su = memberService.deleteMember(member_id);
		
		JSONObject json = new JSONObject();
		String rt = "FAIL";
		if(su>0) rt="OK";
		
		
		
		json.put("item",rt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/chk_id.do")
	public ModelAndView chkId(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		
		String member_id = request.getParameter("member_id");
		
		String chk_id = memberService.chkId(member_id);
		
		//같은 아이디가 존재하면 OK가 가고 
		//같은 아디가 존재하지 않으면 FAIL
		String rt = "OK";
		if(chk_id == null) {
			rt="FAIL";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/member_find_id.do")
	public ModelAndView memberFindId(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		
		String member_email = request.getParameter("member_email");

		String member_id = memberService.memberFindId(member_email);
		
		
		String rt = "FAIL";
		if(member_id != null) {
			rt="OK";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);	
		json.put("member_id", member_id);
		json.put("member_email", member_email);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/member_find_pw.do")
	public ModelAndView memberFindPw(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("UTF-8"); 
		
		String member_id = request.getParameter("member_id");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = memberService.memberFindPw(member_id);
		
		String member_pw = memberDTO.getMember_pw();
		String member_email = memberDTO.getMember_email();
		
		String rt = "FAIL";
		if(memberDTO != null) {
			rt="OK";
		}
		JSONObject json = new JSONObject();
		json.put("rt", rt);	
		json.put("member_pw", member_pw);
		json.put("member_email", member_email);
		json.put("member_id", member_id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
}
