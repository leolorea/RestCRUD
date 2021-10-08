package br.com.person_api_crud.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.person_api_crud.dto.MessageResponseDto;
import br.com.person_api_crud.dto.PersonDto;
import br.com.person_api_crud.dto.PersonFormDto;
import br.com.person_api_crud.model.Person;
import br.com.person_api_crud.service.PersonService;
import exceptions.PersonNotFoundException;

@RestController
@RequestMapping ("/person")
public class PersonController {
	
	
	@Autowired
	private PersonService service;
	
	
	@PostMapping
	public ResponseEntity<PersonDto> save(@RequestBody @Valid PersonFormDto dto, UriComponentsBuilder uriBuilder) {
		PersonDto personDto = service.save(dto);
		
		URI uri = uriBuilder.path("/person/{id}").buildAndExpand(personDto.getId()).toUri();
		return ResponseEntity.created(uri).body(personDto);
	}
	
	@GetMapping
	public Page<PersonDto> list(@PageableDefault(size=10) Pageable pageable){
		return service.list(pageable);
	}
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable Long id) throws PersonNotFoundException{
		return service.findById(id);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public MessageResponseDto deleById(@PathVariable Long id) throws PersonNotFoundException {
		return service.delete(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person update(@PathVariable Long id,@RequestBody @Valid Person person) throws PersonNotFoundException{
		return service.updateById(id, person);
	}
	
	
    
	
}
