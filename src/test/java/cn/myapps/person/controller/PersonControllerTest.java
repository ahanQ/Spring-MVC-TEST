package cn.myapps.person.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import base.AbstractControllerTests;
import cn.myapps.person.entity.Person;
import cn.myapps.person.repository.PersonRepository;

/**
 * Controller测试
 * 
 * @author ahan
 *
 */
// @ActiveProfiles("mysql")
public final class PersonControllerTest extends AbstractControllerTests {

  @Autowired
  private PersonRepository personRepository;

  private Map<Long, Person> allPerson;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) applicationContext)
        .build();
  }

  @Before
  public void allPersonSetup() {
    // 测试前准备的数据
    allPerson = new HashMap<Long, Person>();
    Collection<Person> allPersons = personRepository.findAllpersons();
    for (Person person : allPersons) {
      allPerson.put(person.getId(), person);
    }
  }

  @Test
  public void findById() {
    try {
      Set<Entry<Long, Person>> entrySet = allPerson.entrySet();
      int count = 7;// 可以限制验证的次数，也可以不设置次数。避免测试数据过多。
      for (Entry<Long, Person> entry : entrySet) {
        findByIdCommonTest(entry.getKey(), entry.getValue());
        if (--count <= 0)
          break;
      }
    } catch (Exception e) {
      Assert.fail("PersonControllerTest.findById() have exception: " + e.getMessage());
    }
  }

  @Test
  public void findAllPersons() throws Exception {
    ResultActions resultActions = this.mockMvc.perform(
        get("/persons").accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)));

    resultActions.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    // resultActions.andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8"))
    // .andExpect(jsonPath("$[0].age").value(8));
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[0].id").value(0)).andExpect(jsonPath("$[0].name").value("ahan8")).andExpect(jsonPath("$[0].age").value(8))
    // .andExpect(jsonPath("$[1].id").value(1)).andExpect(jsonPath("$[1].name").value("ahan7")).andExpect(jsonPath("$[1].age").value(7));
  }

  private void personCommonValid(ResultActions resultActions, Person person) {
    // resultActions.andExpect(jsonPath("$[" + person.getId() +
    // "].id").value(1)).andExpect(jsonPath("$[1].name").value("ahan7")).andExpect(jsonPath("$[1].age").value(7));
  }

  @Test
  public void createPerson() throws Exception {
    this.mockMvc
        .perform(post("/person").param("name", "createahan").param("age", "12")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.name").value("createahan"))
        .andExpect(jsonPath("$.age").value(12));
  }

  @Test
  @Repeat(2)
  public void deletePerson() throws Exception {
    this.mockMvc
        .perform(delete("/person/0")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0)).andExpect(jsonPath("$.name").value("ahan"))
        .andExpect(jsonPath("$.age").value(18));
    // 删除不存在的资源时就返回未找到资源的状态并返回错误码和错误信息
    // this.mockMvc
    // .perform(delete("/person/0").accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
    // .andExpect(status().isNotFound())
    // .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    // .andExpect(jsonPath("$.code").value(0)).andExpect(jsonPath("$.message").value("person
    // not found"));
  }

  @Test
  public void updatePerson() throws Exception {
    this.mockMvc
        .perform(put("/person/0").param("name", "updateahan").param("age", "12")
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(0L)).andExpect(jsonPath("$.name").value("updateahan"))
        .andExpect(jsonPath("$.age").value(12));
  }

  private void findByIdCommonTest(Long id, Person person) throws Exception {
    this.mockMvc
        .perform(get("/person/" + person.getId())
            .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(person.getId()))
        .andExpect(jsonPath("$.name").value(person.getName()))
        .andExpect(jsonPath("$.age").value(person.getAge()));
  }
}
