package com.example.employeeservice.service;
import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saved(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(long id);

}
