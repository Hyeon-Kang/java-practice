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

<<<<<<< HEAD
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
=======
public class AppletTest extends Applet implements ActionListener{
	Choice search; //寃��깋 議곌굔 �꽑�깮諛뺤뒪 �꽑�뼵
	Label label; //�씪踰� �꽑�뼵
	TextField textField; //媛믪쓣 �엯�젰諛쏆쓣 �뀓�뒪�듃 �븘�뱶 �꽑�뼵
	TextArea Area; //寃곌낵 媛믪쓣 異쒕젰�븷 �뀓�뒪�듃 �뿉由ъ뼱 �꽑�뼵
	Button button; //寃��깋踰꾪듉 �꽑�뼵
>>>>>>> 6a2fababb3697996133c69b26731bd191a20ff63


private String url = "jdbc:inetdae7://210.115.229.77:2433";
// 二쇱냼 媛� ����옣 蹂��닔, JDBC�쓽 merlia瑜� �궗�슜�븯湲� �븣臾몄뿉 inetdae7�쓣 �궗�슜.
private String user = "20155204";
// ID 媛� ����옣 蹂��닔
private String pass = "password";   ////(蹂�寃� �븘�슂)
// PW 媛� ����옣 蹂��닔


//JDBC �뱶�씪�씠踰� 濡쒕뵫
public void init() {
	try{
		Class.forName("com.inet.tds.TdsDriver");
	}
	catch(ClassNotFoundException e) {
		System.out.println("Class Loading Failed");
		//濡쒕뵫 �떎�뙣�떆 異쒕젰 硫붿떆吏�
	}
}

<<<<<<< HEAD

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

=======
//�씤�꽣�럹�씠�뒪 援ъ꽦
public void start() {
	setLayout(new FlowLayout()); // �젅�씠�븘�썐 諛곗튂�옄
	label = new Label("�옉�뾽 �꽑�깮"); // �븞�궡臾멸뎄
	add(label); // �씪踰⑥텛媛�

	
	search = new Choice(); //�꽑�깮 (泥댄겕諛뺤뒪)
	search.add("--");
	search.add("�쟾泥�"); // �쟾泥� �뀑
	search.add("�씠由�"); // �씠由� �뀑
	search.add("�굹�씠"); // �굹�씠 �뀑
	search.add("�꽦蹂�"); // �꽦蹂� �뀑
	search.add("--");  					////
	search.add("�궫�엯");
	search.add("�궘�젣");
	search.add("媛깆떊");
	add(search); // �꽑�깮諛뺤뒪 異붽��
	

	textField = new TextField(10); //�뀓�뒪�듃 �븘�뱶 媛앹껜 �깮�꽦(寃��깋�뼱 �엯�젰 �쁺�뿭)
	add(textField);// �뀓�뒪�듃 �븘�뱶 異붽��(寃��깋�뼱 �엯�젰 �쁺�뿭)
	button = new Button("search"); //"search" 踰꾪듉 異붽��
	add(button);
	Area = new TextArea(10,50); //�뀓�뒪�듃 �뿉由ъ뼱 異붽��
	add(Area);
	button.addActionListener(this); // 踰꾪듉 �븸�뀡 吏��젙.
}

//Action event handle (荑쇰━ �꽑�깮)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	//而ㅻ꽖�뀡 媛앹껜 珥덇린�솕
	Statement stmt = null;
	//�뒪�뀒�씠�듃癒쇳듃 媛앹껜 珥덇린�솕
	
	String st = ae.getActionCommand();
	//臾몄옄�뿴 蹂��닔 st�뿉  ActionEvent �씤�옄 �궡�슜 ����옣.
	String item = search.getSelectedItem();
	//臾몄옄�뿴 蹂��닔 item�뿉 search�꽑�깮諛뺤뒪�쓽 �꽑�깮 �븘�씠�뀥 臾몄옄�뿴 ����옣.
	
