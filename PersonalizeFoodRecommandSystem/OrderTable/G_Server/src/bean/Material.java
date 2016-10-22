package bean;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Material implements Serializable  {

	private int id;
	private String name;
	private String explain;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}	
}
