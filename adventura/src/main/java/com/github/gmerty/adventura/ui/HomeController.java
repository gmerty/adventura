package com.github.gmerty.adventura.ui;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.github.gmerty.adventura.logika.Hra;
import com.github.gmerty.adventura.logika.IHra;
import com.github.gmerty.adventura.logika.Postava;
import com.github.gmerty.adventura.logika.Prostor;
//import com.github.gmerty.adventura.logika.SeznamPrikazu;
import com.github.gmerty.adventura.logika.Vec;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;


public class HomeController extends GridPane implements Observer{
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciVMistnosti;
	@FXML private ListView<Prostor> seznamVychodu;
	@FXML private ListView<Postava> seznamPostavVMistnosti;
	@FXML private ImageView uzivatel;
	//@FXML private ImageView seznamVeciVBatohu;
	//@FXML private ListView<Vec> seznamVeciVBatohu;
	@FXML private WebView napoveda;
	final Label label = new Label();
	@FXML private ImageView iSerborne;
	@FXML private ImageView iKembridge;
	@FXML private ImageView iPrinceton;
	@FXML private ImageView iClanek;
	@FXML private ImageView iEnigma;
	
	
	private IHra hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	//@SuppressWarnings("deprecation")
	@FXML public void odesliPrikaz() {
		odesliPrikaz(vstupniText.getText());
		
		
	}
	
	 public void odesliPrikaz(String parametr) {
			String vystupPrikazu = hra.zpracujPrikaz(parametr);
			vystup.appendText("\n------\n"+parametr+"\n--------\n");
			vystup.appendText(vystupPrikazu);
			vstupniText.setText("");
			if (hra.getHerniPlan().getBatoh().najdiVBatohu("SS_Diploma")) {
				iSerborne.setVisible(true);
			} 
			if (hra.getHerniPlan().getBatoh().najdiVBatohu("Diplom_bakalare")) {
				iKembridge.setVisible(true);
			}
			if (hra.getHerniPlan().getBatoh().najdiVBatohu("Diplom_doktora")) {
				iPrinceton.setVisible(true);
			}
			if (hra.getHerniPlan().getBatoh().najdiVBatohu("The_Entscheidungsproblem")) {
				iClanek.setVisible(true);
			}
			if (hra.getHerniPlan().getBatoh().najdiVBatohu("informace_od_polaku")) {
				iEnigma.setVisible(true);
			}				
			if (hra.konecHry()) {
				vystup.appendText("\n------\nKonec hry\n------\n");
				vstupniText.setDisable(true);				
			}			
		}
	
	
	@FXML public void pPrikazNovaHra() {
		vstupniText.setDisable(false);
		IHra hra = new Hra();
		seznamVeciVMistnosti.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostavVMistnosti.getItems().clear();
		inicializuj(hra);		
		
	}
	
	@FXML public void pPrikazKonec() {
		hra.zpracujPrikaz("konec");
		vystup.appendText("\n------\nKonec hry\n------\n");
		vstupniText.setDisable(true);
	}
	
	@FXML public void pPrikazNapoveda() throws Exception{
		try {
		WebView napoveda = new WebView();
        Stage stage = new Stage();
        stage.setScene(new Scene(napoveda));  
        stage.show();
        WebEngine engine = napoveda.getEngine();
	    engine.load("napoveda.HTML");
		} catch(Exception e) {
	           e.printStackTrace();
	        }
	}
	
	@FXML public void pJdi() {
		Prostor aktualniProstor = seznamVychodu.getSelectionModel().getSelectedItem();
		odesliPrikaz("jdi "+aktualniProstor.getNazev());		
	}
	
	
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		this.hra = hra;
		seznamVeciVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostavVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
		//seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getVeciVBatohu());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getBatoh().addObserver(this);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVeciVMistnosti.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamPostavVMistnosti.getItems().clear();
		//seznamVeciVBatohu.getItems().clear();
		seznamVeciVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostavVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
		//seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().getVeciVBatohu());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
	}

}

