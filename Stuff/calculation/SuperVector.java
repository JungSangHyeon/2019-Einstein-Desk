package calculation;

import java.util.Vector;

public class SuperVector {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void changeSeat(Vector target, int index1, int index2){
		if(index1==index2) {
//			System.out.println("no change");
			return;}
		
		Object index1Component = target.get(index1);
		Object index2Component = target.get(index2);
		
		if(index1<index2) {
			target.remove(index2Component);
			target.remove(index1Component);
			
			target.add(index1, index2Component);
			target.add(index2, index1Component);
		}else if(index1>index2) {
			target.remove(index1Component);
			target.remove(index2Component);
			
			target.add(index2, index1Component);
			target.add(index1, index2Component);
		}
	}
	
}
