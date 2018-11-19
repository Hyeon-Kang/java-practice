/// Applet_test_실행기

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletEx1Execute extends Applet implements ActionListener{

	Label label; // 라벨 선언
	TextArea Area; //결과 값을 출력할 텍스트 에리어 선언
	Button button; //입력버튼 선언


private String url = "jdbc:inetdae7://210.115.229.77:2433"; //merlia 를 이용하기 때문에 inetdae7
// 주소 값 저장 변수, inet사의 merlia사용
private String user = "20155204";
// ID 값 저장 변수
private String pass = "Ecks0407@";
// PW 값 저장 변수


//JDBC 드라이버 로딩
public void init() {
	try{
		Class.forName("com.inet.tds.TdsDriver");
	}
	catch(ClassNotFoundException e) {
		System.out.println("Class Loading Failed");
		//로딩 실패시 출력 메시지
	}
}


//Interface 설정
public void start() {
	setLayout(new FlowLayout()); // 레이아웃 배치자
	
	//라벨추가
	label = new Label("검 색");
	add(label);
	
	//버튼 추가
	button = new Button("search");
	add(button);

	//텍스트 에리어 범위 지정 및 추가
	Area = new TextArea(10,50);
	add(Area);
	
	//버튼 액션함수 지정
	button.addActionListener(this);
}//start end


//Action event handle (쿼리 선택)
public void actionPerformed(ActionEvent ae) {
	//커넥션, 스테이트먼트 객체 초기화
	Connection con = null;
	Statement stmt = null;
	
	//해당 함수로 들어온 인자 st에 저장
	String st = ae.getActionCommand();
	
	//searchh함수 실행
	searchh();
}//action end



//전체검색 메소드
private void searchh() {
	//커넥션, 스테이트먼트 객체 초기화
	Connection con = null;
	Statement stmt = null;
	
	try {	
		//드라이버 매니저가 연결에 사용할 값 전달
		con = DriverManager.getConnection(url, user, pass);
		
		//DBNAME 입력(최상위 테이블 묶음)
		con.setCatalog("20155204"); 
		
		//객체 데이터 stmt에 저장
		stmt = con.createStatement();
		
		//쿼리 실행결과 result에 저장
		ResultSet result = stmt.executeQuery("SELECT * FROM hallym");
		
		//텍스트 에리어 초기화
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("전체검색 ...\n"); //안내문구 출력
		
		while(result.next()) //순차로 읽어들임
		{
			//getString에 부여된 번호의 열 데이터를 가져온다.
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String id = result.getString(3);
			String pass = result.getString(4);
			String email = result.getString(5);
			String phone = result.getString(6);
			
			//출력 형식
			String value = "이름 : " + Name + " /나이 : " + age+ " /ID : "+ id + " /Password : "+pass+ " /e-mail : "+email+" /phone : "+phone+"\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		//작업 종료 후 객체 닫기(자원 확보 절차)
		con.close();
		stmt.close();
		}
	catch(Exception ee) {System.out.println(ee);}
		
	}//try end



}//AppletEx1 실행기 종료
