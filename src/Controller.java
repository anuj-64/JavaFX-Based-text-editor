import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.JTextComponent;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	private static Stage primarystage;
	private static BorderPane root;
	private static TabPane tabpane;

	@FXML
	private MenuItem newfile;

	@FXML
	private MenuItem save;
	@FXML
	private MenuItem saveas;
	@FXML
	private MenuItem full;
	@FXML
	private MenuItem exitfull;
	@FXML
	private MenuItem copy;
	@FXML
	private MenuItem cut;
	@FXML
	private MenuItem paste;
	@FXML
	private TextArea textAreafind;
	@FXML
	private TextArea textAreaRepalce;
	@FXML
	private CheckBox caseSense;
	@FXML
	private MenuItem find;
	@FXML
	private MenuItem findPrevious;
	@FXML
	private MenuItem findAll;
	@FXML
	private MenuItem replace;
	@FXML
	private MenuItem replaceAll;
public static void set(Stage temp,BorderPane temp2){
	primarystage=temp;
	root=temp2;
}

public static void addview() throws IOException{
	FXMLLoader loader=new FXMLLoader();
	loader.setLocation(Main.class.getResource("SecondaryView.fxml"));
	AnchorPane pane=(AnchorPane)loader.load();
	tabpane=(TabPane)pane.getChildren().get(0);
	root.setCenter(pane);
	AddingTab.setAddingTab(tabpane, 1);

}
//------------------------------------Menu Bar------------------------------------------------
@FXML
public void filemenu(){
	MenuDisableUnable.setFileMenu(save, saveas,tabpane);
	MenuDisableUnable.UnableDisable();
}
@FXML
public void editmenu(){
	if(tabpane.getSelectionModel().getSelectedItem()!=null){
	String text=AddingTab.selectedtext();
	if(text==null){
		cut.setDisable(true);
		copy.setDisable(true);
		paste.setDisable(true);
	}
	else{
		cut.setDisable(false);
		copy.setDisable(false);
		paste.setDisable(false);

	}
	}
	else{
		cut.setDisable(true);
		copy.setDisable(true);
		paste.setDisable(true);

	}

}
@FXML
public void findmenu(){
	MenuDisableUnable.setFindMenu(find,findPrevious,findAll,replace,replaceAll,tabpane);
	MenuDisableUnable.UnableDisableFind();
}
@FXML
public void viewmenu(){
	boolean flag=primarystage.isFullScreen();
	if(!flag){
		full.setDisable(false);
		exitfull.setDisable(true);
	}
	else{
		full.setDisable(true);
		exitfull.setDisable(false);
	}
}

//----------------------------------------File Menu--------------------------------------------
@FXML
public void addnewtab() throws IOException{
	AddingTab.setStage(primarystage);
	AddingTab.addnewtab();
}

@FXML
public void open() throws IOException{
	FileHandling.setStage(primarystage);
	File file =FileHandling.open();
	FileHandling.setparameter(file,tabpane);
	FileHandling.opentab();
}
@FXML
public void save() throws InterruptedException{
	AddingTab.issaved();
	FileHandling.setparameter(new File("tst.txt"), tabpane);
	FileHandling.Save();
}
@FXML
public void saveas() throws InterruptedException{
	AddingTab.issaved();
	FileHandling.setparameter(new File("tst.txt"), tabpane);
	FileHandling.setStage(primarystage);
	FileHandling.SaveAs();
}

@FXML
public void close(){
	System.exit(0);
}

//-----------------------------------------File Menu End--------------------------------------------------------


//----------------------------------------Edit Menu-------------------------------------------------------------

@FXML
public void cut(){
	AddingTab.cut();
}
@FXML
public void copy(){
	AddingTab.copy();
}
@FXML
public void paste(){
	AddingTab.paste();
}
//----------------------------------------Edit Menu End---------------------------------------------------------

