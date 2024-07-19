package com.spring.graphql.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PersonEntity {

    @Id
    private Integer id;
    private String name;
    private String mobile;
    private String email;
    private String[] address;

}
