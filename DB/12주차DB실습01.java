//승선 위치별 생존자 평균 연령 구하기
//181123 최종수정
//주석 및 코드 최적화 작업 



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 

//탑승 구역별

public class DB11_01 {

	Connection con = null;
	//세션연결에 필요한 커넥션 개체
	Statement stmt = null;
	//연산에 필요한 값 저장
	ResultSet rs =null;
	//쿼리 수행 결과 저장 변수
	String query1 = "Select Age From Titanic Where Survived = '1' and Embarked = 'Q' and Age != 'null'"; //탑승구역 Q
	String query2 = "Select Age From Titanic Where Survived = '1' and Embarked = 'S' and Age != 'null'"; //탑승구역 S
	String query3 = "Select Age From Titanic Where Survived = '1' and Embarked = 'C' and Age != 'null'"; //탑승구역 C
	String query4 = "Select Age From Titanic Where Survived = '1' and Embarked = 'null'"; //탑승구역 미확인
	//질의 저장 
	
	//필요한 변수 선언
	double sum =0;
	int count =0;
	double avg =0;
	String Emb ="";
	
	//메인함수
	public static void main(String [] args)
	{
		DB11_01 ex4 = new DB11_01();
		//객체 지정
		
		ex4.ExecuteQ(); //탑숭구역Q
		ex4.ExecuteS(); //탑승구역S
		ex4.ExecuteC(); //탑승구역C
		ex4.ExecuteNull(); //탑승구역 미확인
	}// main
	
	public DB11_01() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://210.115.229.77:2433", "20155204", "비밀번호");
			//DriverManager에 필요한 값 저장 (주소:포트, ID, Pw)
			con.setCatalog("20155204");
			//접근 DB명
			System.out.println("Connected ...");
					
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
	}//text01
	
	public void ExecuteQ() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query1);
		//rs에 쿼리 실행 결과 저장
		sum = 0;
		count = 0;
		avg = 0;
		
		//순서대로 읽어들이기
		if(rs.first()) {
			do {
				String getst = rs.getString(1); //1열 데이터 불러옴
		
					double age = Double.parseDouble(getst);
					count++;
					sum += age;
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		avg = sum/count; //평균 구하기
		System.out.println("Q 승선위치 생존자 평균 나이 : " + avg);
		System.out.println("집계 생존인원(null제외) : " + count);
		
		System.out.println();
	}//executeQ
	
	public void ExecuteS() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query2);
		//rs에 쿼리 실행 결과 저장
		sum = 0;
		count = 0;
		avg = 0;
		
		if(rs.first()) {
			do {
				String getst = rs.getString(1);
		
					double age = Double.parseDouble(getst);
					count++;
					sum += age;
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		avg = sum/count;
		System.out.println("S 승선위치 생존자 평균 나이 : " + avg);
		System.out.println("집계 생존인원(null제외) : " + count);
	
		System.out.println();
	}//executeS
	
	
	
	
	public void ExecuteC() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query3);
		//rs에 쿼리 실행 결과 저장
		sum = 0;
		count = 0;
		avg = 0;
		
		if(rs.first()) {
			do {
				String getst = rs.getString(1);
		
					double age = Double.parseDouble(getst);
					count++;
					sum += age;
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		avg = sum/count;
		System.out.println("C 승선위치 생존자 평균 나이 : " + avg);
		System.out.println("집계 생존인원(null제외) : " + count);
	
		System.out.println();
	}//executeC
	
	
	public void ExecuteNull() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query4);
		//rs에 쿼리 실행 결과 저장
		sum = 0;
		count = 0;
		avg = 0;
		
		if(rs.first()) {
			do {
				String getst = rs.getString(1);
		
					double age = Double.parseDouble(getst);
					count++;
					sum += age;
				
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		avg = sum/count;
		System.out.println("승선위치가 확인되지 않은 생존자 평균 나이 : " + avg);
		System.out.println("집계 생존인원(null제외) : " + count);
	
		System.out.println();
	}//executeNull
	
	
	
	public void Close() {
		try {
			con.close();
			stmt.close();
			rs.close();
			//연산종료 후 connection, statement, 데이터 저장 객체 닫기 (자원 확보)
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		System.out.println("Disconnected...");
	}//CLose
	
}
