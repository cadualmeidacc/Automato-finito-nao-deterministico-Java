package cyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Leitura {
	private String[][] regras;
    private String[] cadeias;
    private Scanner scanner;
    private int[] num;
    private String[] variaveis;
    private String[] terminais;
    
    public String[] getVariaveis() {
        return variaveis;
    }

    public String[] getTerminais() {
        return terminais;
    }

    public String[][] getRegras() {
        return regras;
    }

    public String[] getCadeias() {
        return cadeias;
    }

    public Leitura(File espec, File cadeias) throws FileNotFoundException {
        scanner = new Scanner(espec);
        leituraEspec();
        scanner = new Scanner(cadeias);
        leituraCadeia();
    }

    public void leituraEspec() {
        num = new int[3];
        for (int i = 0; i < 3; i++) {
            num[i] = scanner.nextInt();
        }
        variaveis = new String[num[0]];
        for (int i = 0; i < variaveis.length; i++) {
            variaveis[i] = scanner.next();
        }
        terminais = new String[num[1]];
        for (int i = 0; i < terminais.length; i++) {
            terminais[i] = scanner.next();
        }
        scanner.nextLine();
        String[] regrasAux = new String[num[2]];
        regras = new String[regrasAux.length][4];
        for (int i = 0; i < regras.length; i++) {
            for (int j = 0; j < regras[0].length; j++) {
                regras[i][j] = "";
            }
        }
        for (int i = 0; i < regrasAux.length; i++) {
            regrasAux[i] = scanner.nextLine();
        }
        tratamentoRegras(regrasAux);
    }

    private void leituraCadeia() {
        int numCadeias = scanner.nextInt();
        scanner.nextLine();
        cadeias = new String[numCadeias];
        for (int i = 0; i < cadeias.length; i++) {
            cadeias[i] = scanner.nextLine();
        }
    }

    private void tratamentoRegras(String[] regrasAux) {
        for (int i = 0; i < regras.length; i++) {
            String[] aux = regrasAux[i].split(" ");
            for (int j = 0; j < aux.length; j++) {
                regras[i][j] = aux[j];
            }
        }
    }
}
