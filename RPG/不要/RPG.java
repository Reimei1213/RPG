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

public class RPG extends Application{

Status Gaigon = new Status("GAIGON");
//Stage stage;

int kind = 2;;

int danflag = 1, posiflag = 1, btflag = 0;
int movex=0, movey=0;
int levelExp[] = new int[51];
int monsterBorn, bornflag, atflag, hpflag = 1;
int magicflag, tokugiflag, runflag;
double chara_x=500, chara_y=500;

int[] enemy1 = new int[5];
int[] enemy2 = new int[5];
int[] enemy3 = new int[5];

Random random = new Random();
HBox monsterImage = new HBox();
TextArea text = new TextArea();
TextArea chara1text = new TextArea();
Timeline t1, t2, t3;
//Group magicCommand = new Group();
//Group tokugiCommand = new Group();
//Group root = new Group();
//Group command = new Group();
//AnchorPane screen_pane = new AnchorPane();
//Group screen = new Group();

/*Image cave1 = new Image("Image/cave/cave1.png");
Image cave2 = new Image("Image/cave/cave2.png");
Image cave3 = new Image("Image/cave/cave3.png");
Image cave4 = new Image("Image/cave/cave4.png");
Image gobIma = new Image("Image/goblin.png");
Image slaIma = new Image("Image/slime.png");*/

//ImageView background = new ImageView("Image/WorldImage.jpg");
ImageView chara = new ImageView("Image/chara.png");
/*ImageView attack_command = new ImageView("Image/attack_command.png");
ImageView magic_command = new ImageView("Image/magic_command.png");
ImageView tokugi_command = new ImageView("Image/tokugi_command.png");
ImageView run_away = new ImageView("Image/run_away.png");
ImageView all_back = new ImageView("Image/Allback.png");
ImageView battlescreen = new ImageView("Image/back.png");
ImageView magic_back= new ImageView("Image/command_back_magic.png");
ImageView tokugi_back = new ImageView("Image/command_back_tokugi.png");
ImageView inshitsu = new ImageView("Image/inshitsu.png");
ImageView heal = new ImageView ("Image/boimi.png");*/
//ImageView win = new ImageView("Image/Win.png");
ImageView lose = new ImageView("Image/Lose.png");
ImageView[] daniv = new ImageView[4];
ImageView boss = new ImageView("Image/Boss.png");
GoblinStatus[] goblin =new GoblinStatus[3];
SlimeStatus[] slime = new SlimeStatus[3];

ImageView eneiv1 = new ImageView();
ImageView eneiv2 = new ImageView();
ImageView eneiv3 = new ImageView();

Image slimeiv = new Image("Image/slime.png");
Image gobliniv = new Image("Image/goblin.png");

public void start(Stage stage)throws Exception{

    stage.setTitle("RPG");
    stage.setWidth(1000);
    stage.setHeight(600);

    Button startbtn = new Button("Start");

    startbtn.setOnAction(new EventHandler<ActionEvent>(){
      public void handle (ActionEvent e){
        WorldMap(stage);
      }
    });
    //startbtn.setOnAction(event -> WorldMap());
    stage.setScene(new Scene(startbtn));
    stage.show();
}

void Dungeon(Stage stage){

    Group root = new Group();

    Image cave1 = new Image("Image/cave/cave1.png");
    Image cave2 = new Image("Image/cave/cave2.png");
    Image cave3 = new Image("Image/cave/cave3.png");
    Image cave4 = new Image("Image/cave/cave4.png");

    chara.setX(500);
    chara.setY(500);
    boss.setX(375);
    boss.setY(100);

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
            new KeyFrame(Duration.millis(10.0),e -> MoveDungeon(e, stage)));
    t1.setCycleCount(Timeline.INDEFINITE);
    t1.play();
    root.getChildren().addAll(daniv[3], boss, daniv[2], daniv[1], daniv[0], chara);
    Scene DungeonScene = new Scene(root);
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
    }

    if(btflag == 0)
      if(battle == 0){
        Battle(stage);
        t1.stop();
      }
}

