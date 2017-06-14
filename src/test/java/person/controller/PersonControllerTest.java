package person.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.teemlink.person.entity.Person;
import com.teemlink.person.repository.PersonRepository;

import base.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest extends AbstractContextControllerTests {

  @Autowired
  private PersonRepository personRepository;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  public void contextTest() {
    Assert.assertNotNull(webApplicationContext);
  }

  @Test
  @DirtiesContext
  public void AllTests() throws Exception {

    // POST: /person?name=ahan&age=12
    this.mockMvc
        .perform(post("/person").param("name", "ahan").param("age", "12")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan"))
        .andExpect(jsonPath("$.age").value(12));

    // GET: /person/0
    this.mockMvc
        .perform(get("/person/0")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan"))
        .andExpect(jsonPath("$.age").value(12));

    // PUT: /person/1?name=ahan2&age=14
    this.mockMvc
        .perform(put("/person/0").param("name", "ahan2").param("age", "14")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan2"))
        .andExpect(jsonPath("$.age").value(14));

    // GET: /persons
    this.mockMvc
        .perform(
            get("/persons").accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$[0].name").value("ahan2")).andExpect(jsonPath("$[0].age").value(14));

    // DELETE: /person/0
    this.mockMvc
        .perform(delete("/person/0")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan2"))
        .andExpect(jsonPath("$.age").value(14));

  }

  @Test
  @DirtiesContext
  public void findById() throws Exception {
    Person person = new Person();
    person.setName("ahan2");
    person.setAge(22L);
    personRepository.save(person);
    System.out.println("id: " + personRepository.findAllpersons());
    this.mockMvc
        .perform(get("/person/0")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan2"))
        .andExpect(jsonPath("$.age").value(22));
  }

  @Test
  @DirtiesContext
  public void findAllPersons() throws Exception {
    Person person = new Person();
    person.setName("ahan8");
    person.setAge(8L);
    Person person2 = new Person();
    person2.setName("ahan7");
    person2.setAge(7L);
    personRepository.save(person);
    personRepository.save(person2);
    System.out.println("all: " + personRepository.findAllpersons());
    this.mockMvc
        .perform(
            get("/persons").accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8"))
        .andExpect(jsonPath("$[0].age").value(8)).andExpect(jsonPath("$[1].id").value(1))
        .andExpect(jsonPath("$[1].name").value("ahan7")).andExpect(jsonPath("$[1].age").value(7));
  }

  @Test
  @DirtiesContext
  public void createPerson() throws Exception {
    this.mockMvc
        .perform(post("/person").param("name", "ahan").param("age", "12")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan"))
        .andExpect(jsonPath("$.age").value(12));
    this.mockMvc
        .perform(post("/person").param("name", "ahan2").param("age", "122")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.name").value("ahan2"))
        .andExpect(jsonPath("$.age").value(122));
    System.out.println("create: " + personRepository.findAllpersons());
  }

  @Test
  @DirtiesContext
  public void deletePerson() throws Exception {
    Person person = new Person();
    person.setName("ahan23");
    person.setAge(223L);
    personRepository.save(person);
    System.out.println("delete1: " + personRepository.findAllpersons());
    this.mockMvc
        .perform(delete("/person/0")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan23"))
        .andExpect(jsonPath("$.age").value(223));
    System.out.println("delete2: " + personRepository.findAllpersons());
  }

  @Test
  @DirtiesContext
  public void updatePerson() throws Exception {
    Person person = new Person();
    person.setName("ahanhaha");
    person.setAge(2234L);
    personRepository.save(person);
    System.out.println("update: " + personRepository.findAllpersons());
    this.mockMvc
        .perform(put("/person/0").param("name", "ahan").param("age", "12")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan"))
        .andExpect(jsonPath("$.age").value(12));

  }

  public PersonRepository getPersonRepository() {
    return personRepository;
  }

  public void setPersonRepository(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

}
