package com.spring.graphql.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/test")
public class GraphQlController {

    @Autowired
    private GraphQlService service;


    // Normal REST POST call
    @PostMapping("/add")
    public String addPerson(@RequestBody  List<PersonDTO> list){
        service.addPerson(list);
        return "Success";
    }
    // Normal REST GET call
    @GetMapping("/fetch")
    public List<PersonEntity> get(){
       return service.get();
    }

    // GraphQL POST call
    @PostMapping("/fetchdata")
    public ResponseEntity<Object> fetchData(@RequestBody String query){
       return ResponseEntity.ok(service.fetchData(query));
    }
}
