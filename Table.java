    import java.util.Arrays;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Set;
    import java.util.HashSet;
    import javax.swing.JOptionPane;
    
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
    
    /**
     * Method for getting the tuple in the "n" position of the AraryList.
     * @return An array containing the n-th inserted tuple
     */
    public String[] tuple(int n){
        // Si el índice es mayor al tamaño de la lista, no existe
        if(n >= this.tuples.size()){
            JOptionPane.showMessageDialog(null, "El índice ingresado no es válido");
            return null;
        }
        
        //System.out.println("Tupla: " + Arrays.toString(this.tuples.get(n)));
        return this.tuples.get(n);
    }    
    
    /**
     * Method for checking if a given register is in the Table.
     * @param A tuple containing all the data of a register.
     * @return True if the register is in the table, false otherwise.
     */
    public boolean in(String tuple[]){        
        return this.tuples.contains(tuple);
    }    
    
    private List<String> getTableAttributesAsList(){
        // Traemos los atributos de la tabla como una lista        
        List<String> tableAttributes = Arrays.asList(this.attributes());
        
        return tableAttributes;
    }
    
    /*
     * Relational operations: proyection, seleccion, natural join, rename
     */
    /**
     * Proyect the registers for the attributes passed as parameters
     * @param   The array of attributes to be displayed.
     * @return  A Table with the registers for the given attributes
     */
    public Table proyection(String attributes[]){
        // Volvemos los atributos un conjunto para evitar duplicados        
        //Set<String> attributesSet = new HashSet<>(Arrays.asList(attributes));        
        
        // Volvemos el conjunto una lista para operar más fácilmente los datos
        List<String> attributeList = Arrays.asList(attributes);
        
        // Traemos los atributos de la tabla como una lista
        List<String> tableAttributes = this.getTableAttributesAsList();        
        
        // Declaramos el arreglo que tendrá las posiciones a proyectar
        int[] positions = new int[attributeList.size()];
        int idx = 0;
        
        //System.out.println("Table attributes: " + tableAttributes.toString());
        
        // Agregamos las posiciones al arreglo
        for(String att : attributeList){
            //System.out.println(att);
            positions[idx++] = tableAttributes.indexOf(att.toUpperCase());
            
        }
        
        //System.out.println("Positions: " + Arrays.toString(positions));       
        
        
        // Creamos la nueva tabla con los valores
        Table proyectedTable = new Table(attributeList.toArray(new String[0]));
        
        // Creamos una lista para los nuevos valores
        ArrayList<String[]> newValues = new ArrayList<>();
        
        // Preparamos los valores
        for(int i = 0; i < this.tuples.size(); i++){
            ArrayList<String> values = new ArrayList<>();
            
           for(int j = 0; j < attributes.length; j++){               
               if(Arrays.toString(positions).contains(positions[j] + "")){
                   values.add(this.tuple(i)[positions[j]]);
                   
                   
               }
           }
           // Convertimos la lista en un arreglo y la guardamos en 'newValues'
           String[] temp = values.toArray(new String[0]);
           
           // Guardamos el arreglo en la lista de nuevos valores
           newValues.add(temp);
           
        }
        
        // Pasamos los nuevos valores a un arreglo
        String[][] finalValues = newValues.toArray(new String[0][0]);
        
        // Añadimos los valores a la nueva tabla
        proyectedTable.insert(finalValues);
        
        //System.out.println("Proyected: " + proyectedTable.toString());      
    
        return proyectedTable;
    }
    
    
    /**
     * Returns a table which contains all the registers that match a certain attribute and value
     * @param   attribute -> The name of the column we want to consult.
     * @param   operation -> The operation we want to apply
     * @param   value -> The value we want to match
     * @return A Table with all the registers that match attribute and value.
     */
    public Table selection(String attribute, String operation, String value){
        // Treamos la lista de atributos
        List<String> attributeList = this.getTableAttributesAsList();
                
        //Conseguir el índice del atributo
        int index = attributeList.indexOf(attribute.toUpperCase());
        
        // Creamos la nueva tabla con los valores
        Table selectedTable = new Table(attributeList.toArray(new String[0]));
        
        // Creamos una lista para los nuevos valores
        ArrayList<String[]> newValues = new ArrayList<>();
        
        // Switch de la operación
        switch(operation){
            case "<":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) < Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;
            case "<=":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) <= Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;
            case ">":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) > Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;
            case ">=":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) >= Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;
            case "=":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) == Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;
            case "!=":
                for(int i = 0; i < this.tuples.size(); i++){
                    String[] tup = this.tuple(i);
                    
                    if(Integer.parseInt(tup[index]) != Integer.parseInt(value)){
                        newValues.add(tup);
                    }
                }
                break;  
            default:
                JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
                break;
            }
            
            // Pasamos los nuevos valores a un arreglo
            String[][] finalValues = newValues.toArray(new String[0][0]);
        
            // Añadimos los valores a la nueva tabla
            selectedTable.insert(finalValues);
        
            System.out.println("Selected: " + selectedTable.toString()); 
            
            // Verificar el valor
            return selectedTable;
        }    
 
    public Table naturalJoin(Table t){
        ArrayList<String> commonAtributtes = new ArrayList<>();

        //looking for equal attributes and adding to the ArrayList
        for(String val: t.attributes){
            if(this.getTableAttributesAsList().contains(val)){
                commonAtributtes.add(val);
            }
        }
        // Creamos una tabla con los atributos en común
        Table newTable = new Table(commonAtributtes.toArray(new String[0]));

        for(String[] val: this.tuples){

        }
        


        return null;
    }

    /**
     * Renames attributes values from a Table
     * @param newAttributes
     * @return the new Table
     */
    public Table rename(String [] newAttributes){
        for(int i = 0; i<this.attributes.length;i++){
            this.attributes[i] = newAttributes[i];
        }
        this.tuples.clear();

        return this;
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
