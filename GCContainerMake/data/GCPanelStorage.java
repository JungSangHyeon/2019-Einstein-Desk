package data;

import java.util.Vector;

public class GCPanelStorage {

	private static Vector<GCPanel> GCPanelVector = new Vector<GCPanel>();
	
	public static void add(GCPanel container) {GCPanelVector.add(container);}
	public static void get(int i) {GCPanelVector.get(i);}
	
	public static Vector<GCPanel> getGCPanelVector() {return GCPanelVector;}
	
}
