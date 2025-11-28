package com.david.notes.service;

import com.david.notes.dto.NoteRequest;
import com.david.notes.entity.Note;
import com.david.notes.exception.NotFoundException;
import com.david.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class NoteServiceTest {
    private NoteRepository repo;
    private NoteService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(NoteRepository.class);
        service = new NoteService(repo);
    }

    @Test
    void testCreateNote() {
        NoteRequest req = new NoteRequest("Test Title", "Test Content");
        Note saved = new Note("Test Title", "Test Content");
        saved.setId(1L);

        when(repo.save(any(Note.class))).thenReturn(saved);

        Note result = service.createNote(req);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Title", result.getTitle());
    }

    @Test
    void testGetNoteFound() {
        Note note = new Note("A", "B");
        note.setId(10L);

        when(repo.findById(10L)).thenReturn(Optional.of(note));

        Note found = service.getNote(10L);

        assertEquals(10L, found.getId());
    }

    @Test
    void testGetNoteNotFound() {
        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getNote(999L));
    }

    @Test
    void testUpdateNote() {
        Note existing = new Note("Old", "Old Content");
        existing.setId(5L);

        NoteRequest req = new NoteRequest("New Title", "New Content");

        when(repo.findById(5L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        Note updated = service.updateNote(5L, req);

        assertEquals("New Title", updated.getTitle());
        assertEquals("New Content", updated.getContent());
    }

    @Test
    void testDeleteNoteNotFound() {
        when(repo.existsById(88L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.deleteNote(88L));
    }
}
