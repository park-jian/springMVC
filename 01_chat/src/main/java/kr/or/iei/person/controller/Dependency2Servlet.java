package kr.or.iei.person.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.iei.person.model.vo.Person;
import kr.or.iei.person.model.vo.PersonMgr;

/**
 * Servlet implementation class Dependency2Servlet
 */
public class Dependency2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dependency2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractApplicationContext context = new GenericXmlApplicationContext("/personContext.xml");
		PersonMgr pMgr = context.getBean("pMgr", PersonMgr.class); //xml을 통해서 생성한 PersonMgr 객체를 꺼내오는 작업
		
		Person p = pMgr.getP(); //PersonMgr이 가지고 있는 person객체를 꺼내옴
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("이름: " + p.getName() + "<br>");
		out.println("나이: " + p.getAge() + "<br>");
		out.println("주소: " + p.getAddr() + "<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
