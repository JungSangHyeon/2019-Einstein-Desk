package calculation;

import java.util.Vector;

public class SuperVector {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void change(Vector vector, int c1, int c2) {
		if(c1!=c2) {
			int min = Math.min(c1, c2);
			int max = Math.max(c1, c2);
			Object minObj = vector.get(min);
			Object maxObj = vector.get(max);
			vector.remove(minObj);
			vector.remove(maxObj);
			vector.add(min, maxObj);
			vector.add(max, minObj);
		}
	}
}
