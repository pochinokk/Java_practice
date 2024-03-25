package practice2;
import java.time.LocalDate;
import java.time.Period;
public class Human implements Comparable<Human>{
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    int weight;

    public Human(String firstName, String lastName, LocalDate birthDate, int weight)
    {

        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return "\nHuman{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                "}";
    }

    @Override
    public int compareTo(Human h){

        String rev_this_firstName = new StringBuilder(this.getFirstName()).reverse().toString();
        String rev_h_firstName = new StringBuilder(h.getFirstName()).reverse().toString();

        return rev_this_firstName.compareTo(rev_h_firstName);
    }
}
