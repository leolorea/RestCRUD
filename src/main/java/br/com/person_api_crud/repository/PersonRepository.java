package br.com.person_api_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.person_api_crud.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
