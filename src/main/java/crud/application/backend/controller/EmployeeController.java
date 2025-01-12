package crud.application.backend.controller;


import crud.application.backend.dto.EmployeeDto;
import crud.application.backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;


    // build add employee rest api

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
      EmployeeDto savedEmployee =  employeeService.createEmployee(employeeDto);
      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
       EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
       return ResponseEntity.ok(employeeDto);
    }



    //build get all employees rest api
    @GetMapping ("/allemployee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
       List<EmployeeDto> employees = employeeService.getAllEmployees();
       return ResponseEntity.ok(employees);
    }



    // BUILD UPDATE ALL REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto =  employeeService.updateEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // build delete employee rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted");
    }
}

