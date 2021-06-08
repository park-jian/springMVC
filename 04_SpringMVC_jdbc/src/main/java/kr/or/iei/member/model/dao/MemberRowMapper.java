package kr.or.iei.member.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.or.iei.member.model.vo.Member;

/*RowMapper Class의 의미는? 기존DAO에서 ResultSet이 있을경우 데이터를 가져오는 작업을 했었는데, 그 작업이 정의된 Class라고 보면 됨
 * 예) m.setUserId(rset.getString("USERID"));
 * 사용자 RowMapper Class를 정의하려면 표준 RowMapper Class Implements 해야 함
 */

public class MemberRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		Member m =new Member();
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
