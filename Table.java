import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author ECI, 2021-1
 *
 */
public class Table{


    private String[] attributes;
    private ArrayList<String[]> tuples;
    /*
     * The tables must remain 
     * (i) with the attribute names in uppercase 
     * (ii) without repeating tuples*/

    /**
     * Constructs a new, empty table, with the specified attribute names.
     * @param names, 
    **/
    public Table(String attributes[]){        
        // Pasamos los atributos a mayúsculas
        for (int i = 0; i < attributes.length; i++){
            attributes[i] = attributes[i].toUpperCase();
        }
        
        // Anexamos los atributos al objeto
        this.attributes = attributes;
         
        // Inicializamos las tuplas
        this.tuples = new ArrayList<String[]>();
    }    
    
    
    /**
     * Inserts the specified tuples to this table 
     * @param tuples, 
    **/
    public void insert(String tuples[][]) {  
        // Para cada tupla ingresada...
        for (String[] tuple: tuples){
            // ...Agregamos la tupla a los registros de la tabla
            this.tuples.add(tuple);           
        }
    }

    
    /**
     * Method for getting the quantity of registers
     * @return  The size of the ArrayList which contains the table registers.
     */
    public int size(){ 
        return this.tuples.size();        
    }
    
    /**
     * Method for getting the table attributes.
     * @return An array with the attributes of the table.
     */
    public String[] attributes(){
        return this.attributes;
    }
    
    public String[] tuple(int n){
        return null;
    }    
    
    /**
     * Method for checking if a given register is in the Table.
     * @param A tuple containing all the data of a register.
     * @return True if the register is in the table, false otherwise.
     */
    public boolean in(String tuple[]){        
        return this.tuples.contains(tuple);
    }    

    /*
     * Relational operations: proyection, seleccion, natural join, rename
     */
    
    public Table proyection(String attributes[]){
        return null;
    }

    public Table selection(String attribute, String value){
        return null;
    }    
 
    public Table naturalJoin(Table t){
        return null;
    }

    
    public Table rename(String [] newAttributes){
        return null;
    }
    
    /*
     * Set operators
     * The two relations involved must be union-compatible—that is, the two relations must have the same set of attributes.
     */
    public Table union(Table t){
        return null;
    }

    public Table intersection(Table t){
       return null;
    }  
    
    public Table difference(Table t){
       return null;        
    }
    

    /**
     * Indicates whether some other table is "equal to" this one.
     * @param t the table with which to compare.
     */
    private boolean equals (Table t) {
        return false;
    }

 
    @Override
    public boolean equals (Object o) {
            return this.equals ((Table) o);
    }
 
    @Override
    public String toString () {
          String s = "";
          
          // Agregamos los encabezados
          s = Arrays.toString(this.attributes) + "\n";
          
          // Agregamos los registros
          for(int i = 0; i < this.tuples.size(); i++){
              s += Arrays.toString(this.tuples.get(i)) + "\n";
          }
          
          // Cambiamos los corchetes por paréntesis
          s = s.replace('[', '(');
          s = s.replace(']', ')');
          
          // Eliminamos los espacios
          s = s.replace(" ", "");
          
          System.out.println(s);
          
          return s;
    }

}
