package cn.myapps.person.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import cn.myapps.person.entity.Person;

@Repository
@Profile("local")
public class LocalPersonRepository implements PersonRepository {

  private Long count = 0l;

  private Map<Long, Person> persons = new HashMap<Long, Person>();

  public Collection<Person> findAllpersons() {
    return persons.values();
  }

  public Person findById(Long id) {
    return persons.get(id);
  }

  public Person save(Person person) {
    if (person == null)
      return null;
    if (person.getId() == null) {
      person.setId(count++);
    }
    persons.put(person.getId(), person);
    return person;
  }

  public Person update(Person person) {
    return save(person);
  }

  public Person deleteById(Long id) {
    return persons.remove(id);
  }
}
