///최종 편집일 181120

//사용할 땐 password 변수 값 변경할 것!

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;
import java.util.StringTokenizer;

//메인 applet 정의
public class AppletEx1 extends Applet implements ActionListener{

	//사용할 라벨 선언
	Label lname; 
	Label lage;
	Label lid;
	Label lpass;
	Label lmail;
	Label lphone;

	//값을 입력받을 텍스트 필드 선언
	TextField tname;
	TextField tage;
	TextField tid;
	TextField tpass;
	TextField tmail;
	TextField tphone;

	
	TextArea Area; //결과 값을 출력할 텍스트 에리어 선언
	Button button; //입력버튼 선언


private String url = "jdbc:inetdae7://210.115.229.77:2433";
// 주소 값 저장 변수, merlia사용하므로 inetdae7 기술
	
private String user = "20155204";
// ID 값 저장 변수
	
private String pass = "(비밀번호)";
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
	
	//이름 라벨, 텍스트 필드 추가
	lname = new Label("이 름"); 
	add(lname);
	tname = new TextField(10);
	add(tname);
	
	//나이 라벨, 텍스트 필드 추가
	lage = new Label("나 이");
	add(lage); // 라벨추가
	tage = new TextField(10);
	add(tage);
	
	//ID 라벨, 텍스트 필드 추가
	lid = new Label("ID");
	add(lid);
	tid = new TextField(10);
	add(tid);
	
	//password 라벨, 텍스트 필드 추가
	lpass = new Label("password");
	add(lpass);
	tpass = new TextField(10);
	add(tpass);
	
	//email라벨, 텍스트 필드 추가
	lmail = new Label("e-mail");
	add(lmail);
	tmail = new TextField(10);
	add(tmail);
	
	//phone 라벨, 텍스트 필드 추가
	lphone = new Label("phone");
	add(lphone);
	tphone = new TextField(10);
	add(tphone);

	//입력 버튼 추가 및 버튼액션함수 지정
	button = new Button("입력");
	add(button);
	button.addActionListener(this);
}


//Action event handle (쿼리 선택옵션)
public void actionPerformed(ActionEvent ae) {
	
	//커넥션 객체 초기화
	Connection con = null;
	//스테이트먼트 객체 초기화
	Statement stmt = null;
	
	//반응함수 인자값 st에 저장
	String st = ae.getActionCommand();
	
	//name텍스트 필드 값 n에 저장
	String n = tname.getText();
	
	//age 텍스트 에리어 값을 정수로 반환하여 a에 저장
	int a = Integer.parseInt(tage.getText()); //문자열을 정수로 변환
	
	//순서대로 id, password, mail, phone 텍스트 받아 저장
	String i = tid.getText();
	String p = tpass.getText();
	String e = tmail.getText();
	String ph = tphone.getText();
	
	//삽입 함수에 인자 전달
	Insert1(n, a, i, p, e, ph);
}//actionPerformed end


//데이터 삽입 메소드
private void Insert1(String n, int a, String i, String p, String e, String ph) {
	try {
		//커넥션, 스테이트먼트 객체 선언
		Connection con;
		Statement stmt;
		
		//Connection 객체를 사용하여 DB에 연결을 설정
		con = DriverManager.getConnection(url, user, pass);
		//접근 DB 지정(접속 계정의 최상위 그룹 이름_DB지정)
		con.setCatalog(user);
		
		//질의문 작성을 위한 statement 객체 생성
		stmt = con.createStatement();
		
		
		//생성된 테이블에 값을 삽입
		stmt.executeQuery("INSERT INTO hallym VALUES('"+n+"',"+ a +",'" + i + "','" + p+ "','" +e+ "','" + ph +"')");
		
		
		//작업 종료 후 statement, connection 객체 닫기
		stmt.close();
		con.close();
		
		
	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
		
		//오류 메시지 검출
	}
}

}//AppletEx1 end

