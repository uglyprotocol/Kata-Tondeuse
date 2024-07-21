package org.kata.tondeuse.batch;

import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TondeuseProcessor implements ItemProcessor<Tondeuse, Tondeuse> {

    @Override
    public Tondeuse process(Tondeuse tondeuse) throws Exception {
        for (char command : tondeuse.getInstructions().toCharArray()) {
            switch (command) {
                case 'G':
                    tondeuse.turnLeft();
                    break;
                case 'D':
                    tondeuse.turnRight();
                    break;
                case 'A':
                    tondeuse.moveForward();
                    break;
            }
        }
        return tondeuse;
    }
}
