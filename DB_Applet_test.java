import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;
import java.util.StringTokenizer;


public class AppletTest extends Applet implements ActionListener{
	Choice search; //검색 조건 선택박스 선언
	Label label; //라벨 선언
	TextField textField; //값을 입력받을 텍스트 필드 선언
	TextArea Area; //결과 값을 출력할 텍스트 에리어 선언
	Button button; //검색버튼 선언


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

//인터페이스 구성
public void start() {
	setLayout(new FlowLayout()); // 레이아웃 배치자
	label = new Label("작업 선택"); // 안내문구
	add(label); // 라벨추가

	
	search = new Choice(); //선택 (체크박스)
	search.add("--");
	search.add("전체"); // 전체 셋
	search.add("이름"); // 이름 셋
	search.add("나이"); // 나이 셋
	search.add("성별"); // 성별 셋
	search.add("--");  					////
	search.add("삽입");
	search.add("삭제");
	search.add("갱신");
	add(search); // 선택박스 추가
	

	textField = new TextField(10); //텍스트 필드 객체 생성(검색어 입력 영역)
	add(textField);// 텍스트 필드 추가(검색어 입력 영역)
	button = new Button("search"); //"search" 버튼 추가
	add(button);
	Area = new TextArea(10,50); //텍스트 에리어 추가
	add(Area);
	button.addActionListener(this); // 버튼 액션 지정.
}

//Action event handle (쿼리 선택)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	//커넥션 객체 초기화
	Statement stmt = null;
	//스테이트먼트 객체 초기화
	
	String st = ae.getActionCommand();
	//문자열 변수 st에  ActionEvent 인자 내용 저장.
	String item = search.getSelectedItem();
	//문자열 변수 item에 search선택박스의 선택 아이템 문자열 저장.
	
	//search 내용 선택 값에 따른 분기 발생
	if(st.equals("search")) {
		String n = textField.getText();
		//문자열 변수 n에 텍스트 필드의 내용 가져오기
		
		//전체 선택
		if(item.equals("--")) {}
		
		else if(item.equals("전체")) //선택박스에서 전체 선택
		{
			TotalgetDBSearch(); //전체 검색 메소드 호출
		}
	
	
		//이름 선택
		else if(item.equals("이름")) //선택박스에서 이름 선택
		{
			NamegetDBSearch(n); //이름 검색 메소드 호출
		}
		
		//나이 선택
		else if(item.equals("나이")) //선택박스에서 나이 선택
		{
			AgeSearch(n); //나이 검색 메소드 호출
		}
		
		//성별 선택
		else if(item.equals("성별")) //선택박스에서 전체 선택
		{
			SexSearch(n); //전체 검색 메소드 호출
		}
		
		//삽입 옵션
		if(item.equals("삽입")) {
				
			Insert(n); //삽입 함수에 문자열 n 넣어 실행
		}
		
		//삭제 옵션
		else if(item.equals("삭제")) {
					
			nameDelete(n); // 삭제함수 실행
		}		
		
		//갱신 옵션
		else if(item.equals("갱신")) {
					
		}
		
	}
	

}


//문자열 스플릿 메서드

/*
public void splitString(String n) {
int i = 0;
String []arr = new String[3];
StringTokenizer token =new StringTokenizer(n,",");
	while(token.hasMoreTokens()) {
	arr[i] = token.nextToken();
	i++;
	}
}
	*/