	//search �궡�슜 �꽑�깮 媛믪뿉 �뵲瑜� 遺꾧린 諛쒖깮
	if(st.equals("search")) {
		String n = textField.getText();
		//臾몄옄�뿴 蹂��닔 n�뿉 �뀓�뒪�듃 �븘�뱶�쓽 �궡�슜 媛��졇�삤湲�
		
		//�쟾泥� �꽑�깮
		if(item.equals("--")) {}
		
		else if(item.equals("�쟾泥�")) //�꽑�깮諛뺤뒪�뿉�꽌 �쟾泥� �꽑�깮
		{
			TotalgetDBSearch(); //�쟾泥� 寃��깋 硫붿냼�뱶 �샇異�
		}
	
	
		//�씠由� �꽑�깮
		else if(item.equals("�씠由�")) //�꽑�깮諛뺤뒪�뿉�꽌 �씠由� �꽑�깮
		{
			NamegetDBSearch(n); //�씠由� 寃��깋 硫붿냼�뱶 �샇異�
		}
		
		//�굹�씠 �꽑�깮
		else if(item.equals("�굹�씠")) //�꽑�깮諛뺤뒪�뿉�꽌 �굹�씠 �꽑�깮
		{
			AgeSearch(n); //�굹�씠 寃��깋 硫붿냼�뱶 �샇異�
		}
		
		//�꽦蹂� �꽑�깮
		else if(item.equals("�꽦蹂�")) //�꽑�깮諛뺤뒪�뿉�꽌 �쟾泥� �꽑�깮
		{
			SexSearch(n); //�쟾泥� 寃��깋 硫붿냼�뱶 �샇異�
		}
		
		//�궫�엯 �샃�뀡
		if(item.equals("�궫�엯")) {
				
			Insert(n); //�궫�엯 �븿�닔�뿉 臾몄옄�뿴 n �꽔�뼱 �떎�뻾
		}
		
		//�궘�젣 �샃�뀡
		else if(item.equals("�궘�젣")) {
					
			nameDelete(n); // �궘�젣�븿�닔 �떎�뻾
		}		
		
		//媛깆떊 �샃�뀡
		else if(item.equals("媛깆떊")) {
					
		}
		
	}
	

}


//臾몄옄�뿴 �뒪�뵆由� 硫붿꽌�뱶
>>>>>>> 6a2fababb3697996133c69b26731bd191a20ff63

	button = new Button("입력");
	add(button);
	button.addActionListener(this);
}


<<<<<<< HEAD
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
=======


//�쟾泥닿���깋 硫붿냼�뱶
private void TotalgetDBSearch() {
	Connection con = null;
	Statement stmt = null;
	
	try {
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204"); //DBNAME �엯�젰(理쒖긽�쐞 �뀒�씠釉� 臾띠쓬)
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("�쟾泥닿���깋 寃��깋 ...\n"); //�븞�궡臾멸뎄 異쒕젰
		
		while(result.next()) //�닚李⑤줈 �씫�뀋
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�씠由� : " + Name + " �굹�씠 : " + age+ " �꽦蹂� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		}
	catch(Exception ee) {System.out.println(ee);}
		
	}//try end


// �씠由� 寃��깋�쓽 吏덉쓽泥섎━�솕
private void NamegetDBSearch(String n)//�씠由� 寃��깋
// �궗�슜�옄媛� �엯�젰�븳 �씠由꾩쓣 媛믪쑝濡� 諛쏅뒗�떎.
{
	Connection con = null; //�궗�슜�븷 �뿰寃� 珥덇린�솕
	Statement stmt = null; //stmt 媛앹껜 珥덇린�솕
	try
	{
		con = DriverManager.getConnection(url, user, pass); //�뱶�씪�씠踰� 留ㅻ땲��� �젒�냽 �젙蹂� �엯�젰
		con.setCatalog("20155204"); // �젒�냽 �뀒�씠釉� 寃쎈줈(理쒖긽�쐞 寃쎈줈)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE name = '"+n+"'"); //�뮘�뿉 �엯�젰 �뒪�듃留� 遺숈씠湲�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("�씠由꾩쑝濡� 寃��깋 ...\n"); //�븞�궡臾멸뎄 異쒕젰
		while(result.next()) //�닚李⑤줈 �씫�뀋
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�씠由� : " + Name + " �굹�씠 : " + age+ " �꽦蹂� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�옉�뾽 醫낅즺 諛� 怨듦컙 �솗蹂�
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//�삤瑜� 諛쒖깮 �떆 硫붿떆吏� 異쒕젰
	}
}





//�굹�씠 寃��깋�쓽 吏덉쓽 泥섎━�솕
private void AgeSearch(String num)//�굹�씠 寃��깋
//�궗�슜�옄媛� �엯�젰�븳 �굹�씠瑜� 媛믪쑝濡� 諛쏅뒗�떎.
{
	Connection con = null; //�궗�슜�븷 �뿰寃� 珥덇린�솕
	Statement stmt = null; //stmt 媛앹껜 珥덇린�솕
	try
	{
		con = DriverManager.getConnection(url, user, pass); //�뱶�씪�씠踰� 留ㅻ땲��� �젒�냽 �젙蹂� �엯�젰
		con.setCatalog("20155204"); // �젒�냽 �뀒�씠釉� 寃쎈줈(理쒖긽�쐞 寃쎈줈)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age = "+num); //�뮘�뿉 �엯�젰 �뒪�듃留� 遺숈씠湲�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		while(result.next()) //�닚李⑤줈 �씫�뀋
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�씠由� : " + Name + " �굹�씠 : " + age+ " �꽦蹂� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�옉�뾽 醫낅즺 諛� 怨듦컙 �솗蹂�
		//�옉�뾽 醫낅즺 諛� 怨듦컙 �솗蹂�
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//�삤瑜� 諛쒖깮 �떆 硫붿떆吏� 異쒕젰
	}
}








