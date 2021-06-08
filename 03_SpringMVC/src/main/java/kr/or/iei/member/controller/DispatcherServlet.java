package kr.or.iei.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private HandlerMapping handlerMapping;
     private ViewResolver viewResolver;
     
     public void init() throws ServletException{
    	 //init 메소드는 Servlet 객체가 호출되어 객체가 생성될때 가장 먼저 호출되는 메소드 (생성자 다음으로)
    	 
    	 handlerMapping = new HandlerMapping();
    	 viewResolver = new ViewResolver();
    	 viewResolver.setPrefix("/member/");
    	 viewResolver.setSuffix(".jsp");
    	 
    	 
     }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		//doGet을 호출하거나 doPost를 호출하더라도 무조건 process메소드를 호출하도록 로직 수정
		
		
		//1. 클라이언트의 요청 path 정보를 추출
		String path = request.getRequestURI();
		System.out.println("[DispatcherServlet]사용자가 요청한 Controller : " + path);
		//출력하면 /login.do가 나옴
		
		
		//2. HandlerMapping을 통해 path에 해당하는 Controller를 검색하고 찾아옴
		Controller ctrl = handlerMapping.getController(path);
		
		//찾으면 해당 객체의 hashCode값이 나오고, 없으면 null이 돌아옴
		System.out.println("[DispatcherServlet] HandlerMapping이 찾아준 컨트롤러: " + ctrl);
		
		
		//3. 검색된 Controller를 실행(실행시 request와 response를 보내줌)
		String viewName = ctrl.handleRequest(request, response); //controller들이 기본적으로 handleRequest를 가지고 있음(우리가 임의로 만들어줌)
		
		System.out.println("[DispatcherServlet] Controller 요청페이지: "+ viewName); //확인코드
		
		//4. ViewResolver를 통해 viewName에 해당하는 화면을 검색
		String view = null;
		if(viewName.equals("index")){
			view = "/" + viewName + ".jsp"; 
		}
		else if(!viewName.contains(".do")){ //viewName은 loginSuccess 또는 loginFail임.
			view = viewResolver.getView(viewName); //.do가 없으면 viewResolver를 통해 /member/loginSuccess.jsp를 만들어라.
		}else{  //.do로 되어있는 서블릿 이면 resolver야 관여하지마 ..경로 붙이지마..
			view = viewName;
		}
		
		System.out.println("[DispatcherServlet] Controller요청 페이지 완성 경로: " + view);// 확인코드 

		
		//5. viewResolver를 통해 검색된 페이지로 이동
		request.getRequestDispatcher(view).forward(request, response); //체이닝 기법
		
	}

}
