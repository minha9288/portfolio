package algorithm;

import java.sql.*;

public class material {
	String user_id; // ����� id
	int food_id; // ���� id
	int[] ordered_id; // user_id�� ordered�� �˻��Ͽ� ordered_id�� ���� // ����ڰ� �ֹ��ߴ� �ֹ������� id
	int ordered_id_count; // user_id�� ordered�� �˻��Ͽ� ������ ordered_id�� ���� // ����ڰ� �ֹ���(�湮��) Ƚ��
	int user_food_count; // user_id�� food_id�� ordered�� �˻��Ͽ� ������ ���� // ����ڰ� ���� ������ ������ ���ſ� �ֹ��� Ƚ��
	int flag;
	int user_change_material_count; // user_id�� food_id�� change_material�� �˻��Ͽ� ������ ���� // ����ڰ� ���� ������ ���Ŀ��� ���ſ� ��Ḧ �ٲ� Ƚ��
	int before_change_count; // change_material���� user_id �Ǵ� ordered_id�� �˻��� before_change�� ���� // ����ڰ� ���� ������ ���Ŀ��� ������ before_change�� ����
	int[] before_change; // change_material���� user_id �Ǵ� ordered_id�� �˻��� before_change // ����ڰ� ������ ���Ŀ��� before_change�� id
	int[] before_change_int; // �� before_change�� ���� // ����ڰ� ������ ���Ŀ��� �� before_change�� �ٲ� Ƚ��
	int[] before_change_more; // �� before_change�� ���ؼ� before_change_int�� 2 �̻��̸�, ordered_id_count�� ���� �� �̻��� before_change // ����ڰ� ������ ���Ŀ��� �� before_change�� �ٲ� Ƚ���� 2�� �̻��̸�, ����ڰ� �湮�� �� Ƚ���� �� �̻��� before_change
	int before_change_more_int; // �� before_change_more�� ���� // ����ڰ� ������ ���Ŀ��� �� before_change�� �ٲ� Ƚ���� 2�� �̻��̸�, ����ڰ� �湮�� �� Ƚ���� �� �̻��� before_change�� ����
	int[] after_change_int; // �� before_change_more�� ���� after_change�� �� // �� before_change�� �����ϴ� after_change�� ����
	int[][] after_change; // �� before_change_more�� ���� after_change // �� before_change�� ���� after_change���� id
	int[][] after_change_count; // �� before_change�� ���� after_change���� ���� // ����ڰ� �� before_change��� � after_change�� �� �� �ٲپ����� Ƚ��
	int[] after_change_last; // ���������� �ٲپ� �� after_change // before_change�� ����Ͽ� ��µ� after_change
	
	int[] before_change_include; // ����ڰ� ������ ���Ŀ� ���� before_change
	int[] before_change_include_int; // ����ڰ� ������ ���Ŀ� ���� before_change ������ ��
	int before_change_include_count; // ����ڰ� ������ ���Ŀ� ���� before_change�� ����
	int[][] food_id_include; // ����ڰ� ������ ���Ŀ� ���� �� before_change�� ���� �ٸ� ������ id
	int[] food_id_include_count; // ����ڰ� ������ ���Ŀ� ���� �� before_change�� ���� �ٸ� ������ ����
	int[] food_id_ordered_count; // ����ڰ� ������ ���Ŀ� ���� �� before_change�� ���� �ٸ� ���ĵ� �� ����ڰ� ���� ������ ����
	int[][] after_change_real; // ����ڰ� ������ ���Ŀ� ���� after_change
	int[][] after_change_real_count; // ����ڰ� ������ ���Ŀ� ���� after_change�� ����
	int q9q9;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public void get_parameter(String user_id, int food_id) {
		this.user_id = user_id;
		this.food_id = food_id;
		q1();
	}
	
	public void q1() { // ����ڰ� 2�� �̻� �湮�Ͽ��°�?
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
			System.out.println("���� ����");
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
			System.out.println("2�� �̻� �Ĵ翡 �湮 ��");
			System.out.println(ordered_id_count);
			System.out.println("---------------------------");
			q2();
		} else { // no
			System.out.println("2�� �̻� �Ĵ翡 �湮 ����");
			System.out.println(ordered_id_count);
			System.out.println("---------------------------");
			before_change_more_int = 1;
			d14();
		}
	}
	
	public void q2() { // ����ڰ� ���ſ� ���� ������ ������ �ֹ��� ������ �ִ°�?
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
			System.out.println("���� ����");
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
	
	public void q3() { // ����ڰ� ���ſ� ��Ḧ �ٲ� ������ �ִ°�? �ִٸ� ����ڰ� ������ ���Ŀ��� before_change ��ᰡ �� ������ �����Ѵ�.
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
			System.out.println("���� ����");
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
				System.out.println("���� ����");
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
			System.out.println("���� ����");
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
				System.out.println("���� ����");
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
	
	public void d3() { // ����ڰ� ������ ���Ŀ��� before_change ����� ������ŭ ������ �����ϰ�, �ش� ������ ���� ����� �̸��� �ְ�, �� ��ᰡ ���ſ� �� ���� �ٲ������ �����Ѵ�.
		before_change = new int[before_change_count];
		before_change_int = new int[before_change_count];
		String sql=null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenlight", "root", "1111");
			
			if(flag == 1) { // q4���� ��
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
			} else { // q3���� ��
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
			System.out.println("���� ����");
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
	
	public void q5() { // ����ڰ� ������ ���Ŀ��� �� before_change�� �ٲ� Ƚ���� 2�� �̻��̸�, ����ڰ� �湮�� �� Ƚ���� �� �̻��ΰ�? => before_change_int[i]�� ���� 2 �̻��̸�, ordered_id_count�� �� �̻��ΰ�?
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
	
	public void d5() { // �� before_change�� �ٲ��� after_change�� ������ �� ������ �����Ѵ�.
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
			System.out.println("���� ����");
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
	
	public void d5_1() { // �� before_change�� ���� after_change���� �������� �����Ѵ�.
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
			System.out.println("���� ����");
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

	public void q6() { // ����ڰ� ������ ���Ŀ� before_change���� ���°�?
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
			System.out.println("���� ����");
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
	
	public void d6() { // ����ڰ� ������ ���Ŀ� ���� before_change�� ���Ե� ��� food_id�� ������ id�� �����Ѵ�.
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
			System.out.println("���� ����");
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
			System.out.println("���� ����");
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
	
	public void q7() { // ����ڰ� ������ ���Ŀ� ���� before_change�� �ٱ� Ƚ���� 2�� �̻��̸�, ����ڰ� ������ ���Ŀ� ���� before_change�� ������ ���ĵ��� ����ڰ� �ֹ��� Ƚ���� �� �̻��ΰ�?
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
			System.out.println("���� ����");
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
			System.out.println("���� ����");
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
			System.out.println("���� ����");
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
			System.out.println("���� ����");
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
	
	public void d10() { // �� after_change���� �� ���� �ֹ��Ǿ����� �����Ѵ�.
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
			System.out.println("���� ����");
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
	
	public void d11() { // after_change_count[][]���� ���Ͽ� �� ū ���� ���� after_change[][]�� ã�´�.
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
	
	public void material() { // ��� �׽�Ʈ
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
		bt1 = new JButton("���� �Ѿ��");
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
