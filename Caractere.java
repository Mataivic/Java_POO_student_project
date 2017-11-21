package TP_Pois_Mendel;


// Cette classe représente un caractère phénotypique  régit  par  un  seul  gène.  
public class Caractere {
	private String nom;	
	private Gene geneParent1;
	private Gene geneParent2;
	// Toujours 2 états : un dominant, un récéssif
	private String etat1;
	private String etat2;
	
	// Constructeurs
	public Caractere() {}
	
	public Caractere(String nom, Gene geneParent1, Gene geneParent2, String etat1, String etat2) {
		this.nom = nom;
		this.geneParent1 = geneParent1;
		this.geneParent2 = geneParent2;		
		this.etat1 = etat1;
		this.etat2 = etat2;
	}
	
	/* Getters */
	public String getNom() {
		return nom;
	}
	
	public Gene getGeneParent1(){
		return geneParent1;
	}
	public Gene getGeneParent2(){
		return geneParent2;
	}
	public String getEtat1() {
		return etat1;
	}
	public String getEtat2() {
		return etat2;
	}
	
	// Affiche les attributs du caractere
	public void afficher() {
		System.out.println("Caractère : " + getNom());
		System.out.print(" Parent 1 : ");
		geneParent1.afficher();
		System.out.print(" Parent 2 : ");
		geneParent2.afficher();
	}
	
	// Vérifie si le caractère est homozygote	
	public boolean homozygote() {		
		if ( (this.geneParent1.estDominant() && this.geneParent2.estDominant()) || (!this.geneParent1.estDominant() && !this.geneParent2.estDominant()) ) {
			return true;
		} else return false;
		
	}
	
	// Retourne l'état apparent du gène	
	public String etat() {
		String etatApparent;
		if (this.homozygote() == true) {
			etatApparent = etat1; // homozygote donc les deux états sont les mêmes, on retourne celui qu'on veut
		} else if (geneParent1.estDominant() == true) {
			etatApparent = etat1;
		} else {
			etatApparent = etat2;
		}
		return etatApparent;
	}
	
	public static void main (String[] args) {
		// Test
		Gene gene1 = new Gene("Couleur", 'J');
		Gene gene2 = new Gene("Couleur", 'v');
		Caractere couleur = new Caractere("Couleur ", gene1, gene2, "jaune", "vert");
		couleur.afficher();
		System.out.println(" Etat : le pois est " + couleur.etat());;
	}
}