void WorldMap(Stage stage){

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

    btflag = 1;
    movex = 0;
    movey = 0;

    enemy1[0] = 0;
    enemy2[0] = 0;
    enemy3[0] = 0;

    for (int i=2; i<=50; i++){
        levelExp[i] = i*i;
    }

    t3 = new Timeline(
            new KeyFrame(Duration.millis(10.0),e -> System(e, stage, screen)));
    t3.setCycleCount(Timeline.INDEFINITE);
    t3.play();

    monsterBorn = random.nextInt(3)+1 ;

    switch(monsterBorn){
        case 3:
            enemy3 = EnemyGenerate(3);
            monsterImage.getChildren().addAll(eneiv3);
        case 2:
            enemy2 = EnemyGenerate(2);
            monsterImage.getChildren().addAll(eneiv2);
        case 1:
            enemy1 = EnemyGenerate(1);
            monsterImage.getChildren().addAll(eneiv1);
    }


    //int slimeBorn = monsterBorn - random.nextInt(monsterBorn);
    //int goblinBorn = monsterBorn - slimeBorn;

    /*switch(slimeBorn){
        case 3:
            slime[2] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[2].image);
            System.out.println("�X���C�������ꂽ�I");

        case 2:
            slime[1] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[1].image);
            System.out.println("�X���C�������ꂽ�I");


        case 1:
            slime[0] = new SlimeStatus();
            monsterImage.getChildren().addAll(slime[0].image);
            System.out.println("�X���C�������ꂽ�I");

    }

    switch(goblinBorn){
        case 3:
            goblin[2] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[2].image);
            System.out.println("�S�u���������ꂽ�I");

        case 2:
            goblin[1] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[1].image);
            System.out.println("�S�u���������ꂽ�I");

        case 1:
            goblin[0] = new GoblinStatus();
            monsterImage.getChildren().addAll(goblin[0].image);
            System.out.println("�S�u���������ꂽ�I");

    }*/

    magicCommand.getChildren().addAll(magic_back, heal);
    tokugiCommand.getChildren().addAll(tokugi_back, inshitsu);
    screen_pane.getChildren().addAll(battlescreen, monsterImage, chara1text);
    screen_pane.getChildren().addAll();
    command.getChildren().addAll(attack_command, magic_command, tokugi_command, run_away);
    screen.getChildren().addAll(screen_pane, command);

    Scene BattleScene = new Scene(screen);
    BattleScene.setOnMousePressed(event -> Command(event, screen, magicCommand, tokugiCommand));
    stage.setScene(BattleScene);
    stage.show();


}

void System(ActionEvent e, Stage stage, Group screen){
    chara1text.setText(Gaigon.name + "    Level:  " + Gaigon.level + "\nHP:  " + Gaigon.HP +
            "/" + Gaigon.MaxHP +"\nMP:  " + Gaigon.MP + "/" + Gaigon.MaxMP + "\n�U����:  " +
            Gaigon.attack + "\n�h����:  " + Gaigon.defense);
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
            System.out.println("GAIGON�̃��x����" + Gaigon.level +"�ɏオ����");
        }
        else break;
    }
    WorldMap(stage);
    movex = 0;
    movey = 0;
}

void Command(MouseEvent e, Group screen, Group magicCommand, Group tokugiCommand){

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
    System.out.println("GAIGON�͉A���Ȕl��������");
    enemy3[3] = enemy3[3] - 1;
    enemy2[3] = enemy2[3] - 1;
    enemy1[3] = enemy1[3] - 1;
    System.out.println("�����̖h���͂���������");
}

