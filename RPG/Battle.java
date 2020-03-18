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


public class Battle extends Application{

    int levelExp[] = new int[51];
    int monsterBorn, bornflag, atflag, hpflag = 1;
    int magicflag, tokugiflag, runflag;
    Random random = new Random();
    HBox monsterImage = new HBox();
    TextArea text = new TextArea();
    TextArea chara1text = new TextArea();
    Group screen = new Group();
    Group magicCommand = new Group();
    Group tokugiCommand = new Group();

    Image gobIma = new Image("Image/goblin.png");
    Image slaIma = new Image("Image/slime.png");
    ImageView attack_command = new ImageView("Image/attack_command.png");
    ImageView magic_command = new ImageView("Image/magic_command.png");
    ImageView tokugi_command = new ImageView("Image/tokugi_command.png");
    ImageView run_away = new ImageView("Image/run_away.png");
    ImageView all_back = new ImageView("Image/Allback.png");
    ImageView battlescreen = new ImageView("Image/back.png");

    ImageView magic_back= new ImageView("Image/command_back_magic.png");
    ImageView tokugi_back = new ImageView("Image/command_back_tokugi.png");
    ImageView inshitsu = new ImageView("Image/inshitsu.png");
    ImageView heal = new ImageView ("Image/boimi.png");
    ImageView win = new ImageView("Image/Win.png");
    ImageView lose = new ImageView("Image/Lose.png");

    Status Gaigon = new Status("GAIGON");  //public
    GoblinStatus[] goblin =new GoblinStatus[3];
    SlimeStatus[] slime = new SlimeStatus[3];

    public void start(Stage stage)throws Exception{

        stage.setTitle("Battle");
        stage.setWidth(1000);
        stage.setHeight(600);

        Group command = new Group();
        AnchorPane screen_pane = new AnchorPane();


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
        win.setX(0);
        win.setY(0);
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

        for (int i=2; i<=50; i++){
            levelExp[i] = i*i;
        }


        Timeline t = new Timeline(
                new KeyFrame(Duration.millis(10.0),e -> System(e)));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

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
                System.out.println("ÉSÉuÉäÉìÇ™åªÇÍÇΩ!");

            case 2:
                goblin[1] = new GoblinStatus();
                monsterImage.getChildren().addAll(goblin[1].image);
                System.out.println("ÉSÉuÉäÉìÇ™åªÇÍÇΩ!");

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
        BattleScene.setOnMousePressed(event -> Command(event));
        stage.setScene(BattleScene);
        stage.show();


    }

    void System(ActionEvent e){
        chara1text.setText(Gaigon.name + "    Level:  " + Gaigon.level + "\nHP:  " + Gaigon.HP +
                "/" + Gaigon.MaxHP +"\nMP:  " + Gaigon.MP + "/" + Gaigon.MaxMP + "\nçUåÇóÕ:  " +
                Gaigon.attack + "\nñhå‰óÕ:  " + Gaigon.defense);
        if(bornflag == 1){
            screen.getChildren().addAll(win);
            bornflag = 0;
            LevelUp();
        }
        if(hpflag == 0){
            screen.getChildren().addAll(lose);
            hpflag = 1;
        }
    }

    void LevelUp(){
        while(true){
            if(levelExp[Gaigon.level + 1] - Gaigon.exp < 0){
                Gaigon.exp = Gaigon.exp - levelExp[Gaigon.level + 1];
                Gaigon.MaxHP = Gaigon.MaxHP + levelExp[Gaigon.level + 1];
                Gaigon.MaxMP = Gaigon.MaxMP + levelExp[Gaigon.level + 1];
                Gaigon.attack = Gaigon.attack + levelExp[Gaigon.level + 1] / 2;
                Gaigon.defense = Gaigon.defense + levelExp[Gaigon.level + 1] / 2;
                Gaigon.level++;
                System.out.println("GAIGONÇÃÉåÉxÉãÇ™" + Gaigon.level +"Ç…è„Ç™Ç¡ÇΩÅI");
            }
            else break;
        }
    }

    void Command(MouseEvent e){

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
        System.out.println("GAIGONÇÃâAéºÇ»îlÇË");
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
            System.out.println("GAIGONÇÕÉqÅ[ÉãÇè•Ç¶ÇΩ");
            if(Gaigon.HP + 20 < Gaigon.MaxHP)
                Gaigon.HP = Gaigon.HP + 20;
            else Gaigon.HP = Gaigon.MaxHP;
        }
        else System.out.println("ÉqÅ[ÉãÇè•Ç¶ÇÈÇ±Ç∆Ç™Ç≈Ç´Ç»Ç©Ç¡ÇΩ");
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
            System.out.println("ì¶Ç∞êÿÇÍÇΩ");
        else {
            System.out.println("ì¶Ç∞êÿÇÍÇ»Ç©Ç¡ÇΩ");
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
                    System.out.println("ÉXÉâÉCÉÄÇÃçUåÇÅI");
                    //wait(1);
                    if(Gaigon.HP - (slime[i].attack - Gaigon.defense) > 0)
                        Gaigon.HP = Gaigon.HP - (slime[i].attack - Gaigon.defense);
                    else {
                        Gaigon.HP = 0;
                        hpflag = 0;
                    }
                }
                else System.out.println("ÉXÉâÉCÉÄÇÃçUåÇÇÕÇ´Ç©Ç»Ç©Ç¡ÇΩÅI");
            }
        }

        for(int i=0; i<3; i++){
            if(goblin[i] == null) break;
            if(goblin[i].HP > 0){
                if(goblin[i].attack - Gaigon.defense > 0){
                    System.out.println("ÉSÉuÉäÉìÇÃçUåÇÅI");
                    //wait(1);
                    if(Gaigon.HP - (goblin[i].attack - Gaigon.defense) > 0)
                        Gaigon.HP = Gaigon.HP - (goblin[i].attack - Gaigon.defense);
                    else {
                        Gaigon.HP = 0;
                        hpflag = 0;
                    }
                }
                else System.out.println("ÉSÉuÉäÉìÇÃçUåÇÇÕÇ´Ç©Ç»Ç©Ç¡ÇΩÅI");
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
