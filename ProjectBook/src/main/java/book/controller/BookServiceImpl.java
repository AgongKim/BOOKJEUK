package book.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.bean.BookDTO;
import book.bean.Book_gradeDTO;
import book.bean.GradeBookMemberDTO;
import book.dao.BookDAO;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDAO bookDAO;
	
	public int bookInsert(BookDTO bookDTO) {
		return bookDAO.bookInsert(bookDTO);
	}

	@Override
	public int getLastBookId() {
		return bookDAO.getLastBookId();
	}

	@Override
	public List<BookDTO> topIndexList(int startNum, int endNum) {
		return bookDAO.topIndexList(startNum, endNum);
	}

	@Override
	public String getBook_img(int book_id) {
		return bookDAO.getBook_img(book_id);
	}
	
	@Override
	public List<BookDTO> book_searchlist(String book_name) {
		return bookDAO.book_searchlist(book_name);
	}

	@Override
	public List<HashMap<String, Object>> book_selectList(int book_id) {
		return bookDAO.book_selectList(book_id);
	}
	
	@Override
	public int book_grade_insert(Book_gradeDTO book_gradeDTO) {
		return bookDAO.book_grade_insert(book_gradeDTO);
	}
	@Override
	public Book_gradeDTO book_grade_select(int book_id) {
		return bookDAO.book_grade_select(book_id);
	}

	@Override
	public int setGradeBookMember1(String member_id, int book_id, String graded) {
		return bookDAO.setGradeBookMember1(member_id, book_id, graded);
	}

	@Override
	public int setGradeBookMember2(int book_id, String graded) {
		return bookDAO.setGradeBookMember2(book_id, graded);
	}

	@Override
	public GradeBookMemberDTO getGradeBookMember(String member_id, int book_id) {
		return bookDAO.getGradeBookMember(member_id, book_id);
	}
}
