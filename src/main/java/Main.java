import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] datosNinos = new String[3][4];
        boolean hayDatos = false;

        while (true) {
            imprimirMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); //

                switch (opcion) {
                    case 1:
                        ingresarDatosNiño(scanner, datosNinos);
                        hayDatos = true;
                        break;
                    case 2:
                        System.out.println("Saliendo del programa.");
                        scanner.close();
                        return;
                    case 3:
                        if (hayDatos) {
                            double promedioAlturas = calcularPromedioAlturas(datosNinos);
                            mostrarPromedioAlturas(promedioAlturas);
                        } else {
                            System.out.println("No hay datos de alturas ingresados.");
                        }
                        break;
                    case 4:
                        if (hayDatos) {
                            double promedioPesos = calcularPromedioPesos(datosNinos);
                            mostrarPromedioPesos(promedioPesos);
                        } else {
                            System.out.println("No hay datos de pesos ingresados.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido para la opción del menú.");
                scanner.nextLine();
            }
        }
    }
    public static void imprimirMenu() {
        System.out.println("Menú:");
        System.out.println("1. Ingresar datos de un niño");
        System.out.println("2. Salir");
        System.out.println("3. Calcular promedio de alturas");
        System.out.println("4. Calcular promedio de pesos");
        System.out.print("Seleccione una opción: ");
    }

    public static void ingresarDatosNiño(Scanner scanner, String[][] datosNinos) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Ingrese los datos del niño " + (i + 1));
            datosNinos[i][0] = obtenerNombre(scanner);
            datosNinos[i][1] = obtenerAlturaValidada(scanner);
            datosNinos[i][2] = obtenerPesoValidado(scanner);

            double altura = Double.parseDouble(datosNinos[i][1]);
            double peso = Double.parseDouble(datosNinos[i][2]);

            double imc = calcularIMC(peso, altura);
            datosNinos[i][3] = String.valueOf(imc);

            String categoria = obtenerCategoriaIMC(imc);
            mostrarResultados(datosNinos[i][0], peso, altura, imc, categoria);
        }
    }

    public static String obtenerNombre(Scanner scanner) {
        System.out.print("Nombre: ");
        return scanner.nextLine();
    }

    public static String obtenerAlturaValidada(Scanner scanner) {
        double altura;

        while (true) {
            System.out.print("Altura (m): ");
            try {
                altura = Double.parseDouble(scanner.nextLine());
                if (esAlturaValida(altura)) {
                    return String.valueOf(altura);
                } else {
                    System.out.println("La altura no es válida para un niño. Debe estar entre 0.5 m y 2.5 m.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La altura debe ser un número válido.");
            }
        }
    }

    public static String obtenerPesoValidado(Scanner scanner) {
        double peso;

        while (true) {
            System.out.print("Peso (kg): ");
            try {
                peso = Double.parseDouble(scanner.nextLine());
                if (esPesoValido(peso)) {
                    return String.valueOf(peso);
                } else {
                    System.out.println("El peso no es válido para un niño. Debe estar entre 5 kg y 150 kg.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El peso debe ser un número válido.");
            }
        }
    }

    public static boolean esAlturaValida(double altura) {
        return altura >= 0.5 && altura <= 2.5;
    }

    public static boolean esPesoValido(double peso) {
        return peso >= 5 && peso <= 150;
    }

    public static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    public static String obtenerCategoriaIMC(double imc) {
        if (imc < 18.5) {
            return "Bajo Peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obeso";
        }
    }

    public static double calcularPromedioAlturas(String[][] datosNinos) {
        double sumaAlturas = 0;

        for (int i = 0; i < 3; i++) {
            sumaAlturas += Double.parseDouble(datosNinos[i][1]);
        }
        return sumaAlturas / 3;
    }

    public static double calcularPromedioPesos(String[][] datosNinos) {
        double sumaPesos = 0;

        for (int i = 0; i < 3; i++) {
            sumaPesos += Double.parseDouble(datosNinos[i][2]);
        }
        return sumaPesos / 3;
    }

    public static void mostrarPromedioAlturas(double promedioAlturas) {
        System.out.println("El promedio de las alturas de los tres niños es: " + promedioAlturas + " m");
    }

    public static void mostrarPromedioPesos(double promedioPesos) {
        System.out.println("El promedio de los pesos de los tres niños es: " + promedioPesos + " kg");
    }

    public static void mostrarResultados(String nombre, double peso, double altura, double imc, String categoria) {
        System.out.println("---Datos del niño---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
        System.out.println("IMC: " + imc);
        System.out.println("Categoría de peso: " + categoria);
    }
}



