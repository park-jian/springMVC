package kr.or.iei.tv.model.vo;

//TVmgr의 역할은 TV객체를 저장하고 있는 역할
public class TVmgr {
	private TV tv;

	public TVmgr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TVmgr(TV tv) {
		super();
		this.tv = tv;
	}

	public TV getTv() {
		return tv;
	}

	public void setTv(TV tv) {
		this.tv = tv;
	}
	
}
