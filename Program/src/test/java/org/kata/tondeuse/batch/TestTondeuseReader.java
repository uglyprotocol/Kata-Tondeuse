package org.kata.tondeuse.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestTondeuseReader {
    private ItemReader<Tondeuse> reader;

    @BeforeEach
    public void setUp() throws IOException {
        reader = new TondeuseReader();
    }

    @Test
    public void testReader() throws Exception {
        Tondeuse tondeuse = reader.read();
        assertNotNull(tondeuse);
        assertEquals(1, tondeuse.getX());
        assertEquals(2, tondeuse.getY());
        assertEquals('N', tondeuse.getOrientation());
        assertEquals("GAGAGAGAA", tondeuse.getInstructions());

        tondeuse = reader.read();
        assertNotNull(tondeuse);
        assertEquals(3, tondeuse.getX());
        assertEquals(3, tondeuse.getY());
        assertEquals('E', tondeuse.getOrientation());
        assertEquals("AADAADADDA", tondeuse.getInstructions());
    }

}
