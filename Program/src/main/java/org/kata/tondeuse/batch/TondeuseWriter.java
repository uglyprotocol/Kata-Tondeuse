package org.kata.tondeuse.batch;

import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TondeuseWriter implements ItemWriter<Tondeuse> {
    @Override
    public void write(Chunk<? extends Tondeuse> chunk) throws Exception {
        System.out.println("#############################################\n\n");
        System.out.println("#############################################");
        for (Tondeuse tondeuse : chunk) {
            System.out.println(tondeuse);
        }
        System.out.println("#############################################\n\n");
        System.out.println("#############################################");
    }
}
