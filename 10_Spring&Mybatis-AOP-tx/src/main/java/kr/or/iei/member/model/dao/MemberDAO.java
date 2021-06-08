package kr.or.iei.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;


@Repository("memberDAO")
public class MemberDAO {

	public Member selectLoginMember(SqlSessionTemplate sqlSession, Member m) {
		Member member = sqlSession.selectOne("member.selectLoginMember",m);  //member.selectLoginMember <-- mapper에서 찾아라.
		
		return member;
		
	}

	public int insertSignupMember(SqlSessionTemplate sqlSession, Member m) {
		
		int result = sqlSession.insert("member.insertSignupMember",m);
		return result;
	}

	public ArrayList<Member> selectIdCheck(SqlSessionTemplate sqlSession, String userId) {
		List list = sqlSession.selectList("member.selectIdCheck",userId);
		
		return (ArrayList<Member>)list;
		
	}

	public Member selectRenewalMember(SqlSessionTemplate sqlSession, String userId) {
		Member m = sqlSession.selectOne("member.selectRenewalMember",userId);
		return m;
		
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member sessionMember) {
		int result = sqlSession.update("member.updateMember",sessionMember);
		return result;
		
	}

	public int updateWithDrawMember(SqlSessionTemplate sqlSession, int userNo) {
		int result =sqlSession.update("member.updateWithDrawMember",userNo);
		return result;
		
	}

	public ArrayList<Member> selectMemberAllList(SqlSessionTemplate sqlSession) {
		//java.util.List import
		List list = sqlSession.selectList("member.selectMemberAllList");
		return (ArrayList<Member>)list;
		
	}

	public int updateEndYNStateChange(SqlSessionTemplate sqlSession, int userNo, char endYN) {//mybatis는 최대 한개까지 밖에 못받아 객체에 담아서 보내야 함
		
		//Controller에서 Member객체에 담아서 보내주는게 가장 좋음(지금은 넘어온것 그대로 사용)
		Member m = new Member();
		m.setUserNo(userNo);
		m.setEndYN(endYN);

		int result = sqlSession.update("member.updateEndYNStateChange",m);
		return result;
		
	}

}
