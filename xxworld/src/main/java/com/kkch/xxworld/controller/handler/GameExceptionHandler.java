package com.kkch.xxworld.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kkch.xxworld.exception.UuidInvalidException;

@ControllerAdvice
public class GameExceptionHandler {

	@ExceptionHandler(UuidInvalidException.class)
	public String handleUuidInvalidException() {
		return "relogin";
	}
	
}
