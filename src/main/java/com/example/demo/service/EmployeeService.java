package com.example.demo.service;

import java.io.IOException;
import java.util.List;


import com.example.demo.model.Employee;
import com.example.demo.pojo.EmpPojo;

public interface EmployeeService {
	public void save(EmpPojo pojo) throws Exception;
    public List<Employee> getAll();
    public Employee getBy(Long Id);
    public void deleteBy(Long Id);
    public Employee downloadById(Long Id) throws Exception;
	Employee update(EmpPojo empp, Long Id) throws IOException;
    

}
