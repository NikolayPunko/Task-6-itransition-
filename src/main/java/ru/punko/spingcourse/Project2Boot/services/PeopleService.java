package ru.punko.spingcourse.Project2Boot.services;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.stereotype.Service;
import ru.punko.spingcourse.Project2Boot.models.Person;
import ru.punko.spingcourse.Project2Boot.models.Setting;

import java.util.*;

@Service
public class PeopleService {

    public List<Person> generate(Setting setting) {
        if (setting.getRegion() == null)
            setting.setRegion("DE");

        if (setting.getPage() < 1)
            setting.setPage(1);

        Faker faker = new Faker(new Locale(setting.getRegion()), new Random(setting.getSeed() + setting.getPage() * 31));
        List<Person> people = new ArrayList<>();

        for (int i = setting.getPage() * 20 - 20; i < setting.getPage() * 20; i++) {
            Person person = new Person();
            person.setId(i + 1);
            person.setRandomId(Integer.parseInt(faker.bothify("#")+i));
            person.setFullName(faker.name().fullName());
            person.setAddress(faker.address().fullAddress());
            person.setPhone(faker.phoneNumber().phoneNumber());
            people.add(person);
        }

        if (setting.getMistakes() > 0) {
            mistakes(setting, people);
        }

        return people;
    }

    public void mistakes(Setting setting, List<Person> people) {

        SplittableRandom rnd1 = new SplittableRandom(setting.getSeed() + setting.getPage() + 1);

        SplittableRandom rnd2 = new SplittableRandom(setting.getSeed() + setting.getPage() + 1);

        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < setting.getMistakes(); j++) {
                int n1 = rnd1.nextInt(3);
                int n2 = rnd2.nextInt(3);
                switch (n1) {
                    case (0): {
                        StringBuilder fullName = new StringBuilder(people.get(i).getFullName());
                        if (fullName.length() < 4)
                            n2 = 2;
                        switch (n2) {
                            case (0): {
                                people.get(i).setFullName(replace(fullName, rnd1).toString());
                                break;
                            }
                            case (1): {
                                people.get(i).setFullName(delete(fullName, rnd1).toString());
                                break;
                            }
                            case (2): {
                                people.get(i).setFullName(insert(fullName, rnd1, setting).toString());
                                break;
                            }
                        }
                        break;
                    }
                    case (1): {
                        StringBuilder adress = new StringBuilder(people.get(i).getAddress());
                        if (adress.length() < 4)
                            n2 = 2;
                        switch (n2) {
                            case (0): {
                                people.get(i).setAddress(replace(adress, rnd1).toString());
                                break;
                            }
                            case (1): {
                                people.get(i).setAddress(delete(adress, rnd1).toString());
                                break;
                            }
                            case (2): {
                                people.get(i).setAddress(insert(adress, rnd1, setting).toString());
                                break;
                            }
                        }
                        break;
                    }
                    case (2): {
                        StringBuilder phone = new StringBuilder(people.get(i).getPhone());
                        if (phone.length() < 4)
                            n2 = 2;
                        switch (n2) {
                            case (0): {
                                people.get(i).setPhone(replace(phone, rnd1).toString());
                                break;
                            }
                            case (1): {
                                people.get(i).setPhone(delete(phone, rnd1).toString());
                                break;
                            }
                            case (2): {
                                people.get(i).setPhone(insertPhone(phone, rnd1, setting).toString());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private StringBuilder replace(StringBuilder field, SplittableRandom rnd) {
        StringBuilder result = new StringBuilder(field);
        int number = rnd.nextInt(field.length());
        if (number + 1 != field.length()) {
            result.setCharAt(number, field.charAt(number + 1));
            result.setCharAt(number + 1, field.charAt(number));
        } else {
            result.setCharAt(number, field.charAt(0));
            result.setCharAt(0, field.charAt(number));
        }
        return result;
    }

    private StringBuilder delete(StringBuilder field, SplittableRandom rnd) {
        int number = rnd.nextInt(field.length());
        field.deleteCharAt(number);
        return field;
    }

    private StringBuilder insert(StringBuilder field, SplittableRandom rnd, Setting setting) {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale(setting.getRegion()), new RandomService(new Random()));
        String str = fakeValuesService.regexify(region(setting.getRegion()));
        int number = rnd.nextInt(field.length());
        field.insert(number, str);
        return field;
    }

    private StringBuilder insertPhone(StringBuilder field, SplittableRandom rnd, Setting setting) {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale(setting.getRegion()), new RandomService(new Random()));
        String str = fakeValuesService.bothify("#");
        int number = rnd.nextInt(field.length());
        field.insert(number, str);
        return field;
    }

    private String region(String region) {
        if (region.equals("DE")) {
            return "[a-zäöü]";
        } else if (region.equals("PL")) {
            return "[a-pr-uwy-ząćęłńóśźżŻ]";
        } else if (region.equals("EN")) {
            return "[a-z]";
        }
        else if (region.equals("FR")) {
            return "[a-zàâäôéèëêïîçùûüÿæœ]";
        }
        return null;
    }


}
