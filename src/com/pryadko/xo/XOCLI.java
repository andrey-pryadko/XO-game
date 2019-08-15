package com.pryadko.xo;

import com.pryadko.xo.model.Field;
import com.pryadko.xo.model.Figure;
import com.pryadko.xo.model.Game;
import com.pryadko.xo.model.Player;
import com.pryadko.xo.view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) {
        final String name1 = "Andrey";
        final String name2 = "David";

        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final Game gameXO = new Game(players, new Field(3), "XO");

        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(gameXO);
        while (consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }

    }
}
