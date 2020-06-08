package view;

import model.infoLabel;
import model.SHIP;
import model.ShipPicker;
import view.PreViewManager;

import model.SpaceRunnerButton;
import model.SpaceRunnerSubScene;
import model.exitButtonSubScene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.animation.StrokeTransition;
import javafx.application.Application; 

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group; 
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;;


public class ViewManager {

	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage menuStage;
	
	private final static int MENU_BUTTON_START_X =100;
	private final static int MENU_BUTTON_START_Y =150;
	
	private SpaceRunnerSubScene creditsSubScene;
	private SpaceRunnerSubScene creditsSubScene2;
	private SpaceRunnerSubScene shipChooserScene;
	private SpaceRunnerSubScene scoreSubScene;
	private SpaceRunnerSubScene helpSubScene;
	private SpaceRunnerSubScene exitSubScene;
	private SpaceRunnerSubScene waitSubScene;
	
	//tao noi luu tru subscene
	private SpaceRunnerSubScene sceneToHidden;
	
	private final String BACKGROUND_IMAGE = "model/resource/yellow_panel.png";
	private final String PANELEXIT = "model/resource/yellow_ribbon.png";
	private final String GIF = "model/resource/this.gif";
	public static final String SPLASH_GIF ="model/resource/source.gif";
	public static final String SPLASH_GIF1 ="model/resource/this.gif";
	
	//link anh
	private final String FONT_PATH = "src/model/resource/VCENTI.TTF";		// dung trong thong tin
	private final String FONT_PATH2 = "src/model/resource/VNI-Truck.ttf"; // dung trong cac nut
	private final String BUTTON= "-fx-background-color: transparent; -fx-background-image: url('model/resource/start.png');";
	private final String BUTTONEXITSUBSCENE= "-fx-background-color: transparent; -fx-background-image: url('model/resource/face_on_cross.png');";
	private final String BUTTON_SLIDER_RIGHT= "-fx-background-color: transparent; -fx-background-image: url('model/resource/arrow.png');";
	private final String BUTTON_SLIDER_LEFT= "-fx-background-color: transparent; -fx-background-image: url('model/resource/arrow2.png');";
	private final String YES_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resource/yes_button.png')";
	private final String NO_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resource/no_button.png')";
	
	
	//---- anh thanh vien
	private final String MEMBER1= "model/imageteam/linh.png";
	private final String MEMBER2= "model/imageteam/minh.png";
	private final String MEMBER3= "model/imageteam/ha.png";
	private final String MEMBER4= "model/imageteam/trang.png";
	
	//link nhac
	private final String MUSIC = "src/model/resource/deepside.mp3";
	//tao mot lít luu tru cac button
	List<SpaceRunnerButton> menuButton;
	List<ShipPicker> shipsList;
	
	private SHIP choosenShip;
	
	public ViewManager() {
		menuButton = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScene();
		createButtons();
		createBackground();
		createLogo();
		//createMusic();
	}

//================ Nhạc ===================================
//**************************************************************
//==============================================================
	private void createMusic() {
	       Media sound = new Media(new File(MUSIC).toURI().toString());
	       MediaPlayer mediaPlayer = new MediaPlayer(sound);
	       mediaPlayer.play();
	}
	
	//trien khai cac scene hidden
	private void showSubScene(SpaceRunnerSubScene subScene) {
		if(sceneToHidden != null && sceneToHidden != subScene && sceneToHidden.isHidden() == false) {
			sceneToHidden.moveSubScene();
			subScene.moveSubScene();
		}else {
			subScene.moveSubScene();
		}
		sceneToHidden = subScene;
	}

//================ Sub Scene ===================================
//**************************************************************
//==============================================================
	//khoi tao sub scene
	private void createSubScene() {
		helpSubScene = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(helpSubScene);
		
		scoreSubScene = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(scoreSubScene);
		
		
		//goi pthuc tao shipchoosen sub
		createShipChooserSubScene();
		createCreditsSubScene();
		createCreditsSubScene2();
		createExitSubScene();
		createWaitSubScene();
		createHelpSubScene();
	}
	
