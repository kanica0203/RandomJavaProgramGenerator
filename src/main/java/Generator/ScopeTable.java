package Generator;

import Generator.Scopes.MyConstructor;
import Generator.Scopes.MyExpression;
import Generator.Scopes.MyInterfaces;
import Generator.Scopes.MyMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
 * Class to maintain details of the scoping.
 * WHich variables to use based on scope and type.
 * Used using Hashmap so would be easy to query based on name and type.
 *
 */
public class ScopeTable {
    String class_name;
    // Name, Type
    HashMap<String,String> class_variable_list = new HashMap<>();
    String prev_type;
    MyConstructor constructor_list = new MyConstructor();
    MyMethod method_list = new MyMethod();
    static List<MyInterfaces> global_interface_list = new ArrayList<>();
    MyInterfaces interface_list = new MyInterfaces();
    boolean statement_in_constuctor = false; // set and unset in constructor and method declaration
    MyExpression current_expression = new MyExpression();


    @Override
    public String toString() {
        return "Class Name " + class_name + " \n variables : " + class_variable_list.toString() + " \n constructor " +
                constructor_list.toString() + " \n methods : " + method_list.toString() + "\n interface : " + global_interface_list.toString();
    }

    public String getClass_name() {
        return class_name;
    }

    public List<String> get_identifier_in_scope() {
        List<String> my_variable = new ArrayList();
        Random rand = new Random();
        if(rand.nextInt(2) == 0) {
            //System.out.println(" shall choose from class list...");
            //my_variable = class_variable_list.keySet().toArray()[rand.nextInt(class_variable_list.keySet().toArray().length)];
            int size = class_variable_list.keySet().size();
            //System.out.println(" my size is " + size);
            size = rand.nextInt(size);
            //System.out.println(" random size is " + size);
            for ( String key :  class_variable_list.keySet()) {
                if (size == 0) {
                    my_variable.add(key);
                    my_variable.add(class_variable_list.get(key));
                }
                size--;
            }
        } else {
            if (statement_in_constuctor) {
                my_variable.add(constructor_list.get_variable_in_scope());
                my_variable.add(constructor_list.get_variable_type(my_variable.get(0)));
            } else {
                my_variable.add(method_list.get_variable_in_scope());
                my_variable.add(method_list.get_variable_type(my_variable.get(0)));
            }
        }
        //System.out.println(" choosen == " + my_variable);
        return my_variable;
    }

    public String get_identifier_by_type(String type) {
        String my_variable = "";
        if(type.contains("void")) {
            return "";
        }
        if (statement_in_constuctor) {
            //System.out.println("search in constructor");
            my_variable = constructor_list.get_variable_by_type(type);
        } else {
            //System.out.println("search in method");
            my_variable = method_list.get_variable_by_type(type);
        }
        if (my_variable == null) {
            //System.out.println("seach in classs");
                for(String key: class_variable_list.keySet()) {
                    if(class_variable_list.get(key).contains(type)) {
                        return key;
                    }
                }
        }
        //System.out.println(" I found ... " + my_variable + "===");
        return my_variable;
    }
}
