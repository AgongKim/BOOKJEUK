package member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import book.bean.BookandGrade;
import member.bean.FavoriteDTO;
import member.bean.MemberDTO;
import member.bean.RecordDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public MemberDTO getMember(String member_id) {
		return sqlSession.selectOne("mybatis.memberMapper.getMember",member_id);
	}
	
	public List<RecordDTO> getMonthData(String member_id,int month,int lastDay,int year){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("month", String.format("%02d",month));
		map.put("lastDay",String.format("%02d", lastDay));
		map.put("year",year);
		return sqlSession.selectList("mybatis.memberMapper.getMonthData",map);
	}
	
	public int setGoal(int goal,String member_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goal", goal);
		map.put("member_id",member_id);
		return sqlSession.update("mybatis.memberMapper.setGoal",map);
	}
	
	public List<RecordDTO> getBookRecord(String member_id){
		return sqlSession.selectList("mybatis.memberMapper.getBookRecord",member_id);
	}
	
	public List<FavoriteDTO> getFavorite(String member_id){
		return sqlSession.selectList("mybatis.memberMapper.getFavorite",member_id);
	}
	public int addMember(MemberDTO memberDTO) {
		return sqlSession.insert("mybatis.memberMapper.addMember",memberDTO);
	}
	public MemberDTO chkLogin(String member_id,String member_pw) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("member_pw",member_pw);
		return sqlSession.selectOne("mybatis.memberMapper.chkLogin",map);
	}
	
	public int endReading(String member_id,int book_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id", book_id);
		return sqlSession.update("mybatis.memberMapper.endReading",map);
	}
	public int startReading(String member_id,int book_id,String book_name) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id",member_id);
		map.put("book_id",book_id);
		map.put("book_name", book_name);
		return sqlSession.insert("mybatis.memberMapper.startReading",map);
	}
	public RecordDTO isReading(String member_id,int book_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id",book_id);
		return sqlSession.selectOne("mybatis.memberMapper.isReading",map);
	}
	public int updateReading(String start_time,String end_time,int book_id,String member_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start_time", start_time);
		map.put("end_time",end_time);
		map.put("book_id",book_id);
		map.put("member_id",member_id);
		return sqlSession.update("mybatis.memberMapper.updateReading",map);
	}
	public int setFavorite(String member_id,int book_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id",book_id);
		return sqlSession.insert("mybatis.memberMapper.setFavorite",map);
	}
	public int deleteFavorite(String member_id,int book_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id",book_id);
		return sqlSession.delete("mybatis.memberMapper.deleteFavorite",map);
	}
	public FavoriteDTO isFavorite(String member_id,int book_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id",book_id);
		return sqlSession.selectOne("mybatis.memberMapper.isFavorite",map);
	}
	public List<BookandGrade> getBookShelf(String member_id) {
		return sqlSession.selectList("mybatis.memberMapper.getBookShelf",member_id);
	}
	public int deleteMember(String member_id) {
		return sqlSession.delete("mybatis.memberMapper.deleteMember",member_id);
	}
	
	
	public String chkId(String member_id){
		return sqlSession.selectOne("mybatis.memberMapper.chkId",member_id);
	}
	
	public String memberFindId(String member_id){
		return sqlSession.selectOne("mybatis.memberMapper.memberFindId", member_id);
	}
	
	public MemberDTO memberFindPw(String member_id){
		return sqlSession.selectOne("mybatis.memberMapper.memberFindPw", member_id);
	}
}
