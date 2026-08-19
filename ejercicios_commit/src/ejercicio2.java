import java.util.ArrayList;
public class ejercicio2 {
     public static void separarParesImpares(int[] arreglo) {
        ArrayList<Integer> pares = new ArrayList<>();
        ArrayList<Integer> impares = new ArrayList<>();   
        for (int num : arreglo) {
            if (num % 2 == 0) {
                pares.add(num);
            } else {
                impares.add(num);
            }
        }
        System.out.println("Números pares: " + pares);
        System.out.println("Números impares: " + impares);
    }  
}
