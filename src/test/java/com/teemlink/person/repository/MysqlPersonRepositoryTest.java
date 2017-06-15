package com.teemlink.person.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.teemlink.person.entity.Person;

import spring.config.AppConfig;

@ActiveProfiles("mysql")
@ContextConfiguration(classes = { AppConfig.class })
public class MysqlPersonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private PersonRepository personRepository;

  private static Map<Long, Person> allPerson;

  @BeforeClass
  public static void init() {
    allPerson = new HashMap<Long, Person>();
    allPerson.put(0L, new Person(0L, "ahan", 18));
    allPerson.put(93861369773759488L, new Person(93861369773759488L, "HxHKCu", 88));
    allPerson.put(1598465293393452032L, new Person(1598465293393452032L, "jhTM", 47));
    allPerson.put(1776370164197006336L, new Person(1776370164197006336L, "TjDAldq", 22));
    allPerson.put(5461604025189477376L, new Person(5461604025189477376L, "FOnCyrLS", 58));
    allPerson.put(6624002592547291136L, new Person(6624002592547291136L, "KHTvHtWH", 43));
    allPerson.put(7911526969920167936L, new Person(7911526969920167936L, "KpkyYW", 66));
    allPerson.put(8174430517177737216L, new Person(8174430517177737216L, "PecMlMPF", 68));
    allPerson.put(8354522141506979840L, new Person(8354522141506979840L, "zbIUkG", 73));
    allPerson.put(8580730913348161536L, new Person(8580730913348161536L, "imlRJTH", 66));
  }

  @Test
  public void findAll() {
    Collection<Person> persons = personRepository.findAllpersons();
    for (Person person : persons) {
      Person expectedPerson = allPerson.get(person.getId());
      Assert.assertNotNull(expectedPerson);
      Assert.assertEquals(expectedPerson.getId().longValue(), person.getId().longValue());
      Assert.assertEquals(expectedPerson.getName(), person.getName());
      Assert.assertEquals(expectedPerson.getAge().longValue(), person.getAge().longValue());
    }
    Person person = new Person();
    person.setAge(RandomUtils.nextInt(10, 100));
    person.setName(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
    person.setId(RandomUtils.nextLong());
    personRepository.save(person);
  }

  @Test
  public void findById() {
    Person person = personRepository.findById(0L);
    Assert.assertNotNull(person);
    Assert.assertEquals(0L, person.getId().longValue());
    Assert.assertEquals("ahan", person.getName());
    Assert.assertEquals(18L, person.getAge().longValue());
  }

  @Test
  @Repeat(3)
  public void save() {
    Person person = new Person();
    person.setAge(RandomUtils.nextInt(10, 100));
    person.setName(RandomStringUtils.random(RandomUtils.nextInt(4, 10), true, false));
    person.setId(RandomUtils.nextLong());
    personRepository.save(person);
  }

  @Test
  public void update() {
    Person person = personRepository.update(new Person(5461604025189477376L, "FnCyrLS", 48));
    Assert.assertNotNull(person);
    Assert.assertEquals(5461604025189477376L, person.getId().longValue());
    Assert.assertEquals("FnCyrLS", person.getName());
    Assert.assertEquals(48, person.getAge().longValue());

    person = personRepository.findById(5461604025189477376L);
    Assert.assertNotNull(person);
    Assert.assertEquals(5461604025189477376L, person.getId().longValue());
    Assert.assertEquals("FnCyrLS", person.getName());
    Assert.assertEquals(48, person.getAge().longValue());

    person = personRepository.update(new Person(5461604025189477376L, "FOnCyrLS", 58));
    Assert.assertNotNull(person);
    Assert.assertEquals(5461604025189477376L, person.getId().longValue());
    Assert.assertEquals("FOnCyrLS", person.getName());
    Assert.assertEquals(58, person.getAge().longValue());

  }

  @Test
  public void delete() {
    Person person = personRepository.deleteById(0L);
    Assert.assertNotNull(person);
  }

}
