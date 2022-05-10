package CustomerType;

import java.sql.*;
import java.util.Scanner;

public class CustomerImplement
{

    private Connection connection = null;
    Scanner scan = new Scanner(System.in);
    private Connection getconnetion() throws SQLException {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                "Password@123");
        return connection;
    }

    public void addCustomerDetails(int age,int accountType) throws SQLException {
        System.out.println("Enter Name");
        String name = scan.next();
        System.out.println("Enter MobileNo");
        String mobile = scan.next();
        System.out.println("Enter EmailId");
        String email = scan.next();
        System.out.println(" Enetr Address");
        String address = scan.next();
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        customer.setMobileNo(mobile);
        customer.setEmailId(email);
        customer.setAddress(address);
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Password@123");
        Customer customer2= new Customer(customer.getCustomerId(), customer.getName(), customer.getAge(), customer.getMobileNo(), customer.getEmailId(), customer.getAddress());
        Statement stmt = connection.createStatement();

            String query = "INSERT INTO CUSTOMER VALUES (" + customer2.getName() + ","
                    + "'" + customer2.getAge() + "'" + ","+ customer2.getMobileNo()+ ","
                    + "'" + customer2.getEmailId() + " "+ customer2.getAddress()+" "+customer2.getCustomerId()+")";

            if (stmt.executeUpdate(query) != 0) {
                System.out.println("Unseccessfull Attempt");
            }
            else
            {
                System.out.println("Your Details Update Successfully");
            }

    }


    //<<<<<<<<<<<<<<<<<<<     SavingAccount     >>>>>>>>>>>>>>>>
    public void savingAccount(long account,int id) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement ps=connection.prepareStatement("insert into savingaccount(account_No,customerId)values(?,?)");
        ps.setLong(2,account);
        ps.setInt(4,id);
        int rowsaffected = ps.executeUpdate();    //execute the query with database

    }

    //<<<<<<<<<<<<<<<<<        PayAccount          >>>>>>>>>>>>>>>>>


}
