package AccountType;

public class SavingsAccount extends PayAccount
{
    private double balance;
    private long accountNo;
    private int customerId;
    private float intrestRate;
    final double interesRAte = 2.5;
    public SavingsAccount(int customerId, String name, int age, String mobileNo, String emailId, String address)
    {
        super(customerId, name, age, mobileNo, emailId, address);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(float intrestRate) {
        this.intrestRate = intrestRate;
    }
}
