//JAVA JDBC MYSQL BASIC USER INPUT, PREPARED STATEMENT, SELECT, QUERY
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        try {  //credentials
            String url = "jdbc:mysql://localhost:3306/demo";
            String username = "root";
            String password = "";
            String sql = "SELECT * FROM account"; //query
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);//establishing connection
            if(conn==null){
                System.out.println("connection failed");//check connection exists or not
            } else {
                System.out.println("Connection established successfully");
                Statement st=conn.createStatement(); //statement
                ResultSet rs=st.executeQuery(sql); //result set executing query
                while(rs.next()){ //printing query resulr
                    System.out.println(rs.getInt(1)+ "  " +rs.getString(2) + "  " +rs.getString(3));
                }
                String sql2 = "INSERT INTO account (Id, uname, pwd) VALUES (?, ?, ?)"; //adding data
                PreparedStatement pstmt = conn.prepareStatement(sql2);//prepared statement to avoid sql injection
                pstmt.setInt(1, 4);
                pstmt.setString(2,"real");
                pstmt.setString(3,"me");
                int r = pstmt.executeUpdate();
                if(r>0){
                    System.out.println("Successfully inserted");//success msg
                }else{ System.out.println("Failed insertion");}
                ResultSet v=st.executeQuery(sql);
                while(v.next()){
                    System.out.println(v.getInt(1)+ "  " +v.getString(2)+ "  " +v.getString(3));
                }
                Scanner sc = new Scanner(System.in);\
                
                // getting input from client
                System.out.println("Enter pwd ");
                String z =sc.nextLine();
                System.out.println("Enter id");
                int x =sc.nextInt();
                sc.nextLine();
                System.out.println("Enter uname");
                String y =sc.nextLine();
                sc.close();
                String sql4 = "INSERT INTO account (Id, uname, pwd) VALUES (?, ?, ?)";
                PreparedStatement pstmt2=conn.prepareStatement(sql4);
                pstmt2.setInt(1, x);
                pstmt2.setString(2, y);
                pstmt2.setString(3, z);
                int c=pstmt2.executeUpdate();
                if(c>0){
                    System.out.println("Successfully inserted");} 
                else{System.out.println("Failed insertion");}
                conn.close();}
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

