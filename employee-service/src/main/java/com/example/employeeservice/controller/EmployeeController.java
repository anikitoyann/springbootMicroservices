package com.example.employeeservice.controller;
import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
//Build save departments Rest API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveDepartment(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployed=employeeService.saved(employeeDto);
        return new ResponseEntity<>(savedEmployed, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto>getEmployeeById(@PathVariable("id")Long employeeId){
        APIResponseDto employeeById = employeeService.getEmployeeById(employeeId);
    return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }
}
