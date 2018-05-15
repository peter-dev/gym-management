# HDip in Computer Science - WIT, Programming Fundamentals - Assignment 3

## A console-based Gym Management Application

### The components of the architecture are:

- The MenuController provides a console-based user interface to the application's feature set.
- The Gym API class implements the application's 'business logic'.
  - Reusable parts of the 'business logic' is delegated to a utility class.
- The Data model contains classes based on the problem domain, e.g. Member, Trainer.

### Versioning:

I used SourceTree UI to control my local and remote GitHub Repository.

### Comments:

All unit tests are passing (82 tests in total).

| Grading Spectrum | Requirements | Complete |
| ---------------- | ------------ | -------- |
| Pass (40-50) | Data Model: Basic implementation (Person, Member and Trainer). API: Add members/trainers, numberofMembers/Trainers methods, isValidMember/TrainerIndex methods, basic addMember/Trainer. Menu: Registration, Add New member, List all members. Input validation. | OK |
| Good (50-65) | API: Various Search methods (by email and name), ; XML persistence. Menu: Login, Trainer and Member sub-menus, (Trainer) Add/List/Search members, (Member) View/update profile. | OK |
| Excellent (65-85) | Data Model: Assessment class basic support. API: Utility class. Menu: (Trainer) Add assessment, Update assessment comment. Most unit tests pass | OK |
| Outstanding (85-100) | Data model: Student and Premium Member support; Member assessment query support. API: Fully-featured. Menu: (Member) Progress sub-menu options; Fully-featured. All unit tests pass. | OK |

### Resources:

- MenuController class uses nested submenus. The code is based on the following example:
  - http://chronicles.blog.ryanrampersad.com/2011/03/text-based-menu-in-java/
- GymAPI load() method uses Java 8 Streams to filter the Collection. The code is based on the following example:
  - http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
- GymUtility has a predefined Enum class that contains a list of constants that determine the BMI category.
  - https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
- DateComparator custom class is being used to sort Assessment dates.
  - https://stackoverflow.com/questions/17862717/sorting-a-hashmaps-values-with-sorted-set
  - https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html 
```
public SortedSet sortedAssessmentDates() {
    SortedSet<String> orderedDates = new TreeSet<>(new DateComparator());
    orderedDates.addAll(assessments.keySet());

    return orderedDates;
}
```   
- I installed google-java-format plugin in IntelliJ and reformatted the code to align with Google Java Style Guide.
  - https://google.github.io/styleguide/javaguide.html
  - https://github.com/google/google-java-format