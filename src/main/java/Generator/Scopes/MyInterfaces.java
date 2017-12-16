package Generator.Scopes;

import java.util.HashMap;
import java.util.LinkedHashMap;

/*
 * Class to maintain details of the Interfaces.
 * Has expression related methods for scoping.
 *
 */

public class MyInterfaces {
    LinkedHashMap<String , HashMap<String,String>> interface_signature = new LinkedHashMap<>();
    String current_interface;

    public void add_new_interface(String interface_name) {
        interface_signature.put(interface_name,null);
        setCurrent_interface(interface_name);
    }

    public String getCurrent_interface() {
        return current_interface;
    }

    public void setCurrent_interface(String current_interface) {
        this.current_interface = current_interface;
    }

    public void add_current_parameter(String parameter, String type) {
        HashMap<String,String> temp = new HashMap<>();
        temp.put(parameter, type);
        if(  interface_signature.get(getCurrent_interface()) != null) {
            temp.putAll(interface_signature.get(getCurrent_interface()));
        }
        interface_signature.remove(getCurrent_interface());
        interface_signature.put(getCurrent_interface(),temp);
    }

    @Override
    public String toString() {
        return "\n Interface Name : " + getCurrent_interface() +" method count : " + interface_signature.size() +
                " \n Parameter : " + interface_signature.toString() + " \n local var " ;
    }
}
