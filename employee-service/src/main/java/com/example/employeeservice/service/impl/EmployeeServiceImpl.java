package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    //  private final RestTemplate restTemplate;
    //  private final WebClient webClient;
    private final APIClient apiClient;

    @Override
    public EmployeeDto saved(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(), employeeDto.getDepartmentCode());
        Employee save = employeeRepository.save(employee);
        EmployeeDto employeeDtoSave = new EmployeeDto(save.getId(), save.getFirstName(),
                save.getLastName(), save.getEmail(), save.getDepartmentCode());
        return employeeDtoSave;
    }

    @Override
    public APIResponseDto getEmployeeById(long id) {
        Employee employeeById = employeeRepository.getEmployeeById(id);
        /* ResponseEntity<DepartmentDto> responseEntity =
         restTemplate.getForEntity("http://localhost:8080/api/departments/" +
                employeeById.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto=responseEntity.getBody();*/

      /*   DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employeeById.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block(); */
        DepartmentDto departmentDto = apiClient.getDepartment(employeeById.getDepartmentCode());
        EmployeeDto employeeDto = new EmployeeDto(employeeById.getId(),
                employeeById.getFirstName(),
                employeeById.getLastName(), employeeById.getEmail(), employeeById.getDepartmentCode());
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}
