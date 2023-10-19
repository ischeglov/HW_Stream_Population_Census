import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //ищем количество несовершеннолетних (т.е. людей младше 18 лет)
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);

        //Получаем список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> army = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27 && x.getSex().equals(Sex.MAN))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(army);

        //Получаем отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)
        List<String> workPepole = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() > 18)
                .filter(x-> x.getSex().equals(Sex.MAN) ? x.getAge() < 65 : x.getSex().equals(Sex.WOMAN) && x.getAge() < 60)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(workPepole);
    }
}

