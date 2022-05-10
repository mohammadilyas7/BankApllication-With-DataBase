package AccountType;
import CustomerType.Customer;
public class PayAccount extends Customer
{
    public PayAccount(int customerId, String name, int age, String mobileNo, String emailId, String address)
    {
        super(customerId,name,age,mobileNo,emailId,address);
    }

    private double balance;
    private long accountNo;
    public int customerId;
    //<<<   setter  >>>>

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    //<<<<< setter >>>>
    public double getBalance() {
        return balance;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public int getCustomerId() {
        return customerId;
    }
}
