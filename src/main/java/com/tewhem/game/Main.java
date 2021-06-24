package com.tewhem.game;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        GameScreen screen = new GameScreen();

        screen.setFocusable(false);
        screen.setResizable(false);

        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Game game = new Game();

        game.requestFocus();

        game.addKeyListener(game);

        game.setFocusable(true);

        game.setFocusTraversalKeysEnabled(false);

        screen.add(game);

        screen.setVisible(true);
    }
}
