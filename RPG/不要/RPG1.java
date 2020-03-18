import java.util.Random;

import javafx.util.Duration;
import javafx.event.EventHandler;
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
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class RPG1 extends Application{

Status Gaigon = new Status("GAIGON");
//Stage stage;

int danflag = 1, posiflag = 1, btflag = 0, mapflag = 0;
int movex=0, movey=0;
int levelExp[] = new int[201];
int monsterBorn, bornflag, atflag, hpflag = 1;
int magicflag, tokugiflag, runflag;
double chara_x=500, chara_y=500;

Random random = new Random();
HBox monsterImage = new HBox();
TextArea text = new TextArea();
TextArea chara1text = new TextArea();
Timeline t1, t2, t3;

ImageView chara = new ImageView("Image/chara.png");
ImageView lose = new ImageView("Image/Lose.png");
ImageView[] daniv = new ImageView[4];
GoblinStatus[] goblin = new GoblinStatus[3];
SlimeStatus[] slime = new SlimeStatus[3];
ImageView box = new ImageView("Image/box.png");


public void start(Stage stage)throws Exception{

    stage.setTitle("RPG1");
    stage.setWidth(1000);
    stage.setHeight(600);

    for (int i=2; i<200; i++){
        levelExp[i] = i*i;
    }

    Button startbtn = new Button("Start");
    startbtn.setFont(new Font(50));

    ImageView title = new ImageView("Image/gaigon.PNG");

    startbtn.setOnAction(new EventHandler<ActionEvent>(){
      public void handle (ActionEvent e){
        WorldMap(stage);
      }
    });
    //startbtn.setOnAction(event -> WorldMap());
    VBox root = new VBox();
    root.getChildren().addAll(title, startbtn);
    root.setAlignment(Pos.CENTER);
    stage.setScene(new Scene(root));
    stage.show();
}

void Dungeon(Stage stage){
    mapflag = 1;

    Group root = new Group();

    Image cave1 = new Image("Image/cave/cave1.png");
    Image cave2 = new Image("Image/cave/cave2.png");
    Image cave3 = new Image("Image/cave/cave3.png");
    Image cave4 = new Image("Image/cave/cave4.png");

    chara.setX(chara_x);
    chara.setY(chara_y);
    boss.image.setX(375);
    boss.image.setY(100);

    for(int i=0; i<4; i++)
        daniv[i] = new ImageView();


    /*switch (danflag){
        case 1:
            daniv[0].setImage(cave1);
            daniv[1].setImage(cave2);
            daniv[2].setImage(cave3);
            daniv[3].setImage(cave4);
    }*/

    daniv[0].setImage(cave1);
    daniv[1].setImage(cave2);
    daniv[2].setImage(cave3);
    daniv[3].setImage(cave4);

    t1 = new Timeline(
            new KeyFrame(Duration.millis(25.0),e -> MoveDungeon(e, stage)));
    t1.setCycleCount(Timeline.INDEFINITE);
    t1.play();
    root.getChildren().addAll(daniv[3], boss.image, daniv[2], daniv[1], daniv[0], chara);
    Scene DungeonScene = new Scene(root);

    if(posiflag == 2)
        daniv[0].setX(1000);
    if(posiflag == 3){
        daniv[0].setX(1000);
        daniv[1].setX(1000);
    }
    if(posiflag == 4){
        daniv[0].setX(1000);
        daniv[1].setX(1000);
        daniv[2].setX(1000);
    }

    stage.setScene(DungeonScene);
    DungeonScene.setOnKeyPressed( event -> doKeyAction(event));
    DungeonScene.setOnKeyReleased( event -> doKeyAction2(event));
    //stage.show();
}

  void MoveDungeon(ActionEvent e, Stage stage){

    int battle = random.nextInt(300) ;

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
              WorldMap(stage);
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
        if(boss.image.getX() >= 375 && boss.image.getX() <= 625 && boss.image.getY() >= 100 && boss.image.getY() <= 350)
            BossBattle(stage);
    }

    if(btflag == 0)
      if(battle == 0){
        Battle(stage);
        t1.stop();
      }
}

void BossBattle(Stage stage){

}

