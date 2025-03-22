package ppss.practica3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Tests asociados a la clase FicheroTexto")
public class FicheroTextoTest {
    @Test
    public void C1_contarCaracteres_should_return_Exception_when_file_does_not_exist() {
        // Arrange
        String nombreFichero = "ficheroC1.txt";
        String resultadoEsperado = "ficheroC1.txt (No existe el archivo o el directorio)";
        FicheroTexto ft = new FicheroTexto();
        // Act
        FicheroException exception = Assertions.assertThrows(FicheroException.class, () -> ft.contarCaracteres(nombreFichero));
        // Assert
        Assertions.assertEquals(resultadoEsperado, exception.getMessage());
    }

    @Test
    public void C2_contarCaracteres_should_return_3_when_file_has_3_chars() {
        // Arrange
        String nombreFichero = "src/test/resources/ficheroCorrecto.txt";
        int resultadoEsperado = 3;
        FicheroTexto ft = new FicheroTexto();
        // Act
        int resultadoReal = Assertions.assertDoesNotThrow(() -> ft.contarCaracteres(nombreFichero));
        // Assert
        Assertions.assertEquals(resultadoEsperado, resultadoReal);
    }

    @Tag("excluido")
    @Test
    public void C3_contarCaracteres_should_return_Exception_when_file_cannot_be_read() {
        Assertions.fail();
    }

    @Tag("excluido")
    @Test
    public void C4_contarCaracteres_should_return_Exception_when_file_cannot_be_closed() {
        Assertions.fail();
    }
}
