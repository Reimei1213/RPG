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

public class RPG3 extends Application{

Status Gaigon = new Status("GAIGON");
Random random = new Random();

int danflag = 1, posiflag = 1, btflag = 0, mapflag = 0;
int movex=0, movey=0;
int levelExp[] = new int[201];
int monsterBorn, bornflag, atflag, hpflag = 1;
int magicflag, tokugiflag, runflag;
int status = random.nextInt(Gaigon.exp * 5 + 1);
double chara_x=500, chara_y=500;


HBox monsterImage = new HBox();
TextArea text = new TextArea();
TextArea chara1text = new TextArea();
Timeline t1, t2, t3;
ImageView chara = new ImageView("Image/chara.png");
ImageView lose = new ImageView("Image/Lose.png");
ImageView[] daniv = new ImageView[4];
ImageView box = new ImageView("Image/box.png");
GoblinStatus[] goblin;
SlimeStatus[] slime;


public void start(Stage stage)throws Exception{

    stage.setTitle("RPG");
    stage.setWidth(1000);
    stage.setHeight(600);

    for (int i=2; i<200; i++){
        levelExp[i] = i*i;
    }

    Button startbtn = new Button("Ç∑ÇΩÅ[Ç∆");
    startbtn.setFont(new Font(50));

    ImageView title = new ImageView("Image/gaigon.PNG");

    startbtn.setOnAction(new EventHandler<ActionEvent>(){
      public void handle (ActionEvent e){
        WorldMap(stage);
      }
    });
    VBox root = new VBox();
    root.getChildren().addAll(title, startbtn);
    root.setAlignment(Pos.CENTER);
    stage.setScene(new Scene(root));
    stage.show();
}

    void Dungeon(Stage stage){

        Group root = new Group();

        Image cave = new Image("Image/cave.png");
    
        chara.setX(chara_x);
        chara.setY(chara_y);
        box.setX(375);
        box.setY(100);

        mapflag = 1;

        for(int i=0; i<4; i++)
            daniv[i] = new ImageView();

        for(int i=0; i<4; i++)
            daniv[i].setImage(cave);

        t1 = new Timeline(
            new KeyFrame(Duration.millis(25.0),e -> MoveDungeon(e, stage, root)));
        t1.setCycleCount(Timeline.INDEFINITE);
        t1.play();
        root.getChildren().addAll(daniv[3], box, daniv[2], daniv[1], daniv[0], chara);
        Scene DungeonScene = new Scene(root);
        stage.setScene(DungeonScene);
        DungeonScene.setOnKeyPressed( event -> doKeyAction(event));
        DungeonScene.setOnKeyReleased( event -> doKeyAction2(event));
    }

    void MoveDungeon(ActionEvent e, Stage stage, Group root){

        int battle = random.nextInt(300) ;
        ImageView Clear = new ImageView("Image/clear.png");

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
            daniv[0].setX(1000);
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
            daniv[0].setX(1000);
            daniv[1].setX(1000);
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
            daniv[0].setX(1000);
            daniv[1].setX(1000);
            daniv[2].setX(1000);
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
            if(chara.getX() >= 375 && chara.getX() <= 625 && chara.getY() >= 100 && chara.getY() <= 250){
                root.getChildren().addAll(Clear);
                t1.stop();
                t2.stop();
                t3.stop();
            }
        }

