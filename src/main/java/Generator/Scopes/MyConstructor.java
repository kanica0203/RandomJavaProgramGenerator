package Generator.Scopes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/*
 * Class to maintain details of the Constructor.
 * Has methods to add constructor method, constructor parameters,
 * check for duplication, maintain variable list local and parametric.
 *
 */
public class MyConstructor {
    String constructor_name;
    int constructor_count;
    LinkedHashMap<Integer, HashMap<String,String>> signature = new LinkedHashMap<>();
    LinkedHashMap<Integer, HashMap<String,String>> local_variable = new LinkedHashMap<>();

    public MyConstructor() {
        constructor_count = 0;
    }

    public String getConstructor_name() {
        return constructor_name;
    }

    public void setConstructor_name(String constructor_name) {
        this.constructor_name = constructor_name;
    }

    public void add_new_signature() {
        constructor_count++;
        signature.put(constructor_count,null);
        local_variable.put(constructor_count,null);
    }

    public void add_current_parameter(String parameter, String type) {
        HashMap<String,String> temp = new HashMap<>();
        temp.put(parameter, type);
        if( signature.get(this.constructor_count) != null) {
            temp.putAll(signature.get(constructor_count));
        }
        signature.remove(constructor_count);
        signature.put(constructor_count,temp);
    }

    public void add_current_local(String parameter, String type) {
        HashMap<String,String> temp = new HashMap<>();
        temp.put(parameter, type);
        if( local_variable.get(constructor_count) != null) {
            temp.putAll(local_variable.get(constructor_count));
        }
        local_variable.remove(this.constructor_count);
        local_variable.put(constructor_count,temp);
    }

    public boolean is_duplication() {
        if(constructor_count < 2) {
 //           //System.out.println("===== size is lesss");
            return false;
        } else {
            // since hashmap with increasing counter, use constructor count instead
            // of key as its easier
            // <= since constructor count will be the current counter
            for( int i = 1; i <= constructor_count - 1; i++) {
              //  //System.out.println(" running for " + i + " out of " + constructor_count);
                HashMap<String,String> temp1 = signature.get(i);
                for (int j = i + 1 ; j <= constructor_count; j++) {
                    HashMap<String,String> temp2 = signature.get(j);
                    // both null return back
                    if( temp1 == null && temp2 == null) {
                        //System.out.println("====== both nbull ");
                        return true;
                    } else if (temp1 != null && temp2 != null) {
                        //System.out.println(" the both temp are " + temp1.toString() + " \n " + temp2.toString());
                        // if both not null , check size
                        if ( temp1.keySet().size() == temp2.keySet().size()) {
                            // if size is same, check types
                            // if doesn't match, different return false;
                            for( String value :  temp1.values()) {
                                if(!temp2.containsValue(value)) {
                                    //System.out.println(" ===== key no ");
                                    return false;
                                }
                            }
                            //System.out.println(" all keys same and size..");
                            return true;
                        }
                    }
                }
            }
//            //System.out.println(" ====== exhusese");
            return false;
        }

    }

    @Override
    public String toString() {
        return " \n Constructor Name : " + constructor_name + " constructor count : " + constructor_count + " \n  Constructor param : " +
                signature + "\n constructor local " + local_variable;
    }

    public String get_variable_in_scope() {
        String my_variable = "";
        if(signature.get(constructor_count) != null) {
            my_variable = get_variable_from_param();
        } else {
            my_variable = get_variable_from_local();
        }
        return my_variable;
    }

    public String get_variable_from_param() {
        Random rand = new Random();
        int size = signature.get(constructor_count).keySet().size();
        size = rand.nextInt(size);
       // //System.out.println(" choosing form constructor " + size);
        for ( String key :  signature.get(constructor_count).keySet()) {
            if (size == 0) {
                //my_variable = signature.get(constructor_count).get(key);
                return key;
            }
            size--;
        }
        return null;
    }

    public String get_variable_from_local() {
        String my_variable = "";
        Random rand = new Random();
        int size = local_variable.get(constructor_count).keySet().size();
        size = rand.nextInt(size);
            for ( String key :  local_variable.get(constructor_count).keySet()) {
                if (size == 0) {
                    //my_variable = signature.get(constructor_count).get(key);
                    my_variable = key;
                }
                size--;
            }
        return my_variable;
    }

    public String get_variable_type(String key) {
        if(signature.get(constructor_count) != null) {
            if (signature.get(constructor_count).containsKey(key)) {
                return signature.get(constructor_count).get(key);
            } else {
                return local_variable.get(constructor_count).get(key);
            }
        } else {
            return local_variable.get(constructor_count).get(key);
        }
    }

    public String get_variable_by_type(String type) {
        if(signature.get(constructor_count) != null) {
            if (signature.get(constructor_count).containsValue(type)) {
                for (String key : signature.get(constructor_count).keySet()) {
                    if (signature.get(constructor_count).get(key).contains(type)) {
                        return key;
                    }
                }
            }
        }
        if(local_variable.get(constructor_count) != null) {
            if (local_variable.get(constructor_count).containsValue(type)) {
                for (String key : local_variable.get(constructor_count).keySet()) {
                    if (local_variable.get(this.constructor_count).get(key).contains(type)) {
                        return key;
                    }
                }
            }
        }
        return null;
    }
}
