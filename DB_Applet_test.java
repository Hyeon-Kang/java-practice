import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;
import java.util.StringTokenizer;

public class AppletEx1 extends Applet implements ActionListener{
/*	Choice search; //�˻� ���� ���ùڽ� ����
	Choice option; //��� ���� �ڽ� ����
*/
	//Label label;

	Label lname; //�� ����
	Label lage;
	Label lid;
	Label lpass;
	Label lmail;
	Label lphone;

	//TextField textField;

	TextField tname; //���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextField tage;
	TextField tid;
	TextField tpass;
	TextField tmail;
	TextField tphone;

	
	TextArea Area; //��� ���� ����� �ؽ�Ʈ ������ ����
	Button button; //�Է¹�ư ����


private String url = "jdbc:inetdae7://210.115.229.77:2433";
// �ּ� �� ���� ����
private String user = "20155204";
// ID �� ���� ����
private String pass = "Ecks0407@";
// PW �� ���� ����


//JDBC ����̹� �ε�
public void init() {
	try{
		Class.forName("com.inet.tds.TdsDriver");
	}
	catch(ClassNotFoundException e) {
		System.out.println("Class Loading Failed");
		//�ε� ���н� ��� �޽���
	}
}


//Interface ����
public void start() {
	setLayout(new FlowLayout()); // ���̾ƿ� ��ġ��
	
	lname = new Label("�� ��"); // �ȳ�����
	add(lname);
	tname = new TextField(10);
	add(tname);
	
	lage = new Label("�� ��");
	add(lage); // ���߰�
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


	button = new Button("�Է�");
	add(button);
	button.addActionListener(this);
}


//Action event handle (���� ����)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	String n = tname.getText();
	//String a = tage.getText();
	int a = Integer.parseInt(tage.getText()); //���ڿ��� ������ ��ȯ
	String i = tid.getText();
	String p = tpass.getText();
	String e = tmail.getText();
	String ph = tphone.getText();
	
	Insert1(n, a, i, p, e, ph);
}


//������ ���� �޼ҵ�
private void Insert1(String n, int a, String i, String p, String e, String ph) {
	try {
		Connection con;
		Statement stmt;
		//Connection ��ü�� ����Ͽ� DB�� ������ ����
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog(user);
		//���ǹ� �ۼ��� ���� statement ��ü ����
		stmt = con.createStatement();
		
		
		//������ ���̺� ���� ����
		stmt.executeQuery("INSERT INTO hallym VALUES('"+n+"',"+ a +",'" + i + "','" + p+ "','" +e+ "','" + ph +"')");
		
		
		//�۾� ���� �� statement, connection ��ü �ݱ�
		stmt.close();
		con.close();
		
		
	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
		
		//���� �޽��� ����
	}
}

}//AppletEx1 end

