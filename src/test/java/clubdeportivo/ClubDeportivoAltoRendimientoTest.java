package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClubDeportivoAltoRendimientoTest {

    private int maximoPersonasGrupo;
    private double incremento;
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento;
    ClubDeportivoAltoRendimiento clubDeportivoAltoRendimientoWithTam;


    @BeforeEach
    public void initGrupo() throws ClubException {
         clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("nombre",20,2);
        clubDeportivoAltoRendimientoWithTam = new ClubDeportivoAltoRendimiento("nombre",3,20,2);
    }



    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_clubAlto_with_zero_or_negative_maximo_throw_error_test(int maximo){

        assertThrows(ClubException.class,()->{

            ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("nombre",maximo,2);

        });

    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_clubAlto_with_zero_or_negative_incremento_throw_error_test(int incremento){

        assertThrows(ClubException.class,()->{

            ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("nombre",2,incremento);

        });

    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_clubAlto_with_tam_with_zero_or_negative_maximo_throw_error_test(int maximo){

        assertThrows(ClubException.class,()->{

            ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("nombre",3,maximo,2);

        });

    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_clubAlto_with_tam_with_zero_or_negative_incremento_throw_error_test(int incremento){

        assertThrows(ClubException.class,()->{

            ClubDeportivoAltoRendimiento clubDeportivoAltoRendimiento = new ClubDeportivoAltoRendimiento("nombre",3,2,incremento);

        });

    }

    @Test
    public void ingresosTest(){

        assertEquals(0,clubDeportivoAltoRendimiento.ingresos());

    }

    @Test
    public void ingresosTest_with_datos() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Test", 10, 20.0);
        String[] datos = {"Actividad", "Descripcion", "15", "5", "10.0"};
        club.anyadirActividad(datos);
        assertEquals(60.0, club.ingresos()); // Assuming initial income is 10.0
    }

    @Test
    public void testAnyadirActividad() throws ClubException {
        String[] datos = {"Actividad", "Descripcion", "15", "5", "10.0"};
        clubDeportivoAltoRendimiento.anyadirActividad(datos);

    }

    @Test
    public void testAnyadirActividadInsufficientData() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Test", 10, 20.0);
        String[] datos = {"Actividad", "Descripcion"};
        assertThrows(ClubException.class,()->{

            club.anyadirActividad(datos);
        });

    }

    @Test
    public void testAnyadirActividadInvalidNumberFormat() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club Test", 10, 20.0);
        String[] datos = {"Actividad", "Descripcion", "15", "invalid", "10.0"};
        assertThrows(ClubException.class,()->{

            club.anyadirActividad(datos);
        });
    }



}