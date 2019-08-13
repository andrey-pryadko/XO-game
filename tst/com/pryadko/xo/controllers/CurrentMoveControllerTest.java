package com.pryadko.xo.controllers;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CurrentMoveControllerTest {

    @Test
    public void testCurrentMoveWhenNextMoveIsX() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        for (int i = 0; i < 3; i += 1) {
            final Field field = new Field(3);
            field.setFigure(new Point(0, i), Figure.X);
            field.setFigure(new Point(1, i), Figure.O);
            assertEquals(Figure.X, currentMoveController.currentMove(field));
        }
    }

    @Test
    public void testCurrentMoveWhenNextMoveIsO() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        for (int i = 0; i < 3; i += 1) {
            final Field field = new Field(3);
            field.setFigure(new Point(0, i), Figure.X);
            field.setFigure(new Point(1, i), Figure.O);
            field.setFigure(new Point(2, i), Figure.X);
            assertEquals(Figure.O, currentMoveController.currentMove(field));
        }
    }

    @Test
    public void testFirstMove() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        final Field field = new Field(3);
        assertEquals(Figure.X, currentMoveController.currentMove(field));
    }

    @Test
    public void testCurrentMoveWhenFieldIsFull() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        final Field field = new Field(3);
        for (int i = 0; i < 3; i += 1) {
            for (int x = 0; x < 3; x += 1) {
                field.setFigure(new Point(x, i), Figure.X);
                field.setFigure(new Point(x, i), Figure.O);
                field.setFigure(new Point(x, i), Figure.X);
            }
        }
        assertNull(currentMoveController.currentMove(field));
    }
}