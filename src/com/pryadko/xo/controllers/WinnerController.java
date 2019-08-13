package com.pryadko.xo.controllers;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import com.pryadko.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner(final Field field) {
        final int fieldSize = field.getSize();
        try {
            for (int i = 0; i < fieldSize; i += 1) {
                if (isWinner(field, new Point(i, 0), p -> new Point(p.x, p.y + 1))) {
                    return field.getFigure(new Point(i, 0));
                }
            }

            for (int i = 0; i < fieldSize; i += 1) {
                if (isWinner(field, new Point(0, i), p -> new Point(p.x + 1, p.y))) {
                    return field.getFigure(new Point(0, i));
                }
            }

            if (isWinner(field, new Point(0, 0), p -> new Point(p.x + 1, p.y + 1))) {
                return field.getFigure(new Point(0, 0));
            }

            if (isWinner(field, new Point(fieldSize - 1, 0), p -> new Point(p.x - 1, p.y + 1))) {
                return field.getFigure(new Point(fieldSize - 1, 0));
            }

        } catch (final InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isWinner(final Field field,
                            final Point currentPoint,
                            final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);

        try {
            currentFigure = field.getFigure(currentPoint);

            if (currentFigure == null) {
                return false;
            }

            nextFigure = field.getFigure(nextPoint);

        } catch (final InvalidPointException e) {
            return true;
        }

        if (currentFigure != nextFigure) {
            return false;
        }

        return isWinner(field, nextPoint, pointGenerator);

    }

    private interface IPointGenerator {

        Point next(final Point point);

    }

}
