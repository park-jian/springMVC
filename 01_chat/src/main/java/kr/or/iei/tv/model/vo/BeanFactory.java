package kr.or.iei.tv.model.vo;

public class BeanFactory {
	public static Object getBean(String beanName){
		if(beanName.equals("samsung")){
			return new SamSungTV();
		}else if(beanName.equals("lg")){
			return new LgTV();
		}
		return null;
	}
}
