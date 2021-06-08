package kr.or.iei.member.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier(value="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
		Member member = mDAO.selectLoginMember(sqlSession, m);
		return member;
	}

	public int insertSignupMember(Member m) {
		int result = mDAO.insertSignupMember(sqlSession,m); //이 코드에 의해 DAO통해 db에 고객 정보 등록후 돌아와 아래 소스코드 수행. 이 상황에서는 문제있는 코드라서 커밋되면 안되는데
		                                             //커밋된뒤 돌아와 아래 코드 수행하므로 문제가 발생. 스프링은 무조건 트랜잭션 자동제어해줘 하나만 커밋 되버림.
		// mDAO.insertSignupMember(sqlSession,m);  //고의로 에러 발생 유도하는 소스코드
		 
		 
		 
		 
		/* 트랜잭션은 aop에서 자동관리 하도록 처리할것임
		 * 스프링에서 자체 수동커밋을 허용하지 않아서 사용 불가
		if(result>0){
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}*/
		return result;
	}

	public ArrayList<Member> selectIdCheck(String userId) {
		ArrayList<Member> list = mDAO.selectIdCheck(sqlSession,userId);
		return list;
	}

	public Member selectRenewalMember(String userId) {
		Member m = mDAO.selectRenewalMember(sqlSession,userId);
		return m;
	}

	public int updateMember(Member sessionMember) {
		int result= mDAO.updateMember(sqlSession,sessionMember);
		return result;
	}

	public int updateWithDrawMember(int userNo) {
		int result = mDAO.updateWithDrawMember(sqlSession,userNo);
		return result;
		
	}

	public ArrayList<Member> selectMemberAllList() {
		ArrayList<Member> list=mDAO.selectMemberAllList(sqlSession);
		return list;
	}

	public int updateEndYNStateChange(int userNo, char endYN) {
		int result = mDAO.updateEndYNStateChange(sqlSession,userNo,endYN);
		return result;
		
	}

}
