/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.time.temporal.TemporalUnit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static tetris.Tetris.pane;
import static tetris.Tetris.faces;
import static tetris.Tetris.piece;

/**
 *
 * @author marti
 */
public class Piece {

    public String name;
    public boolean[][] face;
    //  public boolean  speedyFalling;
    Rectangle piece[] = new Rectangle[4];
    private int faceX;
    private int faceY;
    public Color color;

    public Piece() { //int test) {
        //System.out.println(Math.random());
        //switch (1) {
        switch ((int) Math.floor((Math.random() * 10) / (10.0 / 7.0))) {
            case 0:
                System.out.println("0");
                this.name = "O";
                this.face = faces.O;
                color = Color.YELLOW;
                break;
            case 1:
                System.out.println("1");
                this.name = "I";
                this.face = faces.I;
                color = Color.BLUE;
                break;
            case 2:
                System.out.println("S");
                this.name = "S";
                this.face = faces.S;
                color = Color.RED;
                break;
            case 3:
                System.out.println("Z");
                this.name = "Z";
                this.face = faces.Z;
                color = Color.GREENYELLOW;
                break;
            case 4:
                System.out.println("L");
                this.name = "L";
                this.face = faces.L;
                color = Color.ORANGE;
                break;
            case 5:
                System.out.println("J");
                this.name = "J";
                this.face = faces.J;
                color = Color.PINK;
                break;
            case 6:
                System.out.println("T");
                this.name = "T";
                this.face = faces.T;
                color = Color.PURPLE;
                break;

        }

        for (int i = 0; i < piece.length; i++) {
            piece[i] = new Rectangle(Tetris.BLOCKSIZE, Tetris.BLOCKSIZE);
            piece[i].setFill(color);
        }
    }

    public void movePiece() {
        movePiece(faceX, faceY);
    }

    public void movePieceMESH(int x, int y) {
        x = x * Tetris.MOVE;
        y = y * Tetris.MOVE;
        movePiece(x, y);
    }

    //Souřadnice
    public void movePiece(int x, int y) {
        faceX = x;
        faceY = y;
        reDrawPiece();
    }

    public void reDrawPiece() {

        int i = 0;
        for (int xFace = 0; xFace < face.length; xFace++) {
            for (int yFace = 0; yFace < face[0].length; yFace++) {   //face[0].length-1
                if (face[xFace][yFace]) {
                    piece[i].setX(xFace * Tetris.MOVE + faceX);
                    piece[i].setY(yFace * Tetris.MOVE + faceY);
                    i++;
                }
            }
        }
    }

    public void rotate() {
        boolean[][] shadowFace = face;
        int shadowX = faceX;
        int shadowY = faceY;
        this.face = new boolean[face.length][face[0].length];
        for (int y = 0; y < face[0].length; y++) {
            for (int x = 0; x < face.length; x++) {
                face[x][y] = shadowFace[y][(face.length - 1) - x];
            }
        }
        reDrawPiece();
        System.out.println(Faces.stringify(face));
        boolean corect;
        do {
            System.out.println("do...");
            corect = false;
            for (int i = 0; i < piece.length; i++) {
                if (piece[i].getX() < 0) {
                    faceX += Tetris.MOVE; //moveRight();
                    corect = true;
                    System.out.println("right do...");
                } else if (piece[i].getX() >= Tetris.WIDTH) {
                    faceX -= Tetris.MOVE;//moveLeft();
                    corect = true;
                    System.out.println("left do...");
                }
            }
            reDrawPiece();
        } while (corect);
        if (!canMoveInMASH(0, 0)) {
            face = shadowFace;
            System.out.println(Faces.stringify(face));
            faceX = shadowX;
            faceY = shadowY;
            System.out.println("X: " + faceX + ", Y:" + faceY);
            reDrawPiece();
        }
    }

    public void moveDown() {
        if (canMove("DOWN")) {
            System.out.println("move down");
            movePiece(faceX, faceY + Tetris.MOVE);
        } else {
            writeToMash();
            Tetris.next();
        }
    }

    public void moveRight() {
        if (canMove("RIGHT")) {
            System.out.println("move right");
            movePiece(faceX + Tetris.MOVE, faceY);
        }
    }

    public void moveLeft() {
        if (canMove("LEFT")) {
            System.out.println("move left");
            movePiece(faceX - Tetris.MOVE, faceY);
        }
    }

    private boolean canMove(String direction) {
        for (Rectangle piece1 : piece) {
            if (direction == "LEFT" && (piece1.getX() == 0 || !canMoveInMASH(-1, 0))) {
                return false;
            } else if (direction == "RIGHT" && (piece1.getX() + Tetris.MOVE == Tetris.WIDTH || !canMoveInMASH(1, 0))) {
                return false;
            } else if (direction == "DOWN" && !canMoveInMASH(0, 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveInMASH(int x, int y) {
        for (int i = 0; i < piece.length; i++) {
            if (piece[i].getY() + Tetris.MOVE * y >= Tetris.HEIGH || ((int) piece[i].getX() / Tetris.MOVE) + x < 0 || ((int) piece[i].getX() / Tetris.MOVE) + x >= Tetris.MESH.length) {
                //a = false;
                return false;
            }
//            System.out.println((int) (piece[i].getX() / Tetris.MOVE));
//            System.out.println((int) ((piece[i].getY() / Tetris.MOVE) + 1));
            if (Tetris.MESH[((int) piece[i].getX() / Tetris.MOVE) + x][((int) piece[i].getY() / Tetris.MOVE) + 1 + y] != null) {
                //a = false;   
                return false;
            }
        }
        return true;
    }

    public void writeToMash() {
        for (int i = 0; i < piece.length; i++) {
            Tetris.MESH[(int) (piece[i].getX() / Tetris.MOVE)][(int) (piece[i].getY() / Tetris.MOVE) + 1] = piece[i];
            piece[i] = null;
        }
        System.out.println(Faces.stringify(Tetris.MESH));
    }

    public void hardDrop() {
        while (canMove("DOWN")) {
            moveDown();
            Tetris.addScore(2);
        }
        moveDown();
    }
}
