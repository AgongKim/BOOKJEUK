package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.bean.BookandGrade;
import member.bean.FavoriteDTO;
import member.bean.MemberDTO;
import member.bean.RecordDTO;

public interface MemberService {
	public MemberDTO getMember(String member_id);
	public List<RecordDTO> getMonthData(String member_id,int month,int lastDay,int year);
	public int setGoal(int goal,String member_id);
	public List<RecordDTO> getBookRecord(String member_id);
	public List<FavoriteDTO> getFavorite(String member_id);
	public int addMember(MemberDTO memberDTO);
	public MemberDTO chkLogin(String member_id,String member_pw);
	public int endReading(String member_id,int book_id);
	public int startReading(String member_id,int book_id,String book_name);
	public RecordDTO isReading(String member_id,int book_id);
	
	public int updateReading(String start_time,String end_time,int book_id,String member_id);
	public int setFavorite(String member_id,int book_id);
	public int deleteFavorite(String member_id,int book_id);
	public FavoriteDTO isFavorite(String member_id,int book_id);
	public List<BookandGrade> getBookShelf(String member_id);
	public int deleteMember(String member_id);
	
	public String chkId(String member_id);
	public String memberFindId(String member_email);
	public MemberDTO memberFindPw(String member_id);
}
