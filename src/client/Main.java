package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    private static Stage stage;
    public static HashMap<String, Parent> sceneMap;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        sceneMap = new HashMap<>();

        Parent root = FXMLLoader.load(getClass().getResource("mainPage/client.fxml"));
        Parent login = FXMLLoader.load(getClass().getResource("loginPage/login.fxml"));

        sceneMap.put("root", root);
        sceneMap.put("login", login);

        changeScene("login");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScene(String sceneName, double... size) {
        double width = size.length > 0 ? size[0] : 200;
        double height = size.length > 0 ? size[1] : 200;

        stage.setTitle(sceneName);
        stage.setScene(new Scene(sceneMap.get(sceneName), width, height));
    }
}
