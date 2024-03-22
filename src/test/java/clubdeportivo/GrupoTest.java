/*
Integrantes:

    - Jose Canto
    - Valentin Pecqueux
 */

package clubdeportivo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {

    Grupo grupo;
    private final String codigo = "1";
    private final String actividad = "actividad";
    private final int nplazas = 10;
    private final int matriculados = 5;
    private final double tarifa = 2.0;

    @BeforeEach
    public void initGrupo() throws ClubException {
        grupo = new Grupo(this.codigo, this.actividad, this.nplazas, this.matriculados, this.tarifa);
    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    @DisplayName("Create group should return Exception when nplazas is zero or negative")
    public void create_Grupo_With_Zero_Or_Negative_Place_Throw_Error_Test(int nplazas) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(this.codigo, this.actividad, nplazas, this.matriculados, this.tarifa);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-42})
    @DisplayName("Create group should return Exception when matriculados is negative")
    public void create_Grupo_Matriculados_Negative_Throw_Error_Test(int matriculados) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(this.codigo, this.actividad, this.nplazas, matriculados, this.tarifa);
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    @DisplayName("Create group should return Exception when tarifa is zero or negative")
    public void create_Grupo_With_Zero_Or_Negative_Tarifa_Throw_Error_Test(int tarifa) {
        assertThrows(ClubException.class, () -> {
            Grupo grupo = new Grupo(this.codigo, this.actividad, this.matriculados, this.nplazas, tarifa);
        });
    }

    @Test
    @DisplayName("Create group with nmatriculados greater than nplazas")
    public void create_Grupo_With_Matriculados_Superior_To_Nplaza_Throw_Error_Test() {
        int nplazas = 5;
        int nmatriculados = 10;

        assertThrows(ClubException.class, () -> {
            Grupo grupo = new Grupo(this.codigo, this.actividad, nplazas, nmatriculados, this.tarifa);
        });
    }


    @Test
    @DisplayName("Return codigo value correctly")
    public void getCodigo_Test() {
        String expectedCodigo = this.codigo;

        String returnedCodigo = grupo.getCodigo();

        assertEquals(expectedCodigo, returnedCodigo);
    }

    @Test
    @DisplayName("Return actividad value correctly")
    public void getActividad_Test() {
        String expectedActividad = this.actividad;

        String returnedActividad = grupo.getActividad();

        assertEquals(expectedActividad, returnedActividad);
    }

    @Test
    @DisplayName("Return nplazas value correctly")
    public void getNplazas_Test() {
        int expectedPlazas = this.nplazas;

        int returnedPlazas = grupo.getPlazas();

        assertEquals(expectedPlazas, returnedPlazas);
    }

    @Test
    @DisplayName("Return nmatriculados value correctly")
    public void getMatriculados_Test() {
        int expectedMatriculados = this.matriculados;

        int returnedMatriculados = grupo.getMatriculados();

        assertEquals(expectedMatriculados, returnedMatriculados);
    }

    @Test
    @DisplayName("Return tarifa value correctly")
    public void getTarifa_Test(){
        double expectedTarifa = this.tarifa;

        double returnedTarifa = grupo.getTarifa();

        assertEquals(expectedTarifa, returnedTarifa);
    }

    @Test
    @DisplayName("Return plazasLibres value correctly")
    public void plazasLibres_afterCreateGroup_returnsValueCorrectly() {
        int expectedPlazasLibres = this.nplazas - this.matriculados;

        int returnedPlazasLibres = grupo.plazasLibres();

        assertEquals(expectedPlazasLibres, returnedPlazasLibres);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    @DisplayName("actualizarPlazas with zero or negative values must return an Exception")
    public void actualizar_Plazas_With_Zero_Or_Negative_Value_Throw_Error_Test(int n) {
        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(n);
        });
    }

    @Test
    @DisplayName("actualizarPlazas to a less value than nmatriculados should return an Exception")
    public void actualizar_Plazas_With_NewValue_Inferior_To_Matriculados_Throw_Error_Test() {
        int nplazas = this.matriculados - 1;

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(nplazas);
        });
    }

    @Test
    @DisplayName("actualizarPlazas to a higher value than nmatriculados should work properly")
    public void actualizar_Plazas_Change_Nplazas_Value_Test() {
        int nplazasToChange = grupo.getPlazas() + 5;
        int nplazasBeforeChange = grupo.getPlazas();

        try {
            grupo.actualizarPlazas(nplazasToChange);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        int returnedNPlazas = grupo.getPlazas();

        assertNotEquals(nplazasBeforeChange, returnedNPlazas);
        assertEquals(nplazasToChange, returnedNPlazas);
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,91})
    @DisplayName("matricular must return an Exception when plazasLibres is less than value of n")
    public void matricular_With_Not_Enough_Place_Throw_Error_Test(int n) {
        assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    @DisplayName("matricular must return an Exception with zero or negative values, you can't matricular negative users jj")
    public void matricular_With_Zero_Or_Negative_Value_Throw_Error_Test(int n) {
        assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });
    }

    @Test
    @DisplayName("matricular with values less than plazasLibres should work properly")
    public void matricular_Increase_Nmatriculados_Test() {
        int nmatriculadosToAdd = grupo.plazasLibres() - 1;
        int nmatriculadosBeforeAdd = grupo.getMatriculados();

        try {
            grupo.matricular(nmatriculadosToAdd);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        int returnedNMatriculados = grupo.getMatriculados();

        assertNotEquals(nmatriculadosBeforeAdd, returnedNMatriculados);
        assertEquals(nmatriculadosToAdd + nmatriculadosBeforeAdd, returnedNMatriculados);
    }

    @ParameterizedTest
    @CsvSource({
            "2, actividad, 10, 5, 2.0",
            "1, actividad2, 10, 5, 2.0"})
    @DisplayName("Grupo with different codigo or different actividad values should not be equal to another")
    public void equal_Different_Codigo_Return_False_Test(String codigo, String actividad, int nplazas, int matriculados, double tarifa) {
        Grupo grupo2 = null;

        try {
            grupo2 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(grupo.equals(grupo2));
    }

    @ParameterizedTest
    @CsvSource({
            "1, actividad, 10, 5, 2.0",
            "1, ActIviDad, 10, 5, 2.0"
    })
    @DisplayName("equals method should return true when the codigo and actividad values are equal being case insensitive")
    public void equal_Return_True_Test(String codigo, String actividad, int nplazas, int matriculados, double tarifa) {
        Grupo grupo2 = null;

        try {
            grupo2 = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(grupo.equals(grupo2));
    }

    @Test
    @DisplayName("Compare not a Grupo object with a Grupo object should also return false")
    public void equal_With_Different_Object_Type_Return_False_Test() {
        String object = "Im not a Grupo Object";

        assertFalse(grupo.equals(object));
    }

    @Test
    @DisplayName("toString function should return the elements in a specific order")
    public void toString_Test() {
        String expectedToString = "(" + this.codigo + " - " + this.actividad + " - " + this.tarifa + " euros " + "- P:" + this.nplazas + " - M:" + this.matriculados + ")";

        String returnedValue = grupo.toString();

        assertEquals(expectedToString, returnedValue);
    }

    @Test
    @DisplayName("hashCode function should return a specific hashCode value")
    public void hashCode_Test(){
        int expectedHashCode = this.codigo.toUpperCase().hashCode() + this.actividad.toUpperCase().hashCode();

        int returnedHashCode = grupo.hashCode();

        assertEquals(expectedHashCode, returnedHashCode);
    }
}
