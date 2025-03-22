package ppss.practica3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Tests asociados a la clase DataArray")
public class DataArrayTest {
    @Test
    public void C1_delete_should_return_1_3_7_collection_with_3_elements_when_deleting_5_at_1_3_5_7_collection_with_4_elements() {
        // Arrange
        int[] collection = {1, 3, 5, 7};
        int elemBorrar = 5;
        DataArray dataArray = new DataArray(collection);
        int[] collectionEsperada = {1, 3, 7};
        int numElemEsperado = 3;

        // Act
        Assertions.assertDoesNotThrow(() -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertAll(() -> {
            Assertions.assertArrayEquals(collectionEsperada, dataArray.getColeccion());
            Assertions.assertEquals(numElemEsperado, dataArray.size());
        });
    }

    @Test
    public void C2_delete_should_return_1_3_5_7_collection_with_4_elements_when_deleting_3_at_1_3_3_5_7_collection_with_5_elements() {
        // Arrange
        int[] collection = {1, 3, 3, 5, 7};
        int elemBorrar = 3;
        int[] collectionEsperada = {1, 3, 5, 7};
        int numElemEsperado = 4;
        DataArray dataArray = new DataArray(collection);

        // Act
        Assertions.assertDoesNotThrow(() -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertAll(() -> {
            Assertions.assertArrayEquals(collectionEsperada, dataArray.getColeccion());
            Assertions.assertEquals(numElemEsperado, dataArray.size());
        });
    }

    @Test
    public void C3_delete_should_return_9_elements_when_deleting_4_at_collection_with_10_elements() {
        // Arrange
        int[] collection = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int elemBorrar = 4;
        DataArray dataArray = new DataArray(collection);
        int[] collectionEsperada = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int numElemEsperado = 9;

        // Act
        Assertions.assertDoesNotThrow(() -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertAll(() -> {
            Assertions.assertArrayEquals(collectionEsperada, dataArray.getColeccion());
            Assertions.assertEquals(numElemEsperado, dataArray.size());
        });
    }

    @Test
    public void C4_delete_should_return_DataException_m1_when_deleting_from_empty_collection() {
        // Arrange
        int[] collection = {};
        int elemBorrar = 8;
        DataArray dataArray = new DataArray(collection);
        String mensajeEsperado = "No hay elementos en la colección";

        // Act
        DataException exception = Assertions.assertThrows(DataException.class, () -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void C5_delete_should_return_DataException_m2_when_deleting_negative_number() {
        // Arrange
        int[] collection = {1, 3, 5, 7};
        int elemBorrar = -5;
        DataArray dataArray = new DataArray(collection);
        String mensajeEsperado = "El valor a borrar debe ser > 0";

        // Act
        DataException exception = Assertions.assertThrows(DataException.class, () -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void C6_delete_should_return_DataException_m3_when_deleting_from_empty_collection_and_below_0() {
        // Arrange
        int[] collection = {};
        int elemBorrar = 0;
        DataArray dataArray = new DataArray(collection);
        String mensajeEsperado = "Colección vacía. Y el valor a borrar debe ser > 0";

        // Act
        DataException exception = Assertions.assertThrows(DataException.class, () -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    public void C7_delete_should_return_DataException_m4_when_element_not_found() {
        // Arrange
        int[] collection = {1, 3, 5, 7};
        int elemBorrar = 8;
        DataArray dataArray = new DataArray(collection);
        String mensajeEsperado = "Elemento no encontrado";

        // Act
        DataException exception = Assertions.assertThrows(DataException.class, () -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertEquals(mensajeEsperado, exception.getMessage());
    }

    @ParameterizedTest(name = "[{index}] Message exception should be \"{2}\" when we want delete {1}")
    @MethodSource("casosDePruebaC8")
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @DisplayName("delete_With_Exceptions_")
    public void C8_deleteWithExceptions(int[] coleccion, int elemBorrar, String expectedMessage) {
        // Arrange
        DataArray dataArray = new DataArray(coleccion);

        // Act
        DataException exception = Assertions.assertThrows(DataException.class, () -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> casosDePruebaC8() {
        return Stream.of(
                Arguments.of(new int[0], 8, "No hay elementos en la colección"),
                Arguments.of(new int[]{1,3,5,7}, -5, "El valor a borrar debe ser > 0"),
                Arguments.of(new int[0], 0, "Colección vacía. Y el valor a borrar debe ser > 0"),
                Arguments.of(new int[]{1,3,5,7}, 8, "Elemento no encontrado")
        );
    }

    @ParameterizedTest(name = "[{index}] should be {2} when we want delete {1}")
    @MethodSource("casosDePruebaC9")
    @Tag("parametrizado")
    @DisplayName("delete_Without_Exceptions_")
    public void C9_deleteWithoutExceptions(int[] coleccion, int elemBorrar, int[] expectedColeccion, int numElemExpected) {
        // Arrange
        DataArray dataArray = new DataArray(coleccion);

        // Act
        Assertions.assertDoesNotThrow(() -> dataArray.delete(elemBorrar));

        // Assert
        Assertions.assertAll(() -> {
            Assertions.assertArrayEquals(expectedColeccion, dataArray.getColeccion());
            Assertions.assertEquals(numElemExpected, dataArray.size());
        });
    }

    private static Stream<Arguments> casosDePruebaC9() {
        return Stream.of(
                Arguments.of(new int[]{1,3,5,7}, 5, new int[]{1,3,7}, 3),
                Arguments.of(new int[]{1,3,3,5,7}, 3, new int[]{1,3,5,7}, 4),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, 4, new int[]{1,2,3,5,6,7,8,9,10}, 9)
        );
    }
}