import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;
import java.util.StringTokenizer;

public class AppletEx1 extends Applet implements ActionListener{
/*	Choice search; //검색 조건 선택박스 선언
	Choice option; //기능 선택 박스 선언
*/
	//Label label;

	Label lname; //라벨 선언
	Label lage;
	Label lid;
	Label lpass;
	Label lmail;
	Label lphone;

	//TextField textField;

	TextField tname; //값을 입력받을 텍스트 필드 선언
	TextField tage;
	TextField tid;
	TextField tpass;
	TextField tmail;
	TextField tphone;

	
	TextArea Area; //결과 값을 출력할 텍스트 에리어 선언
	Button button; //입력버튼 선언


private String url = "jdbc:inetdae7://210.115.229.77:2433";
// 주소 값 저장 변수
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
	
	lname = new Label("이 름"); // 안내문구
	add(lname);
	tname = new TextField(10);
	add(tname);
	
	lage = new Label("나 이");
	add(lage); // 라벨추가
	tage = new TextField(10);
	add(tage);
	
	lid = new Label("ID");
	add(lid);
	tid = new TextField(10);
	add(tid);
	
	lpass = new Label("password");
	add(lpass);
	tpass = new TextField(10);
	add(tpass);
	
	lmail = new Label("e-mail");
	add(lmail);
	tmail = new TextField(10);
	add(tmail);
	
	lphone = new Label("phone");
	add(lphone);
	tphone = new TextField(10);
	add(tphone);


	button = new Button("입력");
	add(button);
	button.addActionListener(this);
}


//Action event handle (쿼리 선택)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	String n = tname.getText();
	//String a = tage.getText();
	int a = Integer.parseInt(tage.getText()); //문자열을 정수로 변환
	String i = tid.getText();
	String p = tpass.getText();
	String e = tmail.getText();
	String ph = tphone.getText();
	
	Insert1(n, a, i, p, e, ph);
}


//데이터 삽입 메소드
private void Insert1(String n, int a, String i, String p, String e, String ph) {
	try {
		Connection con;
		Statement stmt;
		//Connection 객체를 사용하여 DB에 연결을 설정
		con = DriverManager.getConnection(url, user, pass);
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

