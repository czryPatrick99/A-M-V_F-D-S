package model;

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String contact;  // Phone number as String
    private String cuisineType;

    // Default constructor
    public Restaurant() {}

    // Parameterized constructor
    public Restaurant(int id, String name, String address, String contact, String cuisineType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.cuisineType = cuisineType;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                '}';
    }
}