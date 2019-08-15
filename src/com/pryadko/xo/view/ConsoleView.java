package com.pryadko.xo.view;

import com.pryadko.xo.controllers.CurrentMoveController;
import com.pryadko.xo.controllers.MoveController;
import com.pryadko.xo.controllers.WinnerController;
import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import com.pryadko.xo.model.Game;
import com.pryadko.xo.model.exceptions.InvalidPointException;
import com.pryadko.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.format("Game name: %s\n", game.getName());
        final Field field = game.getField();
        for (int y = 0; y < field.getSize(); y += 1) {
            if (y != 0) {
                printSeparator();
            }
            printLine(field, y);
        }
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is: %s\n", winner);
            return false;
        }
        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            System.out.println("No winner and no moves left!");
            return false;
        }
        System.out.format("Please enter move point for %s\n", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (InvalidPointException | PointAlreadyOccupiedException e) {
            System.out.println("Point is invalid!");
        }
        return true;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please input coordinate %s: ", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("Coordinate is invalid!");
            return askCoordinate(coordinateName);
        }

    }

    public void printLine(final Field field,
                          final int y) {
        for (int x = 0; x < field.getSize(); x += 1) {
            if (x != 0) {
                System.out.print("|");
            }
            System.out.print(" ");
            final Figure figure;
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure == null ? " " : figure );
            System.out.print(" ");
        }
        System.out.println();
    }

    public void printSeparator() {
        System.out.println("-----------");
    }


}
