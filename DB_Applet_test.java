import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;
import java.util.StringTokenizer;




public class AppletTest extends Applet implements ActionListener{
	Choice search; //�˻� ���� ���ùڽ� ����
//	Choice option; //��� ���� �ڽ� ����
	Label label; //�� ����
	TextField textField; //���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextArea Area; //��� ���� ����� �ؽ�Ʈ ������ ����
	Button button; //�˻���ư ����


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


public void start() {
	setLayout(new FlowLayout()); // ���̾ƿ� ��ġ��
	label = new Label("�۾� ����"); // �ȳ�����
	add(label); // ���߰�
/*	
	option = new Choice();
	option.add("--");
	option.add("����");
	option.add("����");
	option.add("����");
	add(option);
	
*/	
	
	search = new Choice(); //���� (üũ�ڽ�)
	search.add("--");
	search.add("��ü"); // ��ü ��
	search.add("�̸�"); // �̸� ��
	search.add("����"); // ���� ��
	search.add("����"); // ���� ��
	search.add("--");  					////
	search.add("����");
	search.add("����");
	search.add("����");
	add(search); // ���ùڽ� �߰�
	

	textField = new TextField(10); //�ؽ�Ʈ �ʵ� ��ü ����(�˻��� �Է� ����)
	add(textField);// �ؽ�Ʈ �ʵ� �߰�(�˻��� �Է� ����)
	button = new Button("search");
	add(button);
	Area = new TextArea(10,50);
	add(Area);
	button.addActionListener(this);
}

//Action event handle (���� ����)
public void actionPerformed(ActionEvent ae) {
	Connection con = null;
	Statement stmt = null;
	
	String st = ae.getActionCommand();
	String item = search.getSelectedItem();
	
	
	
	if(st.equals("search")) {
		String n = textField.getText();
		
		//��ü ����
		if(item.equals("--")) {}
		
		else if(item.equals("��ü")) //���ùڽ����� ��ü ����
		{
			TotalgetDBSearch(); //��ü �˻� �޼ҵ� ȣ��
		}
	
	
		//�̸� ����
		else if(item.equals("�̸�")) //���ùڽ����� �̸� ����
		{
			NamegetDBSearch(n); //�̸� �˻� �޼ҵ� ȣ��
		}
		
		//���� ����
		else if(item.equals("����")) //���ùڽ����� ���� ����
		{
			AgeSearch(n); //���� �˻� �޼ҵ� ȣ��
		}
		
		//���� ����
		else if(item.equals("����")) //���ùڽ����� ��ü ����
		{
			SexSearch(n); //��ü �˻� �޼ҵ� ȣ��
		}
		
		//���� �ɼ�
		if(item.equals("����")) {
				
			Insert(n);
		}
		
		//���� �ɼ�
		else if(item.equals("����")) {
					
			nameDelete(n);
		}		
		
		//���� �ɼ�
		else if(item.equals("����")) {
					
		}
		
	}
	

}


//���ø� �޼���

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




//��ü�˻� �޼ҵ�
private void TotalgetDBSearch() {
	Connection con = null;
	Statement stmt = null;
	
	try {
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204"); //DBNAME �Է�(�ֻ��� ���̺� ����)
		stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("��ü�˻� �˻� ...\n"); //�ȳ����� ���
		
		while(result.next()) //������ �Ф�
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�̸� : " + Name + " ���� : " + age+ " ���� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		}
	catch(Exception ee) {System.out.println(ee);}
		
	}//try end


// �̸� �˻��� ����ó��ȭ
private void NamegetDBSearch(String n)//�̸� �˻�
// ����ڰ� �Է��� �̸��� ������ �޴´�.
{
	Connection con = null; //����� ���� �ʱ�ȭ
	Statement stmt = null; //stmt ��ü �ʱ�ȭ
	try
	{
		con = DriverManager.getConnection(url, user, pass); //����̹� �Ŵ��� ���� ���� �Է�
		con.setCatalog("20155204"); // ���� ���̺� ���(�ֻ��� ���)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE name = '"+n+"'"); //�ڿ� �Է� ��Ʈ�� ���̱�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		Area.setText("�̸����� �˻� ...\n"); //�ȳ����� ���
		while(result.next()) //������ �Ф�
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�̸� : " + Name + " ���� : " + age+ " ���� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�۾� ���� �� ���� Ȯ��
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//���� �߻� �� �޽��� ���
	}
}





//���� �˻��� ���� ó��ȭ
private void AgeSearch(String num)//���� �˻�
//����ڰ� �Է��� ���̸� ������ �޴´�.
{
	Connection con = null; //����� ���� �ʱ�ȭ
	Statement stmt = null; //stmt ��ü �ʱ�ȭ
	try
	{
		con = DriverManager.getConnection(url, user, pass); //����̹� �Ŵ��� ���� ���� �Է�
		con.setCatalog("20155204"); // ���� ���̺� ���(�ֻ��� ���)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age = "+num); //�ڿ� �Է� ��Ʈ�� ���̱�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		while(result.next()) //������ �Ф�
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�̸� : " + Name + " ���� : " + age+ " ���� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�۾� ���� �� ���� Ȯ��
		//�۾� ���� �� ���� Ȯ��
	}
	
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//���� �߻� �� �޽��� ���
	}
}








