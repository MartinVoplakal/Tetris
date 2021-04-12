/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author marti
 */
public class Faces {

    public boolean[][] O;
    public boolean[][] I;
    public boolean[][] S;
    public boolean[][] Z;
    public boolean[][] L;
    public boolean[][] J;
    public boolean[][] T;

    public Faces() {
        O = new boolean[][]{{true, true}, {true, true}};

        I = new boolean[4][4];
        I[1][0] = true;
        I[1][1] = true;
        I[1][2] = true;
        I[1][3] = true;

        S = new boolean[3][3];
        S[1][0] = true;
        S[2][0] = true;
        S[0][1] = true;
        S[1][1] = true;

        Z = new boolean[3][3];
        Z[0][0] = true;
        Z[1][0] = true;
        Z[1][1] = true;
        Z[2][1] = true;

        L = new boolean[3][3];
        L[1][0] = true;
        L[1][1] = true;
        L[1][2] = true;
        L[2][2] = true;

        J = new boolean[3][3];
        J[1][0] = true;
        J[1][1] = true;
        J[1][2] = true;
        J[0][2] = true;

        T = new boolean[3][3];
        T[0][1] = true;
        T[1][1] = true;
        T[2][1] = true;
        T[1][2] = true;

    }

    public static String stringify(boolean[][] a) {
        String result = "";

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[j][i]) {
                    result += "# ";
                } else {
                    result += "  ";
                }
            }
            result += "\n";
        }
        return result;
    }

    public static String stringify(Rectangle[][] a) {

        String result = "";

        for (int j = 0; j < a[1].length; j++) {
            for (int i = 0; i < a.length; i++) {
                if (a[i][j] != null) {
                    result += "# ";
                } else {
                    result += ". ";
                }
            }
            result += "\n";
        }
        return result;
    }
}
