package review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import review.bean.ReviewDTO;
import review.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDAO reviewDAO;
	
	@Override
	public List<ReviewDTO> reviewList(int startNum, int endNum) {
		return reviewDAO.reviewList(startNum, endNum);
	}

	@Override
	public List<ReviewDTO> reviewId(int startNum, int endNum, String member_id) {
		return reviewDAO.reviewId(startNum, endNum, member_id);
	}

	@Override
	public int writeReview(String member_id, int book_id, String review_content) {
		return reviewDAO.writeReview(member_id, book_id, review_content);
	}

	@Override
	public List<ReviewDTO> followingReview(int startNum, int endNum, String member_id) {
		return reviewDAO.followingReview(startNum, endNum, member_id);
	}

}
