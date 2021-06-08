package kr.or.iei.tv.model.vo;

public class SamSungTV implements TV{
	public void powerOn(){
		System.out.println("SamSungTV ---- 전원을 켠다.");
	}
	public void powerOff(){
		System.out.println("SamSungTV ---- 전원을 끈다.");
	}
	public void volumeUp(){
		System.out.println("SamSungTV ---- 소리를 올린다.");
	}
	public void volumeDown(){
		System.out.println("SamSungTV ---- 소리를 내린다.");
	}
	
}
