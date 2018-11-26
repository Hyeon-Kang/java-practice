//실습 12주차 Titanic 승선자 중 여성 생존자 수와 평균 나이를 구하시오. (단 null 값을 가진 인자는 평균 나이 도출에서 제외)

package sqlLAB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class surived {

	Connection con = null;
	//세션연결에 필요한 커넥션 개체
	Statement stmt = null;
	//연산에 필요한 값 저장
	ResultSet rs =null;
	//쿼리 수행 결과 저장 변수
	
	
	String query1 = "select COUNT(*) from Titanic where Sex = 'female' and Survived = '1'"; //여성 생존자 수
	String query2 = "select Age from Titanic where Sex = 'female' and Survived = '1' and Age != 'null'";// 평균 나이 (null 값 인자 제외)
	
	//질의 저장 
	
	double sum =0;
	int count =0;
	double avg =0;
	int surv =0;
	
	public static void main(String [] args)
	{
		surived ex4 = new surived();
		//객체 지정
		
		ex4.Execute2();
		ex4.Execute1();
	}// main
	
	public surived() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://210.115.229.77:2433", "20155204", "Ecks0407@");
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
	
	
	//평균 나이 구하기
	public void Execute2() {
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
				count++;
				String inp = rs.getString(1);
				sum += Double.parseDouble(rs.getString(1));
				
				
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		
		avg = sum/count;
		
		System.out.println("평균 나이 : " + avg);
		System.out.println("나이 총 합 : " + sum + ", 집계 인원(null 미포함) : "+count);
	}//execute
	
	
	public void Execute1() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query1);
		//rs에 쿼리 실행 결과 저장
		sum = 0;
		count = 0;
		avg = 0;
		
		if(rs.first()) {
			do {
				surv = rs.getInt(1);
		
				}while(rs.next());
				//반복수행
			}
		}
		catch(SQLException se) {
			//오류 검출
			System.err.println(se.getMessage());
		}
		avg = sum/count;
		System.out.println("Survived(null 포함) : " + surv);
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

