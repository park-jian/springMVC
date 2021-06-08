package kr.or.iei.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.or.iei.member.model.dao.MemberDAO;
import kr.or.iei.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	@Qualifier(value="memberDAO")
	private MemberDAO mDAO;
	
	
	@Autowired
	@Qualifier(value="jdbcTemplate")
	private JdbcTemplate jdbc;
	
	//MemberService의 생성자 메소드
	/*public MemberServiceImpl(){
		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
	}
	*/
	@Override
	public Member selectLoginMember(Member m) {
		//잘 불러 왔는지 확인용 
		System.out.println("[MemberServiceImpl] selectLoginMember서비스 메소드 호출");
		ArrayList<Member> list = mDAO.selectLoginMember(jdbc, m);
		
		//강제로 Exception이 발생하도록 하는 코드
		//System.out.println(list.get(1));      //0밖에 없는데 1을 호출
	
		if(!list.isEmpty()) return list.get(0); //로그인이 정상 처리 되었을때(로그인 성공)
		else return null;      //로그인이 정상적으로 처리 되지 않았을때(로그인 실패)
		
	}

	public int insertSignupMember(Member m) {
		int result = mDAO.insertSignupMember(jdbc,m);
		return result;
	}

	public ArrayList<Member> selectIdCheck(String userId) {
		ArrayList<Member> list = mDAO.selectIdCheck(jdbc,userId);
		/*이건 해도 그만 안해도 그만
		 * if(!list.isEmpty()) return list.get(0);
		else return null;
		 */
		return list;
	}

	public Member selectRenewalMember(String userId) {
		ArrayList<Member> list = mDAO.selectRenewalMember(jdbc,userId);
		if(!list.isEmpty()) {return list.get(0);}
		else{return null;}
	}

	public int updateMember(Member sessionMember) {
		int result= mDAO.updateMember(jdbc,sessionMember);
		return result;
	}

	public int updateWithDrawMember(int userNo) {
		int result = mDAO.updateWithDrawMember(jdbc,userNo);
		return result;
		
	}

	public ArrayList<Member> selectMemberAllList() {
		ArrayList<Member> list=mDAO.selectMemberAllList(jdbc);
		return list;
	}

	public int updateEndYNStateChange(int userNo, char endYN) {
		int result = mDAO.updateEndYNStateChange(jdbc,userNo,endYN);
		return result;
		
	}

}
