//12주차 과제 01
//사망자 남, 녀 비율 계산하기


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class prac03 {


	Connection con = null;
	//세션연결에 필요한 커넥션 개체
	Statement stmt = null;
	//연산에 필요한 값 저장
	ResultSet rs =null;
	//쿼리 수행 결과 저장 변수
	
	String query1 = "Select  COUNT(Name) From Titanic Where Sex='female' Group by Survived Having Survived = '0'"; //여성 사망자 수
	String query2 = "Select  COUNT(Name) From Titanic Where Sex='male' Group by Survived Having Survived = '0'"; //남성 사망자 수
	String query3 = "Select  COUNT(Name) From Titanic Group by Survived Having Survived = '0'";//전체 사망자 수

	static int ms, fs, al =0;// 순서대로 남성 사망자, 여성 사망자, 전체 사망자 변수

	//main 함수
	public static void main(String [] args)
	{
		
		prac03 ex4 = new prac03();
		//객체 지정
		ex4.Execute1();
		//여성 사망자 수
		ex4.Execute2();
		//남성 사망자 수
		ex4.Execute3();
		//총 사망자 수
		
		//비율 출력
		double mper = (double)ms/al * 100; //여성 사망자 비율
		double fper = (double)fs/al *100; //남성 사망자 비율
		System.out.println("여성 사망자 비율 : "+fper +"%");
		System.out.println("남성 사망자 비율 : "+mper +"%");
	}// main
	
	
	//생성자 초기화(접속)
	public prac03() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://210.115.229.77:2433", "20155204", "비밀번호1");
			//DriverManager에 필요한 값 저장 (주소:포트, ID, Pw)
			con.setCatalog("20155204");
			//접근 DB명
			System.out.println("Connected ...");
					
		}
		catch(SQLException se)
		{
			System.err.println(se.getMessage());
		}
		
	}//생성자 초기화
	

	//여성 사망자 수
	public void Execute1() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query1);
		//rs에 쿼리 실행 결과 저장
		int a=0;
		if(rs.first()) {
			do {
				a = rs.getInt(1);
		
				}while(rs.next());
				//반복수행
			}
		fs = a;
		System.out.println("여성 사망자 수 : " + a);
		}
		
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
	}//execute
	
	//남성 사망자
	public void Execute2() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query2);
		//rs에 쿼리 실행 결과 저장
		int b =0;
		if(rs.first()) {
			do {
				b = rs.getInt(1);
		
				}while(rs.next());
				//반복수행
			}
		ms = b;
		System.out.println("남성 사망자 수 : " + b);
		}
		
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}

	}//execute
	
	//총 사망자 수
	public void Execute3() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query3);
		//rs에 쿼리 실행 결과 저장
		int a=0;
		if(rs.first()) {
			do {
				a = rs.getInt(1);
		
				}while(rs.next());
				//반복수행
			}
		al = a;
		System.out.println("총 사망자 : " + a);
		}
		
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
	}//execute
	
	
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
