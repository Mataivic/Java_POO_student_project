package TP_Pois_Mendel;


// Cette classe décrit un organisme diploïde, qui a un nom et un phénotype
public class Organisme {

	protected String nom;
	protected Phenotype phenotype;
	
	// Constructeur
	public Organisme() {}
	
	public Organisme(String nom, Phenotype phenotype) {
		this.nom = nom;
		this.phenotype = phenotype;
	}
	
	// Getters
	public String getNom() {
		return nom;
	}
	public Phenotype getPhenotype() {
		return phenotype;
	}
	
	// Afficher
	void afficher() {
		System.out.println("Organisme : " + getNom());
		phenotype.afficher();
	}
	
	public static void main (String[] args) {
		Gene gene1 = new Gene("Couleur", 'J');
		Gene gene2 = new Gene("Couleur", 'v');
		Caractere couleur = new Caractere("Couleur", gene1, gene2, "jaune", "vert");
		Gene gene3 = new Gene("Aspect", 'L');
		Gene gene4 = new Gene("Aspect", 'r');
		Caractere aspect = new Caractere("Aspect", gene3, gene4, "lisse", "rugueux");
		Phenotype pheno = new Phenotype();
		pheno.addCaractere(couleur);
		pheno.addCaractere(aspect);		
		Organisme orga = new Organisme("Bestiole", pheno);
		orga.afficher();
	}
}