import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mostrarPromedioAlturas() {
        String[][] datosNinos = new String[3][4];
        datosNinos[0][2] = "25.5";
        datosNinos[1][2] = "0";
        datosNinos[2][2] = "30.0";


        double resultado = Main.calcularPromedioPesos(datosNinos);


        assertEquals(18.5, resultado, 0.01);
    }
}