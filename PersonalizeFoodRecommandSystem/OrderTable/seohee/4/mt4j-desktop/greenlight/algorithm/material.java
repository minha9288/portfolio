package algorithm;

import java.sql.*;

public class material {
	String user_id; // 사용자 id
	int food_id; // 음식 id
	int[] ordered_id; // user_id로 ordered에 검색하여 ordered_id를 추출 // 사용자가 주문했던 주문내역의 id
	int ordered_id_count; // user_id로 ordered에 검색하여 추출한 ordered_id의 갯수 // 사용자가 주문한(방문한) 횟수
	int user_food_count; // user_id와 food_id로 ordered애 검색하여 추출한 갯수 // 사용자가 현재 선택한 음식을 과거에 주문한 횟수
	int flag;
	int user_change_material_count; // user_id와 food_id로 change_material에 검색하여 추출한 갯수 // 사용자가 현재 선택한 음식에서 과거에 재료를 바꾼 횟수
	int before_change_count; // change_material에서 user_id 또는 ordered_id로 검색한 before_change의 갯수 // 사용자가 현재 선택한 음식에서 과거의 before_change의 갯수
	int[] before_change; // change_material에서 user_id 또는 ordered_id로 검색한 before_change // 사용자가 선택한 음식에서 before_change의 id
	int[] before_change_int; // 각 before_change의 갯수 // 사용자가 선택한 음식에서 각 before_change를 바꾼 횟수
	int[] before_change_more; // 각 before_change에 대해서 before_change_int가 2 이상이며, ordered_id_count의 수의 반 이상인 before_change // 사용자가 선택한 음식에서 각 before_change가 바뀐 횟수가 2번 이상이며, 사용자가 방문한 총 횟수의 반 이상인 before_change
	int before_change_more_int; // 각 before_change_more의 개수 // 사용자가 선택한 음식에서 각 before_change가 바뀐 횟수가 2번 이상이며, 사용자가 방문한 총 횟수의 반 이상인 before_change의 갯수
	int[] after_change_int; // 각 before_change_more에 대한 after_change의 수 // 각 before_change에 대응하는 after_change의 갯수
	int[][] after_change; // 각 before_change_more에 대한 after_change // 각 before_change에 대한 after_change들의 id
	int[][] after_change_count; // 각 before_change에 대한 after_change들의 갯수 // 사용자가 각 before_change대신 어떤 after_change로 몇 번 바꾸었는지 횟수
	int[] after_change_last; // 최종적으로 바꾸어 줄 after_change // before_change를 대신하여 출력될 after_change
	
	int[] before_change_include; // 사용자가 선택한 음식에 들어가는 before_change
	int[] before_change_include_int; // 사용자가 선택한 음식에 들어가는 before_change 각각의 수
	int before_change_include_count; // 사용자가 선택한 음식에 들어가는 before_change의 갯수
	int[][] food_id_include; // 사용자가 선택한 음식에 들어가는 각 before_change가 들어가는 다른 음식의 id
	int[] food_id_include_count; // 사용자가 선택한 음식에 들어가는 각 before_change가 들어가는 다른 음식의 갯수
	int[] food_id_ordered_count; // 사용자가 선택한 음식에 들어가는 각 before_change가 들어가는 다른 음식들 중 사용자가 먹은 음식의 갯수
	int[][] after_change_real; // 사용자가 선택한 음식에 들어가는 after_change
	int[][] after_change_real_count; // 사용자가 선택한 음식에 들어가는 after_change의 갯수
	int q9q9;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void get_parameter(String user_id, int food_id) {
		this.user_id = user_id;
		this.food_id = food_id;
		q1();
	}
	
	public void q1() { // 사용자가 2번 이상 방문하였는가?
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch(ClassNotFoundException e) {
			System.err.println("error : Driver not found");
			System.exit(0);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			String sql = "select count(*) from ordered where user_id="+user_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				ordered_id_count=rs.getInt(1);
			}
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		if(ordered_id_count >=2) { // yes
			System.out.println("2번 이상 식당에 방문 함");
			System.out.println(ordered_id_count);
			System.out.println("---------------------------");
			q2();
		} else { // no
			System.out.println("2번 이상 식당에 방문 안함");
			System.out.println(ordered_id_count);
			System.out.println("---------------------------");
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void q2() { // 사용자가 과거에 현재 선택한 음식을 주문한 내역이 있는가?
		String sql=null;
		boolean isTrue=false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			sql = "select count(*) from ordered where food_id="+food_id+" && user_id='"+user_id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					isTrue=true;
					user_food_count=user_food_count + rs.getInt(1);
				}
			}
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		System.out.println(user_food_count);
		System.out.println("---------------------------");
		
		if(isTrue) {
			q3();
		} else {
			q4();
		}
	}
	