//�꽦蹂� 寃��깋�쓽 吏덉쓽 泥섎━�솕
private void SexSearch(String s)//�굹�씠 寃��깋
//�궗�슜�옄媛� �엯�젰�븳 �굹�씠瑜� 媛믪쑝濡� 諛쏅뒗�떎.
{
	Connection con = null; //�궗�슜�븷 �뿰寃� 珥덇린�솕
	Statement stmt = null; //stmt 媛앹껜 珥덇린�솕
	try
	{
		con = DriverManager.getConnection(url, user, pass); //�뱶�씪�씠踰� 留ㅻ땲��� �젒�냽 �젙蹂� �엯�젰
		con.setCatalog("20155204"); // �젒�냽 �뀒�씠釉� 寃쎈줈(理쒖긽�쐞 寃쎈줈)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex = '"+s+"'"); //�뮘�뿉 �엯�젰 �뒪�듃留� 遺숈씠湲�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		
		while(result.next()) //�닚李⑤줈 �씫�뀋
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�씠由� : " + Name + " �굹�씠 : " + age+ " �꽦蹂� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�옉�뾽 醫낅즺 諛� 怨듦컙 �솗蹂�
		//�옉�뾽 醫낅즺 諛� 怨듦컙 �솗蹂�
	}
	 
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//�삤瑜� 諛쒖깮 �떆 硫붿떆吏� 異쒕젰
	}
}





//private void Insert(String name, String age, String sex)
//�뜲�씠�꽣 �궫�엯 硫붿냼�뱶
private void Insert(String n) {
>>>>>>> 6a2fababb3697996133c69b26731bd191a20ff63
	try {
		Connection con;
		Statement stmt;
		//Connection 媛앹껜瑜� �궗�슜�븯�뿬 DB�뿉 �뿰寃곗쓣 �꽕�젙
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog(user);
		//吏덉쓽臾� �옉�꽦�쓣 �쐞�븳 statement 媛앹껜 �깮�꽦
		stmt = con.createStatement();
		
		
<<<<<<< HEAD
		//생성된 테이블에 값을 삽입
		stmt.executeQuery("INSERT INTO hallym VALUES('"+n+"',"+ a +",'" + i + "','" + p+ "','" +e+ "','" + ph +"')");
		
=======
		
		// 臾몄옄�뿴 parsing
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
		
		//�깮�꽦�맂 �뀒�씠釉붿뿉 媛믪쓣 �궫�엯
		stmt.executeQuery("INSERT INTO customer VALUES('"+ name +"'," +age +",'"+ sex +"')");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		Area.insertText("\nInsert Complete.\n", 0); //�삤瑜섍�� �쑉吏�留� 荑쇰━�뒗 �옉�룞�븳�떎. �삤瑜섎븣臾몄뿉 �씠�븯 臾멸뎄 異쒕젰�씠 �븞 �릺�뒗嫄� 媛숈쓬.
		//�뀓�뒪�듃 area 珥덇린�솕

>>>>>>> 6a2fababb3697996133c69b26731bd191a20ff63
		
		//�옉�뾽 醫낅즺 �썑 statement, connection 媛앹껜 �떕湲�
		stmt.close();
		con.close();
		
		
	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
		
		//�삤瑜� 硫붿떆吏� 寃�異�
	}
}

}//AppletEx1 end

<<<<<<< HEAD
=======
//�뜲�씠�꽣 �궘�젣 硫붿냼�뱶
void nameDelete(String r) {
	
	try {
		Connection con;
		Statement stmt;
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204");
		
		stmt = con.createStatement();
		//�뿰寃곕맂 DB臾몄뿉�꽌 吏덉쓽臾� �옉�꽦�쓣 �쐞�븳 媛앹껜 �깮�꽦
		
		stmt.executeQuery("delete FROM customer WHERE name = '"+r+"'");
		//荑쇰━臾�

		//�뀓�뒪�듃 area 珥덇린�솕
	/*	String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		*/
		
		Area.setText("Delete Complete.");
		
		//醫낅즺 諛� 怨듦컙�솗蹂� �떆�옉
		stmt.close();
		con.close();

	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
	}
}//�궘�젣 硫붿냼�뱶 醫낅즺


//媛깆떊 硫붿냼�뱶 (媛깆떊 �떆 留덈떎 �굹�씠 1�뵫 異붽��)


}// �쟾泥� 醫낅즺
>>>>>>> 6a2fababb3697996133c69b26731bd191a20ff63
