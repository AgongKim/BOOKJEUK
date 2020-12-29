package book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.bean.BookDTO;
import book.bean.Book_gradeDTO;
import book.bean.GradeBookMemberDTO;

public interface BookService {
	public int bookInsert(BookDTO bookDTO);
	public int getLastBookId();
	public List<BookDTO> topIndexList(int startNum,int endNum);
	public String getBook_img(int book_id);
	public List<BookDTO> book_searchlist(String book_name);
	public List<HashMap<String, Object>> book_selectList(int book_id);

	public int book_grade_insert(Book_gradeDTO book_gradeDTO);
	public Book_gradeDTO book_grade_select(int book_id);
	
	public int setGradeBookMember1(String member_id,int book_id,String graded);
	public int setGradeBookMember2(int book_id,String graded);
	public GradeBookMemberDTO getGradeBookMember(String member_id,int book_id);
}
