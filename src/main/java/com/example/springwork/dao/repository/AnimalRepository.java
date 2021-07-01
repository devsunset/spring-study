package com.example.springwork.dao.repository;

import java.util.List;

import com.example.springwork.domain.Animal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "animal", path = "animal")
public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long> {

  List<Animal> findByLastName(@Param("name") String name);

}