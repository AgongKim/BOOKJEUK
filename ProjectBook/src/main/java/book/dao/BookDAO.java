package book.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import book.bean.BookDTO;
import book.bean.Book_gradeDTO;
import book.bean.GradeBookMemberDTO;

@Repository
public class BookDAO{
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int bookInsert(BookDTO bookDTO) {
		return sqlSession.insert("mybatis.bookMapper.bookInsert",bookDTO);
	}
	public int getLastBookId() {
		return sqlSession.selectOne("mybatis.bookMapper.getLastBookId");
	}
	public String getBook_img(int book_id) {
		return sqlSession.selectOne("mybatis.bookMapper.getBook_img",book_id);
	}
	public List<BookDTO> topIndexList(int startNum,int endNum){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum",startNum);
		map.put("endNum",endNum);
		return sqlSession.selectList("mybatis.bookMapper.topIndexList",map);
	}
	public List<BookDTO> book_searchlist(String book_name){
		String change_book_name = "%"+book_name + "%";
		return sqlSession.selectList("mybatis.bookMapper.book_searchlist", change_book_name);
	}
	
	public List<HashMap<String, Object>> book_selectList(int book_id){
		return sqlSession.selectList("mybatis.bookMapper.book_select", book_id);
	}
	
	public int book_grade_insert(Book_gradeDTO book_gradeDTO) {
		return sqlSession.insert("mybatis.bookMapper.book_grade_insert", book_gradeDTO);
	}
	
	public Book_gradeDTO book_grade_select(int book_id) {
		return sqlSession.selectOne("mybatis.bookMapper.book_grade_select", book_id);
	}
	
	public int setGradeBookMember1(String member_id,int book_id,String graded) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		map.put("book_id", book_id);
		map.put("graded", graded);
		return sqlSession.insert("mybatis.bookMapper.set_grade_book_member1",map);
	}
	public int setGradeBookMember2(int book_id,String graded) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_id", book_id);
		map.put("graded",graded);
		return sqlSession.insert("mybatis.bookMapper.set_grade_book_member2",map);
	}
	
	public GradeBookMemberDTO getGradeBookMember(String member_id,int book_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("book_id", book_id);
		map.put("member_id", member_id);
		return sqlSession.selectOne("mybatis.bookMapper.get_grade_book_member",map);
	}
}
