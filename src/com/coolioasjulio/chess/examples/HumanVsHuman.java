package com.coolioasjulio.chess.examples;

import javax.swing.JFrame;

import com.coolioasjulio.chess.ChessGame;
import com.coolioasjulio.chess.HumanGUIPlayer;
import com.coolioasjulio.chess.Piece;
import com.coolioasjulio.chess.Player;

public class HumanVsHuman {
    public static void main(String[] args) {
        ChessGame game = new ChessGame(100);
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Player human1 = new HumanGUIPlayer(Piece.WHITE, game);
        Player human2 = new HumanGUIPlayer(Piece.BLACK, game);
        game.runGame(human1, human2);
    }
}
