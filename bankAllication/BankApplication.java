package bankAllication;

import AccountImplement.AccountImplement;

import java.sql.*;
import java.util.Scanner;

public class BankApplication
{
        public static void main(String[] args) throws SQLException, ClassNotFoundException
        {
                System.out.println("***************** <<<<<<<<<< Welcome to IDBC Bank Portal >>>>>>>>>> ****************");
                Scanner scan = new Scanner(System.in);

                BankImplement bankImplement = new BankImplement();

                System.out.println("press 1 Create Account \nPress 2 Existing Account ");
                int press = scan.nextInt();
                switch (press) {
                        case 1:
                                System.out.println("*************** <<<<<<     Which Account You Are Open  >>>>>> *************\nPress 1 Saving Account \nPress 2 Pay Account ");
                                int accountType = scan.nextInt();  //1
                                System.out.println("Enter Your Age");
                                int age = scan.nextInt();  //21
                                if (age >= 18)
                                {
                                        bankImplement.addCustomerDetails(age,accountType);
                                } else
                                {
                                        System.out.println("You are not Eligible Your age less than 18");
                                }
                                break;
                        case 2:
                                BankApplication.existAccount();
                                break;
                        default:
                                System.out.println("wrong input try again");
                }
        }


        static void existAccount() throws SQLException
        {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter Account number");
                long account_Number = scan.nextLong();
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Password@123");
                Statement st = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement("select Account_Number from Accountdetail where Account_Number=?;");
                statement.setLong(1, account_Number);
                ResultSet resultSet = statement.executeQuery();
                long account = 0;
                if (resultSet.next())
                {
                        account = resultSet.getLong(1);
                }
                AccountImplement accountImplement = new AccountImplement();
                if (account == account_Number)
              {
                      int choice,  y;
                      do
                      {
                              System.out.println("********** <<<<<<<<  Select an option below  >>>>>>>> ***********");
                              System.out.println("Press 1 Deposit Amount |\tPress 2 Withdraw Amount |\tPress 3 checkBalance |\tPress 4 fundTransfer |\tpress 5 View Details |\tExit Press any key ");
                              choice = scan.nextInt();
                              switch (choice)
                              {
                                      case 1:
                                              accountImplement.deposit(account_Number);    //running
                                              break;
                                      case 2:
                                              accountImplement.withdrawal(account_Number);  //running
                                              break;
                                      case 3:
                                              accountImplement.checkBalance(account_Number);     //running
                                              break;
                                      case 4:
                                             accountImplement.fundTransfer(account_Number);   //running
                                              break;
                                      case 5:
                                              accountImplement.viewAccountDetails(account_Number);  //running
                                              break;
                                      default:
                                              System.out.println("wrong input ");
                                              break;
                              }
                              System.out.println("Do you want to continue\tyes press 1|\tNo press 2");
                              y = scan.nextInt();
                      }while (y == 1);
              }else
              {
                      System.out.println("Account Does't Exists");
              }

        }

}
