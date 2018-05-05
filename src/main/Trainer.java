public class Trainer extends Person  {

    private String speciality;

    public Trainer(String email, String name, String address, String gender, String speciality) {
        super(email, name, address, gender);
        setSpeciality(speciality);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSpeciality: " + speciality;
    }
}
