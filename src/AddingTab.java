import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.undo.UndoManager;

import com.sun.javafx.text.TextLine;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddingTab {
private static VBox verticalbox;
private static TabPane tabpane;
private static int tabnumber;
public int number=2;
public static JTextPane textpane;
public static Stage primaryStage;
public static void setAddingTab(TabPane temp,int num){
	tabpane=temp;
	tabnumber=num;
}

public static void setStage(Stage stage){
	primaryStage = stage;
}
public static void addnewtab() throws IOException{
	Tab tab=new Tab();
	tab.setText("Untitled "+tabnumber);
	tabnumber++;
	FXMLLoader loader=new FXMLLoader();
	loader.setLocation(Main.class.getResource("TextArea.fxml"));
	AnchorPane pane=(AnchorPane)loader.load();
	Label label=(Label)pane.getChildren().get(0);
	label.setVisible(false);
	BorderPane bpane=(BorderPane)pane.getChildren().get(1);
	//----------------------------------------------Swing area-------------------------------------
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.getAllFonts();


 StyleContext context=new StyleContext();
 Style st1=context.addStyle("st1",null);
 StyleConstants.setForeground(st1, Color.BLACK);
 Style st2=context.addStyle("st2",null);
StyleConstants.setForeground(st2,new Color(196, 39, 183));

    textpane=new JTextPane(new Keywordcoloring(st1, st2));

    textpane.setFont(new Font("Consolas",Font.PLAIN,14));
    JScrollPane jpane=new JScrollPane(textpane);
    TextLineNumber numb=new TextLineNumber(textpane);
    jpane.setRowHeaderView(numb);
	SwingNode node=new SwingNode();
	node.setContent(jpane);
	bpane.setCenter(node);
	//------------------------------------------------Swing area completed--------------------------------------
	tab.setContent(pane);
	tabpane.getTabs().add(tab);
	SingleSelectionModel<Tab> selectionmodel=tabpane.getSelectionModel();
	selectionmodel.select(tab);
    node.requestFocus();

}

public static void issaved() throws InterruptedException{
	Tab tab=tabpane.getSelectionModel().getSelectedItem();
	AnchorPane pane=(AnchorPane)tab.getContent();
	Label label=(Label) pane.getChildren().get(0);
	label.setText("Saved...");
	label.setVisible(true);

}
public static void line(){

	Tab tab=tabpane.getSelectionModel().getSelectedItem();
	AnchorPane pane=(AnchorPane)tab.getContent();
	Label label=(Label) pane.getChildren().get(0);
	label.setText("");

}
public static void cut(){
	textpane.cut();
}
public static void copy(){
	textpane.copy();
}
public static void paste(){
	textpane.paste();

}

public static String selectedtext(){
	return textpane.getSelectedText();
}
}
