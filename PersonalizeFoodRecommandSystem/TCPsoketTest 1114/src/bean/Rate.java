package bean;

import java.io.Serializable;

public class Rate implements Serializable {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -3247294858117883094L;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = -3247294858117883094L;
	
	private String user_id;
	private int food_id;
	private int ordered_rate;
	private String timeout;
	
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getOrdered_rate() {
		return ordered_rate;
	}
	public void setOrdered_rate(int ordered_rate) {
		this.ordered_rate = ordered_rate;
	}
	
	
}
