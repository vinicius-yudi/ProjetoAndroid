import java.util.ArrayList;
import java.util.List;

public class TabelasHash {
    private static final int TAMANHO_TABELA = 6;

    public static void main(String[] args) {
        // Tabelas hash
        String[] hash1 = new String[TAMANHO_TABELA];
        String[] hash2 = new String[TAMANHO_TABELA];

        // Chaves a serem inseridas
        String[] chaves = {"Eu", "Amo", "Programar", "Java"};

        // Inserção nas tabelas e contagem de colisões
        int colisoesHash1 = inserirNaHash(hash1, chaves, 1);
        int colisoesHash2 = inserirNaHash(hash2, chaves, 2);

        // Buscar o índice da chave "Java" na Hash1 e Hash2
        int indiceHash1 = buscarIndice(hash1, "Java");
        int indiceHash2 = buscarIndice(hash2, "Java");

        // Exibir resultados
        System.out.println("Hash1:");
        System.out.println("Chave Java: " + indiceHash1);
        System.out.println("Colisões: " + colisoesHash1);

        System.out.println("\nHash2:");
        System.out.println("Chave Java: " + indiceHash2);
        System.out.println("Colisões: " + colisoesHash2);
    }

    // Função de inserção (usa a função de hash especificada)
    private static int inserirNaHash(String[] tabela, String[] chaves, int tipoHash) {
        int colisoes = 0;

        for (String chave : chaves) {
            int hash = (tipoHash == 1) ? hash1(chave) : hash2(chave);
            int indice = hash % TAMANHO_TABELA;

            if (tabela[indice] == null) {
                tabela[indice] = chave;
            } else {
                // Colisão detectada!
                colisoes++;
                // Estratégia linear probing até achar espaço
                int i = 1;
                while (tabela[(indice + i) % TAMANHO_TABELA] != null) {
                    i++;
                    colisoes++;
                }
                tabela[(indice + i) % TAMANHO_TABELA] = chave;
            }
        }

        return colisoes;
    }

    // Função para buscar índice da chave
    private static int buscarIndice(String[] tabela, String chave) {
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            if (chave.equals(tabela[i])) {
                return i;
            }
        }
        return -1; // Não encontrada
    }

    // Função de hash1 (simples soma dos valores dos caracteres)
    private static int hash1(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i);
        }
        return soma;
    }

    // Função de hash2 (multiplica soma pela posição da primeira letra)
    private static int hash2(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i);
        }
        int primeiraLetra = chave.charAt(0);
        return soma * primeiraLetra;
    }
}