void Heal(){
    if(Gaigon.MP - 3 > 0){
        Gaigon.MP = Gaigon.MP - 3;
        System.out.println("GAIGON�̓q�[����������");
        if(Gaigon.HP + 20 < Gaigon.MaxHP)
            Gaigon.HP = Gaigon.HP + 20;
        else Gaigon.HP = Gaigon.MaxHP;
    }
    else System.out.println("�q�[���������邱�Ƃ��ł��Ȃ�����");
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
        System.out.println("�����؂邱�Ƃ��ł���");
    else {
        System.out.println("�����؂邱�Ƃ��ł��Ȃ�����");
    }
    return a;
}

void Attack(){
    System.out.println("GAIGON�̍U���I");
    atflag = 0;

    if(enemy1[0] != 0)
        if(enemy1[1] > 0){
            atflag = 1;
            enemy1[1] = enemy1[1] - (Gaigon.attack - enemy1[2]);
            if(enemy1[1] <= 0){
                enemy1[0] = 0;
                monsterImage.getChildren().remove(eneiv1);
                monsterBorn--;
                System.out.println(monsterBorn);
                Gaigon.exp = Gaigon.exp + enemy1[4];
                if(enemy1[0] == 1)
                    System.out.println("�X���C�����|�����I");
                if(enemy1[0] == 2)
                    System.out.println("�S�u�������|�����I");
            }
        }

    if(atflag == 0)
        if(enemy2[0] != 0)
            if(enemy2[1] > 0){
                atflag = 1;
                enemy2[1] = enemy2[1] - (Gaigon.attack - enemy2[2]);
                if(enemy2[1] <= 0){
                    enemy2[0] = 0;
                    monsterImage.getChildren().remove(eneiv2);
                    monsterBorn--;
                    System.out.println(monsterBorn);
                    Gaigon.exp = Gaigon.exp + enemy2[4];
                    if(enemy2[0] == 1)
                        System.out.println("�X���C�����|�����I");
                    if(enemy2[0] == 2)
                        System.out.println("�S�u�������|�����I");
                }
            }

    if(atflag == 0)
        if(enemy3[0] != 0)
            if(enemy3[1] > 0){
                atflag = 1;
                enemy3[1] = enemy3[1] - (Gaigon.attack - enemy3[2]);
                if(enemy3[1] <= 0){
                    enemy3[0] = 0;
                    monsterImage.getChildren().remove(eneiv3);
                    monsterBorn--;
                    System.out.println(monsterBorn);
                    Gaigon.exp = Gaigon.exp + enemy3[4];
                    if(enemy3[0] == 1)
                        System.out.println("�X���C�����|�����I");
                    if(enemy3[0] == 2)
                        System.out.println("�S�u�������|�����I");
                }
            }

    if(monsterBorn == 0)
        bornflag = 1;

    /*for(int i=0; i<3; i++){
        if(slime[i] == null) break;
        if(slime[i].HP > 0){
            atflag = 1;
            slime[i].HP = slime[i].HP - (Gaigon.attack - slime[i].defense);
            if(slime[i].HP <= 0){
                monsterImage.getChildren().remove(slime[i].image);
                monsterBorn--;
                Gaigon.exp = Gaigon.exp + slime[i].exp;
                System.out.println("�X���C�����|�����I");
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
                    System.out.println("�S�u�������|�����I");
                    if(monsterBorn == 0)
                        bornflag = 1;
                }
                break;
            }
        }
    }*/
}

