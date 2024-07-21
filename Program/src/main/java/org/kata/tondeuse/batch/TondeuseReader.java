package org.kata.tondeuse.batch;

import org.kata.tondeuse.model.Pelouse;
import org.kata.tondeuse.model.Tondeuse;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TondeuseReader implements ItemReader<Tondeuse> {

    private Pelouse pelouse;
    private List<Tondeuse> tondeuseList;
    private int nextTondeuseIndex;


    public TondeuseReader() {
        tondeuseList = new ArrayList<>();
        nextTondeuseIndex = 0;
        try {
            readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResource("input.txt").openStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            if ((line = br.readLine()) != null) {
                String[] dimensions = line.split(" ");
                pelouse = Pelouse.getInstance();
                pelouse.setSurface(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
            }

            int index = 1;
            while ((line = br.readLine()) != null) {
                String[] position = line.split(" ");
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                char orientation = position[2].charAt(0);

                String instructions = br.readLine();
                Tondeuse tondeuse = new Tondeuse(index++, x, y, orientation, instructions, pelouse);
                tondeuseList.add(tondeuse);
            }
        }
    }

    @Override
    public Tondeuse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (nextTondeuseIndex < tondeuseList.size()) {
            return tondeuseList.get(nextTondeuseIndex++);
        } else {
            return null;
        }
    }
}
