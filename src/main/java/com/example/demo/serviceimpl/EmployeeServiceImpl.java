package com.example.demo.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.pojo.EmpPojo;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.util.StringUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void save(EmpPojo pojo) throws Exception {
		String fileName = StringUtils.cleanPath(pojo.getMultipartFile().getOriginalFilename());
		try {
			if (fileName.contains("...")) {
				throw new Exception("invalied file path" + fileName);

			}
			Employee emp = new Employee();
			emp.setFirstName(pojo.getFirstName());
			emp.setLastName(pojo.getLastName());
			emp.setEmail(pojo.getEmail());
			emp.setFileName(fileName);
			emp.setFileType(pojo.getMultipartFile().getContentType());
			emp.setFileData(pojo.getMultipartFile().getBytes());
			employeeRepository.save(emp);

		} catch (Exception e) {
			throw new Exception("file contins" + fileName);
		}

	}

	@Override
	public List<Employee> getAll() {
		List<Employee> hope = employeeRepository.findAll();
		return hope;
	}

	@Override
	public Employee getBy(Long Id) {
		return employeeRepository.findById(Id).get();

	}

	@Override
	public void deleteBy(Long Id) {

		employeeRepository.deleteById(Id);
	}

	@Override
	public Employee update(EmpPojo empp, Long Id) throws IOException {
		Employee em = employeeRepository.findById(Id).get();
		em.setFirstName(empp.getFirstName());
		em.setLastName(empp.getLastName());
		em.setEmail(empp.getEmail());
		em.setFileType(empp.getMultipartFile().getContentType());
		em.setFileName(StringUtils.cleanPath(empp.getMultipartFile().getOriginalFilename()));
		em.setFileData(empp.getMultipartFile().getBytes());

		return employeeRepository.save(em);

	}

	@Override
	public Employee downloadById(Long Id) throws Exception {
		return employeeRepository.findById(Id).orElseThrow(() -> new Exception("file not found with Id:" + Id));

	}

}
