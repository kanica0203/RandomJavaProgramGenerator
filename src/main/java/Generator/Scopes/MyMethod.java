package Generator.Scopes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/*
 * Class to maintain details of the Method.
 * Has methods to add method method, method parameters,
 * check and maintain variable list local and parametric.
 *
 */
public class MyMethod {
    LinkedHashMap<String , HashMap<String,String>> method_signature = new LinkedHashMap<>();
    LinkedHashMap<String , HashMap<String,String>> method_local = new LinkedHashMap<>();
    String current_method;
    String current_returntype;
    // set and add logic for return type here for the current method.

    public void add_new_method(String method_name) {
        method_signature.put(method_name,null);
        method_local.put(method_name,null);
        setCurrent_method(method_name);
    }

    public String getCurrent_method() {
        return current_method;
    }

    public void setCurrent_method(String current_method) {
        this.current_method = current_method;
    }

    public void add_current_parameter(String parameter, String type) {
        HashMap<String,String> temp = new HashMap<>();
        temp.put(parameter, type);
        if(  method_signature.get(getCurrent_method()) != null) {
            temp.putAll(method_signature.get(getCurrent_method()));
        }
        method_signature.remove(getCurrent_method());
        method_signature.put(getCurrent_method(),temp);
    }

    public void add_current_local(String parameter, String type) {
        HashMap<String,String> temp = new HashMap<>();
        temp.put(parameter, type);
        if(  method_local.get(this.current_method) != null) {
            temp.putAll(method_local.get(getCurrent_method()));
        }
        method_local.remove(getCurrent_method());
        method_local.put(getCurrent_method(),temp);
    }
    @Override
    public String toString() {
        return "\n Method Name : " + getCurrent_method() +" method count : " + method_signature.size() +
                " \n Parameter : " + method_signature.toString() + " \n local var " + method_local;
    }

    public String get_variable_in_scope() {
        String my_variable = "";
        if(method_signature.get(getCurrent_method()) != null) {
            my_variable = get_variable_from_param();
        } else {
            my_variable = get_variable_from_local();
        }
        return my_variable;
    }

    public String get_variable_from_param() {
        String my_variable = "";
        Random rand = new Random();
        int size = method_signature.get(getCurrent_method()).keySet().size();
        size = rand.nextInt(size);
        for ( String key :  method_signature.get(getCurrent_method()).keySet()) {
            if (size == 0) {
                my_variable = key;
            }
            size--;
        }
        return my_variable;
    }

    public String get_variable_from_local() {
        String my_variable = "";
        Random rand = new Random();
        int size = method_local.get(this.current_method).keySet().size();
        size = rand.nextInt(size);
            for ( String key :  method_local.get(getCurrent_method()).keySet()) {
                if (size == 0) {
                    my_variable = key;
                }
                size--;
            }
        return my_variable;
    }

    public String get_variable_type(String key) {
        if(method_signature.get(getCurrent_method()) != null) {
            if (method_signature.get(getCurrent_method()).containsKey(key)) {
                return method_signature.get(getCurrent_method()).get(key);
            } else {
                return method_local.get(getCurrent_method()).get(key);
            }
        } else {
            return method_local.get(getCurrent_method()).get(key);
        }
    }

    public String get_variable_by_type(String type) {
      //  //System.out.println(" current method " + getCurrent_method() + " my nethodf" + method_signature.toString() );
        if(method_signature.get(getCurrent_method()) != null) {
            if (method_signature.get(getCurrent_method()).containsValue(type)) {
                for (String key : method_signature.get(getCurrent_method()).keySet()) {
                    if (method_signature.get(getCurrent_method()).get(key).contains(type)) {
                        return key;
                    }
                }
            }
        }
        if(method_local.get(this.current_method) != null) {
            if (method_local.get(getCurrent_method()).containsValue(type)) {
                for (String key : method_local.get(getCurrent_method()).keySet()) {
                    if (method_local.get(getCurrent_method()).get(key).contains(type)) {
                        return key;
                    }
                }
            }
        }
        return null;
    }

    public String getCurrent_returntype() {
        return current_returntype;
    }

    public void setCurrent_returntype(String current_returntype) {
        this.current_returntype = current_returntype;
    }
}
