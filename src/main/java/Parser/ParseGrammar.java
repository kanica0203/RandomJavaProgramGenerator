package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Parse the grammar.
public class ParseGrammar {
    LinkedHashMap< String , List<Node> > my_rules = new LinkedHashMap<>();

    public void start_parse_grammar() {
        // read file and call the actual parser with each line
        try {
            File file = new File("./src/main/java/config/java3bnf.grammar");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.trim() != "") {
                    actual_parsing(line);
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
            }
            fileReader.close();
//            //System.out.println("Contents of file:");
//            //System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actual_parsing(String line) {
        //System.out.println("\n\nactual line read " + line);
        List<Node> nodes = new ArrayList<>();
        String[] rule = new String[2];
        rule = line.split(":=");
        //System.out.println(" the name is " + rule[0]);
        //System.out.println(" the rhs is " + rule[1]);
        nodes = parse_rhs(rule[1].trim());
        my_rules.put(rule[0].trim(),nodes);
    }

    private List<Node> parse_rhs(String rhs) {
        int group = 1;
        //System.out.println(" this is my rhs" + rhs);
        List<String> elements;
        List<Node> child_nodes = new ArrayList<>();
        //trim to remove an extra space in front.. !! don't forget that
        elements = Arrays.asList(rhs.trim().split(" "));
       // elements.forEach( elem -> //System.out.println(" Elements are -"+elem+"-"));
        for ( int i = 0; i < elements.size() ; i++) {
            String element = elements.get(i);
            char[] brace_types = {'{','(','['};
            Map<Character,Character> end_brace = new HashMap<>();
            end_brace.put('{','}');
            end_brace.put('(',')');
            end_brace.put('[',']');
            String prev_element;
            boolean found = false;
            for (char brace : brace_types) {
                int split_brace = 1;
                if(element.charAt(0) == brace) {
                    found = true;
                    // //System.out.println("does it conta?" + element.contains(""+brace));
                    do {
                        element = elements.get(i);
                        int start = element.contains(""+brace) ? element.indexOf(brace)+1 : 0 ;
                        int end = element.contains(""+end_brace.get(brace)) ? element.indexOf(end_brace.get(brace)) : element.length();
                        prev_element = element;
                        element = element.substring(start,end);
                        //System.out.println(" the element is " + element);
                        Node temp = new Node(element, null);
                        switch (brace) {
                            case '(':
                                temp.setPossible_occurances(1); // one occurance
                                break;
                            case '{':
                                temp.setPossible_occurances(3); // 0 or many occurance
                                break;
                            case '[':
                                temp.setPossible_occurances(2); // 0 or 1 occurance
                                break;
                        }
                        if(!element.contains("|")) {
                            temp.setRequired(true);
                            temp.setGroup(group);
                            temp.setSplit_group(split_brace);
                            child_nodes.add(temp);
                        } else {
                            split_brace++;
                        }
                        if (i < elements.size()) i++;
                    } while ( !prev_element.contains(""+end_brace.get(brace)) && ( i < (elements.size())));
                    i--;
                }
                group++;
            }

            if ( !found) {
                //System.out.println(" the lonely element is " + element);
                Node temp = new Node(element, null);
                temp.setRequired(true);
                temp.setGroup(group);
                temp.setPossible_occurances(0);
                child_nodes.add(temp);
                group++;
            }
        }
        //System.out.println(" child node is " + child_nodes);
        return child_nodes;
    }

    public void construct_program_grammar() {

        for(String key : my_rules.keySet()) {
            List<Node> my_node = my_rules.get(key);
            for( Node node : my_node) {
                node.setTerminal(!my_rules.containsKey(node.getName()));
            }
        }
        getMy_rules();
    }

    public LinkedHashMap<String, List<Node>> getMy_rules() {
        //System.out.println("\n\nMy rules so far.. ");
        for(String key : my_rules.keySet()) {
            //System.out.println(" values are " + key + " : " + my_rules.get(key).toString());
        }
        return my_rules;
    }
}
