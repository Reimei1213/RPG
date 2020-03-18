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
import javafx.scene.image.Image;


public class Dungeon extends Application {

    int danflag = 1;
    int posiflag = 1;
    int movex=0, movey=0;
    double chara_x, chara_y;

    ImageView[] daniv = new ImageView[4];
    ImageView chara = new ImageView("Image/chara.png");
    ImageView boss = new ImageView("Image/Boss.png");
    Group root = new Group();

    Image cave1 = new Image("Image/cave/cave1.png");
    Image cave2 = new Image("Image/cave/cave2.png");
    Image cave3 = new Image("Image/cave/cave3.png");
    Image cave4 = new Image("Image/cave/cave4.png");


    public void start(Stage stage)throws Exception{
        stage.setTitle("Dungeon");
        stage.setWidth(1000);
        stage.setHeight(600);

        chara.setX(500);
        chara.setY(500);
        boss.setX(375);
        boss.setY(100);

        for(int i=0; i<4; i++)
            daniv[i] = new ImageView();


        switch (danflag){
            case 1:
                daniv[0].setImage(cave1);
                daniv[1].setImage(cave2);
                daniv[2].setImage(cave3);
                daniv[3].setImage(cave4);
        }


        Timeline t = new Timeline(
                new KeyFrame(Duration.millis(10.0),e -> MoveDungeon(e)));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        root.getChildren().addAll(daniv[3], boss, daniv[2], daniv[1], daniv[0], chara);
        Scene DungeonScene = new Scene(root);
        stage.setScene(DungeonScene);
        DungeonScene.setOnKeyPressed( event -> doKeyAction(event));
        DungeonScene.setOnKeyReleased( event -> doKeyAction2(event));
        stage.show();
    }


    void MoveDungeon(ActionEvent e){
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

        if(posiflag == 1){
            if(chara.getX() == 950){
                daniv[0].setX(1000);
                daniv[0].setY(600);
                chara.setX(50);
                chara.setY(chara_y);
                posiflag = 2;
            }
            if(chara.getY() == 0){
                chara.setX(chara_x);
                chara.setY(500);
            }
            if(chara.getX() == 0){
                chara.setX(950);
                chara.setY(chara_y);
            }
            if(chara.getY() == 550){
                //To WorldMap
            }
        }

        if(posiflag == 2){

            if(chara.getX()== 950){
                daniv[0].setX(0);
                daniv[0].setY(0);
                chara.setX(50);
                chara.setY(chara_y);
                posiflag = 1;
            }
            if(chara.getY() == 0){
                daniv[1].setX(1000);
                daniv[1].setY(600);
                chara.setX(chara_x);
                chara.setY(500);
                posiflag = 3;
            }
            if(chara.getX() == 0){
                daniv[0].setX(0);
                daniv[0].setY(0);
                chara.setX(950);
                chara.setY(chara_y);
                posiflag = 1;
            }
            if(chara.getY() == 550){
                daniv[0].setX(0);
                daniv[0].setY(0);
                chara.setX(chara_x);
                chara.setY(0);
                posiflag = 1;
            }
        }

        if(posiflag == 3){
            if(chara.getX() == 950){
                daniv[0].setX(0);
                daniv[0].setY(0);
                daniv[1].setX(0);
                daniv[1].setY(0);
                chara.setX(50);
                chara.setY(chara_y);
                posiflag = 1;
            }
            if(chara.getY() == 0){
                daniv[0].setX(0);
                daniv[0].setY(0);
                daniv[1].setX(0);
                daniv[1].setY(0);
                chara.setX(chara_x);
                chara.setY(500);
                posiflag = 1;
            }
            if(chara.getX() == 0){
                daniv[2].setX(1000);
                daniv[2].setY(600);
                chara.setX(950);
                chara.setY(chara_y);
                posiflag = 4;
            }
            if(chara.getY() == 550){
                daniv[0].setX(0);
                daniv[0].setY(0);
                daniv[1].setX(0);
                daniv[1].setY(0);
                chara.setX(chara_x);
                chara.setY(0);
                posiflag = 1;
            }
        }

        if(posiflag == 4){
            if(chara.getX() == 950){
                chara.setX(50);
                chara.setY(chara_y);
            }
            if(chara.getY() == 0){
                chara.setX(chara_x);
                chara.setY(500);
            }
            if(chara.getX() == 0){
                chara.setX(950);
                chara.setY(chara_y);
            }
            if(chara.getY() == 550){
                chara.setX(chara_x);
                chara.setY(0);
            }
        }
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
