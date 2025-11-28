package com.david.notes.controller;

import com.david.notes.dto.NoteRequest;
import com.david.notes.dto.NoteResponse;
import com.david.notes.entity.Note;
import com.david.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), note.getUpdatedAt());
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest request) {
        Note created = noteService.createNote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> get(@PathVariable("id") Long id) {
        Note n = noteService.getNote(id);
        return ResponseEntity.ok(toResponse(n));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> list(@RequestParam(value = "q", required = false) String q) {
        List<Note> list = (q == null || q.isBlank()) ? noteService.listAll() : noteService.search(q);
        List<NoteResponse> resp = list.stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(@PathVariable("id") Long id, @Valid @RequestBody NoteRequest req) {
        Note updated = noteService.updateNote(id, req);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
