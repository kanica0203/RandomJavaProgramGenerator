import Generator.Scopes.MyConstructor;

import Generator.Structure_Final;
import Parser.Node;
import Parser.ParseGrammar;
import config.Generator_Specs;
import config.XMLConfig;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class test {
    @Test
    public void Neg_TestgetLiteral() throws Exception{
        ParseGrammar parser = new ParseGrammar();
        parser.start_parse_grammar();
        parser.construct_program_grammar();
        LinkedHashMap< String , List<Node>> my_rules = new LinkedHashMap<>();
        my_rules = parser.getMy_rules();

        //System.out.println("\n\n\n\n\n\n\n\n");

        Generator_Specs my_specs = new XMLConfig().parse_input_xml();
        Structure_Final sf = new Structure_Final(my_rules,my_specs);
        String s = sf.get_literal("int");
        assertNotEquals( s,"FloatLiteral");
    }

    @Test
    public void TestLiteral() throws Exception{
        ParseGrammar parser = new ParseGrammar();
        parser.start_parse_grammar();
        parser.construct_program_grammar();
        LinkedHashMap< String , List<Node>> my_rules = new LinkedHashMap<>();
        my_rules = parser.getMy_rules();

        //System.out.println("\n\n\n\n\n\n\n\n");

        Generator_Specs my_specs = new XMLConfig().parse_input_xml();
        Structure_Final sf = new Structure_Final(my_rules,my_specs);
        String s = sf.get_literal("int");
        assertEquals( s,"IntegerLiteral");
    }


    @Test
    public void TestRandom() throws Exception{
        // test no duplicates occur in consecutive runs of random for smaller values.
        Structure_Final sf = new Structure_Final();
        int n1 = sf.get_new_random(3);
        int n2 = sf.get_new_random(3);
        assertNotEquals(n1,n2);
    }
}