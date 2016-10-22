package member;

import java.util.HashMap;
import java.util.Map;

import bean.User;
/**
 * 
 * @author hanyujin
 *
 */
public class Table {
	private static Table table;
	private Map<String,User> userList;
	
	private Table(){
		userList=new HashMap<>();
	}
	public static Table  getInstance(){
		if(table==null)
			table=new Table();
		return table;
	}
	public void insertUser(String tableNum,User user){
		userList.put(tableNum, user);
	}
	public void deleteUser(String tableNum){
		userList.remove(tableNum);
	}
}
