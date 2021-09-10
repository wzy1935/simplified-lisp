import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Frame extends Application {
    static BorderPane mainPane;
    static Pane leftPane;Pane centerPane;Pane bottomPane;Pane topPane;
    static TextArea txtCode,txtResults;
    static Button btnStart,btnStop;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainPane = new BorderPane();
        centerPane = new Pane();
        topPane = new Pane();
        bottomPane = new Pane();

        mainPane.setCenter(centerPane);
        mainPane.setLeft(leftPane);
        mainPane.setBottom(bottomPane);
        mainPane.setTop(topPane);


        txtCode = new TextArea();
        txtCode.setPrefSize(800,600);
        txtCode.setFont(Font.font("Courier New", FontWeight.BOLD,18));
        centerPane.getChildren().add(txtCode);

        txtResults = new TextArea();
        txtResults.setPrefSize(800,200);
        txtResults.setFont(Font.font("Courier New", FontWeight.BOLD,18));
        bottomPane.getChildren().add(txtResults);

        btnStart = new Button("start");
        btnStop = new Button("stop");
        btnStart.setOnAction(event -> {
            TreeNode[] trees = TreeSetter.treesInitialize();
            for (TreeNode t : trees) Calculator.calculate(t,true);
            println(" - Process finished - ");
        });
        topPane.getChildren().addAll(btnStart);


        primaryStage.setScene(new Scene(mainPane));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void println(String string) {
        txtResults.appendText(string + "\n");
    }

}
