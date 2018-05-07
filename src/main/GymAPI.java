import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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


    public ArrayList<Member> listMembersWithIdealWeight() {
        // Returns a list containing all the members details in the gym whose latest assessment weight is an ideal weight (based on the devine method). Returns an empty list if none are found
        ArrayList<Member> membersList = new ArrayList<>();
        return membersList;
    }

    public ArrayList<Member> listMembersBySpecificBMICategory(String category) {
        // Returns a string containing all the members details in the gym whose BMI category(based on the latest assessment weight) partially or entirely matches the entered category. Returns an empty list if none are found.
        ArrayList<Member> membersList = new ArrayList<>();
        return membersList;
    }

    public String listMemberDetailsImperialAndMetric() {
        // Joe Soap: xx kg (xxx lbs) x.x metres (xx inches). Joan Soap: xx kg (xxx lbs) x.x metres (xx inches).
        // If there are no members in the gym, the message "No registered members" should be returned.
        return "";
    }

    public void store() throws Exception {
        // Push the members and trainers array lists out to the associated XML file.
        ArrayList<Person> gymMembers = new ArrayList<>();
        gymMembers.add(new Member("piotr@dell.com", "Piotr Baran", "Cork", "M", 2, 70, "Sample"));
        gymMembers.add(new Trainer("zenon@trainer.com","Zenon Teat", "Dublin", "M", "Fitness"));
        gymMembers.add(new Member("tara@dell.com", "Tara Baran", "Cork", "F", 1.6f, 50, "Sample"));


        XStream xstream = new XStream(new StaxDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gym-members.xml"));
        out.writeObject(gymMembers);
        out.close();
    }

    public void load() throws Exception {
        // Pull the members and trainers array lists from the associated XML file.

        Class<?>[] classes = new Class[] { Member.class, Trainer.class };
        XStream xstream = new XStream(new StaxDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("gym-members.xml"));

        ArrayList<Member> membersFromFile = new ArrayList<>();
        ArrayList<Trainer> trainersFromFile = new ArrayList<>();

        ArrayList<Person>  allDataFromFile = (ArrayList<Person>) in.readObject();

        in.close();

        // http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
        // I wanted to experiment with Java 8 "out of box" functionality, therefore I am using Streams to go through the Collection
        // and I am filtering objects, I am aware it might not be the most efficient way of loading the data but I wanted to get used to the
        // idea of using Lambda Expressions

        allDataFromFile
                .stream()
                .filter(s -> s instanceof Member)
                .forEach(s -> membersFromFile.add((Member)s));
        allDataFromFile
                .stream()
                .filter(s -> s instanceof Trainer)
                .forEach(s -> trainersFromFile.add((Trainer)s));

    }


}
