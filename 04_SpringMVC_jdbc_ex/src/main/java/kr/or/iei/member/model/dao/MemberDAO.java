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
		Object [] params = {m.getUserId(), m.getUserPwd()};
		
		ArrayList<Member> list = (ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper());
		return list;
	}

	public int memberInsert(JdbcTemplate jdbc, Member m) {
		
		String query="INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,DEFAULT,'N')";
		
		int result = jdbc.update(query, m.getUserId(), m.getUserPwd(), m.getUserName(), m.getAge(), m.getAddress());
		
		
		return result;
	}

}
