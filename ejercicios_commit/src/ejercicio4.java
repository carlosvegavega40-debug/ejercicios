public class ejercicio4 {
    public static void ejecutarTodo() {
        System.out.println("-----Menu del Ejercicios-----");
        int[] arreglo1 = ejercicio1.pedirArreglo("primer arreglo");
        int[] arreglo2 = ejercicio1.pedirArreglo("segundo arreglo");
        System.out.println("\n=== RESULTADOS EJERCICIO 1 ===");
        ejercicio1.imprimirArreglo(arreglo1, "Primer arreglo");
        ejercicio1.imprimirArreglo(arreglo2, "Segundo arreglo");
        System.out.println("\n=== EJERCICIO 2 ===");
        System.out.println("Primer arreglo:");
        ejercicio2.separarParesImpares(arreglo1);
        System.out.println("Segundo arreglo:");
        ejercicio2.separarParesImpares(arreglo2);
        System.out.println("\n=== EJERCICIO 3 ===");
        System.out.println("Primer arreglo:");
        ejercicio3.contarParesImpares(arreglo1);
        System.out.println("Segundo arreglo:");
        ejercicio3.contarParesImpares(arreglo2);
    }

    public static void main(String[] args) {
        ejecutarTodo();
    }
}
