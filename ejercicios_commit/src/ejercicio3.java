public class ejercicio3 {
    public static void contarParesImpares(int[] arreglo) {
        int pares = 0;
        int impares = 0;       
        for (int num : arreglo) {
            if (num % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }
        System.out.println("Cantidad de números pares: " + pares);
        System.out.println("Cantidad de números impares: " + impares);
    }
}