void AttackEnemy(){

    if(enemy1[0] != 0)
        if(enemy1[1] > 0){
            if(enemy1[2] - Gaigon.defense > 0){
                if(Gaigon.HP - (enemy1[2] - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (enemy1[2] - Gaigon.defense);
                else{
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
                if(enemy1[0] == 1)
                    System.out.println("�X���C���̍U���I");
                if(enemy1[0] == 2)
                    System.out.println("�S�u�����̍U���I");
            }
            else if(enemy1[0] == 1)
                    System.out.println("�X���C���̍U���͓������Ȃ�����");
            else if(enemy1[0] == 2)
                System.out.println("�S�u�����̍U���͓������Ȃ�����");
        }

    if(enemy2[0] != 0)
        if(enemy2[1] > 0){
            if(enemy2[2] - Gaigon.defense > 0){
                if(Gaigon.HP - (enemy2[2] - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (enemy2[2] - Gaigon.defense);
                else{
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
                if(enemy2[0] == 1)
                    System.out.println("�X���C���̍U���I");
                if(enemy2[0] == 2)
                    System.out.println("�S�u�����̍U���I");
            }
            if(enemy2[0] == 1)
                    System.out.println("�X���C���̍U���͓������Ȃ�����");
            if(enemy2[0] == 2)
                System.out.println("�S�u�����̍U���͓������Ȃ�����");
        }

    if(enemy3[0] != 0)
        if(enemy3[1] > 0){
            if(enemy3[2] - Gaigon.defense > 0){
                if(Gaigon.HP - (enemy3[2] - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (enemy3[2] - Gaigon.defense);
                else{
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
                if(enemy3[0] == 1)
                    System.out.println("�X���C���̍U���I");
                if(enemy3[0] == 2)
                    System.out.println("�S�u�����̍U���I");
            }
            if(enemy3[0] == 1)
                    System.out.println("�X���C���̍U���͓������Ȃ�����");
            if(enemy3[0] == 2)
                System.out.println("�S�u�����̍U���͓������Ȃ�����");
        }


    /*for(int i=0; i<3; i++){
        if(slime[i] == null) break;
        if(slime[i].HP > 0){
            if(slime[i].attack - Gaigon.defense > 0){
                System.out.println("�X���C���̍U��");
                //wait(1);
                if(Gaigon.HP - (slime[i].attack - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (slime[i].attack - Gaigon.defense);
                else {
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
            }
            else System.out.println("�X���C���̍U���͓������Ȃ�����");
        }
    }

    for(int i=0; i<3; i++){
        if(goblin[i] == null) break;
        if(goblin[i].HP > 0){
            if(goblin[i].attack - Gaigon.defense > 0){
                System.out.println("�S�u�����̍U���I");
                //wait(1);
                if(Gaigon.HP - (goblin[i].attack - Gaigon.defense) > 0)
                    Gaigon.HP = Gaigon.HP - (goblin[i].attack - Gaigon.defense);
                else {
                    Gaigon.HP = 0;
                    hpflag = 0;
                }
            }
            else System.out.println("�S�u�����̍U���͓������Ȃ�����");
        }
    }*/
}

int[] EnemyGenerate(int a){

    int enemy = random.nextInt(kind+1);
    int[] enemyStatus = new int[5];

    if(enemy == 1){
        enemyStatus[0] = 1;
        enemyStatus[1] = 3;
        enemyStatus[2] = 2;
        enemyStatus[3] = 2;
        enemyStatus[4] = 1;
        if(a == 1)
            eneiv1.setImage(slimeiv);
        if(a == 2)
            eneiv2.setImage(slimeiv);
        if(a == 3)
            eneiv3.setImage(slimeiv);
    }

    if(enemy == 2){
        enemyStatus[0] = 2;
        enemyStatus[1] = 5;
        enemyStatus[2] = 3;
        enemyStatus[3] = 1;
        enemyStatus[4] = 3;
        if(a == 1)
            eneiv1.setImage(gobliniv);
        if(a == 2)
            eneiv2.setImage(gobliniv);
        if(a == 3)
            eneiv3.setImage(gobliniv);
    }
    return enemyStatus;
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
        this.HP = 3;
        this.attack = 2;
        this.defense = 2;
        this.exp = 1;
    }
}

class GoblinStatus{
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/goblin.png");

    GoblinStatus(){
        this.name = "Goblin";
        this.HP = 5;
        this.attack = 3;
        this.defense = 1;
        this.exp = 3;
    }
}
}
