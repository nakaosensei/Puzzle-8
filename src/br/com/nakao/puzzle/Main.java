/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nakao.puzzle;

import br.com.nakao.Heuristic.Heuristic;
import br.com.nakao.Heuristic.Manhatam;
import br.com.nakao.Heuristic.PiecesOutOfPlace;
import br.com.nakao.Search.AStar;
import br.com.nakao.Search.Gulosa;
import br.com.nakao.Search.Search;
import java.util.Scanner;

/**
 *
 * @author Thiago Nakao <nakaosensei@gmail.com>
 */
public class Main {

    public static void main(String[] args) {
        int[][] init = {{2, 3, 1}, {0, 5, 6}, {4, 7, 8}};
       // int[][] init = {{5, 3, 1}, {0, 8, 7}, {6, 4, 2}};
        int[][] fim = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int opt = 0;
        Scanner entrada = new Scanner(System.in);
        Heuristic h = new Manhatam();
        Search s = new AStar();
        System.out.println("Bem vindo ao NK-Puzzle, o puzzle inicial é: \n2 3 1\n0 5 6\n4 7 8 \nE o final é: \n1 2 3\n4 5 6\n7 8 0\n");
        while (opt != 4) {
            System.out.println("1-Selecionar Heuristica(Manhatam ou Pecas Fora do lugar)");
            System.out.println("2-Selecionar Estrategia de Busca(A* ou Gulosa)");
            System.out.println("3-Executar");
            System.out.println("4-Sair");
            int val = entrada.nextInt();
            if (val == 1) {
                System.out.println("1-Manhatam\n2-Peças fora do lugar");
                int val2 = entrada.nextInt();
                if (val2 == 1) {
                    h = new Manhatam();
                } else if (val2 == 2) {
                    h = new PiecesOutOfPlace();
                }
            } else if (val == 2) {
                System.out.println("1-A*\n2-Gulosa");
                int val3 = entrada.nextInt();
                if (val3 == 1) {
                    s = new AStar();
                } else if (val == 2) {
                    s = new Gulosa();
                }
            } else if (val == 3) {
                s.h = h;
                System.out.println(s.buscar(init, fim));
                System.out.println("TOTAL DE EXPANSOES:"+s.totalExpansoes);
            } else if (val == 4) {
                System.exit(0);
            }
        }
    }
}
