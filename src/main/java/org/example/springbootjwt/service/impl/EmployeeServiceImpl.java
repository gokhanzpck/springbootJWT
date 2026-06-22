package org.example.springbootjwt.service.impl;

import org.example.springbootjwt.dto.dtoDepartmannt;
import org.example.springbootjwt.dto.dtoEmployee;
import org.example.springbootjwt.model.Department;
import org.example.springbootjwt.model.Employee;
import org.example.springbootjwt.repository.EmployeeRepository;
import org.example.springbootjwt.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
     private EmployeeRepository employeeRepository;

    @Override
    public dtoEmployee findEmployeeById(Long id) {
         dtoEmployee dtoEmployee=new dtoEmployee();
        dtoDepartmannt dtoDepartmannt=new dtoDepartmannt();

        Optional<Employee> optional=employeeRepository.findById(id);
        if (optional.isEmpty()){
            return  null;
        }
        Employee employee=optional.get();
        Department department=employee.getDepartment();
        BeanUtils.copyProperties(employee,dtoEmployee);
        BeanUtils.copyProperties(department,dtoDepartmannt);
        dtoEmployee.setDepartmannt(dtoDepartmannt);


        return dtoEmployee;
//Bir çalışanın (Employee) bilgilerini getirirken, bağlı olduğu departmanın (Department) bilgilerini de DTO olarak döndürmek.
    }
}
