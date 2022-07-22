package br.com.projeto.resources.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {

	private LocalDateTime timeStamp;
	private Integer status;
	private String error;
	private String path;
	
}