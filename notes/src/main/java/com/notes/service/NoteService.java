package com.notes.service;

import com.notes.request.NoteRequest;

import jakarta.validation.Valid;

public interface NoteService {

	NoteRequest createNote(@Valid NoteRequest noteRequest);

}
