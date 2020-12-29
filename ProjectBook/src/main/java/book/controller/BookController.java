package book.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import book.bean.BookDTO;
import book.bean.Book_gradeDTO;
import book.bean.GradeBookMemberDTO;
import tag.bean.TagDTO;
import tag.controller.TagService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	TagService tagService;
	
	//데이터 추가하는 매소드 책정보저장 + 관련태그저장 으로 테이블 2개 한번에 처리한다.)
	@RequestMapping(value = "/book_insert.do")
	public ModelAndView BookInsert(HttpServletRequest request,MultipartFile photo)throws Exception{
		request.setCharacterEncoding("UTF-8"); // 한글 설정
		//파일저장위치(서버가 컴파일하는 위치이다.)
		String dir = "C:\\android_James (2)\\spring\\workspace\\ProjectBook\\src\\main\\webapp\\storage";
		
		String book_name = request.getParameter("book_name");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		Float grade = Float.parseFloat(request.getParameter("grade"));
		String book_summary = request.getParameter("book_summary");
		String book_link = request.getParameter("book_link");
		String book_img = photo.getOriginalFilename();
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBook_name(book_name);
		bookDTO.setWriter(writer);
		bookDTO.setPublisher(publisher);
		bookDTO.setGrade(grade);
		bookDTO.setBook_summary(book_summary);
		bookDTO.setBook_link(book_link);
		bookDTO.setBook_img(book_img);
		//id>>자동생성//이미지>>파일 따로 제외
		int su_book = bookService.bookInsert(bookDTO);
		//여기서부터 책과 관련된 태그 저장 (태그테이블)
		int book_id = bookService.getLastBookId();
		int genre_poet;
		int genre_essay;
		int genre_selfDev;
		int genre_history;
		int genre_science;
		int genre_novel;
		int genre_comics;
		int genre_art;
		
		if(request.getParameter("genre_poet")!=null)genre_poet=1;
		else genre_poet=0;
		if(request.getParameter("genre_essay")!=null)genre_essay=1;
		else genre_essay=0;
		if(request.getParameter("genre_selfDev")!=null)genre_selfDev=1;
		else genre_selfDev=0;
		if(request.getParameter("genre_history")!=null)genre_history=1;
		else genre_history=0;
		if(request.getParameter("genre_science")!=null)genre_science=1;
		else genre_science=0;
		if(request.getParameter("genre_novel")!=null)genre_novel=1;
		else genre_novel=0;
		if(request.getParameter("genre_comics")!=null)genre_comics=1;
		else genre_comics=0;
		if(request.getParameter("genre_art")!=null)genre_art=1;
		else genre_art=0;
		
		
		TagDTO tagDTO = new TagDTO();
		tagDTO.setBook_id(book_id);
		tagDTO.setBook_name(book_name);
		tagDTO.setGenre_art(genre_art);
		tagDTO.setGenre_comics(genre_comics);
		tagDTO.setGenre_essay(genre_essay);
		tagDTO.setGenre_history(genre_history);
		tagDTO.setGenre_novel(genre_novel);
		tagDTO.setGenre_poet(genre_poet);
		tagDTO.setGenre_science(genre_science);
		tagDTO.setGenre_selfDev(genre_selfDev);
		System.out.println(tagDTO.toString());
		int su_tag = tagService.bookInsert(tagDTO);
		
		String rt = "FAIL";
		if(su_book>0&&su_tag>0) rt = "OK";
		//책정보&태그정보 모두 저장완료되면 파일저장시작
		if(rt.equals("OK")){
			//전송되어온 파일 이름
			String originname = photo.getOriginalFilename();
			//storage폴더에 저장된 파일 이름
			File file = new File(dir,originname);
			FileCopyUtils.copy(photo.getInputStream(), new FileOutputStream(file));
			System.out.println("originname : "+originname);
			//System.out.println("filename : "+filename);
			if(originname==null||originname.equals("")){
				System.out.println("사진데이터가 없습니다.");
			}
		}
		 
		//강한별
			Float book_good = Float.parseFloat(request.getParameter("book_good"));
			Float book_neut = Float.parseFloat(request.getParameter("book_neut"));
			Float book_bad = Float.parseFloat(request.getParameter("book_bad"));
			Book_gradeDTO book_gradeDTO = new Book_gradeDTO();
			book_gradeDTO.setBook_id(bookService.getLastBookId());
			book_gradeDTO.setBook_good(book_good);
			book_gradeDTO.setBook_neut(book_good);
			book_gradeDTO.setBook_bad(book_bad);
				
			int su_grade = bookService.book_grade_insert(book_gradeDTO);
		
		JSONObject json = new JSONObject();
		json.put("rt",rt);
		json.put("book_id", book_id);
		//화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		//src/main/webapp/ProjectBook.jsp를 호출한다.
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	
	//평점 상위 목록 불러오기
	@RequestMapping(value = "/top_index_list.do")
	public ModelAndView topIndexList(HttpServletRequest request) throws Exception{
		// 데이터
		int pg = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
				
		// DB 
		// 1페이지당 1~15
		int endNum = pg * size;					// 1 * 15 = 15
		int startNum = endNum - (size - 1);		// 15 - (15 -1) = 1
		
		List<BookDTO> list = bookService.topIndexList(startNum, endNum);
		
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
	//강한별
		@RequestMapping(value = "/book_searchlist.do")
		public ModelAndView book_searchlist(HttpServletRequest request)throws Exception{
		    
			request.setCharacterEncoding("UTF-8");
			String book_name = request.getParameter("book_name");
			//System.out.println("而⑦듃濡ㅻ윭 �씠由�?" + book_name);
			List<BookDTO> list = bookService.book_searchlist(book_name);
			
			
		    String rt = "FAIL";		
			int total = list.size();
		    if(total > 0) {rt = "OK";}
		    
		    
		    
		    //json �뜲�씠�꽣 �깮�꽦
		    JSONObject json = new JSONObject();
		    json.put("rt",rt);
		    json.put("total", total);
		    
		    if(total > 0) {
		    	JSONArray item = new JSONArray();

		    	for(int i=0; i<list.size(); i++) {
			    	BookDTO dto = list.get(i);
			    	//�뙆�씪�씠由� �뼸�뼱�삤湲�
			    	String fileName = bookService.getBook_img(dto.getBook_id());
			    	
			    	//�뙆�씪 URL
			    	String fileURL = "";
			    	if(!fileName.equals("")) {
			    		fileURL = request.getScheme() + "://" + request.getServerName()
			    		+ ":" + request.getServerPort() + request.getContextPath()
			    		+ "/storage/" + fileName;
			    	}
			    	//System.out.println(fileURL);
			    	
			    	JSONObject temp = new JSONObject();
			    	temp.put("book_id", dto.getBook_id());
			    	temp.put("book_name", dto.getBook_name());
			    	temp.put("writer", dto.getWriter());
			    	temp.put("publisher", dto.getPublisher());
			    	temp.put("grade", dto.getGrade());
			    	temp.put("book_summary", dto.getBook_summary());
			    	temp.put("book_link", dto.getBook_link());
			    	temp.put("book_img", fileURL);
			    	
			    	item.put(i, temp);
			    	
		    	}  
		    	json.put("item", item);
		    }

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("json",json);
			modelAndView.setViewName("ProjectBook.jsp");
			return modelAndView;
		}
		
		@RequestMapping(value = "/book_select.do")
		public ModelAndView book_select(HttpServletRequest request)throws Exception{
			
			request.setCharacterEncoding("UTF-8");
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			List<HashMap<String,Object>> list = bookService.book_selectList(book_id);	
			//강한별
			Book_gradeDTO book_gradeDTO = new Book_gradeDTO();
			book_gradeDTO = bookService.book_grade_select(book_id);
			
			String rt = "FAIL";		
			int total = 0;
		    if(list.size()>0) {rt="ok"; total=list.size();}
		    
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
					temp.put("book_good", book_gradeDTO.getBook_good());
			    	temp.put("book_neut", book_gradeDTO.getBook_neut());
			    	temp.put("book_bad", book_gradeDTO.getBook_bad());
				}
				item.put(temp);
			}
			json.put("item", item);

		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("json",json);
			modelAndView.setViewName("ProjectBook.jsp");
			return modelAndView;
		}
		@RequestMapping(value = "/set_grade_book_member.do")
		ModelAndView setGradeBookMember(HttpServletRequest request)throws Exception{
			request.setCharacterEncoding("UTF-8");
			String member_id = request.getParameter("member_id");
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			
			//graded는  good/bad/neut 여야한다.
			String graded = request.getParameter("graded");
			//먼저 회원 평가 테이블에 인서트 
			int su1 = bookService.setGradeBookMember1(member_id, book_id, graded);
			//해당하는 책 평가에 +1
			int su2 = bookService.setGradeBookMember2(book_id, graded);
			
			String rt = "FAIL";
			if(su1>0&&su2>0) {
				rt= "OK";
			}
			JSONObject json = new JSONObject();
			json.put("rt",rt);
			//화면 네비게이션
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("json",json);
			//src/main/webapp/ProjectBook.jsp를 호출한다.
			modelAndView.setViewName("ProjectBook.jsp");
			return modelAndView;
		}
		@RequestMapping(value = "/get_grade_book_member.do")
		ModelAndView getGradeBookMember(HttpServletRequest request)throws Exception{
			request.setCharacterEncoding("UTF-8");
			String member_id = request.getParameter("member_id");
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			GradeBookMemberDTO gradeBookMemberDTO = bookService.getGradeBookMember(member_id, book_id);
			
			String rt = "FAIL";
			JSONObject json = new JSONObject();
			if(gradeBookMemberDTO!=null) {
				rt= "OK";
				json.put("member_id", gradeBookMemberDTO.getMember_id());
				json.put("graded",gradeBookMemberDTO.getGraded());
			}
			
			json.put("rt",rt);
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("json",json);
			//src/main/webapp/ProjectBook.jsp를 호출한다.
			modelAndView.setViewName("ProjectBook.jsp");
			return modelAndView;
		}
}
