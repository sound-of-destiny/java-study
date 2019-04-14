package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonTest {

    public static void main(String[] args) {
        Person person1 = new Person("zhang san", 20);
        Person person2 = new Person("li si", 30);
        Person person3 = new Person("wang wu", 40);

        List<Person> list = Arrays.asList(person1, person2, person3);
        PersonTest personTest = new PersonTest();
        List<Person> personList = personTest.getPersonsByUsername("zhang san", list);
        personList.forEach(person -> System.out.println(person.getUsername()));
    }

    public List<Person> getPersonsByUsername(String username, List<Person> persons) {
        return persons.stream().filter(person -> person.getUsername().equals(username)).collect(Collectors.toList());
    }

}
