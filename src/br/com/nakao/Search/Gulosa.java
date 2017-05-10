/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.Search;

import br.com.nakao.puzzle.Puzzle;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public class Gulosa extends Search {

    public Puzzle buscar(int[][] startPuzzle, int[][] endPuzzle) {
        totalExpansoes = 0;
        Puzzle n = new Puzzle();
        n.puzzle = startPuzzle;
        n.fx = 0;
        n.gx = 0;
        n.hx = h.getHx(n.puzzle, endPuzzle);
        n.fx = n.gx + n.hx;
        LinkedList<Puzzle> resultadoFinal = new LinkedList<>();
        PriorityQueue<Puzzle> queueAberta = new PriorityQueue<>();
        queueAberta.offer(n);
        while (!queueAberta.isEmpty()) {
            Puzzle q = queueAberta.poll();
            if (Puzzle.PuzzleEquals(q.puzzle, endPuzzle)) {
                return q;
            }
            resultadoFinal.add(q);
            LinkedList<Puzzle> sucessores = Puzzle.successors(q);
            totalExpansoes += sucessores.size();
            for (Puzzle s : sucessores) {
                if (Puzzle.PuzzleEquals(s.puzzle, endPuzzle)) {
                    return s;
                }
                float custoF = h.getHx(s.puzzle, endPuzzle);
                boolean pula = false;
                for (Puzzle c : resultadoFinal) {
                    if (Puzzle.PuzzleEquals(s.puzzle, c.puzzle) && c.fx <= custoF) {
                        pula = true;
                        break;
                    }
                }
                if (pula) {
                    continue;
                }
                boolean notOnAbertos = true;
                boolean menor = true;

                for (Puzzle o : queueAberta) {
                    if (Puzzle.PuzzleEquals(s.puzzle, o.puzzle)) {
                        notOnAbertos = false;
                        break;
                    }
                }
                for (Puzzle o : queueAberta) {
                    if (s.fx < o.fx) {
                        menor = true;
                        break;
                    }
                }
                if (notOnAbertos || menor) {
                    s.pai = q;
                    s.gx = 0;
                    s.fx = custoF;
                    if (notOnAbertos) {
                        queueAberta.offer(s);
                    }
                }
            }
        }
        return null;
    }
}
