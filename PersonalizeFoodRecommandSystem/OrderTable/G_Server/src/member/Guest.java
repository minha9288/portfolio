package member;

import bean.Food;
import bean.User;
/**
 * 
 * @author hanyujin
 *
 */
public class Guest extends User{
	private Food food;
	
	public void Guest(User user){
		this.setId(user.getId());
		this.setAge(user.getAge());
		this.setSex(user.getSex());
		this.setNation(user.getNation());
	}
	public void Guest(Food food){
		this.food=food;
	}
	public void Guest(String id, int age,String sex,String type,String nation){
		this.setId(id);
		this.setAge(age);
		this.setSex(sex);
		this.setNation(nation);
	}
}
