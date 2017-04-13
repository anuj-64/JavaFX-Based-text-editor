import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.text.html.Option;

import com.sun.javafx.collections.SetAdapterChange;

import javafx.collections.SetChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Findclass {
	private static Stage primarystage;
	private static AnchorPane pane;
	private static TabPane tabPane;
	private static JTextPane textPane = new JTextPane();
	private static String stringToFind = "";
	private static int count = -1;
	private static String replaceString = "";
	public static void set(Stage temp, TabPane tabpane) {
		primarystage = temp;
		tabPane = tabpane;
	}

	public static void FindStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Find.fxml"));
		pane = (AnchorPane) loader.load();
		BorderPane borderPane = (BorderPane) primarystage.getScene().getRoot();
		borderPane.setBottom(pane);
		pane.prefWidthProperty().bind(borderPane.widthProperty());
		/*
		 * Scene scene=new Scene(pane); Stage stage=new Stage();
		 * stage.setScene(scene);
		 * stage.initModality(Modality.APPLICATION_MODAL);
		 * stage.initOwner(primarystage); stage.show();
		 */
	}

	public static void showDialog(String ss) {
		new AnchorPane().getChildren().add(new Label(ss));
	}

	public static void getStatus(String buttonName, String ToFind, JTextPane jPane,String ReplaceWith){
		if(stringToFind.equals(ToFind) && textPane.getText().equals(jPane.getText())){
			if(buttonName.equals("find")){
				count++;
				Find(textPane,stringToFind);
			}
			if(buttonName.equals("findPrevious")){
				if(count>0)
					count--;
				Find(textPane,stringToFind);
			}
			if(buttonName.equals("findAll"))
				FindAll(textPane,++count,stringToFind);
			if(buttonName.equals("replace"))
				Replace(textPane,count,stringToFind,ReplaceWith);
			if(buttonName.equals("replaceAll"))
				ReplaceAll(textPane,count,stringToFind,ReplaceWith);
		}else{
			textPane = jPane;
			stringToFind = ToFind;
			replaceString = ReplaceWith;
			count = -1;
			getStatus(buttonName, ToFind, jPane, ReplaceWith);
		}
	}

	public static void Find(JTextPane jTextPane, String stringToFind) {
		String hostText = jTextPane.getText();
		stringToFind = stringToFind;
		int countTill = hostText.indexOf(stringToFind), subNum = 0;
		while(subNum < count && countTill >= 0){
			subNum++;
			countTill = hostText.indexOf(stringToFind, countTill+1);
		}
		if(countTill >= 0){
			String subString = hostText.substring(0, countTill);
			int compansate = subString.length() - subString.replaceAll("[\n]","").length();
			System.out.println(compansate+" compansate");
			textPane.setSelectionStart(countTill-compansate);
			textPane.setSelectionEnd(countTill+stringToFind.length()-compansate);
		}else{
			System.out.println("its Over");
			count = -1;
			countTill = 0;
			subNum = 0;
		}
		System.out.println(countTill+" "+count+" "+subNum);
	}
	public static void FindAll(JTextPane textPane, int count, String stringToFind) {
		String hostText = textPane.getText();
		stringToFind = stringToFind;
		int countTill = hostText.indexOf(stringToFind);
		while(countTill >= 0){
			String subString = hostText.substring(0, countTill);
			int compansate = subString.length() - subString.replaceAll("[\n]","").length();
			textPane.setSelectionStart(countTill-compansate);
			textPane.setSelectionEnd(countTill+stringToFind.length()-compansate);
			textPane.setSelectionColor(Color.YELLOW);
			countTill = hostText.indexOf(stringToFind, countTill+1);
		}
	}

	public static void Replace(JTextPane textPane, int count, String stringToFind, String replaceWith) {
		String hostText = textPane.getText();
		stringToFind = stringToFind;
		int countTill = hostText.indexOf(stringToFind), subNum = 0;
		while(subNum < count && countTill >= 0){
			subNum++;
			countTill = hostText.indexOf(stringToFind, countTill+1);
		}
		if(countTill >= 0){
			String subString = hostText.substring(0, countTill);
			int compansate = subString.length() - subString.replaceAll("[\n]","").length();
			System.out.println(compansate+" compansate");
			textPane.setSelectionStart(countTill-compansate);
			textPane.setSelectionEnd(countTill+stringToFind.length()-compansate);
			if(textPane.getSelectedText().equals(stringToFind)){
				System.out.println(textPane.getSelectedText());
				textPane.replaceSelection(replaceWith);
			}
		}
	}

	public static void ReplaceAll(JTextPane textPane, int count, String stringToFind, String replaceWith) {
		String hostText = textPane.getText();
		stringToFind = stringToFind;
		int countTill = hostText.indexOf(stringToFind);
		while(countTill >= 0){
			String subString = hostText.substring(0, countTill);
			int compansate = subString.length() - subString.replaceAll("[\n]","").length();
			textPane.setSelectionStart(countTill-compansate);
			textPane.setSelectionEnd(countTill+stringToFind.length()-compansate);
			textPane.replaceSelection(replaceWith);
			countTill = hostText.indexOf(stringToFind, countTill+1);
		}
	}
}
