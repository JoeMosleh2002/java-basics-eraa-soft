package main.EWallet912.model;

public class Account {
    private String userName;
    private String password;
    private Double balance;
    private String phoneNumber;
    private Float age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }
}
