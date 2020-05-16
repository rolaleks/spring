package ru.geekbrains;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        //ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        //ApplicationContext context = new ClassPathXmlApplicationContext("config-scan.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        stage.setTitle("Cloud Storage");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();

        ControllerManager.setMainController(loader.getController());
    }
}
