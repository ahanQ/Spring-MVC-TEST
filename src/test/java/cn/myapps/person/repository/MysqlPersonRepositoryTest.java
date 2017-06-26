package cn.myapps.person.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;

import base.AbstractRepositoryTests;
import cn.myapps.person.entity.Person;

/**
 * {@link MysqlPersonRepository} 测试，直接从 {@link AbstractRepositoryTests}
 * 继承以启用测试。<br/>
 * 添加 {@link ActiveProfiles} 注解并设置其值为 mysql
 * 
 * @author ahan
 *
 */
public final class MysqlPersonRepositoryTest extends AbstractRepositoryTests {

  @Autowired
  private PersonRepository personRepository;

  private RandomStringGenerator rsg = new RandomStringGenerator.Builder().withinRange('A', 'z')
      .filteredBy(CharacterPredicates.LETTERS).build();

  private Map<Long, Person> allPerson;

  @Before
  public void init() {
    if (allPerson == null) {
      allPerson = new HashMap<Long, Person>();
    }
    Collection<Person> persons = personRepository.findAllpersons();
    for (Person person : persons) {
      allPerson.put(person.getId(), person);
    }
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
    person.setName(rsg.generate(RandomUtils.nextInt(4, 10)));
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
    person.setName(rsg.generate(RandomUtils.nextInt(4, 10)));
    person.setId(RandomUtils.nextLong());
    personRepository.save(person);
  }

  @Test
  public void update() {
    Person person = personRepository.update(5461604025189477376L,
        new Person(5461604025189477376L, "FnCyrLS", 48));
    Assert.assertNotNull(person);
    Assert.assertEquals(5461604025189477376L, person.getId().longValue());
    Assert.assertEquals("FnCyrLS", person.getName());
    Assert.assertEquals(48, person.getAge().longValue());

    person = personRepository.findById(5461604025189477376L);
    Assert.assertNotNull(person);
    Assert.assertEquals(5461604025189477376L, person.getId().longValue());
    Assert.assertEquals("FnCyrLS", person.getName());
    Assert.assertEquals(48, person.getAge().longValue());

    person = personRepository.update(5461604025189477376L,
        new Person(5461604025189477376L, "FOnCyrLS", 58));
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
