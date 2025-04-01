package controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Entrenador;

public class LoginController {
	Entrenador entrenador = new Entrenador("luisre", "123456", 1000);
	public Stage stage;
	public boolean sonido = false;
	public MediaPlayer mediaPlayer;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgSonido;

    @FXML
    private Label lblError;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    void comprobarLoguin(ActionEvent event) {
    	if(txtUsuario.getText().isEmpty()) {
    		lblError.setText("Error: Insertar nombre usuario");
    		lblError.setVisible(true);
    		//JOptionPane.showMessageDialog(null, "Error: escribe nombre de usuario");
    	}else if (txtPassword.getText().isEmpty()) {
    		lblError.setText("Error: Insertar password");
    		lblError.setVisible(true);
    	}else {
    		String usuario = txtUsuario.getText();
    		String pass = txtPassword.getText();
    		
    		if(entrenador.getUsuario().equals(usuario)) {
    			
    			if(entrenador.getPass().equals(pass)) {
    				lblError.setText("Correcto");
    	    		lblError.setVisible(true);
    	    		
    	    		abrirPantallaMenu(entrenador);
    			}else {
    				lblError.setText("Error: contraseña incorrecta");
    	    		lblError.setVisible(true);
    			}
    		}else {
    			lblError.setText("Error: usuario no existe");
        		lblError.setVisible(true);
    		}
    	}
    }

    @FXML
    void registrarUsuario(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    public void setStage(Stage primaryStage) {
    	stage = primaryStage;
    }

    private void abrirPantallaMenu(Entrenador ent) {
    	try {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/menu.fxml"));
    	     Parent root = loader.load();
    	     
    	     MenuController  menuController = loader.getController();
    	     
    	     Scene scene = new Scene(root);
    	     Stage stage = new Stage();
    	     
    	        
    	     stage.setTitle("Menú principal");
    	     stage.setScene(scene);
    	     menuController.init(ent,stage, this);
    	     
    	     stage.show();
    	     
    	     this.stage.close();
    	     
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

	public void show() {
		stage.show();
		lblError.setVisible(false);
		txtUsuario.setText("");
		txtPassword.setText("");
		
	}
	
	public void sonido() {
		if(!this.sonido) {
			mediaPlayer.play();
			
			imgSonido.setImage(new Image(new File("./img/sonidoact.png").toURI().toString()));
			this.sonido = true;
		}else {
			mediaPlayer.pause();
			this.sonido=false;
			imgSonido.setImage(new Image(new File("./img/sonidodes.png").toURI().toString()));
			
		}
	}
	
	@FXML
    void activarDesactivarSonido(MouseEvent event) {
		sonido();
    }
	
	@FXML
	public void initialize() {
		String rutaSonido = "./sonidos/Opening.mp3";
		Media sound = new Media(new File(rutaSonido).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		sonido();
	}
}
