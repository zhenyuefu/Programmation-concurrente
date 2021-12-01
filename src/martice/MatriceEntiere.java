package martice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class MatriceEntiere {
    private int lig, col;
    private int[][] mat;

    public MatriceEntiere(int lignes, int colonnes) {
        lig = lignes;
        col = colonnes;
        mat = new int[lignes][colonnes];
    }

    public MatriceEntiere(File fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            Scanner scanner = new Scanner(reader);
            int lignes = scanner.nextInt();
            int colonnes = scanner.nextInt();
            lig = lignes;
            col = colonnes;
            mat = new int[lignes][colonnes];
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    mat[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getElem(int i, int j) {
        return mat[i][j];
    }

    public int getLignes() {
        return lig;
    }

    public int getColonnes() {
        return col;
    }

    public void setElem(int i, int j, int v) {
        mat[i][j] = v;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(String.format("%8d", mat[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void affiche() {
        System.out.println(this);
    }

    public void initZero() {
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = 0;
            }
        }
    }

    public MatriceEntiere transpose() {
        MatriceEntiere result = new MatriceEntiere(col, lig);
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                result.setElem(j, i, mat[i][j]);
            }
        }
        return result;
    }

    public MatriceEntiere add(MatriceEntiere mat2) throws TaillesNonConcordantesException {
        if (!(mat2.getLignes() == lig) && (mat2.getColonnes() == col))
            throw new TaillesNonConcordantesException();
        MatriceEntiere result = new MatriceEntiere(lig, col);
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                result.setElem(i, j, mat[i][j] + mat2.getElem(i, j));
            }
        }
        return result;
    }

    public MatriceEntiere multipication(int n) {
        MatriceEntiere result = new MatriceEntiere(lig, col);
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] *= n;
            }
        }
        return result;
    }

    public MatriceEntiere multipication(MatriceEntiere mat2) throws TaillesNonConcordantesException {
        if (!(mat2.getLignes() == col))
            throw new TaillesNonConcordantesException();
        MatriceEntiere result = new MatriceEntiere(lig, mat2.getColonnes());
        for (int i = 0; i < lig; i++) {
            for (int j = 0; j < mat2.getColonnes(); j++) {
                int val = 0;
                for (int k = 0; k < col; k++) {
                    val += mat[i][k] * mat2.getElem(k, j);
                }
                result.setElem(i, j, val);
            }
        }
        return result;
    }

    public static int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j)
        throws TaillesNonConcordantesException {
        if (!(m2.getLignes() == m1.getColonnes()))
            throw new TaillesNonConcordantesException();
        int res = 0;
        for (int k = 0; k < m1.getColonnes(); k++) {
            res += m1.getElem(i, k) * m2.getElem(k, j);
        }
        return res;
    }

}
