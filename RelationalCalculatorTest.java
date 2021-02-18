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
    
    /**
     * Verify if a new register is correctly added to the table
     * @result A new register is added to the table without any problem.
     */
    @Test    
    public void shouldProyectTable(){        
        // Creamos los atributos de la tabla
        String[] attr = {"first", "second", "third"};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Creamos la tabla
        calculator.add(attr);
        
        // Agregamos los datos
        String[][] tuples = {{"first1", "second1", "third1"}, {"first2", "second2", "third2"}};
        
        calculator.insert(tuples);
        
        // Verificamos la proyección
        String[] desiredAttr = {"first", "third"};
        String[][] desiredReg = {{"first1", "third1"}, {"first2", "second2"}};
        
        // Verificamos que la información recibida sea igual a la esperada
        String expected =  "(FIRST,THIRD)\n(first1,third1)\n(first2,third2)\n";
        
        assertEquals(expected, calculator.proyect(desiredAttr).toString());      
        
    }
    
    
    /**
     * Verify if a new register is correctly added to the table
     * @result A new register is added to the table without any problem.
     */
    @Test    
    public void shouldSelectTable(){        
        // Creamos los atributos de la tabla
        String[] attr = {"id", "name", "grade"};
        
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Creamos la tabla
        calculator.add(attr);
        
        // Agregamos los datos
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        calculator.insert(tuples);
        
        // Verificamos la selección
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg =  {{"0002", "david", "28"}, {"0004", "javier", "25"}};
        
        // Verificamos que la información recibida sea igual a la esperada
        String expected =  "(ID,NAME,GRADE)\n(0002,david,28)\n(0004,javier,25)\n";
        
        assertEquals(expected, calculator.select("GRADE", "!=", "44").toString());      
        
    }
    
    /**
     * Verify if the last table is deleted
     * @result A Stack of tables without the top element
     */
    @Test
    public void shouldDelete(){
        // Creamos la calculadora
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Agregamos varias tablas
        String[] attr = {"first", "second"};
        calculator.add(attr);
        
        String[] attr2 = {"third", "fourth"};
        calculator.add(attr2);
        
        String[] attr3 = {"fifth", "sixth"};
        calculator.add(attr3);
        
        // Al eliminar una tabla, el tamaño se reduce en uno
        int expected = calculator.getStackSize() - 1;
        
        // Eliminamos la tabla del top del stack
        calculator.delete();
        
        assertEquals(expected, calculator.getStackSize());
        
    }    
 
    /**
     * Verify if the attributes of the last table were changed correctly
     * @result If the attributes were correctly changed
     */
    @Test
    public void shouldRenameTable(){
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Añadimos los atributos
        String[] attr = {"first", "second"};
        calculator.add(attr);
        
        // Renombramos los atributos
        String[] newAttr = {"second", "first"};
        calculator.renameTable(newAttr);
        
        // Verificamos cambios
        String expected =  "(SECOND,FIRST)\n";
        
        assertEquals(expected, calculator.consult());        
        
    }
    
    /**
     * Verify if the union of two tables was done correctly
     * @result A valid union of two tables appended to the Stack
     */
    @Test
    public void shouldUnionTables(){
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Creamos las tablas
        String[] attr = {"id", "name"};
        String[][] reg = {{"0001", "Juan David"}, {"0002", "Carlos Javier"}};
        calculator.add(attr, reg);
        
        String[][] reg2 = {{"0003", "David Javier"}, {"0004", "Juan Carlos"}};
        calculator.add(attr, reg2);
        
        // Unimos las tablas
        calculator.union();
        
        
        // Las dos tablas antiguas se sacan del stack y se agrega la nueva únicamente
        assertEquals(1, calculator.getStackSize());
        
        // Verificamos la correctitud de la tabla
        String expected ="(ID,NAME)\n(0001,Juan David)\n(0002,Carlos Javier)\n(0003,David Javier)\n(0004,Juan Carlos)\n";
        assertEquals(expected, calculator.consult());        
        
    }
    
    
    /**
     * Verify if the difference of two tables was done correctly
     * @result A valid differnce of two tables appended to the Stack
     */
    @Test
    public void shouldDifference(){
        // Creamos la instancia de la clase
        RelationalCalculator calculator = new RelationalCalculator();
        
        // Creamos las tablas
        String[] attr = {"id", "name"};
        String[][] reg1 = {{"0001", "Juan"},{"0002", "David"},{"0003", "Carlos"}};
        String[][] reg2 = {{"0002", "David"},{"0003", "Carlos"}};
        
        calculator.add(attr, reg1);
        calculator.add(attr, reg2);
        
        // Calculamos la diferencia
        calculator.difference();
        
        // Verificamos el resultado
        String expected = "(ID,NAME)\n(0001,Juan)\n";
        
        assertEquals(expected, calculator.consult());
        
        
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
