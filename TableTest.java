import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdk.jfr.Timestamp;

import java.util.Arrays;

/**
 * @author   ECI, Juan David Murillo, Carlos Orduz
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
    
    /**
     * Mock test that just have to pass.
     */
    @Test    
    public void shouldPass(){
        assertTrue(true);
    }
    
    /**
     * Mock test that should fail.
     */
    @Test
    public void shouldFail(){
        fail("JAJA la prueba falló");
    }
    
    /**
     * Mock test that should throw an error.
     */
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
     * Verify if the proyection is working good
     * @result The table containing the proyection of the table
     */
    @Test
    public void shouldProyectValues(){
        // Definimos las columnas de las tablas
        String[] attributes = {"first", "second", "third"};
        
        // Definimos los registros
        String[][] tuples = {{"first1", "second1", "third1"}, {"first2", "second2", "third2"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la proyección
        String[] desiredAttr = {"first", "third"};
        String[][] desiredReg = {{"first1", "third1"}, {"first2", "second2"}};
        
        // Creamos la tabla esperada
        //Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        //newTable.insert(desiredReg);
        
        // String esperado
        String res = "(FIRST,THIRD)\n(first1,third1)\n(first2,third2)\n";
        
        // Verificamos si son iguales
        assertEquals(res, myTable.proyection(desiredAttr).toString());
        
    }

    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the values stored are
     *  lower than the given value.
     */
    @Test
    public void shouldSelectLowerValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota menor a 28)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0004", "javier", "25"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0004,javier,25)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", "<", "28").toString());
    }
    
    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the values stored are
     *  lower or equal than the given value.
     */
    @Test
    public void shouldSelecLowerEqualValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota menor a 28)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0002", "david", "28"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0002,david,28)\n(0004,javier,25)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", "<=", "28").toString());
    }
    
    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the values stored are
     *  greater than the given value.
     */
    @Test
    public void shouldSelecGreaterValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota mayor a 28)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0001", "juan", "44"}, {"0003", "carlos", "44"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0001,juan,44)\n(0003,carlos,44)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", ">", "28").toString());
    }
    
    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the values stored are
     *  lower or equal than the given value.
     */
    @Test
    public void shouldSeleGreaterEqualValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota mayor o igual a 28)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0001,juan,44)\n(0002,david,28)\n(0003,carlos,44)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", ">=", "28").toString());
    }
    
    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the value is equal to the
     * given value
     */
    @Test
    public void shouldSelectEqualValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota igual a 44)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0001", "juan", "44"}, {"0003", "carlos", "44"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0001,juan,44)\n(0003,carlos,44)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", "=", "44").toString());
    }
    
    /**
     * Verify if the selection is working good
     * @result The table containing the registers where the value is different to
     * the given one
     */
    @Test
    public void shouldSelectDifferentValues(){
        // Definimos las columnas de las tablas
        // ID: String
        // NAME: String
        // GRADE: 0 <= (Int) String <= 50
        String[] attributes = {"id", "name", "grade"};
        
        // Definimos los registros
        String[][] tuples = {{"0001", "juan", "44"}, {"0002", "david", "28"},
    {"0003", "carlos", "44"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla
        Table myTable = new Table(attributes);
        
        // Poblamos la tabla
        myTable.insert(tuples);
        
        // Verificamos la selección (nota diferente de 44)
        String[] desiredAttr = {"id", "name", "grade"};
        String[][] desiredReg = {{"0002", "david", "28"}, {"0004", "javier", "25"}};
        
        // Creamos la tabla esperada
        Table newTable = new Table(desiredAttr);
        
        // Poblamos la tabla esperada
        newTable.insert(desiredReg);
        
        // String esperado
        String res = "(ID,NAME,GRADE)\n(0002,david,28)\n(0004,javier,25)\n";
        
        // Verificamos si son iguales
        assertEquals(res, newTable.selection("GRADE", "!=", "44").toString());
    }
    
    /**
     * Verify if the rename method is working good
     * @result The table rename its attributes correctly
     */
    @Test
    public void shouldRename(){
        String[] attributes = {"First", "Second"};
        Table table = new Table(attributes);

        String[] newAttributes = {"Third","Fourth"};
        table.rename(newAttributes);
        
        String res = "(Third,Fourth)\n";
        assertEquals(table.toString(),res);

    }
    
    /**
     * Verify if the union method is working good
     * @result The table containing the registers resulting from the union of two
     * tables
     */
    @Test
    public void shouldUnion(){
        String[] attributes1 = {"nombre","apellido1"};
        String[] attributes2 = {"nombre","apellido1"};
        String[][] reg1 = {{"ANTONIO","PEREZ"},{"ANTONIO","GARCIA"},{"PEDRO","RUIZ"}};
        String[][] reg2 = {{"JUAN","APARICIO"},{"ANTONIO","GARCIA"},{"LUIS","LOPEZ"}};

        Table table1 = new Table(attributes1);
        table1.insert(reg1);

        Table table2 = new Table(attributes1);
        table2.insert(reg2);
        String answer = "(NOMBRE,APELLIDO1)\n(ANTONIO,PEREZ)\n(ANTONIO,GARCIA)\n(PEDRO,RUIZ)\n(JUAN,APARICIO)\n(LUIS,LOPEZ)\n";

        assertEquals(table1.union(table2).toString(),answer);

    }
    
    /**
     * Verify if the 'in' methof is working good
     * @result True if the registers contain a given tuple, false otherwise
     */
    @Test
    public void inTest(){
        String[] attributes1 = {"nombre","apellido1"};
        String[][] reg1 = {{"ANTONIO","PEREZ"},{"ANTONIO","GARCIA"},{"PEDRO","RUIZ"}};

        Table t1 = new Table(attributes1);
        t1.insert(reg1);
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
