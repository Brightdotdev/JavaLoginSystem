package org.example.LogInSystem;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;
public class ApplicationController {

@FXML
    private Circle circlElement;
@FXML
    private AnchorPane rootBody;
    private double x = 10;



    public void UP(ActionEvent e){
        System.out.println("UP");
        circlElement.setTranslateY(circlElement.getTranslateY()  + x);
    }

    public void Down(ActionEvent e){
        System.out.println("Down");

        circlElement.setTranslateY(circlElement.getTranslateY() -x);

    }
    public void Left(ActionEvent e){
        System.out.println("Left");
        circlElement.setTranslateX(circlElement.getTranslateX() -x);

    }
    public void Right(ActionEvent e){
        System.out.println("Right");
        circlElement.setTranslateX(circlElement.getTranslateX() + x);
    }
    public void newCircle(){
        System.out.println("Creating a new Circle");
        Circle circle = new Circle(20);
        circle.setLayoutX(200.03);
        circle.setLayoutY(20.03);
        circle.setTranslateX(x);
        circle.setTranslateX(x + 10.32);
        circle.setFill(Color.BISQUE);
        rootBody.getChildren().add(circle);
    }

}
