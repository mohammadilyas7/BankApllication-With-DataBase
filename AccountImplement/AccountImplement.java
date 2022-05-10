package AccountImplement;
import AccountType.Declare;
import java.sql.*;
import java.util.Scanner;

public class AccountImplement implements Declare
{

    Connection connection = null;
    Scanner scan = new Scanner(System.in);
    private Connection getconnetion() throws SQLException
    {
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Password@123");
        return connection;
    }

    //<<<<<<<<<<<<   DEPOSITE    >>>>>>>>>>
    @Override
    public void deposit(long account_number) throws SQLException
    {
        System.out.println("Enter The Amount to be Deposit");
        double deposit = scan.nextDouble();
        connection = getconnetion();
        PreparedStatement predStnt = connection.prepareStatement("update Accountdetail set Balance = balance + ? where Account_Number=?");
        predStnt.setDouble(1,deposit);
        predStnt.setLong(2,account_number);
        int store = predStnt.executeUpdate();

        if (store != 0)
        {
            PreparedStatement ps = connection.prepareStatement("insert into cashTransaction (Transaction_Time,Account_Number,Amount,Description) values(current_time(),?,?,'Credit')");
            ps.setLong(1,account_number);
            ps.setDouble(2,deposit);

            int temp = ps.executeUpdate();
            if (temp != 0){
                System.out.println("Transaction Completed");
            }
        }
    }

        //<<<<<<<<<     WITHDRAWAL      >>>>>>>>>
    @Override
    public void withdrawal(long account_number) throws SQLException
    {
        System.out.println("Enter The Withdraw Balance");
        double withdraw = scan.nextDouble();
        connection = getconnetion();
        PreparedStatement ok = connection.prepareStatement("select balance from Accountdetail where Account_Number = ?");
        ok.setLong(1,account_number);
        ResultSet res = ok.executeQuery();
        double check_balance =0;
        if (res.next())
        {
            check_balance =res.getDouble(1);
        }

        if(check_balance >= withdraw)
        {
            connection =getconnetion();
            PreparedStatement pst = connection.prepareStatement("update Accountdetail set Balance = balance - ? where Account_Number=?");
            pst.setDouble(1, withdraw);
            pst.setLong(2, account_number);

            int store = pst.executeUpdate();
            if (store != 0)
            {
                connection = getconnetion();
                PreparedStatement ps = connection.prepareStatement("insert into cashTransaction (Transaction_Time,Account_Number,Amount,Description) values(current_time(),?,?,'Debit')");
                ps.setLong(1, account_number);
                ps.setDouble(2, withdraw);

                int temp = ps.executeUpdate();
                if (temp != 0)
                {
                    System.out.println("Transaction Completed");
                }
            }

        }
        else
        {
            System.out.println("Insufficient Balance");
        }

    }
        //<<<<<<<<<<      checkBalance      >>>>>>>>>>>>

    @Override
    public void checkBalance(long account_number) throws SQLException {
        connection = getconnetion();
        Statement st = connection.createStatement();

        PreparedStatement pou = connection.prepareStatement("select balance from accountdetail where account_number=?;");
        pou.setLong(1,account_number);
        ResultSet resultSet = pou.executeQuery();
        if (resultSet.next()){
            System.out.println("Balance : "+resultSet.getDouble(1));
        }
    }
        //<<<<<<<<<      FundTransfer      >>>>>>>>>>
    @Override
    public void fundTransfer(long account_number) throws SQLException
    {
        System.out.println("Enter the Account Number For Transfer");
        long transferAccountNumber = scan.nextLong();
        System.out.println("Enter the Amount");
        double amountTransfer = scan.nextDouble();

        connection = getconnetion();
        PreparedStatement ok = connection.prepareStatement("select balance from Accountdetail where Account_Number = ?");
        ok.setLong(1,account_number);
        ResultSet res = ok.executeQuery();
        double check_balance =0;
        if (res.next())
        {
            check_balance =res.getDouble(1);
        }


        if(check_balance >= amountTransfer)
        {
            connection = getconnetion();
            PreparedStatement ps12 = connection.prepareStatement("select Account_Number from Accountdetail where Account_Number = ?");
            ps12.setLong(1,transferAccountNumber);
            ResultSet rset = ps12.executeQuery();
            long account =0;
            if (rset.next())
            {
                account =rset.getLong(1);
            }

            if (account == transferAccountNumber)
            {
                connection = getconnetion();
                PreparedStatement pst6 = connection.prepareStatement("update accountdetail set balance=balance-? where account_number=?");
                pst6.setDouble(1, amountTransfer);
                pst6.setLong(2, account_number);
                int i = pst6.executeUpdate();
                if (i != 0) {
                    connection = getconnetion();
                    PreparedStatement pst7 = connection.prepareStatement("insert into transactions(transaction_time,From_Account,to_account,remarks) values (current_time(),?,?,?)");
                    pst7.setLong(1, account_number);
                    pst7.setLong(2, transferAccountNumber);
                    pst7.setDouble(3, amountTransfer);
                    int j = pst7.executeUpdate();
                    if (j != 0) {
                        connection = getconnetion();
                        PreparedStatement pst8 = connection.prepareStatement("update accountdetail set balance = balance + ? where account_number=?");
                        pst8.setDouble(1, amountTransfer);
                        pst8.setLong(2, transferAccountNumber);
                        int x = pst8.executeUpdate();
                        if (x != 0) {
                            connection = getconnetion();
                            PreparedStatement pst10 = connection.prepareStatement("insert into transactions(transaction_time,from_account,to_account,amount) values (current_time(),?,?,?)");
                            pst10.setLong(1, transferAccountNumber);
                            pst10.setLong(2, account_number);
                            pst10.setDouble(3, amountTransfer);
                            int y = pst10.executeUpdate();
                            if (y != 0) {
                                System.out.println("Transaction Complete");
                            }
                        }
                    }

                }

            }   else {
                System.out.println("This Account Number");
                System.out.println("Account number Not Genreted Try Again");
            }
        }//balace check
        else {
            System.out.println("No Sufficient Balance");
        }
    }

    @Override
    public void viewAccountDetails(long account_number) throws SQLException
    {
        connection =getconnetion();
        PreparedStatement pst4 = connection.prepareStatement("select * from accountdetail where account_number = ?");
        pst4.setLong(1, account_number);
        ResultSet resultSet = pst4.executeQuery();
        System.out.println();
        System.out.format("%-20s%-20s%-20s%-20s%-20s","Account_Number","Opening-Date-Time","Type-Account","Balance","Customer_Id\n");
        if (resultSet.next())
        {

            System.out.format("%-21s%-20s%-20s%-20s%-20s","\n"+ resultSet.getLong(1), resultSet.getDate(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getInt(5));
            System.out.println("\n");
        }
        connection.close();
    }


}

