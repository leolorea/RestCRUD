package br.com.person_api_crud.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.person_api_crud.dto.MessageResponseDto;
import br.com.person_api_crud.dto.PersonDto;
import br.com.person_api_crud.dto.PersonFormDto;
import br.com.person_api_crud.model.Person;
import br.com.person_api_crud.repository.PersonRepository;
import exceptions.PersonNotFoundException;
import net.minidev.json.writer.BeansMapper.Bean;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Transactional
	public PersonDto save(PersonFormDto dto) {
		Person person = modelMapper.map(dto, Person.class);
		Person savedPerson = repository.save(person);

		return modelMapper.map(savedPerson, PersonDto.class);

	}

	public Page<PersonDto> list(Pageable pageable) {
		Page<Person> personList = repository.findAll(pageable);
		return personList.map(t -> modelMapper.map(t, PersonDto.class));

	}

	public MessageResponseDto delete(Long id) throws PersonNotFoundException{
		
		repository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
		repository.deleteById(id);
		MessageResponseDto messageResponse = createMessageResponse("Person deleted with id ", id);
		return messageResponse;
	}

	public Person updateById(Long id, Person person) throws PersonNotFoundException{
		
		 repository.findById(id)
         .orElseThrow(() -> new PersonNotFoundException(id));
		 
		 Person personAtual = repository.findById(id).get();
		 
		 BeanUtils.copyProperties(person, personAtual, "id");

		return repository.save(personAtual);
	      
		
	}
		
	
	 private MessageResponseDto createMessageResponse(String s, Long id2) {
	        return MessageResponseDto.builder()
	                .message(s + id2)
	                .build();
	    }

	public Person findById(Long id) throws PersonNotFoundException {
		
		repository.findById(id)
        .orElseThrow(() -> new PersonNotFoundException(id));
		
		return repository.findById(id).get();
	}

}
