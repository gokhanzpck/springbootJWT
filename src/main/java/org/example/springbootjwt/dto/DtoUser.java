package org.example.springbootjwt.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.internal.Abstract;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DtoUser {
    private String username;

    private String password;
}