//전체검색 메소드
private void TotalgetDBSearch() {
	Connection con = null;
	Statement stmt = null;
	
	try {
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204"); //DBNAME 입력(최상위 테이블 묶음)
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("전체검색 검색 ...\n"); //안내문구 출력
		
		while(result.next()) //순차로 읽ㅇ
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "이름 : " + Name + " 나이 : " + age+ " 성별 : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		}
	catch(Exception ee) {System.out.println(ee);}
		
	}//try end


// 이름 검색의 질의처리화
private void NamegetDBSearch(String n)//이름 검색
// 사용자가 입력한 이름을 값으로 받는다.
{
	Connection con = null; //사용할 연결 초기화
	Statement stmt = null; //stmt 객체 초기화
	try
	{
		con = DriverManager.getConnection(url, user, pass); //드라이버 매니저 접속 정보 입력
		con.setCatalog("20155204"); // 접속 테이블 경로(최상위 경로)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE name = '"+n+"'"); //뒤에 입력 스트링 붙이기

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("이름으로 검색 ...\n"); //안내문구 출력
		while(result.next()) //순차로 읽ㅇ
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "이름 : " + Name + " 나이 : " + age+ " 성별 : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//작업 종료 및 공간 확보
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//오류 발생 시 메시지 출력
	}
}





//나이 검색의 질의 처리화
private void AgeSearch(String num)//나이 검색
//사용자가 입력한 나이를 값으로 받는다.
{
	Connection con = null; //사용할 연결 초기화
	Statement stmt = null; //stmt 객체 초기화
	try
	{
		con = DriverManager.getConnection(url, user, pass); //드라이버 매니저 접속 정보 입력
		con.setCatalog("20155204"); // 접속 테이블 경로(최상위 경로)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age = "+num); //뒤에 입력 스트링 붙이기

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		while(result.next()) //순차로 읽ㅇ
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "이름 : " + Name + " 나이 : " + age+ " 성별 : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//작업 종료 및 공간 확보
		//작업 종료 및 공간 확보
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//오류 발생 시 메시지 출력
	}
}








//성별 검색의 질의 처리화
private void SexSearch(String s)//나이 검색
//사용자가 입력한 나이를 값으로 받는다.
{
	Connection con = null; //사용할 연결 초기화
	Statement stmt = null; //stmt 객체 초기화
	try
	{
		con = DriverManager.getConnection(url, user, pass); //드라이버 매니저 접속 정보 입력
		con.setCatalog("20155204"); // 접속 테이블 경로(최상위 경로)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex = '"+s+"'"); //뒤에 입력 스트링 붙이기

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		
		while(result.next()) //순차로 읽ㅇ
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "이름 : " + Name + " 나이 : " + age+ " 성별 : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//작업 종료 및 공간 확보
		//작업 종료 및 공간 확보
	}
	 
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//오류 발생 시 메시지 출력
	}
}





//private void Insert(String name, String age, String sex)
//데이터 삽입 메소드
private void Insert(String n) {
	try {
		Connection con;
		Statement stmt;
		//Connection 객체를 사용하여 DB에 연결을 설정
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog(user);
		//질의문 작성을 위한 statement 객체 생성
		stmt = con.createStatement();
		
		
		
		// 문자열 parsing
		int i = 0;
		String []arr = new String[3];
		StringTokenizer token =new StringTokenizer(n,",");
			while(token.hasMoreTokens()) {
			arr[i] = token.nextToken();
			i++;
			}
		
		String name = arr[0];
		String age = arr[1];
		String sex = arr[2];
		
		//생성된 테이블에 값을 삽입
		stmt.executeQuery("INSERT INTO customer VALUES('"+ name +"'," +age +",'"+ sex +"')");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		Area.insertText("\nInsert Complete.\n", 0); //오류가 뜨지만 쿼리는 작동한다. 오류때문에 이하 문구 출력이 안 되는거 같음.
		//텍스트 area 초기화

		
		//작업 종료 후 statement, connection 객체 닫기
		stmt.close();
		con.close();
		
		
	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
		
		//오류 메시지 검출
	}
}


//데이터 삭제 메소드
void nameDelete(String r) {
	
	try {
		Connection con;
		Statement stmt;
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204");
		
		stmt = con.createStatement();
		//연결된 DB문에서 질의문 작성을 위한 객체 생성
		
		stmt.executeQuery("delete FROM customer WHERE name = '"+r+"'");
		//쿼리문

		//텍스트 area 초기화
	/*	String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		*/
		
		Area.setText("Delete Complete.");
		
		//종료 및 공간확보 시작
		stmt.close();
		con.close();

	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
	}
}//삭제 메소드 종료


//갱신 메소드 (갱신 시 마다 나이 1씩 추가)


}// 전체 종료
