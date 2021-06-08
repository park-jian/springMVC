package kr.or.iei.file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.member.model.vo.Member;

@Controller
public class FileController {
//FileService가 있다고 하면 FileService도 어노테이션(@Service)으로 생성해주어야 하고,
	//Controller에서 @Autowired를 통해서 의존성 주입을 해주어야 함(MemberController와 동일)
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/fileUploadPage.do")
	public String fileUploadPage(){
		System.out.println("[/fileUploadPage.do] 정상 호출");
		return "file/fileUploadPage";
		//ViewResolver에 의해서 /WEB-INF/views/file/fileUploadPage.jsp로 만들어짐
	}
	
	@RequestMapping(value="/fileUpload.do")
	public String fileUpload(HttpServletRequest request) throws IOException{ // HttpServletRequest request는 아래 request씌이는게 있어서 넣어줌.
		System.out.println("[/fileUpload.do] 정상 호출");
		
		//파일이 업로드 되는 경로
		String uploadPath="/resources/file/";
		
		//최대 파일 사이즈를 정하기 위한 값
		int uploadFileSizeLimit = 10*1024*1024;//최대 10MB까지 업로드 가능
		
		//파일이름 인코딩 값
		String encType="UTF-8";
		
		//현재 프로젝트에 대한 정보를 가지고 있는 객체 (바로 아래는 기존 Servlet코드임)
		//ServletContext context=request.getServletContext();  <-getServletContext();은 servlet에서 만든 request만 쓸 수 있음
		// 여기서는 지금 쓸 수 없어서 맨 위에다가 아래 2줄 코드를 작성해줘야 한다.
		//@Autowired
		//ServletContext context;
		
		//ServletContext를 이용하여 실제 경로를 가져와야 함
		String realUploadPath = context.getRealPath(uploadPath);
		System.out.println("완성된 실제 업로드 절대 경로: " + realUploadPath);
		//실행해보면 아래처럼 경로가 나옴.
		//완성된 실제 업로드 절대 경로: C:\java_all\springWorkSpace\05_SpringMVC(spring)\src\main\webapp\resources\file\

		MultipartRequest multi = new MultipartRequest(request,       //MultipartRequest은 servlet용으로 import해.
									realUploadPath,            //try-catch 나오니 throws IOException해줘
									uploadFileSizeLimit,
									encType,
									new DefaultFileRenamePolicy());
				
		// 주석된 것을 해보면 기존 jsp&servlet에 했을때 로직임. 이걸 해보면
		 //위 코드까지 진행하면 파일 업로드 까지는 문제 없이 처리된 것
     
           //이제는 DB Table안에 저장할 정보를 추출하는 작업
           
           // 파일 이름 가져오기 
           String originalFileName = multi.getFilesystemName("file");
           
           // 업로드 유저 ID값 가져오기 (session에서 가져와야 함)
           HttpSession session = request.getSession();
           Member m = (Member)session.getAttribute("member");
           String fileUser = m.getUserId();  //userId가 업로드 유저 (fileUser)
           
           
           
           // 업로드 시간 만들기
           // 시간 포맷 및 현재 시간값 가져오기
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); //포맷 만들기
           long currentTime = Calendar.getInstance().getTimeInMillis(); //시간값 가져오기
           Timestamp uploadTime = Timestamp.valueOf(formatter.format(currentTime));

           // 원본 파일의 이름바꾸는 작업 (바꾸는 파일의 이름은 시간값_kh)
           // 기존에 만들어놓은 currentTime 변수를 통해서 파일 이름 수정하기 (File 객체 활용)
           
           //File 객체는 경로를 통해서 해당 파일을 연결하는 객체
           File file = new File(realUploadPath+"\\"+originalFileName);
           
           //File객체가 가지고 있능 renameTo 메소드를 통해서 파일의 이름을 바꿀 수 있음
           file.renameTo(new File(realUploadPath+"\\"+currentTime+"_kh"));
           String changedFileName = currentTime+"_kh";
           
           
           //File 객체를 통해 파일이름이 변경되면 새롭게 연결하는 파일 객체가 필요함
           File reNameFile = new File(realUploadPath+"\\"+changedFileName);
           String filePath = reNameFile.getPath();
           
           
           // 해당  업로드된 file의 사이즈
           long fileSize = reNameFile.length(); 
           
           
           //여기까지가 DB에 들어갈 값 셋팅 / 아래는 확인
           System.out.println("파일 이름 (원본) : " + originalFileName);
           System.out.println("파일 이름 (변경) : " + changedFileName);
           System.out.println("파일 경로 : " + filePath);
           System.out.println("파일 사이즈 : " + fileSize);
           System.out.println("업로드 유저 : " + fileUser);
           System.out.println("업로드 시간 : " + uploadTime);
           		
	
		
		
		return null;
	}
}
