package org.kata.tondeuse.model;

public class Tondeuse {

    private final int id;
    private int x;
    private int y;
    private char orientation;
    private final String instructions;
    private final int maxX;
    private final int maxY;

    public Tondeuse(int id, int x, int y, char orientation, String instructions, Pelouse pelouse) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.instructions = instructions;
        this.maxX = pelouse.getSurface().get("x");
        this.maxY = pelouse.getSurface().get("y");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getInstructions() {
        return instructions;
    }

    public char getOrientation(){
        return orientation;
    }

    public void turnLeft() {
        orientation = switch (orientation) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> orientation;
        };
    }

    public void turnRight() {
        orientation = switch (orientation) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> orientation;
        };
    }

    public void moveForward() {
        switch (orientation) {
            case 'N' -> y = Math.min(maxY, y + 1);
            case 'E' -> x = Math.min(maxX, x + 1);
            case 'S' -> y = Math.max(0, y - 1);
            case 'W' -> x = Math.max(0, x - 1);
        }
    }

    @Override
    public String toString() {
        return "Position finale de la tondeuse " + id + " : " + x + " " + y + " " + orientation;
    }
}
