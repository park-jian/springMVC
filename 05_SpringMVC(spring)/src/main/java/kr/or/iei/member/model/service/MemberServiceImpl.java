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
		
		ArrayList<Member> list = mDAO.selectLoginMember(jdbc, m);
		if(!list.isEmpty()) return list.get(0);
		else return null;
		
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
