import Generator.Structure_Final;
import Parser.Node;
import Parser.ParseGrammar;
import config.Generator_Specs;
import config.XMLConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

// The program starts from here
public class Main {
    public static void main(String args[]) {
        //System.out.println(" Program start..");
        // to parse the grammar
        // The parser uses the files from config, yeah the .grammar file
        ParseGrammar parser = new ParseGrammar();
        parser.start_parse_grammar();
        parser.construct_program_grammar();
        LinkedHashMap< String , List<Node>> my_rules = new LinkedHashMap<>();
        my_rules = parser.getMy_rules();

        //System.out.println("\n\n\n\n\n\n\n\n");

        Generator_Specs my_specs = new XMLConfig().parse_input_xml();
        //System.out.println(" \n\n\n\n\n\n\n\n\n");

        for ( int i = 0; i < my_specs.getNumber_of_classes() + my_specs.getNumber_of_interfaces(); i++ ) {
            Structure_Final prog_struct2 = new Structure_Final(my_rules,new XMLConfig().parse_input_xml());
            String output = prog_struct2.generate_program("ClassStart",new StringBuilder());
            //write structure
            //System.out.println(" \nptod\n\n" + output);
            //System.out.println("\n scope table" + prog_struct2.scope_table);
            write_to_file(output,prog_struct2.scope_table.getClass_name());
            //System.out.println("Wrote to file.");
        }
    }

    public static void write_to_file(String my_prog_code, String file_name) {
        FileWriter filew =null;
        BufferedWriter buffw =null;
        File output = new File("generated_programs/GeneratedClasses/"+ file_name + ".java");
        output.getParentFile().mkdirs();
        try {
            filew = new FileWriter(output, false);
            buffw = new BufferedWriter(filew);
            buffw.write("// created at " + new Date() + System.lineSeparator());
            buffw.write(my_prog_code.toString());
            buffw.close();
            buffw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
