package kr.or.iei.member.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.or.iei.member.model.vo.Member;

public class MemberRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		Member m = new Member();
		m.setUserNo(rset.getInt("USERNO"));
		m.setUserId(rset.getString("USERID"));
		m.setUserPwd(rset.getString("USERPWD"));
		m.setUserName(rset.getString("USERNAME"));
		m.setAge(rset.getInt("AGE"));
		m.setAddress(rset.getString("ADDRESS"));
		m.setEnrollDate(rset.getDate("ENROLLDATE"));
		m.setEndYN(rset.getString("END_YN").charAt(0));
		return m;
	}

}
