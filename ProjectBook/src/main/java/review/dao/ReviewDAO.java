package review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import review.bean.ReviewDTO;

@Repository
public class ReviewDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<ReviewDTO> reviewList(int startNum,int endNum){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum",endNum);
		return sqlSession.selectList("mybatis.reviewMapper.reviewList",map);
	}
	
	public List<ReviewDTO> reviewId(int startNum,int endNum,String member_id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum",endNum);
		map.put("member_id", member_id);
		return sqlSession.selectList("mybatis.reviewMapper.reviewId",map);
	}
	public int writeReview(String member_id,int book_id,String review_content) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id",book_id);
		map.put("review_content", review_content);
		return sqlSession.insert("mybatis.reviewMapper.writeReview",map);
	}
	public List<ReviewDTO> followingReview(int startNum,int endNum,String member_id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum",endNum);
		map.put("member_id", member_id);
		return sqlSession.selectList("mybatis.reviewMapper.followingReview",map);
	}
}
