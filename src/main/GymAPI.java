import java.util.ArrayList;

// THIS CODE IS INCOMPLETE

public class GymAPI {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    public GymAPI() {
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {

    }

    public void addTrainer(Trainer trainer) {

    }

    public int numberOfMembers() {
        return 0;
    }

    public int numberOfTrainerss() {
        return 0;
    }

    public boolean isValidMemberIndex(int index) {
        return false;
    }

    public boolean isValidTrainerIndex(int index) {
        return false;
    }

}
