package org.example.springbootjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class dtoEmployee {

  private Long id;

  private String firstName;

  private String lastName;


  private dtoDepartmannt departmannt;




}
