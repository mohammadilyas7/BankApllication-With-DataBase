package CustomerType;

public class Customer
{
    private int customerId;
    private  String Name;
    private  int age;
    private String mobileNo;
    private String emailId;
    private String address;

    public Customer() {

    }

    //<<<<<<<<   Setter  >>>>>>

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //<<<<<<<<<   Getter   >>>>>>>>

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAddress() {
        return address;
    }

    // constructer

    public Customer(int customerId, String name, int age, String mobileNo, String emailId, String address) {
        this.customerId = customerId;
        Name = name;
        this.age = age;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
    }


    // to String method

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
