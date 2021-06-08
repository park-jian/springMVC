package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.iei.member.model.vo.Member;

public class MemberDAO {

	public Member loginMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member member = null;
		
		String query = "select * from member where USERID = ? and USERPWD = ?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				member = new Member();
				member.setUserNo(rset.getInt("USERNO"));
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setAge(rset.getInt("AGE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setEnrollDate(rset.getDate("ENROLLDATE"));
				member.setEndYN(rset.getString("END_YN").charAt(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	public int memberInsert(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query="INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,DEFAULT,'N')";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getAddress());
			
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
