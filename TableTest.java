import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * @author   ECI
 * @version 2021-1
 */
public class TableTest
{
 
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }
 
    @Test    
    public void shouldPass(){
        assertTrue(true);
    }
    
    @Test
    public void shouldFail(){
        fail("JAJA la prueba falló");
    }

    @Test    
    public void shouldErr(){
        throw new NullPointerException();
        //throw new AssertionError("¡Este es mi error!");
    }
    
    /**
     * Create an empty table
     * @result An empty table will be created without any errors.
     */    
     @Test
    public void shouldCreateAnEmptyTables(){
        // Definimos las columnas de la tabla
        String [] attributes={"SIGLA","NOMBRE"};
        
        // Creamos la tabla de acuerdo a las columnas
        Table courses=new Table(attributes);
        
        
        assertEquals(0,courses.size());
        
        
        assertArrayEquals(attributes,courses.attributes());
    }
    
    /**
     * Create and populate a table
     * @result A table will be created and populated without any errors.
     */
    
    @Test
    public void shouldCreateTables(){
        // Campos de la tabla
        String [] attributes={"SIGLA","NOMBRE"};
        // Registros de la tabla
        String [][] data={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};

        // Creamos la tabla
        Table courses=new Table(attributes);
        
        // Insertamos la información
        courses.insert(data);
        
        // Verificamos que el número de registros sea el correcto
        assertEquals(2,courses.size());
        
        // Verificamos que los datos ingresados estén en la tabla 
        assertTrue(courses.in(data[0]));
        assertTrue(courses.in(data[1]));
    }

    @Test
    public void shouldNotInsertBadTuples(){
        String [] attributes={"SIGLA","NOMBRE"};
        String [][] data={{"POOB","Programacion orientada a objetos"},{"MBDA", "Modelos y bases de datos"}};
        Table courses=new Table(attributes);
        courses.insert(data);
        
    }    
    
    /**
     * Verify if the table print is correct
     * @result The table is printed according to the stablished format.
     */
    @Test
    public void shouldRepresentTableAsString(){
        // Definimos las columnas de las tablas
        String [] attributes={"first","second","third"};
        
        // Definimos los registros de las tablas
        String [][] data={{"x","x","x"},{"b","c",""},{"b","f","g"},{"d","d","d"},{"a","b","x"},{"a","b","c"}};
        
        // Creamos la tabla de acuerdo a las columnas
        Table example=new Table(attributes);
        
        // Poblamos la tabla
        example.insert(data);
        
        // Verificamos si la impresión de la tabla es válida
        String result="(FIRST,SECOND,THIRD)\n(x,x,x)\n(b,c,)\n(b,f,g)\n(d,d,d)\n(a,b,x)\n(a,b,c)\n";
        assertEquals(result,example.toString()); 
    }
   
    

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        
    }
}
