package view;

import model.InfoLabel;
import model.MODE;
import model.ModeChooser;

import model.RPGButton;
import model.RPGSubScene;
import model.exitButtonSubScene;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;
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

import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;;


public class ViewManager {

	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage menuStage;
	private Stage preViewMainStage;
	
	private final static int MENU_BUTTON_START_X =100;
	private final static int MENU_BUTTON_START_Y =150;
	
	private RPGSubScene creditsSubScene;
	private RPGSubScene creditsSubScene2;
	private RPGSubScene creditsSubScene3;
	private RPGSubScene modeChooserScene;
	private RPGSubScene scoreSubScene;
	private RPGSubScene helpSubScene;
	private RPGSubScene exitSubScene;
	private RPGSubScene waitSubScene;
	
	//tao noi luu tru subscene
	private RPGSubScene sceneToHidden;
	
	private final String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";
	private final String PANELEXIT = "model/resources/yellow_ribbon.png";
	private final String CHARACTOR = "model/resources/charactor.png";
	public static final String SPLASH_GIF ="model/gif/source.gif";
	
//	private static final int tile_width = 64, tile_height = 64;
//	private static final int ncols = 32, nrows = 20;
	
	//link anh
	private final String FONT_PATH = "src/model/resources/VCENTI.TTF";		// dung trong thong tin
	private final String FONT_PATH2 = "src/model/resources/VNI-Truck.ttf"; // dung trong cac nut
	private final String BUTTON= "-fx-background-color: transparent; -fx-background-image: url('model/resources/start.png');";
	private final String BUTTONEXITSUBSCENE= "-fx-background-color: transparent; -fx-background-image: url('model/resources/face_on_cross.png');";
	private final String BUTTON_SLIDER_RIGHT= "-fx-background-color: transparent; -fx-background-image: url('model/resources/arrow.png');";
	private final String BUTTON_SLIDER_LEFT= "-fx-background-color: transparent; -fx-background-image: url('model/resources/arrow2.png');";
	private final String YES_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resources/yes_button.png')";
	private final String NO_BUTTON = "-fx-background-color: transparent; -fx-background-image: url('model/resources/no_button.png')";
	
	
	//---- anh thanh vien
	private final String MEMBER1= "model/imageteam/linh.png";
	private final String MEMBER2= "model/imageteam/minh.png";
	private final String MEMBER3= "model/imageteam/ha.png";
	private final String MEMBER4= "model/imageteam/trang.png";
	private final String MEMBER5= "model/imageteam/vuong.png";
	
	private final String NUMBER1= "model/resources/number_1.png";
	
	List<RPGButton> menuButton;
	List<ModeChooser> modesList;
	
	private MODE chosenMode;
	
