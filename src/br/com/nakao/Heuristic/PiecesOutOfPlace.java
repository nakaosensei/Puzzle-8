/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.Heuristic;



/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public class PiecesOutOfPlace extends Heuristic{
    
    public int getHx(int[][] from, int[][] to) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(from[i][j]!=to[i][j]){
                    sum++;
                }
            }
        }
        return sum;
    }
}
