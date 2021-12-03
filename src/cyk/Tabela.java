package cyk;

public class Tabela {
	boolean aceita;
    String cadeia;
    String[][] tabela;
    
    public Tabela(String cadeia) {
        this.cadeia = cadeia;
        tratarCadeia();
        tabela = new String[this.cadeia.length() + 1][this.cadeia.length() + 1];
        preencheTabela();
        preencheCadeiaNaTabela();
    }
    
    private void preencheTabela() {
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[0].length; j++) {
                tabela[i][j] = "";
            }
        }
    }

    private void tratarCadeia() {
        this.cadeia = cadeia.replaceAll(" ", "");
    }

    private void preencheCadeiaNaTabela() {
        for (int i = 1; i < tabela.length; i++) {
            tabela[0][i] = cadeia.charAt(i - 1) + "";
            tabela[i][0] = cadeia.charAt(i - 1) + "";
        }
    }
}
