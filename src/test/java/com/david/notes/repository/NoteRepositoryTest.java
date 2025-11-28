package com.david.notes.repository;

import com.david.notes.entity.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository repo;

    @Test
    void testSearchByTitleOrContent() {
        repo.save(new Note("Shopping List", "Buy milk and eggs"));
        repo.save(new Note("Workout Plan", "Cardio for 30 minutes"));

        List<Note> results = repo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase("milk", "milk");

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("Shopping List", results.get(0).getTitle());
    }

}
