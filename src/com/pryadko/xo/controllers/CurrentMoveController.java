package com.pryadko.xo.controllers;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import com.pryadko.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class CurrentMoveController {

    public Figure currentMove(final Field field) {
        final Figure FIRST_PLAYER_FIGURE = Figure.X;
        final Figure SECOND_PLAYER_FIGURE = Figure.O;
        int countFigure = 0;

        for (int i = 0; i < field.getSize(); i += 1) {
            countFigure += countFiguresInTheRow(field, i);
        }

        if (countFigure == field.getSize() * field.getSize()) {
            return null;
        }

        if (countFigure % 2 == 0) {
            return FIRST_PLAYER_FIGURE;
        }

        return SECOND_PLAYER_FIGURE;
    }

    public int countFiguresInTheRow(final Field field, final int rowNumber) {
        int countFigureInRow = 0;
        try {
            for (int i = 0; i < field.getSize(); i += 1) {
                if (field.getFigure(new Point(i, rowNumber)) != null) {
                    countFigureInRow += 1;
                }
            }
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        return countFigureInRow;
    }

}
