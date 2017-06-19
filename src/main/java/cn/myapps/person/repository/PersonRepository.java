package cn.myapps.person.repository;

import java.util.Collection;

import cn.myapps.person.entity.Person;

/**
 * 数据访问层，只定义接口，根据不同需要，实现不同的实现类。比如不同的数据库要按需加载。
 * 
 * @author ahan
 *
 */
public interface PersonRepository {
  /**
   * 查询所有的 {@link Person}
   * 
   * @return
   */
  public Collection<Person> findAllpersons();

  /**
   * 根据 id 查询唯一的 {@link Person}
   * 
   * @param id
   * @return
   */
  public Person findById(Long id);

  /**
   * 保存 {@link Person}
   * 
   * @param person
   * @return
   */
  public Person save(Person person);

  /**
   * 根据 id 更新 {@link Person}
   * 
   * @param person
   * @return
   */
  public Person update(Person person);

  /**
   * 根据 id 删除 {@link Person}
   * 
   * @param id
   * @return
   */
  public Person deleteById(Long id);
}
