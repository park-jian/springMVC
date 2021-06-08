package kr.or.iei.bean.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Servlet implementation class BeanTest5Servlet
 */
public class BeanTest5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeanTest5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		CollectionMapBean bean= context.getBean("mapBean",CollectionMapBean.class);
		
		Map<String, String> map = bean.getDataMap();
		
		//자바에서도 맵에 있는 데이터를 출력할때에는 key값을 추출해서 iterator화 시켜야 함.
		Set<String> keys = map.keySet();	//keyset으로 key값을 가져옴.
		Iterator<String> iter=keys.iterator();    //java.util.Iterator
		
		while(iter.hasNext()){
			String key = iter.next();
			System.out.println("키값:" + key + "/데이터값 : " + map.get(key));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
