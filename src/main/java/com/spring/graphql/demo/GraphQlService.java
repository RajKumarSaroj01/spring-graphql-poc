package com.spring.graphql.demo;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraphQlService {

    @Autowired
    private PersonRepo repo;


    @Value("classpath:graphql/person.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File file=resource.getFile();
        TypeDefinitionRegistry registry=new SchemaParser().parse(file);
        RuntimeWiring wiring=buildWiring();
        GraphQLSchema schema=new SchemaGenerator().makeExecutableSchema(registry,wiring);
        graphQL=GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<PersonEntity>> fetcher1= data->{
            return (List<PersonEntity>) repo.findAll();
        };
        DataFetcher<PersonEntity> fetcher2=data->{
            return (PersonEntity) repo.findByEmail(data.getArgument("email"));
        };
        return RuntimeWiring.newRuntimeWiring().type("Query"
                ,typewriting->typewriting.dataFetcher("getAllPerson",fetcher1)
                        .dataFetcher("findPerson",fetcher2)
        ).build();
    }

    public void addPerson(List<PersonDTO> list){
        List<PersonEntity> entities=new ArrayList<>();
        for(PersonDTO dto:list){
            PersonEntity entity=new PersonEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setMobile(dto.getMobile());
            entity.setMobile(dto.getMobile());
            entity.setAddress(dto.getAddress());
            entities.add(entity);
        }
        repo.saveAll(entities);
    }
    public List<PersonEntity> get(){
        return repo.findAll();
    }

    public Object fetchData(String query) {
        return  graphQL.execute(query);
    }

    public PersonEntity createPerson(PersonDTO input) {
        PersonEntity entity=new PersonEntity();
        entity.setId(input.getId());
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setMobile(input.getMobile());
        entity.setMobile(input.getMobile());
        entity.setAddress(input.getAddress());
        return repo.save(entity);
    }
}
