package bean;


import java.io.Serializable;

public class User implements Serializable {
	private String id; //핸드폰 핸드폰 기계 값
	private int age; 
	private String sex; 
	private String nation;
	private String tableNum;

	public User() {
		this(null, 0, null, null, null,null);
	}

	public User(String id, int age, String sex, String type, String country,String tableNum) {
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.nation = country;
		this.tableNum=tableNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}

}
