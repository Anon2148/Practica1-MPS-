/*
Integrantes:

    - Jose Canto
    - Valentin Pecqueux
 */
package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoTest {
    private ClubDeportivo club;
    private String nombre = "myClub";

    @BeforeEach
    public void initClubDeportivo() {
        try {
            club = new ClubDeportivo(nombre);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -42})
    @DisplayName("Create ClubDeportivo with zero or negative value as Grupo size should return an Exception")
    public void clubDeportivo_whenGruposIsZeroOrNegativeValue_returnsClubException(int n) {
        assertThrows(ClubException.class, () -> {
            club = new ClubDeportivo(nombre, n);
        });
    }

    @Test
    @DisplayName("AnyadirActividad should return an Exception when data is not passed correctly as a parameter")
    public void anyadirActividad_whenParamsFormatIsNotCorrect_returnsNumberFormatException() {
        String[] datos = {"Codigo", "Actividad", "plazas", "matriculados", "tarifa"};

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    @DisplayName("anyadirActividad should add the activity to a group and the add that group to the club")
    public void anyadirActividad_whenParamsAreCorrect_returnsActivityAddedToAGroup() {
        String[] datos = {"1", "Actividad", "10", "5", "2.0"};
        String expectedToString = this.nombre + " --> " + "[ " + "(" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:" + datos[2] + " - M:" + datos[3] + ")" + " ]";
        String toStringBeforeAddingActivity = club.toString();

        try {
            club.anyadirActividad(datos);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        String returnedToString = club.toString();

        assertNotEquals(toStringBeforeAddingActivity, returnedToString);
        assertEquals(expectedToString, returnedToString);
    }

    @Test
    @DisplayName("anyadirActividad should return Exception when grupo passed as a parameter is null")
    public void anyadirActividad_whenNullGroupIsPassedAsParameter_returnsException() {
        Grupo grupo = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo);
        });
    }

    @Test
    @DisplayName("anyadirActividad should update activity when the same Grupo is passed as a parameter")
    public void anyadirActividad_whenClubIsEmpty_returnsActivityAddedToAGroup() {
        Grupo grupo = null;
        int plazas = 10;
        int matriculados = 5;
        double tarifa = 5.0;
        String codigo = "1";
        String actividad = "actividad";
        String toStringBeforeUpdate = this.nombre + " --> " + "[ " + "(" + codigo + " - " + actividad + " - " + tarifa + " euros " + "- P:" + plazas + " - M:" + matriculados + ")" + " ]";
        String expectedToString = this.nombre + " --> " + "[ " + "(" + codigo + " - " + actividad + " - " + tarifa + " euros " + "- P:" + (plazas + 10) + " - M:" + matriculados + ")" + " ]";

        try {
            grupo = new Grupo(codigo, actividad, plazas, matriculados, tarifa);
            club.anyadirActividad(grupo);
            grupo = new Grupo(codigo, actividad, plazas + 10, matriculados, tarifa);
            club.anyadirActividad(grupo);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        String returnedToString = club.toString();

        assertNotEquals(toStringBeforeUpdate, returnedToString);
        assertEquals(expectedToString, returnedToString);
    }

    @Test
    @DisplayName("plazasLibres should be zero when club is empty")
    public void plazasLibres_whenClubIsNull_returnsZero() {
        String actividad = "actividad";
        int expectedPlazasLibres = 0;

        int returnedPlazasLibres = club.plazasLibres(actividad);

        assertEquals(expectedPlazasLibres, returnedPlazasLibres);
    }

    @Test
    @DisplayName("plazasLibres should return correct value of plazasLibres before adding an activity in the club")
    public void plazasLibres_whenParamActivityExists_returnsPlazasLibresOfThatActivity() {
        String[] datos = {"1", "Actividad", "10", "5", "2.0"};
        int expectedPlazasLibres = 10 - 5;

        try {
            club.anyadirActividad(datos);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        int returnedPlazasLibres = club.plazasLibres(datos[1]);

        assertEquals(expectedPlazasLibres, returnedPlazasLibres);
    }

    @Test
    @DisplayName("matricular should return Exception when npersonas is greater than nplazas of the activity")
    public void matricular_whenNPersonasIsGreaterThanNPlazasLibresInActividad_returnsException() {
        String[] datos = {"1", "Actividad", "10", "5", "2.0"};
        int npersonas = 100;

        try {
            club.anyadirActividad(datos);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }

        assertThrows(ClubException.class, () -> {
            club.matricular(datos[1], npersonas);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -42})
    public void matricular_whenNPersonasIsZeroOrNegative_returnsSameClub(int npersonas) {
        String[] datos = {"1", "Actividad", "10", "5", "2.0"};
        String toStringBeforeMatricular = this.nombre + " --> " + "[ " + "(" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:" + datos[2] + " - M:" + datos[3] + ")" + " ]";

        try {
            club.anyadirActividad(datos);
            club.matricular(datos[1], npersonas);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        String toStringAfterMatricular = club.toString();

        assertEquals(toStringBeforeMatricular, toStringAfterMatricular);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    public void matricular_whenNpersonasIsAValidParameter_returnsUpdatedClub(int npersonas) {
        String[] datos = {"1", "Actividad", "10", "5", "2.0"};
        String expectedToString = this.nombre + " --> " + "[ " + "(" + datos[0] + " - " + datos[1] + " - " + datos[4] + " euros " + "- P:" + datos[2] + " - M:" + (5 + (npersonas % 6)) + ")" + " ]";

        try {
            club.anyadirActividad(datos);
            club.matricular(datos[1], npersonas);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        String returnedToString = club.toString();

        assertEquals(expectedToString, returnedToString);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 10, 10.0",
            "5.0, 4, 20",
            "3.5, 15, 52.5"
    })
    public void ingresos_returnsCorrectIngresosFromMatriculadosAndTarifa(double tarifa, int matriculados, double expectedIngresos) {
        String codigo = "codigo";
        String actividad = "actividad";
        int plazas = 50;

        try {
            Grupo grupo = new Grupo(codigo, actividad, plazas, matriculados, tarifa);
            club.anyadirActividad(grupo);
        } catch (ClubException e) {
            System.out.println(e.getMessage());
        }
        double returnedIngresos = club.ingresos();

        assertEquals(expectedIngresos, returnedIngresos);
    }
}
