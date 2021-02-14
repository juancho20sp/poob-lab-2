import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Test class for the functions of the RelationalCalculator class.
 * 
 * @author Juan David Murillo - Carlos Orduz 
 * @version V1.0
 */
public class RelationalCalculatorTest
{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }
    
    /**
     * Create an empty table
     * @result An empty table will be created without any errors.
     */
    @Test    
    public void shouldCreateTableWithoutTuples(){
        //add(String [] attributes)
        String[] attributes = {"first", "second"};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Agregamos una nueva tabla solo con atributos
        calculator.add(attributes);
        
        // Verificamos que el tamaño del stack sea el correcto
        assertEquals(1, calculator.getStackSize());
        
        // Verificamos que los atributos del último stack creado sean los correctos        
        assertEquals("(FIRST,SECOND)\n", calculator.consult());
        
        //assertTrue(true);
    }
    
    
    /**
     * Create a table with registers
     * @result An table with registers will be created without any errors.
     */
    @Test    
    public void shouldCreateTableWithTuples(){
        //add(String [] attributes, String[][] tuples)
        String[] attributes = {"first", "second"};
        
        // Creamos los registros
        String[][] tuples = {{"r11", "r12"}, {"r21", "r22"}};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Agregamos una tabla con atributos y registros
        calculator.add(attributes, tuples);
        
        // Verificamos que el tamaño del stack se al correcto
        assertEquals(1, calculator.getStackSize());
        
        // Verificamos que los datos ingresados sean los esperados
        String expected = "(FIRST,SECOND)\n(r11,r12)\n(r21,r22)\n";
        
        assertEquals(expected, calculator.consult());        
    }
    
    /**
     * Return the "print" of the table in the top of the stack
     * @result The table is printed according to the stablished format.
     */
    @Test    
    public void shouldConsultTopTable(){
        // Creamos los atributos
        String[] attributes = {"attribute"};
        
        // Creamos los registros
        String[][] tuple = {{"register"}};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        calculator.add(attributes,tuple);
        
        // Verificamos que la información recibida sea igual a la esperada
        String expected = "(ATTRIBUTE)\n(register)\n";  
        
        assertEquals(expected, calculator.consult());        
    }
    
    /**
     * Verify if a new register is correctly added to the table
     * @result A new register is added to the table without any problem.
     */
    @Test    
    public void shouldAddNewRegister(){
        //insert(String[][] tuples)
        // Creamos los atributos de la tabla
        String[] attr = {"col1", "col2"};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Creamos la tabla
        calculator.add(attr);
        
        // Agregamos los datos
        String[][] tuples = {{"row11", "row12"}, {"row21", "row22"}, {"row31", "row32"}};
        
        calculator.insert(tuples);
        
        // Verificamos que la información recibida sea igual a la esperada
        String expected = "(COL1,COL2)\n(row11,row12)\n(row21,row22)\n(row31,row32)\n";
        
        assertEquals(expected, calculator.consult());
    }
    
    public void delete(){
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
