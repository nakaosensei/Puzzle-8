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
public class AStar extends Search {

    public Puzzle buscar(int[][] start, int[][] finalPuzzle) {
        totalExpansoes = 0;
        Puzzle n = new Puzzle();
        n.puzzle = start;
        n.fx = 0;
        n.gx = 0;
        n.hx = h.getHx(n.puzzle, finalPuzzle);
        n.fx = n.gx + n.hx;
        LinkedList<Puzzle> resultadoFinal = new LinkedList<>();
        PriorityQueue<Puzzle> queueAberta = new PriorityQueue<>();
        queueAberta.offer(n);
        while (!queueAberta.isEmpty()) {
            Puzzle q = queueAberta.poll();
            if (Puzzle.PuzzleEquals(q.puzzle, finalPuzzle)) {
                return q;
            }
            resultadoFinal.add(q);
            LinkedList<Puzzle> sucessores = Puzzle.successors(q);
            totalExpansoes+=sucessores.size();
            for (Puzzle s : sucessores) {
                if (Puzzle.PuzzleEquals(s.puzzle, finalPuzzle)) {
                    return s;
                }
                float custoG = q.gx + h.getHx(q.puzzle, s.puzzle);
                float custoF = custoG + h.getHx(s.puzzle, finalPuzzle);
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
                    s.gx = custoG;
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
