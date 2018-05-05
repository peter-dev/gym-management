public class StudentMember extends Member {

    private String studentId;
    private String collegeName;

    public StudentMember(String email, String name, String address, String gender, float height, float startWeight, String chosenPackage, String studentId, String collegeName) {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public void chosenPackage(String chosenPackage) {
        // TODO
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCollege Name: " + collegeName;
    }
}

