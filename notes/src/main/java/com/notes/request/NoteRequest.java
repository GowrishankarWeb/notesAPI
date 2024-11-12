package com.notes.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequest {
	
	private Long id;
	
	private String title;
	
	@NotBlank(message = "Note is required")
	private String note;

}
