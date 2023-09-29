package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert DepartmentDto to Department entity
        Department department=new Department(departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto saveDepartmentDto=new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode());
        return saveDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department=departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto=new DepartmentDto(department.getId(),
                department.getDepartmentName(),department.getDepartmentDescription(),
                department.getDepartmentCode());
        return departmentDto;
    }
}