        if(btflag == 0)
        if(battle == 0){
            t1.stop();
            Battle(stage);
        }
    }

    void WorldMap(Stage stage){

        mapflag = 0;

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
            t2.stop();
            Battle(stage);
        }

        if(chara_x >= 800 && chara_x <= 900)
            if(chara_y >= 400 && chara_y <= 500){
                t2.stop();
                Dungeon(stage);
            }

        if(chara_x == 0 && chara_y == 0){
            System.out.println("GAIGONÇÃHPÇ∆MPÇ™âÒïúÇµÇΩ");
            Gaigon.HP = Gaigon.MaxHP;
            Gaigon.MP = Gaigon.MaxMP;
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

        slime = new SlimeStatus[3];
        goblin = new GoblinStatus[3];


        t3 = new Timeline(
                new KeyFrame(Duration.millis(25.0),e -> System(e, stage, screen)));
        t3.setCycleCount(Timeline.INDEFINITE);
        t3.play();

        monsterBorn = random.nextInt(3)+1 ;
        int slimeBorn = monsterBorn - random.nextInt(monsterBorn);
        int goblinBorn = monsterBorn - slimeBorn;

        switch(slimeBorn){
            case 3:
                slime[2] = new SlimeStatus();
                monsterImage.getChildren().addAll(slime[2].image);
                System.out.println("ÉXÉâÉCÉÄÇ™åªÇÍÇΩÅI");

            case 2:
                slime[1] = new SlimeStatus();
                monsterImage.getChildren().addAll(slime[1].image);
                System.out.println("ÉXÉâÉCÉÄÇ™åªÇÍÇΩÅI");


            case 1:
                slime[0] = new SlimeStatus();
                monsterImage.getChildren().addAll(slime[0].image);
                System.out.println("ÉXÉâÉCÉÄÇ™åªÇÍÇΩÅI");

        }

        switch(goblinBorn){
            case 3:
                goblin[2] = new GoblinStatus();
                monsterImage.getChildren().addAll(goblin[2].image);
                System.out.println("ÉSÉuÉäÉìÇ™åªÇÍÇΩÅI");

            case 2:
                goblin[1] = new GoblinStatus();
                monsterImage.getChildren().addAll(goblin[1].image);
                System.out.println("ÉSÉuÉäÉìÇ™åªÇÍÇΩÅI");

            case 1:
                goblin[0] = new GoblinStatus();
                monsterImage.getChildren().addAll(goblin[0].image);
                System.out.println("ÉSÉuÉäÉìÇ™åªÇÍÇΩÅI");

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
                "/" + Gaigon.MaxHP +"\nMP:  " + Gaigon.MP + "/" + Gaigon.MaxMP + "\nçUåÇóÕ:  " +
                Gaigon.attack + "\nñhå‰óÕ:  " + Gaigon.defense);
        if(bornflag == 1){
            bornflag = 0;
            btflag = 0;
            t3.stop();
            LevelUp(stage);
        }
        if(hpflag == 0){
            t3.stop();
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
                System.out.println("GAIGONÇÃÉåÉxÉãÇ™" + Gaigon.level +"Ç…è„Ç™Ç¡ÇΩ");
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
        System.out.println("GAIGONÇÕâAéºÇ»îlÇËÇÇµÇΩ");
        for(int i=0; i<3; i++){
            if(slime[i] == null) break;
            else if(slime[i].HP > 0){
                slime[i].defense = slime[i].defense - 1;
                System.out.println("ÉXÉâÉCÉÄÇÃñhå‰óÕÇ™â∫Ç™Ç¡ÇΩ");
            }
        }

        for(int i=0; i<3; i++){
            if(goblin[i] == null) break;
            else if(goblin[i].HP > 0){
                goblin[i].defense = goblin[i].defense - 1;
                System.out.println("ÉSÉuÉäÉìÇÃñhå‰óÕÇ™â∫Ç™Ç¡ÇΩ");
            }
        }
    }

    void Heal(){
        if(Gaigon.MP - 3 > 0){
            Gaigon.MP = Gaigon.MP - 3;
            System.out.println("GAIGONÇÕ Ç⁄Ç¢Ç› Çè•Ç¶ÇΩ");
            if(Gaigon.HP + 20 < Gaigon.MaxHP)
                Gaigon.HP = Gaigon.HP + 20;
            else Gaigon.HP = Gaigon.MaxHP;
        }
        else System.out.println("Ç⁄Ç¢Ç› Çè•Ç¶ÇÈÇ±Ç∆Ç™Ç≈Ç´Ç»Ç©Ç¡ÇΩ");
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
            System.out.println("ì¶Ç∞êÿÇÈÇ±Ç∆Ç™Ç≈Ç´ÇΩ");
        else {
            System.out.println("ì¶Ç∞êÿÇÈÇ±Ç∆Ç™Ç≈Ç´Ç»Ç©Ç¡ÇΩ");
        }
        return a;
    }

    void Attack(){
        System.out.println("GAIGONÇÃçUåÇÅI");
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
                    System.out.println("ÉXÉâÉCÉÄÇì|ÇµÇΩÅI");
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
                        System.out.println("ÉSÉuÉäÉìÇì|ÇµÇΩÅI");
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
                    System.out.println("ÉXÉâÉCÉÄÇÃçUåÇ");
                    if(Gaigon.HP - (slime[i].attack - Gaigon.defense) > 0)
                        Gaigon.HP = Gaigon.HP - (slime[i].attack - Gaigon.defense);
                    else {
                        Gaigon.HP = 0;
                        hpflag = 0;
                    }
                }
                else System.out.println("ÉXÉâÉCÉÄÇÃçUåÇÇÕìñÇΩÇÁÇ»Ç©Ç¡ÇΩ");
            }
        }

        for(int i=0; i<3; i++){
            if(goblin[i] == null) break;
            if(goblin[i].HP > 0){
                if(goblin[i].attack - Gaigon.defense > 0){
                    System.out.println("ÉSÉuÉäÉìÇÃçUåÇÅI");
                    if(Gaigon.HP - (goblin[i].attack - Gaigon.defense) > 0)
                        Gaigon.HP = Gaigon.HP - (goblin[i].attack - Gaigon.defense);
                    else {
                        Gaigon.HP = 0;
                        hpflag = 0;
                    }
                }
                else System.out.println("ÉSÉuÉäÉìÇÃçUåÇÇÕìñÇΩÇÁÇ»Ç©Ç¡ÇΩ");
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
        this.attack = 5;
        this.defense = 3;
        this.exp = 0;
        this.level = 1;
    }
}

class SlimeStatus{
    Random random = new Random();
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/slime.png");

    SlimeStatus(){
        this.name = "Slime";
        this.HP = 10;
        this.attack = 5;
        this.defense = 3;
        this.exp = random.nextInt(Gaigon.level * 5 + 1);
    }
}

class GoblinStatus{
    Random random = new Random();
    int HP, attack, defense, exp;
    String name;
    ImageView image = new ImageView("Image/goblin.png");

    GoblinStatus(){
        this.name = "Goblin";
        this.HP = 20;
        this.attack = 8;
        this.defense = 2;
        this.exp = random.nextInt(Gaigon.level * 5 + 1);
    }
}
}
