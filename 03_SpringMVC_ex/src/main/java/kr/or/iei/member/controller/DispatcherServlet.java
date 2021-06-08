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
    	 handlerMapping = new HandlerMapping();
    	 viewResolver = new ViewResolver();
    	 viewResolver.setPrefix("/WEB-INF/views/");
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

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1. 클라이언트 요청 path정보를 추출
		String path = request.getRequestURI();
		System.out.println("[DispatcherServlet]사용자가 요청한 Controller : " + path);
		//2. HandlerMapping을 통해 path에 해당하는 Controller를 검색하고 찾아옴
		Controller ctrl = handlerMapping.getController(path);
		System.out.println("[DispatcherServlet]Handlermapping이 찾아준 컨트롤러 : " + ctrl);
		//3. 검색된 Controller를 실행(실행시 request와 response를 보내줌)
		String viewName = ctrl.handleRequest(request, response);
		System.out.println("[DispatcherServlet] Controller 요청페이지 : " + viewName);
		//4. ViewResolver를 통해 viewName에 해당하는 화면 검색
		
		String view = null;
		if(viewName.equals("index")){
			view = "/" + viewName + ".jsp";
		}else if(!viewName.contains(".do")){
			view = viewResolver.getView(viewName);
		}else{
			view = viewName;
		}
		
		System.out.println("[DispatcherServlet] Controller요청 페이지 완성 경로 : " + view);
		//5. viewResolver를 통해 검색된 페이지로 이동
		request.getRequestDispatcher(view).forward(request, response);
		
	}
}
