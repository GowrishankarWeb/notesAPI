package com.notes.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoteResponse {
	
	private String message;
	private String reason;
	private Object result;
	
}
