import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MenuDisableUnable {
private static MenuItem save;
private static MenuItem saveas;
private static TabPane tabpane;
private static MenuItem find;
private static MenuItem findPrevious;
private static MenuItem findAll;
private static MenuItem replace;
private static MenuItem replaceAll;
public static void setFileMenu(MenuItem temp,MenuItem tem,TabPane te){
	save=temp;
	saveas=tem;
	tabpane=te;
}

public static void UnableDisable(){
	Tab tab=tabpane.getSelectionModel().getSelectedItem();
	if(tab==null){
		save.setDisable(true);
		saveas.setDisable(true);
	}
	else{
		save.setDisable(false);
		saveas.setDisable(false);
	}

}

public static void setFindMenu(MenuItem fi,MenuItem fp,MenuItem fa,MenuItem rp,MenuItem ra,TabPane te){
	find =fi;
	findPrevious = fp;
	findAll = fa;
	replace = rp;
	replaceAll = ra;
	tabpane = te;
}

public static void UnableDisableFind(){
	Tab tab=tabpane.getSelectionModel().getSelectedItem();
	if(tab==null){
		find.setDisable(true);
		findPrevious.setDisable(true);
		findAll.setDisable(true);
		replace.setDisable(true);
		replaceAll.setDisable(true);
	}
	else{
		find.setDisable(false);
		findPrevious.setDisable(false);
		findAll.setDisable(false);
		replace.setDisable(false);
		replaceAll.setDisable(false);
	}

}
}
