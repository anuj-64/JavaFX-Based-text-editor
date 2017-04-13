import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

import javafx.embed.swing.SwingNode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileHandling {
private static File file;
private static TabPane tabpane;
private static Stage primaryStage;
public static void setparameter(File temp,TabPane tp ){
	file=temp;
	tabpane=tp;
}

public static void setStage(Stage stage){
	primaryStage = stage;
}
public static void opentab() throws IOException{
		if (file != null) {
			AddingTab.setStage(primaryStage);
			AddingTab.addnewtab();
			Tab tab = tabpane.getSelectionModel().getSelectedItem();
			AnchorPane anchPane = (AnchorPane) tab.getContent();
			BorderPane bPane = (BorderPane) anchPane.getChildren().get(1);
			SwingNode swingNode = (SwingNode) bPane.getChildren().get(0);
			JScrollPane jScrollPane = (JScrollPane) swingNode.getContent();
			JViewport jViewport = jScrollPane.getViewport();
			JTextPane jTextPane = (JTextPane) jViewport.getView();
			Scanner in = new Scanner(file);
			String textString = "";
			while (in.hasNextLine()) {
				textString += in.nextLine() + "\n";
			}
			jTextPane.setText(textString);
			tab.setText(file.getName());
			tab.setUserData(file);
		}else{}
}

public static void SaveAs(){
	/*
	 * Traverse to jtextPane in Hierarchy
	 */

	Tab tab = tabpane.getSelectionModel().getSelectedItem();
	AnchorPane anchPane = (AnchorPane) tab.getContent();
	BorderPane bPane = (BorderPane) anchPane.getChildren().get(1);
	SwingNode swingNode = (SwingNode) bPane.getChildren().get(0);
	JScrollPane jScrollPane = (JScrollPane) swingNode.getContent();
	JViewport jViewport = jScrollPane.getViewport();
	JTextPane jTextPane = (JTextPane) jViewport.getView();

	/*
	 * Choose Desination for file And
	 * Set Extention filter
	 */

	FileChooser fileChooser = new FileChooser();
	ExtensionFilter[] extensionFilter = new ExtensionFilter[5];
	extensionFilter[0] = new ExtensionFilter("java", "*.java");
	extensionFilter[1] = new ExtensionFilter("txt", "*.txt");

	extensionFilter[2] = new ExtensionFilter("html", "*.html");
	extensionFilter[3] = new ExtensionFilter("C++", "*.cpp");
	extensionFilter[4] = new ExtensionFilter("C", "*.c");

	fileChooser.getExtensionFilters().addAll(extensionFilter);
	File file = fileChooser.showSaveDialog(primaryStage);

	/*
	 * Write the file on disk
	 *
	 */
	if (file != null) {
		try{
		FileWriter fileWriter = new FileWriter(file);
		//System.out.println(jTextPane.getText());
		fileWriter.write(jTextPane.getText());
		fileWriter.close();
		tab.setText(file.getName());
		tab.setUserData(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

public static void Save(){
	/*
	 * Traverse to jtextPane in Hierarchy
	 */

	Tab tab = tabpane.getSelectionModel().getSelectedItem();
	AnchorPane anchPane = (AnchorPane) tab.getContent();
	BorderPane bPane = (BorderPane) anchPane.getChildren().get(1);
	SwingNode swingNode = (SwingNode) bPane.getChildren().get(0);
	JScrollPane jScrollPane = (JScrollPane) swingNode.getContent();
	JViewport jViewport = jScrollPane.getViewport();
	JTextPane jTextPane = (JTextPane) jViewport.getView();

	File file = (File) tab.getUserData();

	if (file != null) {
		try{
		FileWriter fileWriter = new FileWriter(file);
		//System.out.println(jTextPane.getText());
		fileWriter.write(jTextPane.getText());
		fileWriter.close();
		tab.setText(file.getName());
		tab.setUserData(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	else{
		SaveAs();
	}

}

public static File open(){
	FileChooser filechooser=new FileChooser();

	ExtensionFilter[] extensionFilter = new ExtensionFilter[5];
	extensionFilter[0] = new ExtensionFilter("txt", "*.txt");
	extensionFilter[1] = new ExtensionFilter("java", "*.java");
	extensionFilter[2] = new ExtensionFilter("html", "*.html");
	extensionFilter[3] = new ExtensionFilter("C++", "*.cpp");
	extensionFilter[4] = new ExtensionFilter("C", "*.c");

	filechooser.getExtensionFilters().addAll(extensionFilter);
	File file=filechooser.showOpenDialog(primaryStage);
	return file;
}
}
