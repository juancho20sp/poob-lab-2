import java.util.Stack;
import javax.swing.JOptionPane;
import java.util.Arrays;
/** Calculator.java
 * @author ESCUELA 2021-1, Juan David Murillo, Carlos Orduz
 */
    
public class RelationalCalculator{
    // Se van agregando datos a la pila
    // Cuando llega un operador opera los últimos dos elementos de la pila
    // Aplica el operador y pone el resultado en la pila

    // Todas las funciones se realizan sobre la ÚLTIMA tabla agregada al stack
    
    private Stack<Table> tables;
    private boolean ok;
    //Consultar en el API Java la clase Stack
    
    /**
     * Constructor of the RelationalCalculator class.
     */
    public RelationalCalculator(){
        this.tables = new Stack<Table>();
        this.ok = false;
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
        if(!this.tables.isEmpty()){
            this.ok = true;
            return this.tables.peek().toString();
        }
        else{
            this.ok = false;
            return null;
        }
    }
    
    
    /**
     * Method for deleting the table at the top of the stack
     */
    
    public void delete(){
        int prevLength = this.getStackSize();
        
        // Eliminamos la tabla
        if (this.getStackSize() > 0){
            this.tables.pop();
        } else {
            JOptionPane.showMessageDialog(null, "El stack está vacío, no puede eliminar más elementos");
            this.ok = false;
        }
        
        
        if (this.getStackSize() == prevLength - 1){
            this.ok = true;
        }        
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
    
    /**
    * set operation: 'u' (union), 'i' (intersection), 'd' (difference)
    * relational operation:  'p' (projection), 's' (selection), 'j' (natural join) , 'r' (rename)
    * To project and rename, the attributes are at the top of the stack.
    + To select, the attributes and values are at the top of the row.  
    * If the operation cannot be done, the stack is not modified.
    */
    public void calculate(char operator){
        switch(operator){
            case 'u':
                    // Llamamos a la función de unión
                    this.union();
                    break;
            case 'i':
            case 'd':
            case 'p':
            case 's':
            case 'j':
            case 'r':
                    String res = JOptionPane.showInputDialog("Ingrese los nuevos valores separados por coma y sin espacios:");
                    String[] myRes = res.split(",");
                    
                    // Renombramos los atributos
                    this.renameTable(myRes);
                    break;
            default:
            break;
        }
    }
    
    /**
     * Method for renaming the attributes of a table
     * @param newAttributes     A string array with the new names for the attributes
     * @return  The table with the new attributes
     */
    public Table renameTable(String[] newAttributes) {
        // Tomamos la última tabla
        Table myTable = this.tables.peek();
        
        Table res = null;
        
        // Verificamos que la cantidad de atributos nuevos sea válida
        if (myTable.attributes().length == newAttributes.length){
            // Cambiamos los atributos
            myTable.rename(newAttributes);
            
            // Validamos la acción
            this.ok = true;
            
            // Preparamos la tabla para ser retornada
            res = myTable;
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad de atributos no es la adecuada. \n Requeridos: " + myTable.attributes().length
                                            + "\nEnviados: " + newAttributes.length);
            this.ok = false;
        }
        
        // Devolvemos el resultado
        return res;
    }
    
    /**
     * Method for getting the union of two tables.
     */
    public void union(){
        // Solo podemos hacer union si hay dos o más tablas
        if(this.getStackSize() > 1){
            // Sacamos dos tablas del stack
            Table firstTable = this.tables.pop();
            Table secondTable = this.tables.pop();
            
            // Unimos las tablas
            Table res = secondTable.union(firstTable);
            
            // Agregamos la tabla resultante al stack
            this.tables.push(res);                       
            
            
        } else {
           JOptionPane.showMessageDialog(null, "No hay tablas suficientes para realizar una unión");
        }        
    }
    
    /**
     * Method for getting the union of two tables.
     */
    public void difference(){
        // Solo podemos hacer diferencia si hay dos o más tablas
        if(this.getStackSize() > 1){
            // Sacamos dos tablas del stack
            Table firstTable = this.tables.pop();
            Table secondTable = this.tables.pop();
            
            // Unimos las tablas
            Table res = secondTable.difference(firstTable);
            
            // Agregamos la tabla resultante al stack
            this.tables.push(res);                       
            
            
        } else {
           JOptionPane.showMessageDialog(null, "No hay tablas suficientes para realizar una unión");
        } 
    }
    
    /*Indicates if the last action was successful*/
    /**
     * Indicates if the last action was successful or not
     * @return True if the last action was successful, false otherwise
     */
    public boolean ok(){
        return this.ok;
    }
    
    /**
     * Getter for the size of the stack
     * @return  The size of the tables stack
     */
    public int getStackSize() {
        return this.tables.size();
    }
}
    



