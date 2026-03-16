package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password; // In a real app, store a hash, not plain text
    private String address;
    private String phone;    // Changed from "number" to "phone" and made String
    private String paymentInfo; // Could be a simple string like "VISA ****1234"

    
    public User() {}

    
    public User(int id, String name, String email, String password, String address, String phone, String paymentInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.paymentInfo = paymentInfo;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(String paymentInfo) { this.paymentInfo = paymentInfo; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", paymentInfo='" + paymentInfo + '\'' +
                '}';
    }
}