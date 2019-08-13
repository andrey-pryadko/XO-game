package com.pryadko.xo.controllers;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import com.pryadko.xo.model.exceptions.InvalidPointException;
import com.pryadko.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;

public class MoveController {

    public void applyFigure (final Field field,
                            final Point point,
                            final Figure figure) throws InvalidPointException,
                                                        PointAlreadyOccupiedException {
        if (field.getFigure(point) != null) {
            throw new PointAlreadyOccupiedException();
        }
        field.setFigure(point, figure);

    }
}
