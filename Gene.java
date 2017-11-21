package TP_Pois_Mendel;

// Cette classe représente un gène qui n'a que deux allèles
public class Gene {
	private String nom;
	private char allele;
	private boolean estDominant;
	
	// Constructeurs
	public Gene() {}
	
	public Gene(String nom, char allele) {		
		this.nom = nom;
		this.allele = allele;
		estDominant = estDominant(); 
		/* On utilise dans le constructeur la méthode qui définit si un allèle est dominant : 
		 - pas de risque de se tromper
		 - On instancie les gènes uniquement avec leur nom et leur allèle
		 */		
	}
	
	// Getters
	public String getNom() {
		return nom;
	}	
	public char getAllele() {
		return allele;
	}	
	public boolean getEstDominant() {
		return estDominant;
	}
	
	// Détermine si un allèle est dominant ou récessif
	public boolean estDominant() {
		if (allele > 'A' && allele < 'Z') {
			return true;
		} else {
			return false;
		}
	}
	
	// Affiche les attributs du gene
	void afficher() {
		System.out.println("Gene : " + this.getNom() + " - Allele : " + this.getAllele() + " - Dominant : " + this.getEstDominant());
	}
	
	public static void main (String[] args) {
		// Test		
		Gene gene1 = new Gene("Couleur", 'J');
		Gene gene2 = new Gene("Couleur", 'v');
		gene1.afficher();
		gene2.afficher();	
		
	}
}
