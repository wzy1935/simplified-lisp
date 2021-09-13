import java.lang.reflect.Array;
import java.util.*;

public class Calculator {

    public static HashMap<TreeNode,TreeNode> environment = new HashMap<>();
    private static final HashSet<String> operatorBasics = new HashSet<String>(Arrays.asList("+","-","*","/","%"));

    public static TreeNode calculate (TreeNode node, boolean print) {
        TreeNode[] children = new TreeNode[node.getChildren().size()];
        node.getChildren().toArray(children);

        if (children[0].getData().equals("define")) {

            print = false;

            if (children[1].getData().equals("(")) {
                TreeNode tmp = node.nodeClone();
                TreeNode function = tmp.getChildren().get(1).getChildren().get(0).nodeClone();
                TreeNode.replaceNode(tmp.getChildren().get(0),new TreeNode("lambda"));

                tmp.getChildren().get(1).getChildren().remove(0);
                addKeyInHashMap(function,tmp,node.getFather());

            } else {
                addKeyInHashMap(children[1],children[2],node.getFather());

            }

        } else {

            for (int i = 2; i <= children.length; i++) {
                TreeNode child = children[i-1];

                if (child.getData().equals("(")) {
                    TreeNode.replaceNode(child,calculate(child,false));

                } else if (searchHashMap(child) != null) {
                    TreeNode.replaceNode(child,searchHashMap(child));

                }


            }

            if (operatorBasics.contains(children[0].getData())) {

                return basics(node,print);

            } else if (searchHashMap(children[0]) != null) {
                TreeNode.replaceNode(children[0],searchHashMap(children[0]));

                return lambda(node,print);

            } else if (children[0].getData().equals("(")) {

                return lambda(node,print);

            }


        }


        if (print) Frame.println(node.getData());
        return node;
    }


    public static TreeNode lambda(TreeNode input, boolean print) {
        if (!input.getData().equals("(")) return input;
        ArrayList<TreeNode> formals = input.getChildren().get(0).getChildren().get(1).getChildren();
        ArrayList<TreeNode> actuals = input.getChildren();
        for (int i = 1; i <= formals.size(); i++) {
            addKeyInHashMap(formals.get(i-1),actuals.get(i),input);
        }

        //TreeNode.replaceNode(input,calculate(input.getChildren().get(0).getChildren().get(2),false));
        return calculate(input.getChildren().get(0).getChildren().get(2),print);
    }


    public static TreeNode basics(TreeNode input, boolean print) {
        if (!input.getData().equals("(")) return input;
        ArrayList<TreeNode> t = input.getChildren();
        String[] children = new String[t.size()];
        for (int i = 1; i <= t.size(); i++) children[i-1] = t.get(i-1).getData();
        String op = children[0];
        int tmp = -1;
        switch (op) {
            case "+": {
                tmp = 0;
                for (int i = 2; i <= children.length; i++) {
                    tmp += Integer.parseInt(children[i - 1]);
                }
                break;
            }
            case "-": {
                tmp = Integer.parseInt(children[1]);
                for (int i = 3; i <= children.length; i++) {
                    tmp -= Integer.parseInt(children[i - 1]);
                }
                break;
            }
            case "*": {
                tmp = 1;
                for (int i = 2; i <= children.length; i++) {
                    tmp *= Integer.parseInt(children[i - 1]);
                }
                break;
            }
            case "/": {
                tmp = Integer.parseInt(children[1]);
                for (int i = 3; i <= children.length; i++) {
                    tmp /= Integer.parseInt(children[i - 1]);
                }
                break;
            }
            case "%": {
                tmp = Integer.parseInt(children[1]);
                for (int i = 3; i <= children.length; i++) {
                    tmp %= Integer.parseInt(children[i - 1]);
                }
                break;
            }
        }

        if (print) Frame.println(tmp + "");;
        return new TreeNode(String.valueOf(tmp));

    }

    public static TreeNode searchHashMap (TreeNode node) {
        TreeNode me = node;
        while (me != null) {
            if (me.getEnvironment().containsKey(node)) return me.getEnvironment().get(node).nodeClone();
            me = me.getFather();
        }
        if(environment.containsKey(node)) return environment.get(node).nodeClone();
        return null;
    }

    public static void addKeyInHashMap (TreeNode key, TreeNode value, TreeNode at) {
        if (at == null) {
            environment.put(key.nodeClone(),value.nodeClone());
        } else {
            at.getEnvironment().put(key.nodeClone(),value.nodeClone());
        }


    }


}
