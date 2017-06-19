package cn.myapps.person.controller;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import cn.myapps.person.entity.Person;
import cn.myapps.person.repository.PersonRepository;

@RestController
public class PersonController {

  private PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    super();
    this.personRepository = personRepository;
  }

  @GetMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<Person> findAllperson() {
    return personRepository.findAllpersons();
  }

  @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Person person(@PathVariable Long id) {
    return personRepository.findById(id);
  }

  @PostMapping(path = "/person", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Person createPerson(@RequestParam String name, @RequestParam Integer age) {
    Person person = new Person();
    person.setName(name);
    person.setAge(age);
    return personRepository.save(person);
  }

  @PutMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Person updatePerson(@PathVariable Long id, @RequestParam String name,
      @RequestParam Integer age) {
    Person person = personRepository.findById(id);
    if (person == null) {
      person = new Person();
    }
    person.setName(name);
    person.setAge(age);
    return personRepository.save(person);
  }

  @DeleteMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Person deletePerson(@PathVariable Long id) {
    return personRepository.deleteById(id);
  }

}
