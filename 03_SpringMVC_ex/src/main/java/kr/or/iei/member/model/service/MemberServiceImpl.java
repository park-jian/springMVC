package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import kr.or.iei.member.model.dao.MemberDAO;
import kr.or.iei.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{
MemberDAO mDAO = new MemberDAO();

	@Override
	public Member loginMember(Member m) {
		Connection conn = getConnection();
		System.out.println("[MemberService] Connection값 : " + conn);
		
		Member member = mDAO.loginMember(conn, m);
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	public Connection getConnection(){
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","spring","spring");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public int memberInsert(Member m) {
		Connection conn = getConnection();
		int result= mDAO.memberInsert(conn,m);
		if(result>0){
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
