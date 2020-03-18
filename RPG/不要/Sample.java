import java.util.Random;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class Sample extends Application{

    ImageView chara = new ImageView("Image/chara.png");
    int movex, movey;
    double chara_x, chara_y;

    public void start(Stage stage) throws Exception{

        ImageView background = new ImageView("Image/download.jpg");

        background.setX(0);
        background.setY(0);
        chara.setX(0);
        chara.setY(0);

        Group root = new Group();
        root.getChildren().addAll(background, chara);
        Scene Mapscene = new Scene(root);

        Timeline t = new Timeline(
            new KeyFrame(Duration.millis(25.0),e -> Move(e)));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        Mapscene.setOnKeyPressed( event -> doKeyAction(event));
        Mapscene.setOnKeyReleased( event -> doKeyAction2(event));

        stage.setScene(Mapscene);
        stage.show();
    }

    void Move(ActionEvent e){
		chara.setX(chara.getX()+movex);
		chara.setY(chara.getY()+movey);
		chara_x = chara.getX();
		chara_y = chara.getY();

		if(chara_x <= 0) chara_x=0;
		if(chara_x >= 1000) chara_x=1000;
		if(chara_y <= 0) chara_y=0;
		if(chara_y >= 600) chara_y=600;

		chara.setX(chara_x);
		chara.setY(chara_y);

	}


	void doKeyAction(KeyEvent event){

		if(event.getCode() == KeyCode.RIGHT){
				movex = 1;

		}
		if(event.getCode() == KeyCode.LEFT){
				movex = -1;
		}

		if(event.getCode() == KeyCode.UP){
				movey = -1;
		}

		if(event.getCode() == KeyCode.DOWN){
				movey = 1;
		}

	}
	void doKeyAction2(KeyEvent event){
		if(event.getCode() == KeyCode.RIGHT){
				movex = 0;


		}
		if(event.getCode() == KeyCode.LEFT){
				movex = 0;

		}

		if(event.getCode() == KeyCode.UP){
				movey = 0;
		}

		if(event.getCode() == KeyCode.DOWN){
				movey = 0;
		}

	}

}