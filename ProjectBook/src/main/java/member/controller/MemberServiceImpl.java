package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.bean.BookandGrade;
import member.bean.FavoriteDTO;
import member.bean.MemberDTO;
import member.bean.RecordDTO;
import member.dao.MemberDAO;
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public MemberDTO getMember(String member_id) {
		return memberDAO.getMember(member_id);
	}

	@Override
	public List<RecordDTO> getMonthData(String member_id, int month,int lastDay,int year) {
		return memberDAO.getMonthData(member_id, month,lastDay,year);
	}

	@Override
	public int setGoal(int goal, String member_id) {
		return memberDAO.setGoal(goal, member_id);
	}

	@Override
	public List<RecordDTO> getBookRecord(String member_id) {
		return memberDAO.getBookRecord(member_id);
	}

	@Override
	public List<FavoriteDTO> getFavorite(String member_id) {
		return memberDAO.getFavorite(member_id);
	}

	@Override
	public int addMember(MemberDTO memberDTO) {
		return memberDAO.addMember(memberDTO);
	}

	@Override
	public MemberDTO chkLogin(String member_id, String member_pw) {
		return memberDAO.chkLogin(member_id, member_pw);
	}

	@Override
	public int endReading(String member_id, int book_id) {
		return memberDAO.endReading(member_id, book_id);
	}

	@Override
	public int startReading(String member_id, int book_id, String book_name) {
		return memberDAO.startReading(member_id, book_id, book_name);
	}

	@Override
	public RecordDTO isReading(String member_id, int book_id) {
		return memberDAO.isReading(member_id, book_id);
	}

	@Override
	public int updateReading(String start_time, String end_time, int book_id,String member_id) {
		return memberDAO.updateReading(start_time, end_time, book_id,member_id);
	}

	@Override
	public int setFavorite(String member_id, int book_id) {
		return memberDAO.setFavorite(member_id, book_id);
	}

	@Override
	public int deleteFavorite(String member_id, int book_id) {
		return memberDAO.deleteFavorite(member_id, book_id);
	}

	@Override
	public FavoriteDTO isFavorite(String member_id, int book_id) {
		return memberDAO.isFavorite(member_id, book_id);
	}

	@Override
	public List<BookandGrade> getBookShelf(String member_id) {
		return memberDAO.getBookShelf(member_id);
	}

	@Override
	public int deleteMember(String member_id) {
		return memberDAO.deleteMember(member_id);
	}

	
	@Override
	public String chkId(String member_id) {
		return memberDAO.chkId(member_id);
	}
	
	@Override
	public String memberFindId(String member_email) {
		return memberDAO.memberFindId(member_email);
	}
	
	@Override
	public MemberDTO memberFindPw(String member_id) {
		return memberDAO.memberFindPw(member_id);
	}
}