	private void createShipChooserSubScene() {
		shipChooserScene = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(shipChooserScene);
		
		infoLabel chooseShipLabel = new infoLabel("CHOOSE YOUR SHIP");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(40);
		shipChooserScene.getPane().getChildren().add(chooseShipLabel);
		shipChooserScene.getPane().getChildren().add(createButtonExitSubScene());
		shipChooserScene.getPane().getChildren().add(createShipsToChoose());
		shipChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private void createCreditsSubScene(){
		creditsSubScene = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(creditsSubScene);
		
		createCreditsSubScene2();
		infoLabel creditsLabel = new infoLabel("TEAM MEMBER");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		creditsSubScene.getPane().getChildren().add(creditsLabel);
		creditsSubScene.getPane().getChildren().add(createButtonExitSubScene());
		creditsSubScene.getPane().getChildren().add(createButtonNext(creditsSubScene2, BUTTON_SLIDER_RIGHT ));
		addinfoCredits(creditsSubScene,"Nguyen Thi Thuy Linh\n"+"\t20176802",MEMBER1,210,150);
		addinfoCredits(creditsSubScene,"Luong Duc Minh\n"+"\t20176821",MEMBER2,210,260);
	}
	
	private void createCreditsSubScene2(){
		creditsSubScene2 = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(creditsSubScene2);
		
		infoLabel creditsLabel = new infoLabel("TEAM MEMBER");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		creditsSubScene2.getPane().getChildren().add(creditsLabel);
		creditsSubScene2.getPane().getChildren().add(createButtonExitSubScene());
		creditsSubScene2.getPane().getChildren().add(createButtonNext(creditsSubScene ,BUTTON_SLIDER_LEFT));
		addinfoCredits(creditsSubScene2,"Nguyen Thanh Ha\n"+"\t20176742",MEMBER3,210,150);
		addinfoCredits(creditsSubScene2,"Hoang Thi Thu Trang\n"+"\t20176891",MEMBER4,210,260);
	}
	
	private void createExitSubScene() {
		exitSubScene = new SpaceRunnerSubScene(PANELEXIT,630,329);
		exitSubScene.setLayoutX(1000);
		exitSubScene.setLayoutY(250);
		mainPane.getChildren().add(exitSubScene);
		
		Text label = new Text("Do you want \n"+"     to exit?");
		try {
			label.setFont(Font.loadFont(new FileInputStream(FONT_PATH2), 35));
		}catch(FileNotFoundException e) {
			label.setFont(Font.font("Viettay Normal", 23));
		}
		label.setLayoutX(220);
		label.setLayoutY(110);
		exitSubScene.getPane().getChildren().add(label);
		
		exitButtonSubScene yes_button = new  exitButtonSubScene(YES_BUTTON,75,45);
		yes_button.setLayoutX(250);
		yes_button.setLayoutY(170);
		
		exitButtonSubScene no_button = new  exitButtonSubScene(NO_BUTTON,70,50);
		no_button.setLayoutX(340);
		no_button.setLayoutY(170);
		
		exitSubScene.getPane().getChildren().add(yes_button);
		exitSubScene.getPane().getChildren().add(no_button);
		
		yes_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				}
		});
		no_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(waitSubScene);
				}
		});
	}
	
	private void createWaitSubScene() {
		waitSubScene = new SpaceRunnerSubScene(SPLASH_GIF,400,600);
		waitSubScene.setLayoutX(1020);
		waitSubScene.setLayoutY(75);
		mainPane.getChildren().add(waitSubScene);

	}
	
	private void createHelpSubScene() {
		helpSubScene = new SpaceRunnerSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(helpSubScene);
		
		infoLabel creditsLabel = new infoLabel("HOW TO PLAY");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		helpSubScene.getPane().getChildren().add(creditsLabel);
		helpSubScene.getPane().getChildren().add(createButtonExitSubScene());
		helpSubScene.getPane().getChildren().add(howToPlay());
	}


//======================= Chèn thông tin =======================
//**************************************************************
//==============================================================
	//add thong tin vao help
	private HBox howToPlay() {
        HBox box = new HBox();
        box.setSpacing(20);
       
        String status = "- Sử dụng mũi tên để điều khiển nhân vật: \n" +
                        "        👈 Di chuyển sang Trái. \n" +
                        "        👉 Di chuyển sang Phải. \n" +
                        "        👆 Di chuyển lên Trên. \n" +
                        "        👇 Di chuyển xuống Dưới.\n" +
                        "\n- SPACE: Tấn công quái vật. \n" +
                        "\n- Để qua màn người chơi phải tiêu diệt\n" +
                        "hết quái vật và thu thập hết vật phẩm. \n";
        Text text = new Text(status);
        text.setFont(Font.font ("Jamiro",FontWeight.BLACK, FontPosture.REGULAR, 20));
        text.setFill(Color.BLACK);
        text.setX(110);
        text.setY(110);
        box.getChildren().add(text);
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(110);
        return box;
    }
	
	// add thong tin vao credit
	private void addinfoCredits(SpaceRunnerSubScene a,String str,String image,int x, int y ) {
		ImageView logo = new ImageView(image);
		logo.setLayoutX(x-150);
		logo.setLayoutY(y-50);
		Text string = new Text(str);
		
		try {
			string.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
		}catch(FileNotFoundException e) {
			// neu k tim duoc font file, tao 1 font binh thuong
			string.setFont(Font.font("Vedana", 23));
		}
		string.setStroke(Color.BLACK); 
		string.setX(x); 
		string.setY(y);		
		// hieu ung do bong
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		
		a.getPane().getChildren().add(string);
		a.getPane().getChildren().add(logo);
		
	}
	