	public void q3() { // 사용자가 과거에 재료를 바꾼 내역이 있는가? 있다면 사용자가 선택한 음식에서 before_change 재료가 몇 개인지 추출한다.
		String sql = null;
		boolean isTrue=false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			sql = "select count(*) from change_material where user_id='"+user_id+"' && food_id="+food_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				isTrue = true;
				user_change_material_count = rs.getInt(1);
			}
			System.out.println(user_change_material_count);
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		if(isTrue) {
			flag = 0;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
				sql = "select count(distinct before_change) from change_material where user_id='"+user_id+"' && food_id="+food_id;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					before_change_count = rs.getInt(1);
				}
				System.out.println(before_change_count);
				System.out.println("---------------------------");
			} catch(SQLException e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(stmt!=null) {
						stmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e){ }
			}
			d3();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void q4() {
		String sql = null;
		boolean isTrue=false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			sql = "select count(*) from change_material where user_id='"+user_id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					isTrue = true;
					user_change_material_count = rs.getInt(1);
				}
			}
			System.out.println(user_change_material_count);
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		if(isTrue) {
			flag = 1;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
				sql = "select count(distinct before_change) from change_material where user_id='"+user_id+"'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					before_change_count = rs.getInt(1);
				}
				System.out.println(before_change_count);
				System.out.println("---------------------------");
			} catch(SQLException e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(stmt!=null) {
						stmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				} catch(Exception e){ }
			}
			d3();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void d3() { // 사용자가 선택한 음식에서 before_change 재료의 갯수만큼 변수를 생성하고, 해당 변수에 각각 재료의 이름을 넣고, 각 재료가 과거에 몇 번씩 바뀌었는지 추출한다.
		before_change = new int[before_change_count];
		before_change_int = new int[before_change_count];
		String sql=null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			
			if(flag == 1) { // q4에서 옴
				sql = "select before_change from change_material where user_id='"+user_id+"'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					for(int i=0; i<before_change_count; i++) {
						if(before_change[i]==0 && before_change[i]!=rs.getInt(1)) {
							before_change[i] = rs.getInt(1);
							before_change_int[i]++;
							System.out.println(i);
							System.out.println(before_change[i]);
							System.out.println(before_change_int[i]);
							System.out.println("~~");
							break;
						} else if(before_change[i]==rs.getInt(1)) {
							before_change_int[i]++;
							System.out.println(i);
							System.out.println(before_change[i]);
							System.out.println(before_change_int[i]);
							System.out.println("~~");
							break;
						}	
					}
				}
			} else { // q3에서 옴
				sql = "select before_change from change_material where user_id='"+user_id+"' && food_id="+food_id;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					for(int i=0; i<before_change_count; i++) {
					if(before_change[i]==0 && before_change[i]!=rs.getInt(1)) {
						before_change[i] = rs.getInt(1);
						before_change_int[i]++;
						System.out.println(i);
						System.out.println(before_change[i]);
						System.out.println(before_change_int[i]);
						System.out.println("~~");
						break;
					} else if(before_change[i]==rs.getInt(1)) {
						before_change_int[i]++;
						System.out.println(i);
						System.out.println(before_change[i]);
						System.out.println(before_change_int[i]);
						System.out.println("~~");
						break;
					}	
				}
			}
		}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		if(flag == 0) {
			q5();
		} else {
			q6();
		}
	}
	
	public void q5() { // 사용자가 선택한 음식에서 각 before_change가 바뀐 횟수가 2번 이상이며, 사용자가 방문한 총 횟수의 반 이상인가? => before_change_int[i]가 각각 2 이상이며, ordered_id_count의 반 이상인가?
		int j=0;
		boolean isTrue = false;
		before_change_more = new int[before_change_count];
		
		for(int i=0; i<before_change_count; i++) {
			if(before_change_int[i] >= 2 && before_change_int[i] >= ordered_id_count/2) {
				before_change_more[j] = before_change[i];
				before_change_more_int++;
				j++;
				isTrue=true;
			}
		}
		for(j=0; j<before_change_more_int; j++) {
			System.out.println(before_change_more[j]);
		}
		System.out.println(before_change_more_int);
		System.out.println("---------------------------");
		
		if(isTrue) {
			d5();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void d5() { // 각 before_change를 바꿔줄 after_change의 종류가 몇 개인지 추출한다.
		after_change_int = new int[before_change_more_int];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				String sql = "select count(distinct after_change) from change_material where user_id='"+user_id+"' && food_id="+food_id+" && before_change="+before_change_more[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					after_change_int[i]=rs.getInt(1);
				}
				System.out.println(after_change_int[i]);
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		d5_1();
	}
	
	public void d5_1() { // 각 before_change에 대한 after_change들이 무엇인지 추출한다.
		int in = after_change_int[before_change_more_int-1];
		after_change =  new int[before_change_more_int][in];
		int j=0;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				j=0;
				String sql = "select distinct after_change from change_material where user_id='"+user_id+"' && food_id="+food_id+" && before_change="+before_change_more[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					System.out.println(i);
					System.out.println(j);
					after_change[i][j] = rs.getInt(1);
					System.out.println(after_change[i][j]);
					j++;
				}
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		d10();
	}

	public void q6() { // 사용자가 선택한 음식에 before_change들이 들어가는가?
		before_change_include = new int[before_change_count];
		before_change_include_int = new int[before_change_count];
		boolean isTrue=false;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_count; i++) {
				String sql = "select material_id from food_material where food_id="+food_id+" && material_id="+before_change[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					isTrue=true;
					before_change_include[i] = rs.getInt(1);
					before_change_include_int[i] = before_change_int[i];
					before_change_include_count++;
				}
				System.out.println(before_change_include[i]);
				System.out.println(before_change_include_int[i]);
			}
			System.out.println(before_change_include_count);
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		if(isTrue) {
			d6();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void d6() { // 사용자가 선택한 음식에 들어가는 before_change가 포함된 모든 food_id의 갯수와 id를 추출한다.
		food_id_include_count = new int[before_change_include_count];
		int in =0; 
		
		int j=0;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_include_count; i++) {
				String sql = "select count(food_id) from food_material where material_id="+before_change_include[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					food_id_include_count[i] = rs.getInt(1);
				}
				System.out.println(food_id_include_count[i]);
				System.out.println("~~~~~~~~");
				
				in = food_id_include_count[before_change_include_count-1];
				food_id_include = new int[before_change_include_count][in];
				sql = "select food_id from food_material where material_id="+before_change_include[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					food_id_include[i][j] = rs.getInt(1);
					System.out.println(food_id_include[i][j]);
					j++;
				}
			}
			
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		d7();
	}
	
	public void d7() {
		food_id_ordered_count = new int[before_change_include_count];
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_include_count; i++) {
				for(int j=0; j<food_id_include_count[i]; j++) {
					String sql = "select count(*) from ordered where user_id='"+user_id+"' && food_id="+food_id_include[i][j];
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()) {
						food_id_ordered_count[i] = food_id_ordered_count[i]+rs.getInt(1);
					}
				}
				System.out.println(food_id_ordered_count[i]);
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		q7();
	}
	
	public void q7() { // 사용자가 선택한 음식에 들어가는 before_change가 바귄 횟수가 2번 이상이며, 사용자가 선택한 음식에 들어가는 before_change를 포함한 음식들을 사용자가 주문한 횟수의 반 이상인가?
		int j=0;
		boolean isTrue = false;
		before_change_more = new int[before_change_count];
		
		for(int i=0; i<before_change_include_count; i++) {
			if(before_change_include_int[i] >= 2 && before_change_include_int[i] >= food_id_ordered_count[i]/2) {
				before_change_more[j] = before_change_include[i];
				before_change_more_int++;
				j++;
				isTrue=true;
			}
		}
		for(j=0; j<before_change_more_int; j++) {
			System.out.println(before_change_more[j]);
		}
		System.out.println(before_change_more_int);
		System.out.println("---------------------------");
		
		if(isTrue) {
			d8();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void d8() {
		after_change_int = new int[before_change_more_int];
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				String sql = "select count(distinct after_change) from change_material where user_id='"+user_id+"' && before_change="+before_change_more[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					after_change_int[i]=rs.getInt(1);
				}
				System.out.println(after_change_int[i]);
				System.out.println("---------------------------");
			}
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		d8_1();
	}
	
	public void d8_1() {
		int in = after_change_int[before_change_more_int-1];
		after_change =  new int[before_change_more_int][in];
		int j=0;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				j=0;
				String sql = "select distinct after_change from change_material where user_id='"+user_id+"' && before_change="+before_change_more[i];
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					after_change[i][j] = rs.getInt(1);
					System.out.println(after_change[i][j]);
					j++;
				}
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		q9();
	}
	
	public void q9() {
		boolean isTrue = false;
		int in = after_change_int[before_change_more_int-1];
		after_change_real = new int[before_change_more_int][in];
		after_change_real_count = new int[before_change_more_int][in];
		int k=0;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				for(int j=0; j<in; j++) {
					String sql = "select * from food_material where food_id="+food_id+" && material_id="+after_change[i][j];
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if(rs.next()) {
						isTrue = true;
						after_change_real[i][k] = rs.getInt(1);
						after_change_real_count[i][k]++;
						System.out.println(i);
						System.out.println(k);
						System.out.println(after_change_real[i][k]);
						System.out.println(after_change_real_count[i][k]);
						k++;
					}
				}
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		
		if(isTrue) {
			q9q9 = 1;
			d9();
		}
		else {
			
		}
	}
	
	public void d9() {
		boolean isTrue = false;
		int in = after_change_int[before_change_more_int-1];
		after_change_count = new int[before_change_more_int][in];
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				for(int j=0; j<in; j++) {
					String sql = "select count(after_change) from change_material where user_id='"+user_id+"' && before_change="+before_change_more[i]+" && after_change="+after_change_real[i][j];
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						if(rs.getInt(1)!=0) {
							isTrue=true;
							after_change_count[i][j] = rs.getInt(1);
							System.out.println(after_change_count[i][j]);
						}
					}
				}
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		if(isTrue) {
			d12();
		} else {
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void d10() { // 각 after_change들이 몇 번씩 주문되었는지 추출한다.
		int in = after_change_int[before_change_more_int-1];
		after_change_count = new int[before_change_more_int][in];
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			for(int i=0; i<before_change_more_int; i++) {
				for(int j=0; j<in; j++) {
					String sql = "select count(after_change) from change_material where user_id='"+user_id+"' && food_id="+food_id+" && before_change="+before_change_more[i]+" && after_change="+after_change[i][j];
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						after_change_count[i][j] = rs.getInt(1);
						System.out.println(after_change_count[i][j]);
					}
				}
			}
			System.out.println("---------------------------");
		} catch(SQLException e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(Exception e){ }
		}
		d11();
	}
	
	public void d11() { // after_change_count[][]들을 비교하여 더 큰 수를 가진 after_change[][]를 찾는다.
		int in = after_change_int[before_change_more_int-1];
		int max[]=new int[before_change_more_int];
		after_change_last = new int[before_change_more_int];
		int max_count=0;
		for(int i=0; i<before_change_more_int; i++) {
			max[i]=0;
		}
		for(int i=0; i<before_change_more_int; i++) {
			for(int j=0; j<in; j++) {
				if(j+1>=3) {
					break;
				}
				else {
					if(max[i]<after_change_count[i][j]) {
						max[i] = after_change_count[i][j];
						max_count=j;
					}
				}
			}
			after_change_last[i] = after_change[i][max_count];
			System.out.println(after_change_last[i]);
		}
		System.out.println("---------------------------");
		material();
	}
	
	public void d12() {
		int in = after_change_int[before_change_more_int-1];
		int max[]=new int[before_change_more_int];
		after_change_last = new int[before_change_more_int];
		int max_count=0;
		for(int i=0; i<before_change_more_int; i++) {
			max[i]=0;
		}
		for(int i=0; i<before_change_more_int; i++) {
			for(int j=0; j<in; j++) {
				if(j+1>=3) {
					break;
				}
				else {
					if(max[i]<after_change_real_count[i][j]) {
						max[i] = after_change_real_count[i][j];
						max_count=j;
					}
				}
			}
			after_change_last[i] = after_change_real[i][max_count];
			System.out.println(after_change_last[i]);
		}
		System.out.println("---------------------------");
		material();
	}
	
	public void d14() {
		before_change_more = new int[before_change_more_int];
		after_change_last = new int[before_change_more_int];
		for(int i=0; i<before_change_more_int; i++) {
			before_change_more[i] = 1;
			after_change_last[i] = 1;
		}
		material();
	}
	
	public void material() { // 출력 테스트
		System.out.println("user : "+ user_id);
		System.out.println("food : "+ food_id);
		for(int i = 0; i<before_change_more_int; i++) {
			System.out.println("before_change["+i+"] : "+ before_change_more[i]);
			System.out.println("after_change["+i+"] : "+ after_change_last[i]);
		}
	}

}



/*
package food_material;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class food_view {
	JButton bt1;
	String user_id;
	int food_id;
	
	public food_view(Container ct) {
		bt1 = new JButton("재료로 넘어가라");
		user_id = "5";
		food_id = 2;
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				material_algorithm ma = new material_algorithm();
				ma.get_parameter(ct, user_id, food_id);
				
				ct.remove(bt1);
				ct.repaint();
			}
		});
		
		ct.add(bt1);
		
	}
	
}*/
