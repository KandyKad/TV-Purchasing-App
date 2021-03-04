package tv;
import java.io.*;
import java.io.BufferedReader;
import java.sql.*;
import java.util.Scanner;

public class menuforuser {
  
		  
  	static void ViewCart(String username)throws IOException
   {
        try
        {
          	
            //view entire cart of that user          
	    String a = "select * from cart where USERNAME='"+username+"'";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
            Statement stmt = con.createStatement();
            Statement stmt84 = con.createStatement();
	    ResultSet aa =stmt.executeQuery(a);
            ResultSet ab =stmt84.executeQuery(a);
            if(ab.next()==false)
            {
                System.out.println("Your Cart is empty");
                return;
            }
            //displayCart(aa);
            System.out.println("SRNO\tBRAND\t\tTVTYPE\tTVSIZE\tPRICE\tCOUNT");
             while(aa.next())
                {
                    int srno = aa.getInt("SRNO");
                    String brand = aa.getString("BRAND");
                    String tvtype = aa.getString("TVTYPE");
                    int tvsize = aa.getInt("TVSIZE");
                    int price = aa.getInt("PRICE");
                    int count  = aa.getInt("COUNT");
                
                    System.out.println(srno + "\t" + brand + "\t\t"+ tvtype +  "\t" + tvsize + "\t" + price + "\t" + count);
                    System.out.println("-------------------------------------------------------------------------------------------");

                }
          
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
   }
  
    static void ViewStock(String username)throws IOException
   {
        try
        {
            int sr=0;
            String q;
            q = "select * from stock";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
            Statement stmt = con.createStatement();
            Statement stmt10 = con.createStatement();
            Statement stmt11 = con.createStatement();
            Statement stmt12 = con.createStatement();
            Statement stmt13 = con.createStatement();
            Statement stmt14 = con.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            //displayStock(rs);
            System.out.println("BRAND\t\tTVTYPE\tTVSIZE\tPRICE\tQUANTITY");
            while(rs.next())
            {
               String brand = rs.getString("BRAND");
               String tvtype = rs.getString("TVTYPE");	 
               int tvsize = rs.getInt("TVSIZE");
               int price = rs.getInt("PRICE");
               int quantity = rs.getInt("QUANTITY");
   
               System.out.println(brand + "\t\t"+ tvtype +  "\t" + tvsize + "\t" + price + "\t" + quantity);
               System.out.println("-------------------------------------------------------------------------------------------");
                
            }
            String y="Select MAX(SRNO) from cart";
            ResultSet rs10 = stmt.executeQuery(y);
            if(rs10.next())
            {
                sr=rs10.getInt(1);
            }
            else
            {
               sr=0; 
            }
            while(true)//outer true
            {   
                int g=0;
                System.out.println("Want to Add to cart");
                System.out.println("Enter 1 for Yes");
                System.out.println("Enter 2 for No");
                DataInputStream ds=new DataInputStream(System.in);
                int choice=Integer.parseInt(ds.readLine());
                if(choice==1)
                {
                   while (true)//inner true
                    {
                       System.out.println("Enter no. of Different TVs which you want to add to cart");
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
                            String p = "Select QUANTITY from stock where BRAND= '"+arr[0]+"' and TVTYPE= '"+arr[1]+"' and TVSIZE= '"+arr[2]+"' and PRICE='"+Integer.parseInt(arr[3])+"'";
                            ResultSet ps = stmt.executeQuery(p);
                            if (ps.next())
                            {
                                int quant = ps.getInt("QUANTITY");
                                String q2 = "Select COUNT from cart where BRAND= '"+arr[0]+"' and TVTYPE= '"+arr[1]+"' and TVSIZE= '"+arr[2]+"' and PRICE='"+Integer.parseInt(arr[3])+"' and USERNAME='"+username+"'";
                                ResultSet rt = stmt11.executeQuery(q2);
                                if(rt.next())
                                {
                                    
                                        int p7 = rt.getInt("COUNT");
                               
                                        if(Integer.parseInt(arr[4])<=quant-p7)
                                        {
                                            int joy=p7+Integer.parseInt(arr[4]);
                                            
                                            String q9="UPDATE cart SET COUNT='"+joy+"' where BRAND= '"+arr[0]+"' and TVTYPE= '"+arr[1]+"' and TVSIZE= '"+arr[2]+"' and PRICE='"+Integer.parseInt(arr[3])+"' and USERNAME='"+username+"'";
                                                ResultSet gs =stmt12.executeQuery(q9);
                                        }
                                        
                                
                                
                                        else
                                        {
                                          l:
                                          {
                                           System.out.println("You have added this item previously. So total count of required items exceeding stock limit!!! What do you want to do?");
                                           System.out.println("1. ADD all stock items to the cart i.e. "+ quant);
                                           System.out.println("2. Back to add next TV");
                                           int choice1 = Integer.parseInt(ds.readLine());
                                           if (choice1 == 1)
                                           {
                                             String q3="UPDATE cart SET COUNT='"+quant+"' where BRAND= '"+arr[0]+"' and TVTYPE= '"+arr[1]+"' and TVSIZE= '"+arr[2]+"' and PRICE='"+Integer.parseInt(arr[3])+"' and USERNAME='"+username+"'";
                                             ResultSet qw = stmt13.executeQuery(q3);
                                           }
                                           else if( choice1 == 2)
                                           {
                                               n=n-1;
                                               continue;
                                           }
                                           else
                                           {
                                               System.out.println("Input a correct number");
                                               break l;
                                           }  
                                    
                                            }// l
                                
                             
                                            }//else
                                    }//IF exists
                                    else
                                    {
                                        if(Integer.parseInt(arr[4])<=quant)
                                        {
                                            sr = sr + 1;
                                            String q1="INSERT INTO cart (SRNO,USERNAME,BRAND, TVTYPE, TVSIZE, PRICE, COUNT) VALUES ('"+sr+"','"+username+"','"+arr[0]+"','"+arr[1]+"',"+Integer.parseInt(arr[2])+","+Integer.parseInt(arr[3])+","+Integer.parseInt(arr[4])+")";
                                            ResultSet gs =stmt14.executeQuery(q1);
                                        }
                                        
                                
                                
                                        else
                                        {
                                          l8:
                                          {
                                           System.out.println("Stock is low!!! What do you want to do?");
                                           System.out.println("1. ADD all stock items to the cart i.e. "+ quant);
                                           System.out.println("2. Back to add next TV");
                                           int choice1 = Integer.parseInt(ds.readLine());
                                           if (choice1 == 1)
                                           {
                                             sr = sr + 1;
                                             String q1="INSERT INTO cart (SRNO,USERNAME,BRAND, TVTYPE, TVSIZE, PRICE, COUNT) VALUES ('"+sr+"','"+username+"','"+arr[0]+"','"+arr[1]+"',"+Integer.parseInt(arr[2])+","+Integer.parseInt(arr[3])+","+quant+")";
                                             ResultSet qw = stmt10.executeQuery(q1);
                                           }
                                           else if( choice1 == 2)
                                           {
                                               n=n-1;
                                               continue;
                                           }
                                           else
                                           {
                                               System.out.println("Input a correct number");
                                               break l8;
                                           }  
                                    
                                            }// l
                                
                             
                                            }//else
                                    }
                                
                            }
                            else
                            {
                                System.out.println("You attempted to add invalid item");
                                continue;
                            }
                            
                            n=n-1;
                            i=i+1;

                            
                            
                        }// n loop
                        l2:{
                            System.out.println("Want to add more to the cart??");
                            System.out.println("Enter 1 for YES");
                            System.out.println("Enter 2 for NO");
                            int k= Integer.parseInt(ds.readLine());
                            if(k==1)
                            {
                            g=0;
                            continue;
                            }
                            if(k==2)
                            {
                                g=1;
                                break;
                            }
                            if(k!=2)
                            {
                                System.out.println("Enter correct option");
                                break l2;
                            }
                        
                        }
                    }//true loop 
                
                }
                else
                {
                    break;
                }
                
                if(g==1) 
                {
                    break; 
                }
                
            }//outer true           
                                    
                                    
                                    
                                    
                
        }//try
         
        catch(Exception e)
        { 
            System.out.println(e);
        }
   }
  
