package com.luis.wms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luis.wms.domain.Employee;
import com.luis.wms.query.EmployeeQueryObject;
import com.luis.wms.query.PageResult;
import com.luis.wms.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeDAOTest {

	@Autowired
	private IEmployeeService EmployeeService;
	
	@Test
	public void testSave() {
			Employee entity = new Employee();
			entity.setName("Bob");
			entity.setPassword("101");
			entity.setEmail("bob@qq.com");
			entity.setAge(20 );
			entity.setAdmin(false);
			EmployeeService.save(entity);
	}

	@Test
	public void testDelete() {
		Employee entity = new Employee();
		entity.setId(1L);
		EmployeeService.delete(entity);
	}

	@Test
	public void testUpdate() {
		Employee entity = new Employee();
		entity.setName("开发部");
		entity.setId(2L);
		EmployeeService.update(entity);
	}

	@Test
	public void testGet() {
		Employee entity = EmployeeService.get(2L);
		System.out.println(entity);
	}

	@Test
	public void testListAll() {
		List<Employee> list = EmployeeService.listAll();
		for (Employee entity : list) {
			System.out.println(entity);
		}
	}

	@Test
	public void test1() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setKeyword("b");
		PageResult result = EmployeeService.query(qo);
		System.out.println(result);
	}
}
