package AccountType;

import java.sql.SQLException;

public interface Declare
{
     void deposit(long account_number) throws SQLException;

     void withdrawal(long account_number) throws SQLException;

     void checkBalance(long account_number) throws SQLException;

     void fundTransfer(long account_number) throws SQLException;
     void viewAccountDetails(long account_number) throws SQLException;
}