void WorldMap(Stage stage){

    mapflag = 0;
    posiflag = 1;

    Group root = new Group();
    ImageView background = new ImageView("Image/WorldImage.png");
    ImageView dgimage = new ImageView("Image/dungeon.png");

    background.setX(0);
    background.setY(0);
    chara.setX(chara_x);
    chara.setY(chara_y);
    dgimage.setX(800);
    dgimage.setY(400);

    root.getChildren().addAll(background, dgimage, chara);
    Scene MapScene = new Scene(root);

    t2 = new Timeline(
      new KeyFrame(Duration.millis(25.0),e -> Move(e, stage)));
    t2.setCycleCount(Timeline.INDEFINITE);
    t2.play();
    MapScene.setOnKeyPressed( event -> doKeyAction(event));
    MapScene.setOnKeyReleased( event -> doKeyAction2(event));

    stage.setScene(MapScene);
    stage.show();
}

  void Move(ActionEvent e, Stage stage){

    int battle = random.nextInt(300) ;

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

    if(btflag == 0)
      if(battle == 0){
        Battle(stage);
        t2.stop();
      }

    if(chara_x >= 800 && chara_x <= 900)
        if(chara_y >= 400 && chara_y <= 500)
            Dungeon(stage);
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

  void Battle(Stage stage){

    Group magicCommand = new Group();
    Group tokugiCommand = new Group();
    Group command = new Group();
    Group screen = new Group();
    AnchorPane screen_pane = new AnchorPane();

    ImageView attack_command = new ImageView("Image/attack_command.png");
    ImageView magic_command = new ImageView("Image/magic_command.png");
    ImageView tokugi_command = new ImageView("Image/tokugi_command.png");
    ImageView run_away = new ImageView("Image/run_away.png");
    ImageView battlescreen = new ImageView("Image/back.png");
    ImageView magic_back= new ImageView("Image/command_back_magic.png");
    ImageView tokugi_back = new ImageView("Image/command_back_tokugi.png");
    ImageView inshitsu = new ImageView("Image/inshitsu.png");
    ImageView heal = new ImageView ("Image/boimi.png");

    chara1text.setMaxWidth(235);
    chara1text.setMaxHeight(250);
    chara1text.setEditable(false);

    attack_command.setX(0);
    attack_command.setY(250);
    magic_command.setX(0);
    magic_command.setY(300);
    tokugi_command.setX(0);
    tokugi_command.setY(350);
    run_away.setX(0);
    run_away.setY(400);

    inshitsu.setX(250);
    inshitsu.setY(250);
    heal.setX(250);
    heal.setY(250);
    magic_back.setX(200);
    magic_back.setY(250);
    tokugi_back.setX(200);
    tokugi_back.setY(250);
    //win.setX(0);
    //win.setY(0);
    lose.setX(0);
    lose.setY(0);

    AnchorPane.setTopAnchor(text, 0.0);
    AnchorPane.setLeftAnchor(text, 0.0);
    AnchorPane.setTopAnchor(battlescreen, 0.0);
    AnchorPane.setLeftAnchor(battlescreen, 0.0);
    AnchorPane.setTopAnchor(monsterImage, 0.0);
    AnchorPane.setLeftAnchor(monsterImage, 0.0);
    AnchorPane.setTopAnchor(chara1text, 0.0);
    AnchorPane.setLeftAnchor(chara1text, 750.0);

    //goblin = new GoblinStatus[3];
    //slime = new SlimeStatus[3];

    btflag = 1;
    movex = 0;
    movey = 0;


    t3 = new Timeline(
            new KeyFrame(Duration.millis(10.0),e -> System(e, stage, screen)));
    t3.setCycleCount(Timeline.INDEFINITE);
    t3.play();

    monsterBorn = random.nextInt(3)+1 ;
    int slimeBorn = monsterBorn - random.nextInt(monsterBorn);
    int goblinBorn = monsterBorn - slimeBorn;

    switch(slimeBorn){
        case 3:
            slime[2] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[2].image);
            System.out.println("スライムが現れた！");

        case 2:
            slime[1] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[1].image);
            System.out.println("スライムが現れた！");

        case 1:
            slime[0] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[0].image);
            System.out.println("スライムが現れた！");

    }

    switch(goblinBorn){
        case 3:
            goblin[2] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[2].image);
            System.out.println("ゴブリンが現れた！");

        case 2:
            goblin[1] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[1].image);
            System.out.println("ゴブリンが現れた！");

        case 1:
            goblin[0] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[0].image);
            System.out.println("ゴブリンが現れた！");

    }

    magicCommand.getChildren().addAll(magic_back, heal);
    tokugiCommand.getChildren().addAll(tokugi_back, inshitsu);
    screen_pane.getChildren().addAll(battlescreen, monsterImage, chara1text);
    screen_pane.getChildren().addAll();
    command.getChildren().addAll(attack_command, magic_command, tokugi_command, run_away);
    screen.getChildren().addAll(screen_pane, command);

    Scene BattleScene = new Scene(screen);
    BattleScene.setOnMousePressed(event -> Command(event, screen, magicCommand, tokugiCommand, stage));
    stage.setScene(BattleScene);
    stage.show();


}

