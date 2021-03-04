//Version 1
// 15/10/2020  (1:37AM)
// Television Buying App
//1. Login - USERNAME, PASSWORD
//2. Stock - SUPPLIER, TV-BRAND, TV-TYPE, TV-SIZE, PRICE_PER_PIECE, QUANTITY, PRODUCT ID(PID),
//3. Admin - USERNAME, PASSWORD
//4. CART - USERNAME, PRODUCT ID, COUNT_PER_PRODUCT_ID
//5. Payment History- USERNAME, PRODUCT IDS, TIMESTAMP
////////////Create Table First using Below Queries////////////////////////
//CREATE TABLE login(USERNAME VARCHAR(25) NOT NULL PRIMARY KEY, PASSWORD VARCHAR(20));
//CREATE TABLE stock(BRAND VARCHAR(20), TVTYPE VARCHAR(10), TVSIZE NUMBER(3), PRICE NUMBER(6), QUANTITY NUMBER(4));
//CREATE TABLE admin(ADMINNAME VARCHAR(25), ADMINPASS VARCHAR(20));
//CREATE TABLE cart(SRNO NUMBER(3) NOT NULL PRIMARY KEY , USERNAME VARCHAR(25) REFERENCES login(USERNAME), BRAND VARCHAR(20), TVTYPE VARCHAR(10), TVSIZE NUMBER(3), PRICE NUMBER(6), COUNT NUMBER(4)); 
//CREATE TABLE history(USERNAME VARCHAR(25) REFERENCES login(USERNAME), BRAND VARCHAR(20), TVTYPE VARCHAR(10), TVSIZE NUMBER(3), PRICE NUMBER(6), COUNT NUMBER(3), DT TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

//FOR MANUAL INPUT, FORMAT FOR TV ENTRIES are as following:
// "BRAND TYPE TVSIZE PRICE QUANTITY"(without "" quotes) in one line.

package tv;
import java.io.*;
import java.sql.*;

public class TV {

    public static void main(String[] args) {
        
        try
        {
            System.out.println("Enter Choice no. who you are.");
            System.out.println("Enter 1 for Customer");
            System.out.println("Enter 2 for Dealer");
            DataInputStream ds = new DataInputStream(System.in);
            int person=Integer.parseInt(ds.readLine());
        	            
            int m = 1;
          if (person == 1) // Customer
          { 
            
            String username=login.lo();
            while(m==1)
            {
              m = menuforuser.mfu(username);
            }

          }
                else if(person == 2) //Dealer
          {
        while(true)
        {
            
            System.out.println("Enter admin user_name : ");
            String username=ds.readLine();
            System.out.println("Enter admin password : ");
            String password=ds.readLine();
            String q1="select adminpass from admin where adminname='"+username+"'";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(q1);
            if(rs.next())
            {
                if(password.equals(rs.getString(1)))
                {
                    System.out.println("*********WELCOME "+username+"*********");
                    menufordealer.mdu();
                    break;
                }
                else
                {
                    System.out.println("***Your Password is incorrect.***");
                    continue;
                }
            }
                else {
                    System.out.println("***User not found in the database.**");
                    continue;
                }
        }


          }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
