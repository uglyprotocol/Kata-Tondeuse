package org.kata.tondeuse.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.tondeuse.model.Pelouse;
import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTondeuseWriter {

    private ItemWriter<Tondeuse> writer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        writer = new TondeuseWriter();
        System.setOut(new PrintStream(outContent));
    }

    //@Test
    public void testWriter() throws Exception {
        Pelouse p = Pelouse.getInstance();
        p.setSurface(5, 5);

        Tondeuse machine1 = new Tondeuse(1, 1, 3, 'N', "", p);
        Tondeuse machine2 = new Tondeuse(2, 5, 1, 'E', "", p);

        writer.write(Chunk.of(machine1, machine2));

        String expectedOutput = "Position finale de la tondeuse 1 : 1 3 N\n" +
                "Position finale de la tondeuse 2 : 5 1 E\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
