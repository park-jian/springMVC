package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

import kr.or.iei.member.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> loginMember(JdbcTemplate jdbc, Member m) {
		String query = "SELECT * FROM MEMBER WHERE USERID=? AND USERPWD=? AND END_YN='N'";
		System.out.println(m.getUserId() + "/" + m.getUserPwd());
		Object [] params = {m.getUserId(), m.getUserPwd()}; //위치홀더 순서에 맞춰 쓰기
		
		//SELECT 구문은 query 메소드/ INSERT, UPDATE, DELETE 구문은 update 메소드
		//SELECT 구문은 ResultSet에 따라서 결과를 가져와야 하기 때문에 RowMapper라는 클래스를 정의해놓고 사용해야함
		ArrayList<Member> list=(ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper()); //무조건 list로 넘어옴
		
		System.out.println("DAO list" + list);
		
		return list;
	}

	public int memberInsert(JdbcTemplate jdbc, Member m) {
		
		
		String query="insert into member values(member_seq.nextval,?,?,?,?,?,default,'N')";
		
		int result = jdbc.update(query, m.getUserId(),
						m.getUserPwd(),
						m.getUserName(),
						m.getAge(),
						m.getAddress());
			
		
		return result;
	}

	public ArrayList<Member> renewalMember(JdbcTemplate jdbc, String userId) {
		String query="SELECT * FROM MEMBER WHERE USERID=? AND END_YN='N'";
		Object [] params = {userId}; 
		ArrayList<Member> list=(ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper()); //무조건 list로 넘어옴
		
		return list;
	}

	public int memberUpdate(JdbcTemplate jdbc, Member m) {
		String query = "UPDATE MEMBER SET USERPWD=?, AGE=?, ADDRESS=? WHERE USERNO=?";
		int result = jdbc.update(query,
				 	m.getUserPwd(),
				 	m.getAge(),
				 	m.getAddress(),
				 	m.getUserNo());
		
		return result;
				
		
		
	}

}
