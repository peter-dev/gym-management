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
        // // chosenPackage should be set to the package associated with their collegeName. If there is no package associated with their college, default to “Package 3”
        // Initially we set this to "Package 3" and we can revisit later
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCollege Name: " + collegeName;
    }
}

