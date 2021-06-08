package kr.or.kh.test.ano;

import org.springframework.stereotype.Component;

@Component("sonySpeaker")
public class SonySpeaker implements Speaker{
	public SonySpeaker(){
		System.out.println("소니 스피커 객체 생성 완료");
	}
	
	public void powerOn(){
		System.out.println("Sony Speaker==> 전원이 켜졌습니다.");
	}
	
	public void powerOff(){
		System.out.println("Sony Speaker==> 전원이 꺼졌습니다.");
	}
}
