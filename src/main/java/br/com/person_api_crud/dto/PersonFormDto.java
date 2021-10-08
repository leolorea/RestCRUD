package br.com.person_api_crud.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonFormDto {
	
 
    @NotBlank
	private String firstname;
    
    @NotBlank
	private String lastname;
    
    @CPF
    @NotBlank
	private String cpf;

    @DateTimeFormat
    @Past
	private LocalDate birthday;

    @NotBlank
	private String phone;


}
