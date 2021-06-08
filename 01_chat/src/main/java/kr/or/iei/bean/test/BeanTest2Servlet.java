package kr.or.iei.bean.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Servlet implementation class BeanTest2Servlet
 */
public class BeanTest2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeanTest2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		TestData td = context.getBean("data",TestData.class);
		
		System.out.println("초기값 : " + td.getData());  //초기값 100출력
		
		td.setData(td.getData()+1); //td에 101이 셋팅
		td = context.getBean("data", TestData.class); //싱글톤 방식에 의해 객체가 매번 새롭게 생성되는게 아니라 기존 객체를 유지함.
		System.out.println("두번째 : " + td.getData());  //따라서  101출력
		
		td.setData(td.getData()+1); //td에 101이 셋팅
		td = context.getBean("data", TestData.class); //싱글톤 방식에 의해 객체가 매번 새롭게 생성되는게 아니라 기존 객체를 유지함.
		System.out.println("세번째 : " + td.getData());  //따라서  102출력
		
		td.setData(td.getData()+1); //td에 101이 셋팅
		td = context.getBean("data", TestData.class); //싱글톤 방식에 의해 객체가 매번 새롭게 생성되는게 아니라 기존 객체를 유지함.
		System.out.println("네번째 : " + td.getData());  //따라서  103출력
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
