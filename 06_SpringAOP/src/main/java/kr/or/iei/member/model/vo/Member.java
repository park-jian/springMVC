package kr.or.iei.member.model.vo;

import java.sql.Date;

public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private int age;
	private String address;
	private Date enrollDate;
	private char endYN;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int userNo, String userId, String userPwd, String userName, int age, String address, Date enrollDate,
			char endYN) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.enrollDate = enrollDate;
		this.endYN = endYN;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public char getEndYN() {
		return endYN;
	}
	public void setEndYN(char endYN) {
		this.endYN = endYN;
	}
	
	
}
