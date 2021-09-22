
import java.util.Scanner;
import java.io.*;

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
                sb.append(mat[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
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

    public static void main(String[] args) {
        File file = new File("data/donnees_produtit1");
        MatriceEntiere matrice1 = new MatriceEntiere(file);
        file = new File("data/donnees_produit2");
        MatriceEntiere matrice2 = new MatriceEntiere(file);
        try {
            MatriceEntiere result = matrice1.multipication(matrice2);
            System.out.println(result);
        } catch (TaillesNonConcordantesException e) {
            e.printStackTrace();
        }
    }

}
