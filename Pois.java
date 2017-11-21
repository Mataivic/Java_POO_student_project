package TP_Pois_Mendel;

//import java.util.ArrayList;

// Cette classe représente le pois, un organisme
public class Pois extends Organisme {	
	
	/* retourne un phénotype contenant deux caractères associés respectivement aux couples d’allèles
	 (coul1, coul2) et (form1, form2). coul1 et coul2  ́etant chacun soit jaune ('J') soit vert ('v'), 
	 et form1 et form2 soit lisse ('L') soit ridé ('r').
	 */
	private static Phenotype phenotype(char coul1, char coul2, char form1, char form2) {
		Gene gene1 = new Gene("Couleur", coul1);
		Gene gene2 = new Gene("Couleur", coul2);
		Caractere couleur = new Caractere("Couleur", gene1, gene2, "jaune", "vert");
		
		Gene gene3 = new Gene("Aspect", form1);
		Gene gene4 = new Gene("Aspect", form2);
		Caractere forme = new Caractere("Forme", gene3 , gene4, "lisse", "rugueux");
		
		Phenotype pheno = new Phenotype();
		pheno.addCaractere(couleur);
		pheno.addCaractere(forme);
		return pheno;
	}
	
	// Constructeurs	
	public Pois(String nom, char coul1, char coul2, char form1, char form2) {		
		super(nom, phenotype(coul1, coul2, form1, form2));		
	}
	
	public Pois(String nom, Phenotype pheno) {
		super(nom, pheno);
	}
	
	// Retourne l'état apparent du caractère "Forme"
	public String forme() {
		return getPhenotype().get("Forme").etat();
	}
	
	// Retourne l'état apparent du caractère "Couleur"
	public String couleur() {
		return getPhenotype().get("Couleur").etat();
	}
	
	// Test
	public static void main(String[] args) {
		Pois pois = new Pois("Pois", 'J', 'v', 'L', 'r');
		pois.afficher();
		System.out.println(pois.forme());
		System.out.println(pois.couleur());
	}

}