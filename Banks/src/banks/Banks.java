/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.application.Application;
import javax.swing.JComboBox;

/**
 *
 * @author User
 */
public class Banks extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                
                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new Banks();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                applet.start();
            }
        });
       
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
                
            }
        });
    }
    
    private void createScene() {
        

        	GridPane grid = new GridPane();
grid.setMinSize(300, 300);
grid.setPadding(new Insets(40, 40, 10, 60));
grid.setVgap(5);
grid.setHgap(5);
 
        Text bank = new Text("Bank Name:");
        Text city = new Text("City:");
grid.add(bank, 2, 3);
TextField text = new TextField();
grid.add(text, 2, 4);
grid.add(city, 2, 5);
TextField text2 = new TextField();
text2.setPrefColumnCount(10);
grid.add(text2, 2, 6);


        Button btn = new Button();
        btn.setText("Enter");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println(""+text.getText()+"\n"+text2.getText());
                try{
                PlaceInfo p = new PlaceInfo(text.getText(),text2.getText());
 
                }
                catch(Exception e){ 
                }
            }
        });
        
        
grid.add(btn, 2, 8); 
fxContainer.setScene(new Scene(grid));
	fxContainer.show();
        
        
    }
    
}
