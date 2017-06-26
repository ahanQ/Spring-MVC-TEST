package cn.myapps.person.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.myapps.base.config.DataSources;
import cn.myapps.person.entity.Person;

public class ConnectPersonRepository implements PersonRepository {
	
  private static final String INSERT = "insert into person (id, name, age) values (?, ?, ?)";

  private static final String QUERY_BY_ID = "select * from person where id=?";

  private static final String QUERY_ALL = "select * from person where 1=1";

  private static final String UPDATE = "update person set name=?, age=? where id=?";

  private static final String DELETE = "delete from person where id=?";
  
	@Autowired
	private DataSources dataSources;

	public Collection<Person> findAllpersons() {
		
		DataSource dataSource = dataSources.getDataSource("2");
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(QUERY_ALL);
			resultSet = statement.executeQuery();
			Collection<Person> result = new ArrayList<Person>();
			while(resultSet.next()){
				Long id = resultSet.getLong("ID");
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				
				Person person = new Person(id, name, age);
				
				result.add(person);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();
					statement = null;
				}
				if(connection != null){
					connection.close();
					connection = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
			}
		}
		return null;
	}

	public Person findById(Long id) {
		
		DataSource dataSource = dataSources.getDataSource("2");
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(QUERY_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Person person = null;
			if(resultSet.next()){
				id = resultSet.getLong("ID");
				String name = resultSet.getString("name");
				Integer age = resultSet.getInt("age");
				
				person = new Person(id, name, age);
				
			}
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();
					statement = null;
				}
				if(connection != null){
					connection.close();
					connection = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
			}
		}
		return null;
	}

	public Person save(Person person) {
		
		DataSource dataSource = dataSources.getDataSource("2");
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(INSERT);
			statement.setLong(1, person.getId());
			statement.setString(2, person.getName());
			statement.setInt(3, person.getAge());
			
			int rowSet = statement.executeUpdate();
			if(rowSet == 1){
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null){
					connection.close();
					connection = null;
				}
				if(statement != null) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
			}
		}
		return null;
	}

	public Person update(Long id, Person person) {
		
		DataSource dataSource = dataSources.getDataSource("2");
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, person.getName());
			statement.setInt(2, person.getAge());
			statement.setLong(3, id);
			
			int rowSet = statement.executeUpdate();
			if(rowSet == 1){
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null){
					connection.close();
					connection = null;
				}
				if(statement != null) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
			}
		}
		return null;
	}

	public Person deleteById(Long id) {
		
		DataSource dataSource = dataSources.getDataSource("2");
		PreparedStatement statement = null;
		Connection connection = null;
		Person person = null;
		person = findById(id);
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, id);
			
			int rowSet = statement.executeUpdate();
			if(rowSet == 1){
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null){
					connection.close();
					connection = null;
				}
				if(statement != null) {
					statement.close();
					statement = null;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
			}
		}
		return null;
	}

}
