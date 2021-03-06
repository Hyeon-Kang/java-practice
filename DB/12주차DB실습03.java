//12주차 실습 03
//1등급 선실 탑승자가 차지하는 전체 생존자의 비율은?


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
	
	String query1 = "select COUNT(*) from Titanic where Pclas ='1' and Survived = '1' group by Pclas"; //1등급 선실 생존자 수
	String query2 = "select COUNT(*) from Titanic Where Survived ='1' group by Survived having Survived ='1'"; //전체 생존자

	static int pcsurv =0;
	static int allsurv = 0;

	//main 함수
	public static void main(String [] args)
	{
		
		prac03 ex4 = new prac03();
		//객체 지정
		ex4.Execute1();
		//1등급 선실 생존자
		ex4.Execute2();
		//전체 생존자
		
		//비율 출력
		double per = (double)pcsurv/allsurv*100;
		
		System.out.println("1등급 선실 탑승자 생존 비율 : "+per +"%");
	}// main
	
	
	//생성자 초기화(접속)
	public prac03() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://210.115.229.77:2433", "20155204", "");
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
		pcsurv = a;
		System.out.println("1등급 선실 생존자 : " + pcsurv);
		}
		
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
	}//execute
	
	
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
		allsurv = b;
		System.out.println("전체 생존자 : " + allsurv);
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
