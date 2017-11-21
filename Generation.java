package TP_Pois_Mendel;

import java.util.ArrayList;
import java.util.Set;

public abstract class Generation {
	
	protected String nomOrga;
	protected int numero;
	protected ArrayList<Organisme> listeOrganismes = new ArrayList<Organisme>();	

	/* Constructeurs */
	public Generation() {}
	
	// Constructeur avec numero = 0
	public Generation(String nomOrga, Organisme orga1, Organisme orga2) {
		this.nomOrga = nomOrga;
		numero = 0;
		listeOrganismes.add(orga1);
		listeOrganismes.add(orga2);
	}
	
	// Constructeur avec le numero en paramètre
	public Generation(String nomOrga, int numero, Organisme orga1, Organisme orga2) {
		this.nomOrga = nomOrga;
		this.numero = numero;
		listeOrganismes.add(orga1);
		listeOrganismes.add(orga2);
	}
	
	// Constructeur avec liste d'Organismes
		public Generation(String nomOrga, ArrayList<Organisme> listeOrganismes) {
			this.nomOrga = nomOrga;
			numero = 0;
			this.listeOrganismes = listeOrganismes;
		}
	
	// Constructeur avec liste d'Organismes
	public Generation(String nomOrga, int numero, ArrayList<Organisme> listeOrganismes) {
		this.nomOrga = nomOrga;
		this.numero = numero;
		this.listeOrganismes = listeOrganismes;
	}
	
	// Getters et Setters
	
	public String getNomOrga() {
		return nomOrga;
	}	
	
	public void setNumero(int numero) {
		this.numero = numero;		
	}
	
	public ArrayList<Organisme> getListeOrga() {
		return listeOrganismes;
	}
	
	// Permet de consulter le numéro de la génération
	public int numeroGeneration() {		
		return numero;
	}
	
	// Retourne le i-ème individu de la génération
	public Organisme get(int i) {
		return listeOrganismes.get(i);
	}
	
	// Ajoute un individu à la génération
	public void add(Organisme individu) {
		listeOrganismes.add(individu);
	}
	
	// Ajoute tous les individus de la génération donnée à la génération courante
	public void add(Generation generation) {
		for (int i = 0; i< generation.listeOrganismes.size(); i++) {
			this.add(generation.listeOrganismes.get(i));
		}
	}
	
	// Consulte le nombre d'individus de la génération
	public int nombre() {
		return listeOrganismes.size();
	}
	
	/* Permet de consulter le nombre d'individus de la génération ayant le caractère apparent 'etat' */
	public int nombre(String etat) {
		int compteur = 0;
		// Parcours de la liste des organismes
		for (Organisme orga : listeOrganismes) {			
			// On progresse jusqu'à avoir la liste des caractères du phénotype de chaque organisme			
			Phenotype pheno = orga.getPhenotype();
			ArrayList<Caractere> listeCaracteres = pheno.getListeCaracteres();
			// Parcours de la liste de caractères du phenotype
			for (Caractere carac : listeCaracteres) {
				// Si l'état apparent du caractère correspond, on incrémente compteur
				if (carac.etat() == etat) {
					compteur += 1;
				}
			}			
		}		
		return compteur;
	}
	
	/* Permet de consulter le nombre d'individus de la génération ayant les caractèrse apparents dans 'etats' */
	public int nombre(Set<String> etats) {		
		int compteur = 0;
		// Parcours de la liste des organismes
		for (Organisme orga : listeOrganismes) {
			int etatsValides = 0;
			// On progresse jusqu'à avoir la liste des caractères du phénotype de chaque organisme			
			Phenotype pheno = orga.getPhenotype();
			ArrayList<Caractere> listeCaracteres = pheno.getListeCaracteres();
			// Parcours de la liste de caractères du phenotype
			for (Caractere carac : listeCaracteres) {
				// Incrémente etatsValides si tous les états sont apparents
				for (String etat : etats) {
					if (carac.etat().equals(etat)) {
						etatsValides += 1;
					}
				}
			}
			// Si tous les états sont apparents, on incrémente compteur
			if (etatsValides == etats.size()) {
				compteur += 1;
			}					
		}		
		return compteur;
	}
	
	/* Affiche  l'état  de  la génération : le nombre d'individus par combinaison de caractères apparents */
	public abstract void afficher();
	
	
	/* Crée une génération en croisant les deux individus o1 et o2 */
	public abstract Generation croiser(Organisme o1, Organisme o2);		
	
	/* Retourne la génération suivante en croisant toutes les paires d'organisme (y compris un organisme
	avec lui même) pour engendrer la génération suivante */
	public Generation suivante() {
		// TODO
		ArrayList<Generation> orgCroises = new ArrayList<Generation>();
		
		// Remplissage de la liste d'organismes croisés avec les croisements entre chaque organismes
		for (int i=0; i < this.nombre(); ++i) {
			for (int j=i; j < this.nombre(); ++j) {
				orgCroises.add(croiser(get(i), get(j))); // Croisements. On a donc une liste de générations.
				// Chaque génération contiend les individus issus du croisement de chaque i et j
			}
		}
		
		Generation genSuivante = orgCroises.get(0);
		orgCroises.remove(0);
		for (Generation gen : orgCroises) {
			genSuivante.add(gen);
		}
		genSuivante.setNumero(numeroGeneration()+1); // On incrémente le numéro de génération
		
		return genSuivante;
	}	
}