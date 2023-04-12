package com.example.demo.controller;

import java.io.IOException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Employee;
import com.example.demo.pojo.EmpPojo;
import com.example.demo.service.EmployeeService;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api("employee management")

public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	// save the employee or add employee details

	@PostMapping(value="/save",consumes = 
			 MediaType.MULTIPART_FORM_DATA_VALUE
	   )
	public void saveDetails(@ModelAttribute EmpPojo Pojo) throws Exception {

		employeeService.save(Pojo);

	}

	// get the list of employees or display the all the employees
	@ApiOperation("get detailes")
	@GetMapping("/get")
	public List<Employee> getDeta() {
		List<Employee> nope = employeeService.getAll();
		return nope;

	}

	// get perticular user by using id
	@GetMapping("/get/{Id}")
	//@ApiOperation("get by detailes")
	public Employee getBy(@PathVariable Long Id) {
		Employee hope = employeeService.getBy(Id);
		return hope;

	}

	// delete user by using id
	@DeleteMapping("/dele/{Id}")
	//@ApiOperation("delete detailes")
	public void deleteDet(@PathVariable Long Id) {
		employeeService.deleteBy(Id);
	}

	// update the employee by using the id
	@PutMapping("/update/{Id}")
//	@ApiOperation("update detailes")
	public Employee updateById(@ModelAttribute EmpPojo empp, @PathVariable Long Id) throws IOException {
		return employeeService.update(empp, Id);

	}

	// to download the cv by using the id number
	@GetMapping("/down/{Id}")
	//@ApiOperation("download cv")
	public ResponseEntity<ByteArrayResource> downLoad(@PathVariable Long Id) throws Exception {
		Employee e = employeeService.downloadById(Id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(e.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + e.getFileName() + "\"")
				.body(new ByteArrayResource(e.getFileData()));

	}

}
