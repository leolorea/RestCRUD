package br.com.person_api_crud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponseDto {
	private String message;

}
