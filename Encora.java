package encora;

import java.util.Scanner;

public class Encora {

    public static int[][] makeChange(int n) {
        int[][] set = new int[1000][]; 
        int tamanhoSet = 0; 
        int[] valorMoedas = {25, 10, 5, 1}; 
        int[] combinacaoAtual = new int[4];   

        tamanhoSet = recursaoMakeChange(n, 0, valorMoedas, combinacaoAtual, set, tamanhoSet); // Chamada recursiva para ter todas possibilidades

        int[][] resultante = new int[tamanhoSet][]; // Passa os arrays de tamanhoSet para resultante
        for (int i = 0; i < tamanhoSet; i++) {
        	resultante[i] = set[i];
        }

        return resultante;
    }

    private static int recursaoMakeChange(int n, int indice, int[] valorMoedas, int[] combinacaoAtual, int[][] set, int tamanhoSet) {
        if (n == 0) {
            if (!estaNoSet(set, tamanhoSet, combinacaoAtual)) { // Se não tá no "set" ainda é adicionada uma cópia da combinação atual lá
                set[tamanhoSet] = combinacaoAtual.clone();
                tamanhoSet++;
            }
            return tamanhoSet;
        }

        if (indice >= valorMoedas.length) {
            return tamanhoSet; // Se todas as moedas foram testadas
        }

        int maxCoins = n / valorMoedas[indice]; // Tenta adicionar o máximo de moedas possíveis a cada posição
        for (int i = 0; i <= maxCoins; i++) {
        	combinacaoAtual[indice] = i; // Define a quantidade atual de moedas
        	tamanhoSet = recursaoMakeChange(n - (i * valorMoedas[indice]), indice + 1, valorMoedas, combinacaoAtual, set, tamanhoSet);
            combinacaoAtual[indice] = 0; // Limpa para a próxima iteração
        }

        return tamanhoSet;
    }

    private static boolean estaNoSet(int[][] set, int tamanhoSet, int[] combinacaoAtual) { // Verifica se uma combinação já tá no "Set"
        for (int i = 0; i < tamanhoSet; i++) {
            if (compararArray(set[i], combinacaoAtual)) {
                return true;
            }
        }
        return false;
    }

    private static boolean compararArray(int[] a, int[] b) { // Compara o array atual com o que esta sendo referenciado do "set"
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {    	
    	Scanner scan = new Scanner (System.in);
    	System.out.println("Quantos centavos você tem? ");
        int n = scan.nextInt(); // Recebe o valor
        scan.close(); 
        int[][] resultado = makeChange(n); // Chama função makeChange passando "n" como parâmetro

        System.out.println("Combinações para " + n + " centavos:");
        for (int i = 0; i < resultado.length; i++) { // Vai printando cada um dos resultados
        	int[] possibilidades = resultado[i];
            System.out.println("[" + possibilidades[0] + ", " + possibilidades[1] + ", " + possibilidades[2] + ", " + possibilidades[3] + "]");
        }
    }
}
