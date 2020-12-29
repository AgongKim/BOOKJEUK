package review.controller;

import java.util.List;

import review.bean.ReviewDTO;
import review.dao.ReviewDAO;

public interface ReviewService {
	public List<ReviewDTO> reviewList(int startNum,int endNum);
	public List<ReviewDTO> reviewId(int startNum,int endNum,String member_id);
	public int writeReview(String member_id,int book_id,String review_content);
	public List<ReviewDTO> followingReview(int startNum,int endNum,String member_id);
}