void System(ActionEvent e, Stage stage, Group screen){
    chara1text.setText(Gaigon.name + "    Level:  " + Gaigon.level + "\nHP:  " + Gaigon.HP +
            "/" + Gaigon.MaxHP +"\nMP:  " + Gaigon.MP + "/" + Gaigon.MaxMP + "\n攻撃力:  " +
            Gaigon.attack + "\n防御力:  " + Gaigon.defense);
    if(bornflag == 1){
        //screen.getChildren().addAll(win);
        bornflag = 0;
        btflag = 0;
        LevelUp(stage);
        t3.stop();
    }
    if(hpflag == 0){
        screen.getChildren().addAll(lose);
        hpflag = 1;
    }
}

void LevelUp(Stage stage){
    while(true){
        if(levelExp[Gaigon.level + 1] - Gaigon.exp < 0){
            Gaigon.exp = Gaigon.exp - levelExp[Gaigon.level + 1];
            Gaigon.MaxHP = Gaigon.MaxHP + random.nextInt(10);
            Gaigon.MaxMP = Gaigon.MaxMP + random.nextInt(10);
            Gaigon.attack = Gaigon.attack + random.nextInt(10);
            Gaigon.defense = Gaigon.defense + random.nextInt(10);
            Gaigon.level++;
            System.out.println("GAIGONのレベルが" + Gaigon.level +"に上がった");
        }
        else break;
    }
    movex = 0;
    movey = 0;
    if(mapflag == 0)
        WorldMap(stage);
    if(mapflag == 1)
        Dungeon(stage);
}

void Command(MouseEvent e, Group screen, Group magicCommand, Group tokugiCommand, Stage stage){

    double mouse_x, mouse_y;
    mouse_x = e.getX();
    mouse_y = e.getY();

    if(mouse_x >= 0 && mouse_x <= 200){

        if(mouse_y >= 250 && mouse_y <= 300){
            screen.getChildren().remove(magicCommand);
            screen.getChildren().remove(tokugiCommand);
            tokugiflag = 0;
            magicflag = 0;
            Attack();
            //wait(1);
            AttackEnemy();
        }

        if(mouse_y > 300 && mouse_y <= 350)
            if(magicflag == 0){
                screen.getChildren().remove(tokugiCommand);
                screen.getChildren().addAll(magicCommand);
                tokugiflag = 0;
                magicflag = 1;
            }

        if(mouse_y > 350 && mouse_y <= 400)
            if(tokugiflag == 0){
                screen.getChildren().remove(magicCommand);
                screen.getChildren().addAll(tokugiCommand);
                tokugiflag = 1;
                magicflag = 0;
            }

        if(mouse_y > 400 && mouse_y <= 450){
            screen.getChildren().remove(magicCommand);
            screen.getChildren().remove(tokugiCommand);
            tokugiflag = 0;
            magicflag = 0;
            runflag = Run_away();
            if(runflag == 0)
                AttackEnemy();
            if(runflag == 1)
                if(mapflag == 0)
                    WorldMap(stage);
                if(mapflag == 1)
                    Dungeon(stage);
        }
    }

    if(mouse_x >= 250 && mouse_x <= 700 && mouse_y >= 250 && mouse_y <= 450){
        if(magicflag == 1){
            Magic(e);
            AttackEnemy();
        }
        if(tokugiflag == 1){
            Tokugi(e);
            AttackEnemy();
        }
    }

}

void Inshitsu(){
    System.out.println("GAIGONは陰湿な罵りをした");
    for(int i=0; i<3; i++){
        if(slime[i] == null) break;
        else if(slime[i].HP > 0){
            slime[i].defense = slime[i].defense - 1;
            System.out.println("スライムの防御力が下がった");
        }
    }

    for(int i=0; i<3; i++){
        if(goblin[i] == null) break;
        else if(goblin[i].HP > 0){
            goblin[i].defense = goblin[i].defense - 1;
            System.out.println("ゴブリンの防御力が下がった");
        }
    }
}

void Heal(){
    if(Gaigon.MP - 3 > 0){
        Gaigon.MP = Gaigon.MP - 3;
        System.out.println("GAIGONはヒールを唱えた");
        if(Gaigon.HP + 20 < Gaigon.MaxHP)
            Gaigon.HP = Gaigon.HP + 20;
        else Gaigon.HP = Gaigon.MaxHP;
    }
    else System.out.println("ヒールを唱えることができなかった");
}