	public Stage getMainStage() {
		return preViewMainStage;
	}
	
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
		mainPane.getChildren().add(buttonNextToMenu());
	}

	private exitButtonSubScene buttonNextToMenu() {
		exitButtonSubScene startButton = new exitButtonSubScene(BUTTON_SLIDER_LEFT,60,50);
		startButton.setLayoutX(40);
		startButton.setLayoutY(30);
		
		startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startButton.setEffect(new DropShadow());
			}
		});
		
		startButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startButton.setEffect(null);
			}
		});
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				PreViewManager preViewManager = new PreViewManager();
				preViewManager.createNewGame(mainStage);
			}
		});
		return startButton;
	}
	//trien khai cac scene hidden
	private void showSubScene(RPGSubScene subScene) {
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
		createModeChooserSubScene();
		createCreditsSubScene();
		createScoreSubScene();
		createExitSubScene();
		createWaitSubScene();
		createHelpSubScene();
	}
	
	private void createModeChooserSubScene() {
		modeChooserScene = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(modeChooserScene);
		
		InfoLabel chooseModeLabel = new InfoLabel("SELECT MODE");
		chooseModeLabel.setLayoutX(110);
		chooseModeLabel.setLayoutY(40);
		modeChooserScene.getPane().getChildren().add(chooseModeLabel);
		modeChooserScene.getPane().getChildren().add(createButtonExitSubScene());
		modeChooserScene.getPane().getChildren().add(createModesToChoose());
		modeChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private void createCreditsSubScene(){
		creditsSubScene = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(creditsSubScene);
		
		createCreditsSubScene2();
		InfoLabel creditsLabel = new InfoLabel("GAME DEV");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		creditsSubScene.getPane().getChildren().add(creditsLabel);
		creditsSubScene.getPane().getChildren().add(createButtonExitSubScene());
		creditsSubScene.getPane().getChildren().add(createButtonNext(creditsSubScene2, BUTTON_SLIDER_RIGHT ));
		addinfoCredits(creditsSubScene,"Nguyen Thi Thuy Linh\n"+"\t20176802",MEMBER1,210,150);
		addinfoCredits(creditsSubScene,"Luong Duc Minh\n"+"\t20176821",MEMBER2,210,260);
	}
	
	private void createCreditsSubScene2(){
		creditsSubScene2 = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(creditsSubScene2);
		
		createCreditsSubScene3();
		InfoLabel creditsLabel = new InfoLabel("MENU DEV");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		creditsSubScene2.getPane().getChildren().add(creditsLabel);
		creditsSubScene2.getPane().getChildren().add(createButtonExitSubScene());
		//creditsSubScene2.getPane().getChildren().add(createButtonNext(creditsSubScene ,BUTTON_SLIDER_LEFT));
		creditsSubScene2.getPane().getChildren().add(createButtonNext(creditsSubScene3 ,BUTTON_SLIDER_RIGHT));
		
		addinfoCredits(creditsSubScene2,"Nguyen Thanh Ha\n"+"\t20176742",MEMBER3,210,150);
		addinfoCredits(creditsSubScene2,"Hoang Thi Thu Trang\n"+"\t20176891",MEMBER4,210,260);
	}
	private void createCreditsSubScene3(){
		creditsSubScene3 = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(creditsSubScene3);
		
		InfoLabel creditsLabel = new InfoLabel("TEAM MEMBER");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		creditsSubScene3.getPane().getChildren().add(creditsLabel);
		creditsSubScene3.getPane().getChildren().add(createButtonExitSubScene());
		creditsSubScene3.getPane().getChildren().add(createButtonNext(creditsSubScene ,BUTTON_SLIDER_LEFT));
		addinfoCredits(creditsSubScene3,"Nguyen Quoc Vuong\n"+"\t20176915",MEMBER5,210,150);
		
	}
	
	private void createExitSubScene() {
		exitSubScene = new RPGSubScene(PANELEXIT,630,329);
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
		waitSubScene = new RPGSubScene(SPLASH_GIF,400,600);
		waitSubScene.setLayoutX(1020);
		waitSubScene.setLayoutY(75);
		mainPane.getChildren().add(waitSubScene);
		showSubScene(waitSubScene);
	}
	
	private void createHelpSubScene() {
		helpSubScene = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(helpSubScene);
		
		InfoLabel creditsLabel = new InfoLabel("HOW TO PLAY");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		
		ImageView imageView = new ImageView(CHARACTOR);
		imageView.setX(400);
		imageView.setY(150);
		imageView.setFitHeight(118);
		imageView.setFitWidth(100);
		helpSubScene.getPane().getChildren().add(creditsLabel);
		helpSubScene.getPane().getChildren().add(createButtonExitSubScene());
		helpSubScene.getPane().getChildren().add(howToPlay());
		helpSubScene.getPane().getChildren().add(imageView);
		
	}
	
	private void createScoreSubScene() {
		scoreSubScene = new RPGSubScene(BACKGROUND_IMAGE,600,438);
		mainPane.getChildren().add(scoreSubScene);
		
		InfoLabel creditsLabel = new InfoLabel("HIGH SCORE");
		creditsLabel.setLayoutX(110);
		creditsLabel.setLayoutY(40);
		ImageView image = new ImageView(NUMBER1);
		image.setX(380);
		image.setY(120);
		scoreSubScene.getPane().getChildren().add(creditsLabel);
		scoreSubScene.getPane().getChildren().add(image);
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
                		"hết quái vật rồi tìm đường sang map khác. \n";
        Text text = new Text(status);
        text.setFont(Font.font ("Jamiro",FontWeight.BLACK, FontPosture.REGULAR, 19));
        text.setFill(Color.BLACK);
        text.setX(110);
        text.setY(110);
        box.getChildren().add(text);
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(110);
        return box;
    }
	
	// add thong tin vao credit
	private void addinfoCredits(RPGSubScene a,String str,String image,int x, int y ) {
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
	private HBox createModesToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		modesList = new ArrayList<>();
		for(MODE mode : MODE.values()) { // them 1 anh vao moi MODE
			ModeChooser modeToPick = new ModeChooser(mode);
			modesList.add(modeToPick);
			box.getChildren().add(modeToPick);
			//thiet lap hanh dong click mouse
			modeToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for(ModeChooser mode: modesList) {
						//neu click vao 1 trong cac modes, moi mode deu thiet lap thanh flase
						//co nghia la no khong duoc chon
						mode.setIscircleChosen(false);
					}
					modeToPick.setIscircleChosen(true);
					chosenMode = modeToPick.getMode();
				}
			});
		}
		box.setLayoutX(300-(118*2));
		box.setLayoutY(140);
		return box;
	}
	
	private exitButtonSubScene createButtonToStart() {
		exitButtonSubScene startButton = new exitButtonSubScene(BUTTON,100,53);
		startButton.setLayoutX(400);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (chosenMode != null) {
					PreGameViewManager preNewGame = new PreGameViewManager();
					preNewGame.createPreNewGame(mainStage, chosenMode);
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
	
	private exitButtonSubScene createButtonNext( RPGSubScene a, String image) {
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
	private void addMenuButton(RPGButton button) {
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
		RPGButton starButton = new RPGButton("PLAY",25);
		addMenuButton(starButton);
		
		starButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(modeChooserScene);
			}
		});
	}
	
	private void createScoreButton() {
		RPGButton scoreButton = new RPGButton("SCORES",25);
		addMenuButton(scoreButton);
		
		scoreButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
			}
		});
	}
	
	private void createHelpButton() {
		RPGButton helpButton = new RPGButton("HELP",25);
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
		});
	}
	
	private void createCreditsButton() {
		RPGButton creditsButton = new RPGButton("CREDITS",25);
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
		RPGButton exitButton = new RPGButton("EXIT",25);
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
		Image backgroundImage = new Image("view/resources/background3.png", 1024,768,false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
	}
	
//========================= Logo =========================
//**************************************************************
//==============================================================
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/logo.png");
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
