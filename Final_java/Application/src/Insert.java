import java.sql.*;

public class Insert{
public static void main(String[] args) throws Exception {
String dburl = "jdbc:mysql://localhost:3306/harshdb";
String dbuser = "root";
String dbpass = "";
String drivername = "com.mysql.jdbc.Driver";

// Step 1: Arrange and Load Drivers:
Class.forName(drivername);

// Step 2: Establish Connection:
Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
// Check is Connection established?
if (con != null) {
System.out.println("Connection Sucessful");
}
else {
System.out.println("Connection Failed");
}
// Step 3: Create statement Object
Statement st = con.createStatement();

// Step 4: Write insert query for Faculty Table
String sql = "insert into social_media(Firstname,Lastname,Username,password,follower,likes,varify) value ('Umang','Panchal','Umang_1','111',600,500,'not'),('Virat','patel','virat12','virat_12',90,30,'not'),('Mahendrasing','Dhoni','ms_12','777',900,950,'not'),('Suresh','Raina','suresh12','1212',50,100,'not')";

// Step 5: Execute Query for insert, delete, update using executeUpdate()
int r = st.executeUpdate(sql); // This provides number of rows affected
if (r > 0) {
System.out.println("Insertion Success");
}
else {
System.out.println("Insertion failed");
}
}
}

