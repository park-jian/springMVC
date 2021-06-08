package kr.or.iei.bean.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.iei.person.model.vo.Person;
import kr.or.iei.person.model.vo.PersonMgr;
import kr.or.iei.tv.model.vo.TV;
import kr.or.iei.tv.model.vo.TVmgr;

/**
 * Servlet implementation class BeanTestServlet
 */
public class BeanTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeanTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractApplicationContext context=new GenericXmlApplicationContext("/applicationContext.xml");
		
		//import만 사용된 xml을 불러와서 객체를 가져오도록 하겠음
		
		Person p = context.getBean("pMgr",PersonMgr.class).getP();
		
		System.out.println("이름: " + p.getName());
		System.out.println("나이: " + p.getAge());
		System.out.println("주소: " + p.getAddr());
		
		TV tv = context.getBean("tvMgr", TVmgr.class).getTv();
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
