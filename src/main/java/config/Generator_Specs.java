package config;

import java.lang.reflect.Field;

/*
 * Contains the Specifications for the generator
 * as to number of classes, interfaces, methods, etc.
 */
public class Generator_Specs {
    static int number_of_classes;
    int max_class_field;
    int max_methods_class;
    int max_constructors;
    int recur_probab;
    static int number_of_interfaces;
    int max_number_of_interfaces;
    int max_para_method;
    int max_stmts_methods;
    int maxloops;
    int maxifstatement;

    public String toString(){
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append( this.getClass().getName() );
        result.append(newLine);

        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields){
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                //System.out.println(ex);
            }
            result.append(newLine);
        }
        return result.toString();
    }


    public int getNumber_of_classes() {
        return number_of_classes;
    }
    public void decNumber_of_classes(int occur_left) {
        this.max_class_field -= occur_left;
    }

    public int getMax_class_field() {
        return max_class_field;
    }
    public void decMax_class_field(int occur_left) {
        this.max_class_field -= occur_left;
    }

    public int getMax_constructors() {
        return max_constructors;
    }
    public void decgetMax_constructors(int occur_left) {
        this.max_constructors -= occur_left;
    }

    public int getMax_methods_class() {
        return max_methods_class;
    }
    public void decMax_methods_class(int occur_left) {
        this.max_methods_class -= occur_left;
    }

    public int getRecur_probab() {
        return recur_probab;
    }
    public void decRecur_probab(int occur_left) {
        this.recur_probab -= occur_left;
    }

    public int getNumber_of_interfaces() {
        return number_of_interfaces;
    }
    public void decNumber_of_interfaces(int occur_left) {
        this.number_of_interfaces -= occur_left;
    }

    public int getMax_number_of_interfaces() {
        return max_number_of_interfaces;
    }
    public void decMax_number_of_interfaces(int occur_left) {
        this.max_number_of_interfaces -= occur_left;
    }

    public int getMax_para_method() {
        return max_para_method;
    }
    public void decgetMax_para_method(int occur_left) {
        this.max_para_method -= occur_left;
    }

    public int getMax_stmts_methods() {
        return max_stmts_methods;
    }
    public void decMax_stmts_methods(int occur_left) {
        this.max_stmts_methods -= occur_left;
    }
    public int setMax_stmts_methods(int x) {
        return this.max_stmts_methods = x;
    }

    public int getMaxloops() {
        return maxloops;
    }
    public void decMaxloops(int occur_left) {
        this.maxloops -= occur_left;
    }

    public int getMaxifstatement() {
        return maxifstatement;
    }
    public void decMaxifstatement(int occur_left) {
        this.maxifstatement -= occur_left;
    }

}
