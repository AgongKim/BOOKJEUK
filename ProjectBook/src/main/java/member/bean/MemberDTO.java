package member.bean;

public class MemberDTO {
	private String member_nick;
	private String member_id;
	private String member_pw;
	private String member_genre;
	private String member_photo;
	private String member_phone;
	private int member_goal;
	private String member_email;
	
	
	
	
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getMember_goal() {
		return member_goal;
	}
	public void setMember_goal(int member_goal) {
		this.member_goal = member_goal;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_genre() {
		return member_genre;
	}
	public void setMember_genre(String member_genre) {
		this.member_genre = member_genre;
	}
	public String getMember_photo() {
		return member_photo;
	}
	public void setMember_photo(String member_photo) {
		this.member_photo = member_photo;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	
}
