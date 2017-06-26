package cn.myapps.person.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.myapps.person.entity.Person;

/**
 * Mysql数据库访问的{@link PersonRepository}的实现类
 * 
 * @author ahan
 *
 */
@Repository
@Profile("mysql")
public class MysqlPersonRepository implements PersonRepository {

  private static final String INSERT = "insert into person (id, name, age) values (?, ?, ?)";

  private static final String QUERY_BY_ID = "select * from person where id=?";

  private static final String QUERY_ALL = "select * from person where 1=1";

  private static final String UPDATE = "update person set name=?, age=? where id=?";

  private static final String DELETE = "delete from person where id=?";

  @Autowired
  private JdbcOperations jdbcOperations;

  public Collection<Person> findAllpersons() {
    return jdbcOperations.query(QUERY_ALL, new RowMapper<Person>() {
      public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
      }
    });
  }

  public Person findById(Long id) {
    return jdbcOperations.queryForObject(QUERY_BY_ID, new RowMapper<Person>() {
      public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        return person;
      }
    }, id);
  }

  public Person save(Person person) {
    int rowNum = jdbcOperations.update(INSERT, person.getId(), person.getName(), person.getAge());
    if (rowNum != 1) {
      return null;
    }
    return person;
  }

  public Person update(Long id, Person person) {
    int rowNum = jdbcOperations.update(UPDATE, person.getName(), person.getAge(), id);
    if (rowNum != 1) {
      return null;
    }
    return person;
  }

  public Person deleteById(Long id) {
    Person person = findById(id);
    int rowNum = jdbcOperations.update(DELETE, new Object[] { id });
    if (rowNum != 1) {
      return null;
    }
    return person;
  }

}
