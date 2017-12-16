package Generator.Scopes;

/*
 * Class to maintain details of the Expression.
 * Has expression related methods for scoping.
 *
 */
public class MyExpression {
    boolean is_expression;
    String lhs_type;
    boolean is_assignment;
    boolean is_arithmetic;
    int expression_nesting_level;
    int statement_nest_level;
    int method_lines = 0;
    int max_statement=0;

    public MyExpression() {
        is_assignment = false;
        is_arithmetic = false;
        is_expression = false;
        expression_nesting_level = 0;
        statement_nest_level = 0;
        method_lines = 0;
        max_statement =0;
    }

    public String getLhs_type() {
        return lhs_type;
    }

    public void setLhs_type(String lhs_type) {
        this.lhs_type = lhs_type;
    }

    public boolean isIs_assignment() {
        return is_assignment;
    }

    public void setIs_assignment(boolean is_assignment) {
        this.is_assignment = is_assignment;
    }

    public void reset() {
        setIs_assignment(false);
        setIs_arithmetic(false);
        set_express_nesting_level(0);
    //    setStatement_nest_level(0);
    }

    public boolean isIs_arithmetic() {
        return is_arithmetic;
    }

    public void setIs_arithmetic(boolean is_arithmetic) {
        this.is_arithmetic = is_arithmetic;
    }

    public int get_express_nesting_level() {
        return expression_nesting_level;
    }

    public void set_express_nesting_level(int arithmetic_express_nesting_level) {
        this.expression_nesting_level = arithmetic_express_nesting_level;
    }

    public void inc_Arithmetic_express_nesting_level() {
        set_express_nesting_level(get_express_nesting_level()+1);
    }

    public int getStatement_nest_level() {
        return statement_nest_level;
    }

    public void setStatement_nest_level(int statement_nest_level) {
        this.statement_nest_level = statement_nest_level;
    }

    public void inc_statement_nest_level() {
        setStatement_nest_level(getStatement_nest_level() + 1);
    }

    public boolean isIs_expression() {
        return is_expression;
    }

    public void setIs_expression(boolean expr) {
        this.is_expression = expr;
    }

    public int getMethod_lines() {
        return method_lines;
    }

    public void setMethod_lines(int method_lines) {
        this.method_lines = method_lines;
    }
    public void inc_method_line() {
        setMethod_lines(getMethod_lines()+1);
    }

    public int getMax_statement() {
        return max_statement;
    }

    public void setMax_statement(int max_statement) {
        this.max_statement = max_statement;
    }
}
