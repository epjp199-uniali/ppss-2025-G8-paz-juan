package ppss.practica3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Tests asociados a la clase Cine")
public class CineTest {
    @Test
    public void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3() {
        boolean[] asientos = new boolean[0];
        int solicitados = 3;

        Cine cine = new Cine();
        ButacasException exception = Assertions.assertThrows(ButacasException.class, () -> {
            cine.reservaButacas(asientos, solicitados);
        });

        Assertions.assertEquals("No se puede procesar la solicitud", exception.getMessage());
    }

    @Test
    public void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero() {
        boolean[] asientos = new boolean[0];
        int solicitados = 0;
        boolean resultadoEsperado = false;
        boolean[] asientosEsperados = new boolean[0];

        Cine cine = new Cine();
        boolean resultadoReal = Assertions.assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        Assertions.assertAll(() -> {
            Assertions.assertEquals(resultadoEsperado, resultadoReal);
            Assertions.assertArrayEquals(asientosEsperados, asientos);
        });
    }

    @Test
    public void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2() {
        boolean[] asientos = { false, false, false, true, true };
        int solicitados = 2;
        boolean resultadoEsperado = true;
        boolean[] asientosEsperados = { true, true, false, true, true };

        Cine cine = new Cine();
        boolean resultadoReal = Assertions.assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        Assertions.assertAll(() -> {
            Assertions.assertEquals(resultadoEsperado, resultadoReal);
            Assertions.assertArrayEquals(asientosEsperados, asientos);
        });
    }

    @Test
    public void C4_reservaButacas_should_return_false_when_no_seats_free_and_want_1() {
        boolean[] asientos = { true, true, true };
        int solicitados = 1;
        boolean resultadoEsperado = false;
        boolean resultadoEsperado_asientos[] = { true, true, true };
        Cine cine = new Cine();

        boolean resultadoReal = Assertions.assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        Assertions.assertAll(() -> {
            Assertions.assertEquals(resultadoEsperado, resultadoReal);
            Assertions.assertArrayEquals(resultadoEsperado_asientos, asientos);
        });
    }

    @ParameterizedTest(name = "[{index}] should be {0} when we want {3} and {4}")
    @MethodSource("casosDePrueba")
    @DisplayName("reservaButacas_")
    @Tag("parametrizado")
    public void reservaButacasC5(boolean expectedBoolean, boolean[] expectedAsientos, boolean[] asientos, int solicitados, String message) {
        // Arrange
        Cine cine = new Cine();

        // Act
        boolean resultadoReal = Assertions.assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        Assertions.assertAll(() -> {
            Assertions.assertEquals(expectedBoolean, resultadoReal);
            Assertions.assertArrayEquals(expectedAsientos, asientos);
        });
    }

    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(false, new boolean[0], new boolean[0], 0, "fila has no seats"),
                Arguments.of(true, new boolean[] { true, true, false, true, true }, new boolean[] { false, false, false, true, true }, 2, "there are 2 free seats"),
                Arguments.of(false, new boolean[] { true, true, true }, new boolean[] { true, true, true }, 1, "all seats are already reserved")
        );
    }
}
