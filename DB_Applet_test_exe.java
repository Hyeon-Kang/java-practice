/// Applet_test_�����
///�ּ� �ް� �ڵ� �����ʿ� need to optimize
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletEx1Execute extends Applet implements ActionListener{

	Label label; // �� ����
	TextArea Area; //��� ���� ����� �ؽ�Ʈ ������ ����
	Button button; //�Է¹�ư ����


private String url = "jdbc:inetdae7://210.115.229.77:2433"; //merlia �� �̿��ϱ� ������ inetdae7
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
	
	label = new Label("�� ��");
	add(label);
	
	button = new Button("search");
	add(button);

	Area = new TextArea(10,50);
	add(Area);
	button.addActionListener(this);
}//start end


//Action event handle (���� ����)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	
	searchh();
}//action end



//��ü�˻� �޼ҵ�
private void searchh() {
	Connection con = null;
	Statement stmt = null;
	
	try {
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204"); //DBNAME �Է�(�ֻ��� ���̺� ����)
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM hallym");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("��ü�˻� ...\n"); //�ȳ����� ���
		
		while(result.next()) //������ �о����
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String id = result.getString(3);
			String pass = result.getString(4);
			String email = result.getString(5);
			String phone = result.getString(6);
			
			
			String value = "�̸� : " + Name + " /���� : " + age+ " /ID : "+ id + " /Password : "+pass+ " /e-mail : "+email+" /phone : "+phone+"\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		}
	catch(Exception ee) {System.out.println(ee);}
		
	}//try end



}//AppletEx1 ����� ����