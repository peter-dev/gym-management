public class Assessment {

    private float weight;
    private float thigh;
    private float waist;
    private String comment;
    private String trainer;

    public Assessment(float weight, float thigh, float waist, String comment, String trainer) {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
        this.trainer = trainer;
    }

    // constructor to satisfy JUnit Test
    public Assessment(float weight, float thigh, float waist, String comment) {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
