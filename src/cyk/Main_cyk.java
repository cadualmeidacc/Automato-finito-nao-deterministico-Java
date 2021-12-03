package cyk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main_cyk {
	 private static Tabela[] tabelas;
	 private static String[][] regras;
	 private static Leitura leitura;
	 
	 public static void main(String[] args) throws FileNotFoundException {
		 File gramatica = new File("C:\\Users\\Eduardo\\Desktop\\TP2\\gramatica.txt");
 		 File cadeias = new File("C:\\Users\\Eduardo\\Desktop\\TP2\\cadeia.txt");
		 leitura = new Leitura(gramatica, cadeias);
		 tabelas = new Tabela[leitura.getCadeias().length];
		 criarTabelas();
		 regras = leitura.getRegras();
		 for (int i = 0; i < tabelas.length; i++) {
			 cyk(tabelas[i]);
		 }
		 getStatus();
		 
	 }
	 
	 private static void getStatus() throws FileNotFoundException {
	        for (int i = 0; i < tabelas.length; i++) {
	            if (tabelas[i].aceita) {
	                System.out.println("Aceito");
	            } else {
	                System.out.print("Não Aceito");
	            }
	        }
	    }

	    public static void criarTabelas() {
	        String[] cadeias = leitura.getCadeias();
	        for (int i = 0; i < cadeias.length; i++) {
	            tabelas[i] = new Tabela(cadeias[i]);
	        }
	    }

	    public static void cyk(Tabela tabela) {
	        if ("&".equals(tabela.cadeia) && cadeiaVaziaEhRegra()) {
	            tabela.aceita = true;
	            return;
	        }

	        int n = tabela.tabela.length;
	        for (int i = 1; i < n; i++) {
	            String variavel = verificaMenorSub(tabela, i);
	            if (variavel != null) {
	                tabela.tabela[i][i] = variavel;
	            }
	        }
	        for (int tamanhoSubcadeia = 2; tamanhoSubcadeia < n; tamanhoSubcadeia++) {
	            for (int posInicial = 1; posInicial < n - tamanhoSubcadeia + 1; posInicial++) {
 	                int posFinal = posInicial + tamanhoSubcadeia - 1;
	                for (int posDivisao = posInicial; posDivisao < posFinal; posDivisao++) {
	                    String variavel = verificaSubcadeia(tabela, posInicial, posFinal, posDivisao);
	                    if (!tabela.tabela[posInicial][posFinal].contains(variavel)) {
	                        tabela.tabela[posInicial][posFinal] += variavel;
	                    }
	                }
	            }
	        }
	        verificaAceitacao(tabela);
	    }

	    public static boolean cadeiaVaziaEhRegra() {
	        for (int i = 0; i < regras.length; i++) {
	            if (("&".equals(regras[i][2]))) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private static void verificaAceitacao(Tabela tabela) {
	        if (tabela.tabela[1][tabela.tabela.length - 1].contains(regras[0][0])) {
	            tabela.aceita = true;
	        } else {
	            tabela.aceita = false;
	        }
	    }

	    private static String verificaMenorSub(Tabela tabela, int caracterCadeia) {
	        String resp = "";
	        for (int i = 0; i < regras.length; i++) {
	            if (tabela.tabela[0][caracterCadeia].equals(regras[i][2])) {
	                resp = resp + " " + regras[i][0];
	            }
	        }
	        return resp;
	    }

	    public static String verificaSubcadeia(Tabela tabela, int posInicial, int posFinal, int posDivisao) {
	        String resp = "";
	        for (int i = 0; i < regras.length; i++) {
	            String b = regras[i][2];
	            String c = regras[i][3];

	            boolean first = tabela.tabela[posInicial][posDivisao].contains(b);
	            boolean second = tabela.tabela[posDivisao + 1][posFinal].contains(c);
	            if (first && second) {
	                resp = resp + " " + regras[i][0];
	            }
	        }
	        return resp;
	    }
	 
	 
}
