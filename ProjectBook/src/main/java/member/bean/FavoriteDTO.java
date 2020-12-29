package member.bean;

import book.bean.BookDTO;

public class FavoriteDTO extends BookDTO{
	String member_id;

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	@Override
	public String getBook_img() {
		// TODO Auto-generated method stub
		return super.getBook_img();
	}

	@Override
	public void setBook_img(String book_img) {
		// TODO Auto-generated method stub
		super.setBook_img(book_img);
	}

	@Override
	public int getBook_id() {
		// TODO Auto-generated method stub
		return super.getBook_id();
	}

	@Override
	public void setBook_id(int book_id) {
		// TODO Auto-generated method stub
		super.setBook_id(book_id);
	}

	@Override
	public String getBook_name() {
		// TODO Auto-generated method stub
		return super.getBook_name();
	}

	@Override
	public void setBook_name(String book_name) {
		// TODO Auto-generated method stub
		super.setBook_name(book_name);
	}

	@Override
	public String getWriter() {
		// TODO Auto-generated method stub
		return super.getWriter();
	}

	@Override
	public void setWriter(String writer) {
		// TODO Auto-generated method stub
		super.setWriter(writer);
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return super.getPublisher();
	}

	@Override
	public void setPublisher(String publisher) {
		// TODO Auto-generated method stub
		super.setPublisher(publisher);
	}

	@Override
	public float getGrade() {
		// TODO Auto-generated method stub
		return super.getGrade();
	}

	@Override
	public void setGrade(float grade) {
		// TODO Auto-generated method stub
		super.setGrade(grade);
	}

	@Override
	public String getBook_summary() {
		// TODO Auto-generated method stub
		return super.getBook_summary();
	}

	@Override
	public void setBook_summary(String book_summary) {
		// TODO Auto-generated method stub
		super.setBook_summary(book_summary);
	}

	@Override
	public String getBook_link() {
		// TODO Auto-generated method stub
		return super.getBook_link();
	}

	@Override
	public void setBook_link(String book_link) {
		// TODO Auto-generated method stub
		super.setBook_link(book_link);
	}
	
	
}
