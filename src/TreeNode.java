import java.util.*;

public class TreeNode {

    private String data;
    private TreeNode father = null;
    private ArrayList<TreeNode> children = new ArrayList<>(3);
    private HashMap<TreeNode,TreeNode> environment = new HashMap<>();

    public TreeNode (String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void addChild (TreeNode child) {
        children.add(child);
        child.setFather(this);
    }

    public void addFather (TreeNode father) {
        father.addChild(this);
    }

    public HashMap<TreeNode, TreeNode> getEnvironment() {
        return environment;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return data + ":" + Arrays.toString(children.toArray());
    }


    public void printAnswer () {
        System.out.println(this.getData());
    }

    public static void replaceNode(TreeNode from, TreeNode to) {
        TreeNode father = from.getFather();
        for (int i = 1; i <= father.getChildren().size(); i++) {
            if (father.getChildren().get(i-1) == from) {
                father.getChildren().set(i-1,to);
                to.setFather(father);
                from.setFather(null);
            }
        }
    }

    public TreeNode nodeClone () {
        TreeNode node = new TreeNode(this.data);

        for (TreeNode t : this.getChildren()) {
            node.addChild(t.nodeClone());
        }


        return node;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(data, treeNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

}
