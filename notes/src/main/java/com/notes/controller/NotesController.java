package com.notes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notes.request.NoteRequest;
import com.notes.response.NoteResponse;
import com.notes.service.NoteService;

import jakarta.validation.Valid;

@RestController
public class NotesController {
	
	@Autowired
	NoteService noteService;
	
	@GetMapping("/apiCheck")
	public String apiCheck() {
		return "working";
	}
	
	@PostMapping("/create")
	public ResponseEntity<NoteResponse> createStudent(@Valid @RequestBody NoteRequest noteRequest, BindingResult result){	
		try {
			if(result.hasErrors()) {
				List<String> errors = new ArrayList<>();
	            for (FieldError error : result.getFieldErrors()) {
	                errors.add(error.getField() + ": " + error.getDefaultMessage());
	            } 
	            String errorString = String.join(", ", errors); 
	            NoteResponse noteResponse  = new NoteResponse(
	            		errorString,
	            		"Failed in the validation checking",
	            		(Object)noteRequest
	            		);
				return new ResponseEntity<NoteResponse>(noteResponse, HttpStatus.CONFLICT);
			}
			else {
				NoteRequest noteInserted=noteService.createNote(noteRequest);
				NoteResponse noteResponse  = new NoteResponse(
	            		"Notes created successfully",
	            		"Notes added",
	            		(Object)noteInserted
	            		);
				return new ResponseEntity<NoteResponse>(noteResponse,HttpStatus.CREATED);
			}
		}catch (Exception e) {
			e.printStackTrace();
			 NoteResponse noteResponse  = new NoteResponse(
	            		e.getMessage()+" "+e.getLocalizedMessage(),
	            		"exception caught",
	            		(Object)noteRequest
	            		);
			return new ResponseEntity<NoteResponse>(noteResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
