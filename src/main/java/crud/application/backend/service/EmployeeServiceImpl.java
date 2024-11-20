package crud.application.backend.service;

import crud.application.backend.dto.EmployeeDto;
import crud.application.backend.entity.Employee;
import crud.application.backend.exception.ResourceNotFoundException;
import crud.application.backend.mapper.EmployeeMapper;
import crud.application.backend.repository.EmployeeRepsitory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepsitory employeeRepsitory;
    @Override

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepsitory.save(employee);
        return EmployeeMapper.mapToEmployeeDto( savedEmployee );
    }

    @Override
    public EmployeeDto getEmployeeById(Long EmployeeId) {

        Employee employee = employeeRepsitory.findById(EmployeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + EmployeeId));



        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepsitory.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

       Employee employee = employeeRepsitory.findById(employeeId).orElseThrow(
        () -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));

       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
       employee.setEmail(updatedEmployee.getEmail());

       Employee updatedEmployeeObj = employeeRepsitory.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {


        Employee employee = employeeRepsitory.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
        employeeRepsitory.deleteById(employeeId);
    }
}
