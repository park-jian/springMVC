package kr.or.iei.member.model.dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;


@Repository("memberDAO")
public class MemberDAO {

	public ArrayList<Member> selectLoginMember(JdbcTemplate jdbc, Member m) {
		String query = "SELECT * FROM MEMBER WHERE USERID=? AND USERPWD=? AND END_YN='N'";
		Object [] params = {m.getUserId(), m.getUserPwd()};
		ArrayList<Member> list = (ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper());
		
		return list;
		
	}

	public int insertSignupMember(JdbcTemplate jdbc, Member m) {
		String query = "INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,DEFAULT,'N')";
		int result = jdbc.update(query,
								m.getUserId(),
								m.getUserPwd(),
								m.getUserName(),
								m.getAge(),
								m.getAddress());
		return result;
	}

	public ArrayList<Member> selectIdCheck(JdbcTemplate jdbc, String userId) {
		String query = "SELECT * FROM MEMBER WHERE USERID=?";
		Object [] params = {userId};
		ArrayList<Member> list = (ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper());
		
		return list;
		
	}

	public ArrayList<Member> selectRenewalMember(JdbcTemplate jdbc, String userId) {
		String query = "SELECT * FROM MEMBER WHERE USERID=?";
		Object [] params = {userId};
		ArrayList<Member> list = (ArrayList<Member>)jdbc.query(query, params, new MemberRowMapper());
		
		return list;
		
	}

	public int updateMember(JdbcTemplate jdbc, Member sessionMember) {
		String query = "UPDATE MEMBER SET USERPWD=?, AGE=?, ADDRESS=? WHERE USERNO=?";
		int result=jdbc.update(query,
							sessionMember.getUserPwd(),
							sessionMember.getAge(),
							sessionMember.getAddress(),
							sessionMember.getUserNo());
		return result;
		
	}

	public int updateWithDrawMember(JdbcTemplate jdbc, int userNo) {
		String query = "UPDATE MEMBER SET END_YN='Y' WHERE USERNO=?";
		
		int result=jdbc.update(query, userNo);
		return result;
		
	}

	public ArrayList<Member> selectMemberAllList(JdbcTemplate jdbc) {
		String query="SELECT * FROM MEMBER";
		ArrayList<Member> list=(ArrayList<Member>)jdbc.query(query, new MemberRowMapper());
		return list;
		
	}

	public int updateEndYNStateChange(JdbcTemplate jdbc, int userNo, char endYN) {
		String query="UPDATE MEMBER SET END_YN=? WHERE USERNO=?";
		int result=jdbc.update(query,Character.toString(endYN),userNo);
		return result;
		
	}

}
