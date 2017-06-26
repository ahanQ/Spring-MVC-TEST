package cn.myapps.person.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.AbstractRepositoryTests;

public class PersonTest extends AbstractRepositoryTests {

	@Autowired
	private Person person1;
	@Autowired
	private Person person2;
	@Autowired
	private Person person3;
	@Autowired
	private Person person4;
	@Autowired
	private Person person5;
	@Autowired
	private Person person6;
	
	@Test
	public void personEquals(){
		Assert.assertNotEquals(person1, person2);
		Assert.assertNotEquals(person2, person3);
		Assert.assertNotEquals(person3, person4);
		Assert.assertNotEquals(person4, person5);
		Assert.assertNotEquals(person5, person6);
		Assert.assertNotEquals(person6, person1);
		
		System.out.println(person1);
		System.out.println(person2);
		System.out.println(person3);
		System.out.println(person4);
		System.out.println(person5);
		System.out.println(person6);
		
	}
	
}
