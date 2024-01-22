package net.springprojectbackend.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springprojectbackend.springboot.exception.ResourceNotFoundException;
import net.springprojectbackend.springboot.model.Employee;
import net.springprojectbackend.springboot.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}		
	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee e = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee not exists with id: " + id));
		return ResponseEntity.ok(e);
	}
	
	//update employee rest api
	@PutMapping
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employeeDetails) {
		Employee e = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee not exists with id: " + id));
		e.setFirstName(employeeDetails.getFirstName());
		e.setLastName(employeeDetails.getLastName());
		e.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee = employeeRepository.save(e);
		return ResponseEntity.ok(updatedEmployee);
	}
}