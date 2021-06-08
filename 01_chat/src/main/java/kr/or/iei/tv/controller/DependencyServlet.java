package kr.or.iei.tv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.iei.tv.model.vo.BeanFactory;
import kr.or.iei.tv.model.vo.LgTV;
import kr.or.iei.tv.model.vo.SamSungTV;
import kr.or.iei.tv.model.vo.TV;
import kr.or.iei.tv.model.vo.TVmgr;

/**
 * Servlet implementation class DependencyServlet
 */
public class DependencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependencyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//TV tv = (TV)BeanFactory.getBean(request.getParameter("bean"));
	//TV tv = (TV)BeanFactory.getBean("lg");  <-- lg인 경우
		
		AbstractApplicationContext cntx = new GenericXmlApplicationContext("/sampleContext.xml");
		
		TV tv =(cntx.getBean("tvMgr",TVmgr.class)).getTv();
		
		//위의 코드 한줄은 아래 2줄 코드랑 동일.
		//TVmgr tvmgr = (cntx.getBean("tvMgr",TVmgr.class));
		//TV tv = tvmgr.getTV();     
		
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
