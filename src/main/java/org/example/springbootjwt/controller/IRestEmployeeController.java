package org.example.springbootjwt.controller;

import org.example.springbootjwt.dto.dtoEmployee;

public interface IRestEmployeeController {


   public dtoEmployee findEmployeeById(Long id);


}
