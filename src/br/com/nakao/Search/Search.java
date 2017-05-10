/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.Search;

import br.com.nakao.Heuristic.Heuristic;
import br.com.nakao.puzzle.Puzzle;

/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public abstract class Search {
    public Heuristic h;
    public int totalExpansoes = 0;
    public abstract Puzzle buscar(int[][] startPuzzle, int[][] endPuzzle);
    
}
