package com.notes.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.notes.entity.Notes;
import com.notes.repository.NotesRepository;
import com.notes.request.NoteRequest;

import jakarta.validation.Valid;

@Service
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	NotesRepository notesRepository;

	@Override
	public NoteRequest createNote(@Valid NoteRequest noteRequest) {
		try {
			Notes notes=new Notes();
			BeanUtils.copyProperties(noteRequest, notes);
			Notes noteInserted = notesRepository.save(notes);
			BeanUtils.copyProperties(noteInserted, noteRequest);
			if(noteInserted!=null) {
				return noteRequest;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

}
