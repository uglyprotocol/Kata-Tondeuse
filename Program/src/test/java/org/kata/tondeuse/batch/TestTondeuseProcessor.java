package org.kata.tondeuse.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.tondeuse.model.Pelouse;
import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTondeuseProcessor {
    private ItemProcessor<Tondeuse, Tondeuse> processor;

    @BeforeEach
    public void setUp() {
        processor = new TondeuseProcessor();
    }

    @Test
    public void testProcessor() throws Exception {
        Pelouse p = Pelouse.getInstance();
        p.setSurface(5, 5);
        Tondeuse tondeuse = new Tondeuse(1, 1, 2, 'N', "GAGAGAGAA", p);
        tondeuse = processor.process(tondeuse);
        assertEquals(1, tondeuse.getX());
        assertEquals(3, tondeuse.getY());
        assertEquals('N', tondeuse.getOrientation());

        tondeuse = new Tondeuse(2, 3, 3, 'E', "AADAADADDA", p);
        tondeuse = processor.process(tondeuse);
        assertEquals(5, tondeuse.getX());
        assertEquals(1, tondeuse.getY());
        assertEquals('E', tondeuse.getOrientation());
    }

}
