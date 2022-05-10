package bankAllication;
import java.sql.*;
import java.util.Scanner;

public class BankImplement
{
    Connection connection = null;
    Scanner scan = new Scanner(System.in);
    private Connection getconnetion() throws SQLException
    {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                "Password@123");
        return connection;

    }
    //<<<<<<<<<<<<<<<    Add A Customer details     >>>>>>>>>>>>>>>>>>>>>>>
    public void addCustomerDetails(int age,int type) throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =getconnetion();

        System.out.println("Enter Your Name");
        String name = scan.next();

        System.out.println("Enter Your MobileNo");
        String mobile = scan.next();

        System.out.println("Enter Your EmailId");
        String email = scan.next();

        System.out.println(" Enetr Your Address");
        String address = scan.next();


        PreparedStatement ps=connection.prepareStatement("insert into customer(Name,Age,Contact_Number,Email,Address)values(?,?,?,?,?);");
        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setString(3,mobile);
        ps.setString(4,email);
        ps.setString(5,address);
        int rowsaffected = ps.executeUpdate();

        if (rowsaffected != 0)
        {
            System.out.println("Your Details Update Successfully");
            PreparedStatement pst = connection.prepareStatement("select customer_Id from customer where Email = ?;");
            pst.setString(1,email);
            ResultSet resultSet = pst.executeQuery();
            int customerId = 0;
            if (resultSet.next())
            {
                customerId = resultSet.getInt(1);
            }
            String accountType;
            if (type == 1)   //1
            {
                double balance = 0;
                accountType = "Saving";
               long genrateAccountNumber =  BankImplement.genrateAccountNumber();
                PreparedStatement prest = connection.prepareStatement("insert into accountdetail values (?,current_time(),?,?,?);");
                prest.setLong(1,genrateAccountNumber);
                prest.setString(2,accountType);
                prest.setDouble(3,balance);
                prest.setInt(4,customerId);
                int saving = prest.executeUpdate();
                if (saving != 0) {
                    System.out.println("Your Account created sucussfully");
                    System.out.println("Saving Account_Number  : " + genrateAccountNumber);
                }

            }else
            {
                double balance = 0;
                accountType = "PayAccount";
                long genrateAccountNumber =  BankImplement.genrateAccountNumber();
                PreparedStatement prest = connection.prepareStatement("insert into accountdetail values (?,current_time(),?,?,?);");
                prest.setLong(1,genrateAccountNumber);
                prest.setString(2,accountType);
                prest.setDouble(3,balance);
                prest.setInt(4,customerId);
                int payAccount = prest.executeUpdate();
                if (payAccount != 0) {
                    System.out.println("Your Account created sucussfully");
                    System.out.println("Pay Account_Number  : " + genrateAccountNumber);
                }
            }

        }else
        {
            System.out.println("Unseccessfull Attempt");
        }

    }

    public static long genrateAccountNumber()
    {
        long min = 000000000000;
        long max = 999999999999l;
        long accountNumberGenrate = (long) (Math.random()*(max-min)+min);
        return accountNumberGenrate;
    }

}