//================== Button in sub scene========================
//**************************************************************
//==============================================================
	//tao nut de chon
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		shipsList = new ArrayList<>();
		for(SHIP ship : SHIP.values()) { // them 1 anh vao moi ship
			ShipPicker shipToPick = new ShipPicker(ship);
			shipsList.add(shipToPick);
			box.getChildren().add(shipToPick);
			//thiet lap hanh dong click mouse
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for(ShipPicker ship: shipsList) {
						//neu click vao 1 trong cac ships, moi ship deu thiet lap thanh flase
						//co nghia la no khong duoc chon
						ship.setIsCircleChoosen(false);
					}
					shipToPick.setIsCircleChoosen(true);
					choosenShip = shipToPick.getShip();
				}
			});
		}
		box.setLayoutX(300-(118*2));
		box.setLayoutY(130);
		return box;
	}
	
	private exitButtonSubScene createButtonToStart() {
		exitButtonSubScene startButton = new exitButtonSubScene(BUTTON,100,53);
		startButton.setLayoutX(400);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (choosenShip != null) {
					PreGameViewManager preNewGame = new PreGameViewManager();
					preNewGame.createPreNewGame(mainStage, choosenShip);
				}
			}
		});
		return startButton;
	}

	private exitButtonSubScene createButtonExitSubScene() {
		exitButtonSubScene exitButtonSubScene = new exitButtonSubScene(BUTTONEXITSUBSCENE,50,50);
		exitButtonSubScene.setLayoutX(30);
		exitButtonSubScene.setLayoutY(10);
		exitButtonSubScene.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(waitSubScene);
			}
		});
		return exitButtonSubScene;
	}
	
	private exitButtonSubScene createButtonNext( SpaceRunnerSubScene a, String image) {
		exitButtonSubScene exitButtonSubScene = new exitButtonSubScene(image,60,50);
		exitButtonSubScene.setLayoutX(450);
		exitButtonSubScene.setLayoutY(300);
			exitButtonSubScene.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					showSubScene(a);
				}
			});

		return exitButtonSubScene;
	}

	
//==================== menu ====================================
//**************************************************************
//==============================================================
	/*
	public Stage getMainStage() {
		return mainStage;
	}
	*/
	private void addMenuButton(SpaceRunnerButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButton.size() * 100);
		menuButton.add(button);
		mainPane.getChildren().add(button);
	}
	
	// tao button
	private void createButtons() {
		createStarButton();
		createScoreButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
//================ Button in main scene =======================
//**************************************************************
//==============================================================
	private void createStarButton() {
		SpaceRunnerButton starButton = new SpaceRunnerButton("PLAY",23);
		addMenuButton(starButton);
		
		starButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(shipChooserScene);
			}
		});
	}
	
	private void createScoreButton() {
		SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORES",23);
		addMenuButton(scoreButton);
		
		scoreButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
			}
		});
	}
	
	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP",23);
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
		});
	}
	
	private void createCreditsButton() {
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS",23);
		addMenuButton(creditsButton);
		
		// ket noi nut credit voi credit scene
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);
			}
		});
	}
	
	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT",23);
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(exitSubScene);
			}
		});
	}
	
//========================= back ground=========================
//**************************************************************
//==============================================================
	// set background
	private void createBackground() {
		// doc duoc anh nen
		Image backgroundImage = new Image("view/resource/background3.png", 1024,768,false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
	
//========================= Logo =========================
//**************************************************************
//==============================================================
	private void createLogo() {
		ImageView logo = new ImageView("view/resource/logo.png");
		logo.setFitHeight(120);
		logo.setFitWidth(500);
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		
		mainPane.getChildren().add(logo);
	}
	
	
	public void createNewView(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		mainStage.show();
	}
}
