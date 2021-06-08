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
JdbcTemplate jdbc;
	public MemberServiceImpl(){
		
		//생성자를 이용해서 Service Class가 만들어질때 바로 xml을 읽어서 jdbc객체를 가지고 있는 상태
		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		jdbc = context.getBean("jdbcTemplate",JdbcTemplate.class);
		
		
	}
	@Override
	public Member loginMember(Member m) {
	//	Connection conn = getConnection();
		
		
		System.out.println("[MemberService] Connection값 : " + jdbc);
		
		ArrayList<Member> list= mDAO.loginMember(jdbc, m);
		Member member = null;
		if(!list.isEmpty()){
			//list가 비워져 있지 않다면( 비워져 있지 않으면 로그인 정보가 맞았다라는 뜻)
			
			 member = list.get(0);
		}else{
			//list가 비워져 있다면 ( 비워져 있으면 로그인 정보가 맞지 않았다라는 뜻)
			
			 member=null;
		}
		return member;
	}
	
	public int memberInsert(Member m) {
		
		
		
		int result= mDAO.memberInsert(jdbc,m);
		//여기는 autoCommit됨.
		return result;
	}

	public Member renewalMember(String userId) {
		
		ArrayList<Member> list=mDAO.renewalMember(jdbc,userId);
		Member member = null;
		if(!list.isEmpty()){
			
			 member = list.get(0);
		}
		return member;
		
		
	}
	public int memberUpdate(Member m) {
		int result = mDAO.memberUpdate(jdbc,m);
		return result;
	}

}
