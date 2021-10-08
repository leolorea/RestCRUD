package br.com.person_api_crud.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDto {

	
	private Long id;
   
	private String firstname;

	private String lastname;

	private String cpf;

	private LocalDate birthday;

	private String phone;

}
