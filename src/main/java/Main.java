import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Human human1 = new Human("Ivan", "Ivanov", "soccer", LocalDate.of(1978,12, 31));
        Human human2 = new Human("Petr", "Petrov", "football", LocalDate.of(2005,06, 20));
        System.out.println(new Processor().toJson(human1));
        System.out.println(new Processor().toJson(human2));
    }
}
