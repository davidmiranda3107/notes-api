package com.david.notes.controller;

import com.david.notes.entity.Note;
import com.david.notes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteService service;

    @Test
    void testCreateNote() throws Exception {
        Note saved = new Note("Title", "Content");
        saved.setId(1L);
        saved.setCreatedAt(LocalDateTime.now());

        Mockito.when(service.createNote(Mockito.any())).thenReturn(saved);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Title\", \"content\":\"Content\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetNote() throws Exception {
        Note n = new Note("A", "B");
        n.setId(2L);
        n.setCreatedAt(LocalDateTime.now());

        Mockito.when(service.getNote(2L)).thenReturn(n);

        mockMvc.perform(get("/api/notes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void testListNotes() throws Exception {
        Note n1 = new Note("T1", "C1");
        n1.setId(1L);
        n1.setCreatedAt(LocalDateTime.now());

        Mockito.when(service.listAll()).thenReturn(List.of(n1));

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testUpdateNote() throws Exception {
        Note updated = new Note("Updated", "Updated Content");
        updated.setId(3L);
        updated.setCreatedAt(LocalDateTime.now());
        updated.setUpdatedAt(LocalDateTime.now());

        Mockito.when(service.updateNote(Mockito.eq(3L), Mockito.any())).thenReturn(updated);

        mockMvc.perform(put("/api/notes/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated\", \"content\":\"Updated Content\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(delete("/api/notes/4"))
                .andExpect(status().isNoContent());
    }
}
