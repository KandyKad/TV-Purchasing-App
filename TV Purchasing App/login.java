// Login
package tv;
import java.io.*;
import java.sql.*;

public class login {

    static String lo() throws IOException {
        try {
            String username, password, c, q;
            DataInputStream ds = new DataInputStream(System.in);
            System.out.println("Choose your option:");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            int no = Integer.parseInt(ds.readLine());
            //Login
            if (no == 1) {
                System.out.println("Enter user_name : ");
                username = ds.readLine();
                System.out.println("Enter your password : ");
                password = ds.readLine();
                q = "select password from login where username='" + username + "'";
                
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(q);
                if (rs.next()) {
                    if (password.equals(rs.getString(1))) {
                        System.out.println("********WELCOME "+username+" ********");
                        return username;
                        
                    } else {
                        System.out.println("****Ypur password is incorrect****");
                        con.close();
                        return lo();
                    }
                } else {
                    System.out.println("***User not found in the database.**");
                    con.close();
                    return lo();
                }
                
            } 
            //Signup
            else if (no == 2) {
                System.out.println("Enter user_name : ");
                username = ds.readLine();
                System.out.println("Enter your password : ");
                password = ds.readLine();
                System.out.println("pls confirm your password : ");
                c = ds.readLine();
                if (c.equals(password)) {
                    q = "insert into login values('" + username + "','" + password + "')";
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
                    Statement stmt = con.createStatement();
                    int x = stmt.executeUpdate(q);
                    if (x > 0) {
                        System.out.println("You have registered succesfully......");
                        System.out.println("You can login now.");
                    } else {
                        System.out.println("Sorry Techinical fault you could not get registered ");
                        System.out.println("Please try again");
                    }
                    con.close();
                    return lo();
                } else {
                    System.out.println("..............Your password did not match. Please try again...............");
                   
                   return lo();
                }
            } else {
                System.out.println("Enter right value :");
                return lo();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
     return "Try again";
    }
}
