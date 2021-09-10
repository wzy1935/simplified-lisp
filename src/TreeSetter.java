
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class TreeSetter {
    private static int i = 1;

    public static TreeNode[] treesInitialize() {
        LinkedList<TreeNode> trees = new LinkedList<>();

        String code = Frame.txtCode.getText();

        String[] data = stringReader(code);
        i = 1;

        while (i <= data.length) {

            trees.add(setTree(data));

        }


        TreeNode[] output = new TreeNode[trees.size()];
        trees.toArray(output);
        return output;
    }

    public static TreeNode setTree(String[] data) {

        TreeNode a = setTreeTmp(data);
        return a;

    }

    public static TreeNode setTreeTmp (String[] data) {
        TreeNode father = new TreeNode(data[i-1]);
        i++;
        while (!data[i - 1].equals(")")) {
            if (data[i-1].equals("(")) {
                setTreeTmp(data).addFather(father);
            } else {
                (new TreeNode(data[i-1])).addFather(father);
                i++;
            }

        }
        if (data[i - 1].equals(")")) i++;

        return father;

    }

    public static String[] stringReader (String str) {

        ArrayList<String> list = new ArrayList<>();

        String load = "";
        for (int i = 1; i <= str.length(); i++) {
            char tmp = str.charAt(i-1);
            if (tmp == '（') tmp = '(';
            if (tmp == '）') tmp = ')';

            if (tmp == ' ' || tmp == '\n' || tmp == '(' || tmp == ')') {
                if (!(load.equals(""))) list.add(load);
                load = "";
                if ( tmp == '(' || tmp == ')') list.add(tmp + "");
            } else {
                load += tmp;
            }

        }


        String[] output = new String[list.size()];
        list.toArray(output);

        for (int i = 1; i <= output.length; i++) {
            if (output[i-1].equals("定义")) output[i-1] = "define";
            if (output[i-1].equals("匿名函数")) output[i-1] = "lambda";
            if (output[i-1].equals("加")) output[i-1] = "+";
            if (output[i-1].equals("减")) output[i-1] = "-";
            if (output[i-1].equals("乘")) output[i-1] = "*";
            if (output[i-1].equals("除")) output[i-1] = "/";
            if (output[i-1].equals("取余")) output[i-1] = "%";


        }


        return output;
    }



}
