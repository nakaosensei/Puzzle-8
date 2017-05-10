/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.Heuristic;

import static br.com.nakao.puzzle.Puzzle.getIndex;

/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public class Manhatam extends Heuristic{

    public int getHx(int[][] from, int[][] to) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (from[i][j] == 0) {
                    continue;
                }
                int[] indexFrom = getIndex(from, from[i][j]);
                int[] indexTo = getIndex(to, from[i][j]);
                sum += Math.abs(indexFrom[0] - indexTo[0]) + Math.abs(indexFrom[1] - indexTo[1]);
            }
        }
        return sum;
    }
}
