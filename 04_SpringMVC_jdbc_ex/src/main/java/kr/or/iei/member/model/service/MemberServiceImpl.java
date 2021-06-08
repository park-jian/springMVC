package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import kr.or.iei.member.model.dao.MemberDAO;
import kr.or.iei.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{
	MemberDAO mDAO = new MemberDAO();

	@Override
	public Member loginMember(Member m) {
		//Connection conn = getConnection();
		
		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		
		System.out.println("[MemberService] Connection값 : " + jdbc);
		
		ArrayList<Member> list = mDAO.loginMember(jdbc, m);
		Member member = null;
		if(!list.isEmpty()){
			member=list.get(0);
		}else{
			member=null;
		}
		
		return member;
	}
	
	public int memberInsert(Member m) {

		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		
		int result = mDAO.memberInsert(jdbc, m);
		return result;
	}

}
