import java.util.Stack;

/** Calculator.java
 * @author ESCUELA 2021-1
 */
    
public class RelationalCalculator{
    // Se van agregando datos a la pila
    // Cuando llega un operador opera los últimos dos elementos de la pila
    // Aplica el operador y pone el resultado en la pila

    // Todas las funciones se realizan sobre la ÚLTIMA tabla agregada al stack
    
    private Stack<Table> tables;
    //Consultar en el API Java la clase Stack
    
    /**
     * Constructor of the RelationalCalculator class.
     */
    public RelationalCalculator(){
        this.tables = new Stack<Table>();
    }

    /**
     * Method for adding a new empty table to the stack
     * @param   attributes -> A list of attributes (columns) for the table
     */
    public void add(String [] attributes){  
        // Crear una nueva tabla
        // esa nueva tabla debe pasarle los atributos
        Table myTable = new Table(attributes);
        
        // Agregar la tabla al stack
        this.tables.push(myTable);        
    }
    
    
    /**
     * Method for adding a new populated table to the stack
     * @param   attributes[] -> A list of attributes (columns) for the table
     * @param   tuples[][] -> A list of tuples (registers) for the table
     */
    public void add(String [] attributes, String[][] tuples){ 
        // Crear una nueva tabla
        Table myTable = new Table(attributes);
        
        // Agregarle parámetros a esa tabla
        myTable.insert(tuples);
        
        // Agregar la tabla al stack
        this.tables.push(myTable);
    }
    
    /*Consult the top of the stack*/
    /**
     * Method for consulting the top of the stack (but as a String, not a Table)
     */
    public String consult(){
        //System.out.println(this.tables.peek().toString());
        return this.tables.peek().toString();
    }
    
    public void delete(){
    }
    
    /**
     * Method for inserting tuples into the top table of the stack
     * @params  tuples[][]  An array of arrays containing the registers needed to append to the table
     */
    public void insert(String[][] tuples){ 
        // Traemos la tabla que está en el "top" de la pila
        Table myTable = this.tables.peek();
        
        // Agregamos los nuevos valores
        myTable.insert(tuples);
    }  
    
    /**
     * Method for proyecting the top table from the stack
     * @param   attributes[] An array containing the columns to be displayed
     * @return The proyection of the top table from the stack
     */
    public Table proyect(String[] attributes){
        // Toma la tabla del top del stack
        Table myTable = this.tables.peek();
        
        // Realiza la proyección de esta       
        return myTable.proyection(attributes);
    }
    
    /**
     * Method for selecting specific rows of the top table from the stack
     * @param   attribute -> The attribute we want to check
     * @param   operation -> The operation we want to apply (>,>=,<,<=,=,!=)
     * @return The proyection of the top table from the stack
     */
    public Table select(String attribute, String operation, String value){     
        // Toma la tabla del top del stack
        Table myTable = this.tables.peek();
        
        // Realiza la selección de esta
        return myTable.selection(attribute.toUpperCase(), operation, value);
    }
    
    /*
    * set operation: 'u' (union), 'i' (intersection), 'd' (difference)
    * relational operation:  'p' (projection), 's' (selection), 'j' (natural join) , 'r' (rename)
    * To project and rename, the attributes are at the top of the stack.
    + To select, the attributes and values are at the top of the row.  
    * If the operation cannot be done, the stack is not modified.
    */
    public void calculate(char operator){
    }
    
    /*Indicates if the last action was successful*/
    public boolean ok(){
        return false;
    }
    
    /**
     * Getter for the size of the stack
     * @return  The size of the tables stack
     */
    public int getStackSize() {
        return this.tables.size();
    }
}
    



