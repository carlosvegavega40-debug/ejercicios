import java.util.Scanner;
public class ejercicio1 {    
    public static int[] pedirArreglo(String nombre) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el tamaño de su Arreglo " + nombre + ": ");
        int tamaño = sc.nextInt();
        int[] arreglo = new int[tamaño];
        
        System.out.println("Ingrese los elementos del " + nombre + ":");
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            arreglo[i] = sc.nextInt();
        }
        return arreglo;
    }
    
    public static void imprimirArreglo(int[] arreglo, String nombre) {
        System.out.print(nombre + ": ");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}