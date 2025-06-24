import java.util.Arrays;

public class Main { 

    public static void main(String[] args) {
    
    // 1. Defina seu vetor de 7 números 
    Integer[] meuVetor = {7, 3, 5, 1, 9, 2, 8}; // Exemplo de 7 números

    // 2. Crie uma instância do seu algoritmo de ordenação (QuickSort neste caso)
    QuickSort<Integer> quickSort = new QuickSort<>();
    BubbleSort<Integer> bubbleSort = new BubbleSort<>();
    InsertionSort<Integer> insertionSort = new InsertionSort<>();


    // 3. Imprima o vetor antes da ordenação (opcional, para visualização)
    System.out.println("Vetor original: " + Arrays.toString(meuVetor));

    // 4. Chame o método sort do seu QuickSort
    Integer[] vetorOrdenado = quickSort.sort(Arrays.copyOf(meuVetor, meuVetor.length));
    Integer[] vetorOrdenadoBubble = bubbleSort.sort(Arrays.copyOf(meuVetor, meuVetor.length));
    Integer[] vetorOrdenadoInsertion = insertionSort.sort(Arrays.copyOf(meuVetor, meuVetor.length));

    // 5. Imprima o vetor ordenado
    System.out.println("Vetor ordenado (QuickSort): " + Arrays.toString(vetorOrdenado));
    System.out.println("Vetor ordenado (BubbleSort): " + Arrays.toString(vetorOrdenadoBubble));
    System.out.println("Vetor ordenado (InsertionSort): " + Arrays.toString(vetorOrdenadoInsertion));
    }
}
