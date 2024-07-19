package com.spring.graphql.demo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private Integer id;
    private String name;
    private String mobile;
    private String email;
    private String[] address;

}
