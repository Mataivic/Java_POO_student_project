package TP_Pois_Mendel;

import java.util.ArrayList;

// Cette classe se compose d'un ensemble d'objets de classe Caractere, stockés dans une ArrayList
public class Phenotype {
	
	private ArrayList<Caractere> listeCaracteres = new ArrayList<Caractere>();
	
	public Phenotype(){}
	
	//Getter
	public ArrayList<Caractere> getListeCaracteres() {
		return listeCaracteres;
	}
	
	Caractere get(int i) {
		return listeCaracteres.get(i);		
	}
	
	Caractere get(String nom) {
		Caractere toReturn = null;
		for(int i = 0; i < listeCaracteres.size(); i++) {
			if (listeCaracteres.get(i).getNom() == nom) {
				toReturn = listeCaracteres.get(i);
			}
		}
		return toReturn;
	}
	
	void addCaractere(Caractere caractere) {
		listeCaracteres.add(caractere);
	}
	
	void afficher() {
		System.out.println("Phénotype : ");
		for(int i = 0; i < listeCaracteres.size(); i++) {
			listeCaracteres.get(i).afficher();
		}
	}
	
	public static void main (String[] args) {
		
		// Test global
		Gene gene1 = new Gene("Couleur", 'J');
		Gene gene2 = new Gene("Couleur", 'v');
		Caractere couleur = new Caractere("Couleur", gene1, gene2, "jaune", "vert");
		Gene gene3 = new Gene("Aspect", 'L');
		Gene gene4 = new Gene("Aspect", 'r');
		Caractere aspect = new Caractere("Aspect", gene3, gene4, "lisse", "rugueux");
		Phenotype pheno = new Phenotype();
		pheno.addCaractere(couleur);
		pheno.addCaractere(aspect);
		pheno.afficher();
		
		// Test des méthodes get()
		pheno.get(0).afficher();
		pheno.get("Aspect").afficher();		
	}	
}