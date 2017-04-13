import java.awt.List;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Highlighter;
import javax.swing.text.Style;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

//Source Stackoverflow
//Desciption source :: Oracle and some commmonsense
// Edited by: Anubhav Deshwal
public class Keywordcoloring extends DefaultStyledDocument {
private Style defaultstyle;//Style for text other than keyword
private Style coloredstyle;//Style for keyword
public Keywordcoloring(Style dstyle,Style cstyle){//taking the value for defaultstyle and coloredstyle by constructor use
	defaultstyle=dstyle;
	coloredstyle=cstyle;
}
//okky we have the value of style's
//so we'll be writing some inherited method of DefaultStyledDocument class
//method are insertString() and remove().these method throws BadLocationException
//Both method are text manipulation method of textpane


//implementing insertString
public void insertString(int offset,String str,AttributeSet aset) throws BadLocationException{
	super.insertString(offset, str, aset);//calling insertString of DefaultStyledDocumment so that tring can appear on textpane
	refreshdocument();
	
	Platform.runLater(new Runnable() {
        @Override
        public void run() {
          AddingTab.line();
        }
   });

	
}

//implementing remove
public void remove(int offset,int length) throws BadLocationException{
	super.remove(offset, length);
	refreshdocument();
}

private void refreshdocument() throws BadLocationException{
	//getting string by using getText and getLength method of Document(upper class of StyledDocument)
	String text=getText(0,getLength());
	ArrayList<Coloredword> list=processword(text);
	setCharacterAttributes(0,text.length(),defaultstyle, true);//first we'll set all text to default style
	//now we'll color identifier which are stored in list
	for(Coloredword word:list){
		setCharacterAttributes(word._position,word._word.length(),coloredstyle,true);
	}
}
private ArrayList<Coloredword> processword(String content){
	ArrayList<Coloredword> list=new ArrayList<Coloredword>();//contain all word that are identifier
	content+=" ";//adding whitespace so that last word can be processed easily;
	char data[]=content.toCharArray();
	 String word="";//we'll use this to analyse that whether particular word is identifier or not
	int lastspace=0;
	for(int index=0;index<data.length;index++){
		char temp=data[index];
		if(!(Character.isLetter(temp))||(Character.isDigit(temp)||(temp=='_'))){//this will execute if there is a whitespace means we have a complete 
		lastspace=index;
		//word stored in variable "word"
		
		if(word.length()>0){
			if(isidentifier(word)){
				list.add(new Coloredword(lastspace-word.length(),word));
			}
			
			word="";//reset the word for further analysis
		}
		
		}
	else{//word is not completed yet
			word+=temp;
		}
	}
	
	return list;
}
public boolean isidentifier(String word){//this function will contain list of identifier
	//which will we match with the word
	return (word.equals("public")||word.equals("class")||word.equals("extends")||word.equals("private")||word.equals("public")||word.equals("new")
			||word.equals("int")||word.equals("char")||word.equals("float")||word.equals("return")||word.equals("double")||word.equals("void")
			||word.equals("static")||word.equals("String")||word.equals("byte")||word.equals("short")||word.equals("import"));
}
}
