package com.github.gmerty.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.gmerty.adventura.logika.IHra;
import com.github.gmerty.adventura.logika.Postava;
import com.github.gmerty.adventura.logika.Prostor;
import com.github.gmerty.adventura.logika.Vec;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class HomeController extends GridPane implements Observer{
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Vec> seznamVeciVMistnosti;
	@FXML private ListView<Prostor> seznamVychodu;
	@FXML private ListView<Postava> seznamPostavVMistnosti;
	@FXML private ImageView uzivatel;
	//@FXML private ListView<Vec> seznamVeciVBatohu;
	
	
	private IHra hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	//@SuppressWarnings("deprecation")
	@FXML public void odesliPrikaz() {
		//System.out.println(vstupniText.getText());
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n------\n"+vstupniText.getText()+"\n--------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if (hra.konecHry()) {
			vystup.appendText("\n------\nKonec hry\n------\n");
			vstupniText.setDisable(true);
			
		}
		
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
		//seznamVeciVBatohu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().)
		//System.out.println("");
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVeciVMistnosti.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamVeciVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		seznamPostavVMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		//hra.
	}

}

