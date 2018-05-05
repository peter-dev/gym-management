import java.util.ArrayList;
import java.util.HashSet;

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

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void addMember(Member member) {
        if (member != null) {
            members.add(member);
        }
    }

    public void addTrainer(Trainer trainer) {
        if (trainer != null) {
            trainers.add(trainer);
        }
    }

    public int numberOfMembers() {
        return (members.isEmpty()) ? 0 : members.size();
    }

    public int numberOfTrainers() {
        return (trainers.isEmpty()) ? 0 : trainers.size();
    }

    public boolean isValidMemberIndex(int index) {
        return (index < 0 || (index >= members.size())) ? false : true;
    }

    public boolean isValidTrainerIndex(int index) {
        return (index < 0  || (index >= trainers.size())) ? false : true;
    }

    public Member searchMembersByEmail(String emailEntered) {
        for (Member m : members) {
            if (emailEntered.equals(m.getEmail())) return m;
        }
        return null;
    }

    public Trainer searchTrainersByEmail(String emailEntered) {
        for (Trainer t : trainers) {
            if (emailEntered.equals((t.getEmail()))) return t;
        }
        return null;
    }

    public ArrayList<String> searchMembersByName(String nameEntered) {
        // check if name is null or empty spaces only
        if (nameEntered == null || nameEntered.trim().length() == 0) {
            return null;
        }
        // split name at spaces (based on Lab 12)
        nameEntered = nameEntered.trim().toLowerCase();
        String[] wordArray = nameEntered.split(" ");
        HashSet<String> words = new HashSet<>();
        for (String word : wordArray) {
            words.add(word);
        }
        // create list of names that partially or entirely matches the entered name
        ArrayList<String> membersList = new ArrayList<>();
        for (Member m : members) {
            for (String s : words) {
                if (m.getName().toLowerCase().contains(s)) {
                    membersList.add(m.getName());
                    break;
                }
            }
        }
        return membersList;
    }

    public ArrayList<String> searchTrainersByName(String nameEntered) {
        // check if name is null or empty spaces only
        if (nameEntered == null || nameEntered.trim().length() == 0) {
            return null;
        }
        // split name at spaces (based on Lab 12)
        nameEntered = nameEntered.trim().toLowerCase();
        String[] wordArray = nameEntered.split(" ");
        HashSet<String> words = new HashSet<>();
        for (String word : wordArray) {
            words.add(word);
        }
        // create list of names that partially or entirely matches the entered name
        ArrayList<String> membersList = new ArrayList<>();
        for (Trainer m : trainers) {
            for (String s : words) {
                if (m.getName().toLowerCase().contains(s)) {
                    membersList.add(m.getName());
                    break;
                }
            }
        }
        return membersList;
    }




}