//���� �˻��� ���� ó��ȭ
private void SexSearch(String s)//���� �˻�
//����ڰ� �Է��� ���̸� ������ �޴´�.
{
	Connection con = null; //����� ���� �ʱ�ȭ
	Statement stmt = null; //stmt ��ü �ʱ�ȭ
	try
	{
		con = DriverManager.getConnection(url, user, pass); //����̹� �Ŵ��� ���� ���� �Է�
		con.setCatalog("20155204"); // ���� ���̺� ���(�ֻ��� ���)
		stmt = con.createStatement(); 
		
		ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex = '"+s+"'"); //�ڿ� �Է� ��Ʈ�� ���̱�

		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);

		
		while(result.next()) //������ �Ф�
		{
			String Name = result.getString(1);
			String age = Integer.toString(result.getInt(2));
			String sex = result.getString(3);
			
			String value = "�̸� : " + Name + " ���� : " + age+ " ���� : "+ sex + "\n";
			int index = Area.getText().length();
			Area.insertText(value, index);
		}
		con.close();
		stmt.close();
		//�۾� ���� �� ���� Ȯ��
		//�۾� ���� �� ���� Ȯ��
	}
	 
	
	catch(SQLException se) {
		System.err.println(se.getMessage());
		//���� �߻� �� �޽��� ���
	}
}





//private void Insert(String name, String age, String sex)
//������ ���� �޼ҵ�
private void Insert(String n) {
	try {
		Connection con;
		Statement stmt;
		//Connection ��ü�� ����Ͽ� DB�� ������ ����
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog(user);
		//���ǹ� �ۼ��� ���� statement ��ü ����
		stmt = con.createStatement();
		
		
		
		// ���ڿ� parsing
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
		
		//������ ���̺� ���� ����
		stmt.executeQuery("INSERT INTO customer VALUES('"+ name +"'," +age +",'"+ sex +"')");
		
		String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		
		Area.insertText("\nInsert Complete.\n", 0); //������ ������ ������ �۵��Ѵ�. ���������� ���� ���� ����� �� �Ǵ°� ����.
		//�ؽ�Ʈ area �ʱ�ȭ

		
		//�۾� ���� �� statement, connection ��ü �ݱ�
		stmt.close();
		con.close();
		
		
	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
		
		//���� �޽��� ����
	}
}


//������ ���� �޼ҵ�
void nameDelete(String r) {
	
	try {
		Connection con;
		Statement stmt;
		con = DriverManager.getConnection(url, user, pass);
		con.setCatalog("20155204");
		
		stmt = con.createStatement();
		//����� DB������ ���ǹ� �ۼ��� ���� ��ü ����
		
		stmt.executeQuery("delete FROM customer WHERE name = '"+r+"'");
		//������

		//�ؽ�Ʈ area �ʱ�ȭ
	/*	String count = Area.getText();
		int c = count.length();
		Area.replaceText(" ", 0, c);
		*/
		
		Area.setText("Delete Complete.");
		
		//���� �� ����Ȯ�� ����
		stmt.close();
		con.close();

	}
	catch(SQLException se) {
		System.err.println(se.getMessage());
	}
}//���� �޼ҵ� ����


//���� �޼ҵ� (���� �� ���� ���� 1�� �߰�)


}// ��ü ����
