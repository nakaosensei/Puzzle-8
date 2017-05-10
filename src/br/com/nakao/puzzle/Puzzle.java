/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.puzzle;

import java.util.LinkedList;

/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public class Puzzle implements Comparable<Puzzle> {

    public Puzzle pai;
    public float fx;
    public float hx;
    public float gx;
    public int[][] puzzle;

    @Override
    public int compareTo(Puzzle p) {
        if (this.fx > p.fx) {
            return 1;
        } else if (this.fx < p.fx) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Puzzle p = this;
        int numMoves = 0;
        LinkedList<Puzzle> q = new LinkedList<>();
        while (p != null) {
            q.addLast(p);
            p = p.pai;
            numMoves++;
        }
        while (!q.isEmpty()) {
            Puzzle temp = q.removeLast();
            sb.append(printarPuzzle(temp));
            sb.append("\n");
        }
        sb.append("Qtde Movimentos: " + numMoves);
        return sb.toString();
    }

    public String printarPuzzle(Puzzle p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(p.puzzle[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static LinkedList<Puzzle> successors(Puzzle q) {
        int x = 0;
        int y = 0;
        int[] indices = getIndex(q.puzzle, 0);
        x = indices[0];
        y = indices[1];
        LinkedList<Puzzle> nexts = new LinkedList<Puzzle>();
        int[][] cima = new int[3][3];
        int[][] baixo = new int[3][3];
        int[][] esquerda = new int[3][3];
        int[][] direita = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cima[i][j] = q.puzzle[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                baixo[i][j] = q.puzzle[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                esquerda[i][j] = q.puzzle[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                direita[i][j] = q.puzzle[i][j];
            }
        }
        int temp = 0;
        if (x >= 1) {
            temp = cima[x][y];
            cima[x][y] = cima[x - 1][y];
            cima[x - 1][y] = temp;
            Puzzle n = new Puzzle();
            n.puzzle = cima;
            n.pai = q;

            nexts.add(n);
        }
        if (x <= 1) {
            temp = baixo[x][y];
            baixo[x][y] = baixo[x + 1][y];
            baixo[x + 1][y] = temp;
            Puzzle n = new Puzzle();
            n.puzzle = baixo;
            n.pai = q;
            nexts.add(n);
        }
        if (y >= 1) {
            temp = esquerda[x][y];
            esquerda[x][y] = esquerda[x][y - 1];
            esquerda[x][y - 1] = temp;
            Puzzle n = new Puzzle();
            n.puzzle = esquerda;
            n.pai = q;
            nexts.add(n);
        }
        if (y <= 1) {
            temp = direita[x][y];
            direita[x][y] = direita[x][y + 1];
            direita[x][y + 1] = temp;
            Puzzle n = new Puzzle();
            n.puzzle = direita;
            n.pai = q;
            nexts.add(n);
        }
        return nexts;
    }

    public static int[] getIndex(int[][] puzzle, int val) {
        int[] index = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == val) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return null;
    }

    public static boolean PuzzleEquals(int[][] puzzle1, int[][] puzzle2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle1[i][j] != puzzle2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
