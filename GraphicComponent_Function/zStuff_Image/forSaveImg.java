package zStuff_Image;

import java.io.Serializable;

public class forSaveImg implements Serializable{
	private static final long serialVersionUID = 5125040849777226499L;
	
	public forSaveImg(long l, String fileName) {
		this.id = l;
		this.fileName = fileName;
	}
	
	private long id;
	public long getId() {return id;}
	
	private String fileName;
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}
}
