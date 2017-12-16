package Parser;

import java.util.List;

/*
 * Details of the parsed grammar are stored in the Node.
 */
public class Node {
    private int possible_occurances;
    private String name;
    private List<Node> values;
    private boolean required;
    private boolean terminal;
    private int group;
    private int split_group;

    public Node() {

    }

    public Node(String name, List<Node> my_value) {
        this.setName(name);
        this.setValues(my_value);
        this.setTerminal(false);
    }

    public int getPossible_occurances() {
        return possible_occurances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getValues() {
        return values;
    }

    public void setValues(List<Node> values) {
        this.values = values;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getSplit_group() {
        return split_group;
    }

    public void setSplit_group(int split_group) {
        this.split_group = split_group;
    }

    @Override
    public String toString() {
        return " \n Node Name : " + this.getName() + " Group : " + this.getGroup() + " split : " + this.getSplit_group() +
                "\n\t Terminal : " + this.isTerminal() + " Values : " + this.values;
    }

    public void setPossible_occurances(int possible_occurances) {
        this.possible_occurances = possible_occurances;
    }
}
