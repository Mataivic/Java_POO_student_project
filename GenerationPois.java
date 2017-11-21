package TP_Pois_Mendel;

import java.util.ArrayList;
import java.util.Set;

public class GenerationPois extends Generation{	
	
	private static final String ESPECE = "Pisum sativum";
	private static final String JAUNE = "jaune";
	private static final String VERT = "vert";
	private static final String LISSE = "lisse";
	private static final String RIDE = "ridé";

	/* Constructeurs */
	public GenerationPois() {
		super();
	}
	
	public GenerationPois(Organisme orga1, Organisme orga2) {
		super(ESPECE, orga1, orga2);
		// TODO Auto-generated constructor stub
	}
	
	public GenerationPois(ArrayList<Organisme> listeOrganismes) {
		super(ESPECE, listeOrganismes);
	}

	/* Engendre tous les individus possibles résultant du croisement de deux pois o1 et o2 */
	public Generation croiser(Organisme o1, Organisme o2) {
		if (o1.getNom() == o2.getNom()) {
			// Liste des descendants F+1
			ArrayList<Organisme> descendants = new ArrayList<Organisme>();
		
			// liste de liste de chacun des caractères obtenu par croisement
			ArrayList<ArrayList<Caractere>> listeCaracteres = new ArrayList<ArrayList<Caractere>>();
			for (int i=0; i < o1.getPhenotype().getListeCaracteres().size(); ++i) {
				String nomCaractere = o1.getPhenotype().getListeCaracteres().get(i).getNom();
				listeCaracteres.add(croiserCaracteres(nomCaractere, o1.getPhenotype().get(nomCaractere), o2.getPhenotype().get(nomCaractere)));
			}
			
			descendants = combiner(listeCaracteres, descendants);
			
			return new GenerationPois(descendants);
		} else {
			System.out.println("Impossible de croiser des organismes d'espèces différentes");
			return null;
		}
		
		// TODO			
	}
	
	/* Méthode qui détermine les combinaisons possibles entre les deux alleles d'un caractere
	Prérequis : déclarer correctement les caractères :
	etat1 correspondant à l'état déterminé par l'allèle de geneParent1 et etat2 à celui de geneParent2 */
	private ArrayList<Caractere> croiserCaracteres(String Caractere, Caractere car1, Caractere car2) {
		ArrayList<Caractere> croisements = new ArrayList<Caractere>();
		if (car1.homozygote() && car2.homozygote()) {
			croisements.add(new Caractere(Caractere, car1.getGeneParent1(), car2.getGeneParent1(), car1.getEtat1(), car2.getEtat1())); // hétérozygote
		} else if (!car1.homozygote() && !car2.homozygote()) {
			croisements.add(new Caractere(Caractere, car1.getGeneParent1(), car2.getGeneParent1(), car1.getEtat1(), car2.getEtat1())); // homozygote 1
			croisements.add(new Caractere(Caractere, car1.getGeneParent2(), car2.getGeneParent2(), car1.getEtat2(), car2.getEtat2())); // homozygote 2
			croisements.add(new Caractere(Caractere, car1.getGeneParent1(), car2.getGeneParent2(), car1.getEtat1(), car2.getEtat2())); // hétérozygote
		}
		return croisements;
	}	
	
	private ArrayList<Organisme> combiner(ArrayList<ArrayList<Caractere>> listeCaracteres, ArrayList<Organisme> listeOrganismes) {
		
		Integer i = 1;
		for (Caractere caractere1 : listeCaracteres.get(0)) {
			Phenotype pheno = new Phenotype();
			pheno.addCaractere(caractere1);
			for (Caractere caractere2 : listeCaracteres.get(1)) {
				pheno.addCaractere(caractere2);
				listeOrganismes.add(new Pois("Descendant " + i.toString(), pheno));
				i++;
			}
		}
		return listeOrganismes;
	}
	
	/* Affiche l'état de la génération : 
	nombre de pois pour chaque paire de caractères apparents forme et couleur */	 
	public void afficher() {
		int jauneLisse =0, jauneRide=0, vertLisse=0, vertRide=0;
		ArrayList<String> etats = new ArrayList<String>();
		etats.add(JAUNE); etats.add(VERT); etats.add(LISSE); etats.add(RIDE);
		
		for (Organisme orga : listeOrganismes) {
			Phenotype pheno = orga.getPhenotype();
			ArrayList<Caractere> carac = pheno.getListeCaracteres();
			ArrayList<String> etatsCheck = new ArrayList<String>();
			for (Caractere car : carac) {
				etatsCheck.add(car.etat());
			}
			if (etatsCheck.contains(JAUNE) && etatsCheck.contains(LISSE)) jauneLisse+=1;
			if (etatsCheck.contains(JAUNE) && etatsCheck.contains(RIDE)) jauneRide+=1;
			if (etatsCheck.contains(VERT) && etatsCheck.contains(LISSE)) vertLisse+=1;
			if (etatsCheck.contains(VERT) && etatsCheck.contains(RIDE)) vertRide+=1;
		}
		
		System.out.println("Génération numéro " + numeroGeneration() + " :");
		System.out.println("Nombre de pois jaunes (J) et lisses (L) :" + jauneLisse);
		System.out.println("Nombre de pois jaunes (J) et ridés (r) :" + jauneRide);
		System.out.println("Nombre de pois verts (v) et lisses (L) :" + vertLisse);
		System.out.println("Nombre de pois verts (J) et ridés (r) :" + vertRide);		
	}
	
	public static void main(String[] args) {
		//Test
		
		// Organismes parents (Homozygotes pour chaque caractère)
		Gene gene1 = new Gene("Couleur", 'J');
		Gene gene2 = new Gene("Couleur", 'v');		
		Gene gene3 = new Gene("Aspect", 'L');
		Gene gene4 = new Gene("Aspect", 'r');
		
		Caractere couleurJ = new Caractere("Couleur", gene1, gene1, JAUNE, JAUNE);
		Caractere couleurV = new Caractere("Couleur", gene2, gene2, VERT, VERT);
		Caractere aspectL = new Caractere("Aspect", gene3, gene3, LISSE, LISSE);
		Caractere aspectR = new Caractere("Aspect", gene4, gene4, RIDE, RIDE);
		
		Phenotype phenoJL = new Phenotype();
		Phenotype phenoVR = new Phenotype();
		phenoJL.addCaractere(couleurJ);
		phenoJL.addCaractere(aspectL);
		phenoVR.addCaractere(couleurV);
		phenoVR.addCaractere(aspectR);
		
		Organisme pois1 = new Organisme(ESPECE, phenoJL);
		Organisme pois2 = new Organisme(ESPECE, phenoVR);
		pois1.afficher();
		pois2.afficher();
		
		// Croisements
		Generation F0 = new GenerationPois(pois1, pois2);
		F0.afficher();
		Generation F1 = F0.croiser(pois1,  pois2);
		F1.afficher();
	}
	
}
