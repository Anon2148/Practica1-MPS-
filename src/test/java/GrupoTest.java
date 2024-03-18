import clubdeportivo.ClubException;
import clubdeportivo.Grupo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {

    Grupo grupo;
    final String codigo = "1";
    final String actividad = "actividad";
    final int nplazas = 10;
    final int matriculados = 5;
    final double tarifa = 2;

    @BeforeEach
    public void initGrupo() throws ClubException {
        grupo = new Grupo("1","actividad",nplazas,matriculados,tarifa);
    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_Grupo_With_Zero_Or_Negative_Place_Throw_Error_Test(int nplazas){

        assertThrows(ClubException.class,()->{

            Grupo grupo = new Grupo("1","actividad",nplazas,1,1);

        });

    }

    @Test
    public void create_Grupo_Matriculados_Negative_Throw_Error_Test(){

        assertThrows(ClubException.class,()->{

            Grupo grupo = new Grupo("1","actividad",3,-1,1);

        });

    }


    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void create_Grupo_With_Zero_Or_Negative_Tarifa_Throw_Error_Test(int tarifa){

        assertThrows(ClubException.class,()->{

            Grupo grupo = new Grupo("1","actividad",1,1,tarifa);

        });

    }

    @Test
    public void create_Grupo_With_Matriculados_Superior_To_Nplaza_Throw_Error_Test(){

        assertThrows(ClubException.class,()->{

            Grupo grupo = new Grupo("1","actividad",7,9,1);

        });

    }


    @Test
    public void getCodigo_Test(){
        assertEquals(codigo,grupo.getCodigo());
    }
    @Test
    public void getActividad_Test(){
        assertEquals(actividad,grupo.getActividad());
    }
    @Test
    public void getNplazas_Test(){
        assertEquals(nplazas,grupo.getPlazas());
    }
    @Test
    public void getMatriculados_Test(){
        assertEquals(matriculados,grupo.getMatriculados());
    }
    @Test
    public void getTarifa_Test(){
        assertEquals(tarifa,grupo.getTarifa());
    }



    @Test
    public void actualizar_Plazas_With_NewValue_Inferior_To_Matriculados_Throw_Error_Test() throws ClubException {


        assertThrows(ClubException.class,()->{

            grupo.actualizarPlazas(3);

        });

    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void actualizar_Plazas_With_Zero_Or_Negative_Value_Throw_Error_Test(int n) throws ClubException {


        assertThrows(ClubException.class,()->{

            grupo.actualizarPlazas(n);

        });

    }

    @Test
    public void actualizar_Plazas_Change_Nplazas_Value_Test() throws ClubException {

        int nplazasBefore = 10;
        int nplazasToChange = 20;

        Grupo grupo = new Grupo("1","actividad",nplazasBefore,8,2);

        grupo.actualizarPlazas(nplazasToChange);

        assertEquals(nplazasToChange,grupo.getPlazas());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,9,91})
    public void matricular_With_Not_Enough_Place_Throw_Error_Test(int n) throws ClubException {



        assertThrows(ClubException.class,()->{

            grupo.matricular(n);

        });

    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-42})
    public void matricular_With_Zero_Or_Negative_Value_Throw_Error_Test(int n) throws ClubException {



        assertThrows(ClubException.class,()->{

            grupo.matricular(n);

        });

    }

    @Test
    public void matricular_Increase_Nmatriculados_Test() throws ClubException {

        int matriculosBefore = 5;
        int matriculosToAdd = 3;

        Grupo grupo = new Grupo("1","actividad",10,matriculosBefore,2);

        grupo.matricular(matriculosToAdd);

        assertEquals(matriculosBefore + matriculosToAdd,grupo.getMatriculados());

    }


    @Test
    public void equal_Different_Codigo_Return_False_Test() throws ClubException {

        Grupo grupo2 = new Grupo("2","actividad",10,5,2);

        assertFalse(grupo.equals(grupo2));

    }

    @Test
    public void equal_Return_True_Test() throws ClubException {

        Grupo grupo2 = new Grupo("1","actividad",18,2,1);

        assertTrue(grupo.equals(grupo2));

    }

    @Test
    public void equal_Different_Actividad_Return_False_Test() throws ClubException {

        Grupo grupo2 = new Grupo("1","actividad 2",10,5,2);

        assertFalse(grupo.equals(grupo2));

    }

    @Test
    public void equal_With_Different_Object_Type_Return_False_Test() throws ClubException {

        assertFalse(grupo.equals("Different Object"));

    }

    @Test
    public void toString_Test(){
        assertEquals("("+ codigo + " - "+actividad+" - " + tarifa + " euros "+ "- P:" + nplazas +" - M:" +matriculados+")",grupo.toString());
    }
    @Test
    public void hashCode_Test(){
        assertEquals(codigo.toUpperCase().hashCode()+actividad.toUpperCase().hashCode(),grupo.hashCode());
    }

}
