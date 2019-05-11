package enumInjector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Injector {
	
	//Text 양식 : "Key(val1, val2....) + 아무거나 + Enter"
	//사용 법 :  for(EInt e : EInt.values()) {Injector.inject(e);} /  enum은 implements InjectTarget할것 / enum은 setVal이 같은 것 끼리 묶을 것.
	
//	public enum example implements InjectTarget{
//		e("tableAddress", "keyValue");//tableAddress는 공유되니, 전역변수로 빼놓고 쓰쇼. 주소 바꾸기 편하게.
//
//		String[] info; //val
//		//getVal Method
//		private example(String table, String key) {info = new String[] {table, key};}
//		public String[] getInfo() {return info;}
//		public void setVal(String[] val) {}
//	}
	
	// User
	static final char DILIMETER = ',', VALUESTART = '(', VALUEEND = ')';
	static final String ErrorMSG1 = "INJECTOR ERROR : 주소 ", ErrorMSG2 = "에 ", ErrorMSG3 = "의 값이 없습니다."; //ErrorMSG1+tableAddress+ErrorMSG2+keyValue+ErrorMSG3
	
	// System
	static Scanner sc;
	static final int tableAddress = 0, keyValue = 1, front = 0, back = 1;
	static Vector<InjectTarget[]> injectTargets = new Vector<InjectTarget[]>();
	
	public interface InjectTarget{
		public void setVal(String[] val);//enum에 맞추어 만든다.
		public String[] getInfo();// {tableAddress, keyValue} 둘은 생성자에서 설정 할 것.
	}
	
	public static void inject(InjectTarget target) {
		String[] info = target.getInfo();
		try {Injector.sc = new Scanner(new File(info[tableAddress]));}
		catch (FileNotFoundException e) {e.printStackTrace();}

		String nowLine, nowKey;
		boolean keyNotFound = true;
		while(keyNotFound) {
			if(sc.hasNextLine()) {
				nowLine = sc.nextLine();
				nowKey = nowLine.split("\\"+VALUESTART)[front];//\\는 정규식 관련 문자열이 들어가있으면 발생하는 오류를 막기 위함.
				if(nowKey.equals(info[keyValue])){
					target.setVal(nowLine.split("\\"+VALUESTART)[back].split("\\"+VALUEEND)[front].split("\\"+DILIMETER));
					keyNotFound = false;
				}
			}else {
				System.out.println(ErrorMSG1+info[tableAddress]+ErrorMSG2+info[keyValue]+ErrorMSG3);
				keyNotFound = false;
			}
		}
	}
	
	public static void registeAll(InjectTarget[][] targetArrs) {
		for(InjectTarget[] targetArr : targetArrs) {
			Injector.registe(targetArr);
		}
	}
	
	public static void registe(InjectTarget[] targets) {injectTargets.add(targets);}
	public static void injectRegisted() {
		for(InjectTarget[] targetArr : injectTargets) {
			for(InjectTarget target : targetArr) {
				Injector.inject(target);
			}
		}
	}
}