package tv;
import java.io.*;
import java.io.BufferedReader;
import java.sql.*;
import java.util.Scanner; 

public class menufordealer
{	
    static void mdu()throws IOException
   {
        try
        {
            int choice;
            while (true)
            {
               DataInputStream ds=new DataInputStream(System.in);
               System.out.println("Please enter the no. of different TVs to be sold:");
               int n = Integer.parseInt(ds.readLine());
               int i=1;
               
               while(n>0)
                  {
                    System.out.println("Enter the "+i+" th TV features: Brand, Type, Size, Price, Quantity");
                    /*String[] arr= new String[5];
                    arr[0]=ds.readLine();
                    arr[1]=ds.readLine();
                    arr[2]=ds.readLine();
                    arr[3]=ds.readLine();
                    arr[4]=ds.readLine();*/
                    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                    
                    String userName = myObj.nextLine();  // Read user input
                    String arr[] = userName.split(" ");
                    
                String check="SELECT * FROM stock where BRAND='"+arr[0]+"'and TVTYPE='"+arr[1]+"' AND TVSIZE='"+Integer.parseInt(arr[2])+"' AND PRICE='"+Integer.parseInt(arr[3])+"'";
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1234");
                Statement stmt=con.createStatement();
                ResultSet rs =stmt.executeQuery(check);
                if(rs.next())
                {   
                    int cou = rs.getInt("QUANTITY");
                    cou = cou + Integer.parseInt(arr[4]);
                    String up = "UPDATE stock SET QUANTITY='"+cou+"' WHERE BRAND='"+arr[0]+"'and TVTYPE='"+arr[1]+"' AND TVSIZE='"+Integer.parseInt(arr[2])+"' AND PRICE='"+Integer.parseInt(arr[3])+"'";
                    ResultSet rs2 =stmt.executeQuery(up);
                }
                else 
                     
                    {
                        String q1="INSERT INTO stock (BRAND, TVTYPE, TVSIZE, PRICE, QUANTITY) VALUES ('"+arr[0]+"','"+arr[1]+"',"+Integer.parseInt(arr[2])+","+Integer.parseInt(arr[3])+","+Integer.parseInt(arr[4])+")";
                        ResultSet rs1 =stmt.executeQuery(q1);
                    }
                    n=n-1;
                    i=i+1;
               	  }
               int g=0;
               while(true)
                  {

                    System.out.println("Want to sell more TVs ?");
                    System.out.println("Enter 1 Yes");
                    System.out.println("Enter 2 No");
                    int t = Integer.parseInt(ds.readLine());
                    if (t == 2)
                            {
                                    g=1;
                            break;
                            }
                    if(t!=1)
                            {

                               System.out.println("Enter correct value");
                                continue;
                            }
                    if(t==1)
                            {
                                g=0;
                               break;
                            }
                    }
  if(g==1)
            break;
}
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
   }
}