//------------------------------------------Find Menu-----------------------------------------------------------
public JTextPane retTextPane(){
	Tab tab = tabpane.getSelectionModel().getSelectedItem();
	AnchorPane anchPane = (AnchorPane) tab.getContent();
	BorderPane bPane = (BorderPane) anchPane.getChildren().get(1);
	SwingNode swingNode = (SwingNode) bPane.getChildren().get(0);
	JScrollPane jScrollPane = (JScrollPane) swingNode.getContent();
	JViewport jViewport = jScrollPane.getViewport();
	JTextPane jTextPane = (JTextPane) jViewport.getView();
	return jTextPane;
}

	@FXML
	public void Find() throws IOException {
		if (root.getBottom() == null) {
			Findclass.set(primarystage, tabpane);
			Findclass.FindStage();
		}
		 System.out.println("find");
		if (textAreafind != null) {
			System.out.println(textAreafind.getText());
			JTextPane jTextPane = retTextPane();
			Findclass.getStatus("find", textAreafind.getText().toLowerCase(), jTextPane, textAreaRepalce.getText());
		}
	}

	@FXML
	public void FindPrev() throws IOException {
		if (root.getBottom() == null) {
			Findclass.set(primarystage, tabpane);
			Findclass.FindStage();
		}

		// System.out.println("findPrev");
		if (textAreafind != null) {
			JTextPane jTextPane = retTextPane();
			Findclass.getStatus("findPrevious", textAreafind.getText().toLowerCase(), jTextPane,
					textAreaRepalce.getText());
		}
	}

	@FXML
	public void FindAll() throws IOException {
		if (root.getBottom() == null) {
			Findclass.set(primarystage, tabpane);
			Findclass.FindStage();
		}
		if (textAreafind != null) {
			JTextPane jTextPane = retTextPane();
			Findclass.getStatus("findAll", textAreafind.getText().toLowerCase(), jTextPane, textAreaRepalce.getText());
		}
	}

	@FXML
	public void Replace() throws IOException {
		if (root.getBottom() == null) {
			Findclass.set(primarystage, tabpane);
			Findclass.FindStage();
		}
		if (textAreafind != null && textAreaRepalce != null) {
			JTextPane jTextPane = retTextPane();
			Findclass.getStatus("replace", textAreafind.getText().toLowerCase(), jTextPane, textAreaRepalce.getText());
		}
	}

	@FXML
	public void ReplaceAll() throws IOException {
		if (root.getBottom() == null) {
			Findclass.set(primarystage, tabpane);
			Findclass.FindStage();
		}
		if (textAreafind != null && textAreaRepalce != null) {
			JTextPane jTextPane = retTextPane();
			Findclass.getStatus("replaceAll", textAreafind.getText().toLowerCase(), jTextPane,
					textAreaRepalce.getText());
		}
	}

	@FXML
	public void closeFind() {
		System.out.println("closed");
		root.setBottom(null);
	}

//-----------------------------------------Find MEnu ends------------------------------------------------------

//-----------------------------------------View Menu ----------------------------------------------------------
@FXML
public void fullscreen(){
	primarystage.setFullScreen(true);
}
@FXML
public void exitfullscreen(){
	primarystage.setFullScreen(false);
}

//-----------------------------------------View Menu Ends------------------------------------------------------
//-----------------------------------------Help-----------------------------------------------------------------
public void about() throws IOException{
	FXMLLoader loader=new FXMLLoader();
	loader.setLocation(Main.class.getResource("AboutTabahi.fxml"));
	AnchorPane pane=(AnchorPane)loader.load();
	Scene scene=new Scene(pane);
	Stage aboutwindow=new Stage();
	aboutwindow.setScene(scene);
	aboutwindow.initOwner(primarystage);
	aboutwindow.initModality(Modality.WINDOW_MODAL);
	aboutwindow.show();
}
//----------------------------------------Help End--------------------------------------------------------------
public void cmd() throws IOException{
	Runtime.getRuntime().exec(new String[]{"cmd","/k","start","cmd"});
}
//----------------------------------------Text Area Related-----------------------------------------------------



}
