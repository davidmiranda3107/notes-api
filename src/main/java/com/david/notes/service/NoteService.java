package com.david.notes.service;

import com.david.notes.dto.NoteRequest;
import com.david.notes.entity.Note;
import com.david.notes.exception.NotFoundException;
import com.david.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public Note createNote(NoteRequest req) {
        Note n = new Note(req.getTitle(), req.getContent());
        return repo.save(n);
    }

    public Note getNote(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Note not found with id " + id));
    }

    public List<Note> listAll() {
        return repo.findAll();
    }

    public List<Note> search(String param) {
        return repo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(param, param);
    }

    public Note updateNote(Long id, NoteRequest req) {
        Note existing = getNote(id);
        if (req.getTitle() != null) existing.setTitle(req.getTitle());
        if (req.getContent() != null) existing.setContent(req.getContent());
        return repo.save(existing);
    }

    public void deleteNote(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Note not found with id " + id);
        repo.deleteById(id);
    }
}
