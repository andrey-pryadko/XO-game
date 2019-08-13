package com.pryadko.xo.controllers;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class WinnerControllerTest {

    final int FIELD_SIZE = 3;

    @Test
    public void testGetWinnerWhenIsWinTrue() throws Exception {
        final WinnerController winnerController = new WinnerController();
        final Field field = new Field(FIELD_SIZE);
        for (int i = 0; i < FIELD_SIZE; i += 1) {
            for (int i2 = 0; i < FIELD_SIZE; i += 1) {
                field.setFigure(new Point(i, i2), Figure.X);
            }
        }
        assertEquals(Figure.X, winnerController.getWinner(field));
    }

    @Test
    public void testGetWinnerWhenIsWinFalse() throws Exception {
        final WinnerController winnerController = new WinnerController();
        final Field field = new Field(FIELD_SIZE);
        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(1, 0), Figure.O);
        field.setFigure(new Point(2, 0), Figure.O);
        assertNull(winnerController.getWinner(field));
    }

}
