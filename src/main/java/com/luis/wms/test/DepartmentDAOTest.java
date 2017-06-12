package com.luis.wms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luis.wms.domain.Department;
import com.luis.wms.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentDAOTest {

	@Autowired
	private IDepartmentService departmentService;
	
	@Test
	public void testSave() {
		Department entity = new Department();
		entity.setName("开发部");
		entity.setSn("IT");
		departmentService.save(entity);
	}

	@Test
	public void testDelete() {
		Department entity = new Department();
		entity.setId(1L);
		departmentService.delete(entity);
	}

	@Test
	public void testUpdate() {
		Department entity = new Department();
		entity.setName("开发部");
		entity.setSn("IT");
		entity.setId(2L);
		departmentService.update(entity);
	}

	@Test
	public void testGet() {
		Department dept = departmentService.get(2L);
		System.out.println(dept);
	}

	@Test
	public void testListAll() {
		List<Department> list = departmentService.listAll();
		for (Department dept : list) {
			System.out.println(dept);
		}
	}

}