void Magic(MouseEvent e){
    double mouse_x, mouse_y;
    mouse_x = e.getX();
    mouse_y = e.getY();
    if(mouse_x >= 250 && mouse_x <= 450 && mouse_y >= 250 && mouse_y <= 300)
        Heal();
}

void Tokugi(MouseEvent e){
    double mouse_x, mouse_y;
    mouse_x = e.getX();
    mouse_y = e.getY();
    if(mouse_x >= 250 && mouse_x <= 450 && mouse_y >= 250 && mouse_y <= 300)
        Inshitsu();
}

int Run_away(){
    int a = random.nextInt(2) ;
    if(a == 1)
        System.out.println("逃げ切ることができた");
    else {
        System.out.println("逃げ切ることができなかった");
    }
    return a;
}

void Attack(){
    System.out.println("GAIGONの攻撃！");
    atflag = 0;
    for(int i=0; i<3; i++){
        if(slime[i] == null) break;
        if(slime[i].HP > 0){
            atflag = 1;
            slime[i].HP = slime[i].HP - (Gaigon.attack - slime[i].defense);
            if(slime[i].HP <= 0){
                monsterImage.getChildren().remove(slime[i].image);
                monsterBorn--;
                Gaigon.exp = Gaigon.exp + slime[i].exp;
                System.out.println("スライムを倒した！");
                if(monsterBorn == 0)
                    bornflag = 1;
            }
            break;
        }
    }
    if(atflag == 0){
        for(int i=0; i<3; i++){
            if(goblin[i] == null) break;
            if(goblin[i].HP > 0){
                goblin[i].HP = goblin[i].HP - (Gaigon.attack - goblin[i].defense);
                if(goblin[i].HP <= 0){
                    monsterImage.getChildren().remove(goblin[i].image);
                    monsterBorn--;
                    Gaigon.exp = Gaigon.exp + goblin[i].exp;
                    System.out.println("ゴブリンを倒した！");
                    if(monsterBorn == 0)
                        bornflag = 1;
                }
                break;
            }
        }
    }
}

void AttackEnemy(){

    for(int i=0; i<3; i++){
        if(slime[i] == null) break;
        if(slime[i].HP > 0){
            if(slime[i].attack - Gaigon.defense > 0){
                System.out.println("スライムの攻撃");
                //wait(1);
                if(Gaigon.HP - (slime[i].attack - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (slime[i].attack - Gaigon.defense);
                else {
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
            }
            else System.out.println("スライムの攻撃は当たらなかった");
        }
    }

    for(int i=0; i<3; i++){
        if(goblin[i] == null) break;
        if(goblin[i].HP > 0){
            if(goblin[i].attack - Gaigon.defense > 0){
                System.out.println("ゴブリンの攻撃！");
                //wait(1);
                if(Gaigon.HP - (goblin[i].attack - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (goblin[i].attack - Gaigon.defense);
                else {
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
            }
            else System.out.println("ゴブリンの攻撃は当たらなかった");
        }
    }
}


class Status {
    int HP, MaxHP, MaxMP, MP, attack, defense, exp, level;
    String name;

    Status(String name){
        this.name = name;
        this.HP = 20;
        this.MaxHP = 20;
        this.MP = 10;
        this.MaxMP = 10;
        this.attack = 3;
        this.defense = 1;
        this.exp = 0;
        this.level = 1;
    }
}

class SlimeStatus{
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/slime.png");

    SlimeStatus(){
        this.name = "Slime";
        this.HP = random.nextInt(Gaigon.exp * 5 + 1);
        this.attack = random.nextInt(Gaigon.exp * 5 + 1);
        this.defense = random.nextInt(Gaigon.exp * 5 + 1);
        this.exp = 1;
    }
}

class GoblinStatus{
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/goblin.png");

    GoblinStatus(){
        this.name = "Goblin";
        this.HP = random.nextInt(Gaigon.exp * 5 + 1);
        this.attack = random.nextInt(Gaigon.exp * 5 + 1);
        this.defense = random.nextInt(Gaigon.exp * 5 + 1);
        this.exp = 3;
    }
}

class BossStatus{
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/Boss.png");

    BossStatus(){
        this.name = "Boss";
        this.HP = 9999;
        this.attack =100;
        this.defense = 50;
        this.exp = 5000;
    }
}
}
