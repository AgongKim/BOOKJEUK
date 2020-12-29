package review.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import review.bean.ReviewDTO;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "/reviewList.do")
	public ModelAndView reviewList(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		
		int endNum = page * size;					// 1 * 15 = 15
		int startNum = endNum - (size - 1);		// 15 - (15 -1) = 1
		
		System.out.println(endNum);
		System.out.println(startNum);
		
		List<ReviewDTO> list = reviewService.reviewList(startNum, endNum);
		
		String rt = "FAIL";
		int total = list.size();
		if(total > 0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		json.put("item",list);
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/reviewId.do")
	public ModelAndView reviewId(HttpServletRequest request) throws Exception  {
		request.setCharacterEncoding("UTF-8"); 
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		String member_id = request.getParameter("member_id");
		
		int endNum = page * size;					// 1 * 15 = 15
		int startNum = endNum - (size - 1);		// 15 - (15 -1) = 1
		
		System.out.println(endNum);
		System.out.println(startNum);
		
		List<ReviewDTO> list = reviewService.reviewId(startNum, endNum,member_id);
		
		String rt = "FAIL";
		int total = list.size();
		if(total > 0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		json.put("item",list);
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/write_review.do")
	public ModelAndView insertReview(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		String member_id = request.getParameter("member_id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String review_content = request.getParameter("review_content");
		int su = reviewService.writeReview(member_id, book_id, review_content);
		
		String rt = "FAIL";
		if(su > 0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/followingReview.do")
	public ModelAndView followingReview(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		String member_id = request.getParameter("member_id");
		
		int endNum = page * size;					// 1 * 15 = 15
		int startNum = endNum - (size - 1);		// 15 - (15 -1) = 1
		
		List<ReviewDTO> list = reviewService.followingReview(startNum, endNum, member_id);
		
		String rt = "FAIL";
		int total = list.size();
		if(total > 0) {rt = "OK";}
		
		JSONObject json = new JSONObject();
		json.put("rt", rt);
		json.put("total", total);
		json.put("item",list);
		

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("json",json);
		modelAndView.setViewName("ProjectBook.jsp");
		return modelAndView;
		
	}
	
}
