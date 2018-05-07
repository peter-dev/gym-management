
// THIS CODE IS INCOMPLETE

public class Person {
    private String name;
    private String email;
    private String address;
    private String gender;

    public void setName(String name) {
        int maxLength = 30;
        if (name != null && name.length() > maxLength) {
            this.name = name.substring(0,maxLength);
        } else {
            this.name = name;
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        String defaultGender = "Unspecified";
        switch (gender.toUpperCase()) {
            case "M":
            case "F":
                this.gender = gender.toUpperCase();
                break;
            default:
                this.gender = defaultGender;
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public Person(String email, String name, String address, String gender) {
        this.email = email;
        setName(name);
        this.address = address;
        setGender(gender);
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nGender: " + gender;
    }
}