  	static void Buy(String username)throws IOException
   {
       System.out.println("************************WELCOME FOR PAYMENT*****************************");
        try
        {
              String q, c;    
                // Take stock and cart join for query....
               double FA=0;
               c = "Select BRAND, TVTYPE, TVSIZE, COUNT, PRICE from cart where username='"+username+"'";
               Class.forName("oracle.jdbc.driver.OracleDriver");
               Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
               Statement stmt = con.createStatement();
               Statement stmt2= con.createStatement();
               Statement stmt3 = con.createStatement();
               Statement stmt4= con.createStatement();
               Statement stmt5= con.createStatement();
               Statement stmt6= con.createStatement();
               Statement stmt7= con.createStatement();
               Statement stmt8= con.createStatement();
               Statement stmt9= con.createStatement();
               Statement stmt100= con.createStatement();
               Statement stmt19= con.createStatement();
               ResultSet rs = stmt.executeQuery(c);
               ResultSet rs100 = stmt100.executeQuery(c);    
               if(rs100.next()==false)
               {
                   System.out.println("Your Cart is Empty.\nPlease add Items to your Cart");
                   return;
               }
               while(rs.next())
               {
                
               String brand = rs.getString("BRAND");
               String tvtype = rs.getString("TVTYPE");	 
               int tvsize = rs.getInt("TVSIZE");
               int cou = rs.getInt("COUNT");
               int pri = rs.getInt("PRICE");
               q = "Select QUANTITY from stock where BRAND= '"+brand+"' and TVTYPE= '"+tvtype+"' and TVSIZE= '"+tvsize+"'";
               
               ResultSet qs = stmt2.executeQuery(q);
               if (qs.next())
                    {
                        int quant = qs.getInt("QUANTITY");   
               
                       if(cou <= quant)  //Stock is greater than or equal to cart
                       {
                          FA=FA+cou*pri;
                          int temp = quant - cou;
                          if (temp== 0)
                          {
                          String up5 = "DELETE  from stock where BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                          ResultSet rs5 =stmt3.executeQuery(up5);
                          }
                          else
                          {
                          String up1 = "UPDATE stock SET QUANTITY='"+temp+"' where BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                          ResultSet rs1 =stmt4.executeQuery(up1);
                          }
                          
                          String up2 = "DELETE from cart where username= '"+username+"'and BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                          ResultSet rs2 = stmt5.executeQuery(up2);
                          String up3 = "INSERT into history(USERNAME, BRAND, TVTYPE, TVSIZE, PRICE, COUNT) values ('"+username+"', '"+brand+"', '"+tvtype+"', '"+tvsize+"', '"+pri+"', '"+cou+"')";
                          ResultSet rs3 = stmt6.executeQuery(up3);
                        
                       }
                        
                       else // Stock is less than cart
                        {
                            l3:{
                           System.out.println("This Stock is low!!!--->  "+brand + "\t"+ tvtype +  "\t" + tvsize + "\t" + pri + "\t" + cou+"\nWhat do you want to do?");
                           
                           System.out.println("1. Purchase all stock less than my demand i.e:"+quant);
                           System.out.println("2. Back to menu");
                           DataInputStream ds=new DataInputStream(System.in);
                           int choice = Integer.parseInt(ds.readLine());
                           if (choice == 1)
                           {    
                              FA=FA+quant*pri;
                              String down = "DELETE  from stock where BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                              ResultSet rs3 =stmt7.executeQuery(down);
                              String up90 = "INSERT into history(USERNAME, BRAND, TVTYPE, TVSIZE, PRICE, COUNT) values ('"+username+"', '"+brand+"', '"+tvtype+"', '"+tvsize+"', '"+pri+"', '"+quant+"')";
                              ResultSet qrs3 = stmt19.executeQuery(up90);
                              String down1 = "DELETE  from cart where username= '"+username+"'and BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                              ResultSet rs4 = stmt8.executeQuery(down1);
                           }
                           else if( choice == 2)
                           {
                            System.out.println("Clearing this item from your Cart");
                            String cancel = "DELETE  from cart where username= '"+username+"'and BRAND='"+brand+"'and TVTYPE='"+tvtype+"' AND TVSIZE='"+tvsize+"'";
                            ResultSet can = stmt9.executeQuery(cancel);
                            //System.out.println("Oops! Your cart has been emptied. Keep shopping!");
                           }
                           else
                           {
                               System.out.println("Input a correct number");
                               break l3;
                           
                           }
                       }
                           
                        }
                       
                    }
                else 
                {
                    System.out.println("This item is out of stock.");
                }
               
               
               }
               if(FA>0.0)
               {    
                    System.out.println("Total Amount to be payed="+FA);
                    System.out.println("Payment Successfull");
               }
               else
               {
                   System.out.println("You Didn't Buy Anything");
               }
               con.close();
                         
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
   }
  static void PaymentHistory(String username)throws IOException
   {
        try
        {
              String q;
               q="select * from history where username='"+username+"'";    
              
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1234");
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(q);
              
               System.out.println("--------------------------------YOUR PAYMENT HISTORY--------------------------------");
               System.out.println("BRAND\t\t"+"TYPE\t"+"SIZE\t"+"PRICE\t"+"COUNT\t"+"TIMESTAMP");
              while(rs.next())
              { 

                  System.out.println(rs.getString(2)+"\t\t"+rs.getString(3) + "\t"+ rs.getString(4)+"\t"+rs.getString(5) + "\t"+ rs.getString(6)+"\t"+rs.getString(7));
                  System.out.println("-----------------------------------------------------------------------------------------------------");
              }
              
          
          
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
   }
    static int mfu(String username)throws IOException
   {
        try
        {
            int choice;
            DataInputStream ds=new DataInputStream(System.in);
            System.out.println("------------------YOUR MAIN MENU------------------");
            System.out.println("Enter Your Choice");
            System.out.println("1. View Stock / Add to Cart");
          	System.out.println("2. View Your Cart");
          	System.out.println("3. Buy");
            System.out.println("4. View Payment History");
          	System.out.println("5. Logout & Exit from the system");
            int no=Integer.parseInt(ds.readLine());  
            if(no==1)
            {
              ViewStock(username);
            }
            else if(no==2)
            {
              ViewCart(username);
            }
          	else if(no==3)
            {
              Buy(username);
            }
          	else if(no==4)
            {
              PaymentHistory(username);
            }
          	else if(no==5)
            {
              return 0;
            }
            else
            {
                System.out.println("Enter right value :");
                return 1;
            }  
          	
          	while(true)
            {
          	System.out.println("Want to see the menu once again???");
          	System.out.println("Enter 1 for Yes");
          	System.out.println("Enter 2 for No");
          	int choice2;
          	choice2 = Integer.parseInt(ds.readLine());
          	if(choice2==1)
              return 1;
          	else if(choice2==2)
              return 0;
          	else 
              System.out.println("Enter Correct Value");
            }
        }
        catch(Exception e)
        { 
            System.out.println(e);
        }
   return 0;
   }
}


