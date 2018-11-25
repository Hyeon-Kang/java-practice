
// 다음 3 import는 외부 출력을 위해 추가
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class DB11_03 {

	Connection con = null;
	//세션연결에 필요한 커넥션 개체
	Statement stmt = null;
	//연산에 필요한 값 저장
	ResultSet rs =null;
	//쿼리 수행 결과 저장 변수
	String query1 = "Select * From Titanic Where Age != 'null'";
	
	String text="만 20세 이하 사망자 명단 \n"; //저장 텍스트변수 선언

	public static void main(String [] args)
	{
		DB11_03 ex5 = new DB11_03();
		//객체 지정
		
		ex5.Execute1();
	}// main
	
	public DB11_03() {
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
	
	//쿼리 실행
	public void Execute1() {
		try {
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//커서 옵션 지정
		rs = stmt.executeQuery(query1);
		//rs에 쿼리 실행 결과 저장
	
	
		//속성 값들을 받아들일 변수 선언
		String pass, pclass, name, sex, sibsp, parch, ticket, fare, cabin, emb ="";
		double age;
		if(rs.first()) {
			do {
					//필요 attribute들을 읽어들인다.
					pass = rs.getString(1);
					pclass = rs.getString(3);
					name = rs.getString(4);
					sex = rs.getString(5);
					age = rs.getDouble(6);
					sibsp = rs.getString(7);
					parch = rs.getString(8);
					ticket = rs.getString(9);
					fare = rs.getString(10);
					cabin = rs.getString(11);
					emb = rs.getString(12);
					 
					//출력 형식 지정
					text ="승객번호 : "+pass+", 객실등급 : "+pclass+", 이름 : "+name+", 성별 : "+sex+", 나이 : "+(int)age+", 승선한 형제/자매/배우자 수 : "+sibsp+", 승선한 부모/자녀의 수 : "+parch+", 티켓 번호 : "+ticket+", 요금 : "+fare+", 선실 번호 : "+cabin+", 승선 장소 : "+emb+"\r\n";
					
					//저장 파일 명 (저장 경로는 클래스 폴더에 위치함)
					File file = new File("Dead_list.txt");
					//객체 초기화
					FileWriter writer = null;
					
					try {
			            // (입력 객체 생성)기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
			            writer = new FileWriter(file, true);
			            writer.write(text); //text의 내용을 목적 Dead_list.txt에 작성
			            writer.flush(); //버퍼 정리(확실히 넣기)
					 	} 
					catch(Exception e) {
				            e.printStackTrace();
				        }

					 
			}while(rs.next());
				//반복수행
			System.out.println("사망자 명단 출력 완료.");
			}
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

