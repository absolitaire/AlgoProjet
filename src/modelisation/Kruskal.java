package modelisation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Kruskal {

	public static final int[] premiers = {2  , 3  , 5  , 7 , 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41, 
			43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101, 103, 107, 109, 113,
			127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229};

	public static void main(String[] args){
		//new Kruskal().kruskal2HashCode(Graph.example());
		kruskalXFois(1000000);;
	}


	public static ArrayList<Edge> kruskal(Graph g){
		ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		boolean ajouter;
		int origine, destination;

		Collections.shuffle(list);

		for(Edge e : list){
			origine = e.from;
			destination = e.to;
			ajouter = true;
			//System.out.println("Origine "+origine +" | Destination "+destination);
			for(Edge e2: arbre) {
				trace.add(e2.from);
				trace.add(e2.to);	
			}
			if(trace.contains(origine) && trace.contains(destination)) {
				if(!verifierSeparation(e, arbre)) {
					ajouter = false;
					//System.out.println("cut de "+e.from+"---"+e.to);
				}

			}	
			if(ajouter) {
				//System.out.println("ajout de "+e.from+"---"+e.to);
				arbre.add(e);
			}
		}
		return arbre;
	}

	/*public static boolean verifierSeparationOld(Edge ed, ArrayList<Edge> arbre) {
		HashMap<Edge,Boolean> verification = new HashMap<Edge,Boolean >();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		boolean modification = true;
		for (Edge e : arbre) {
			if(modification) {
				verification.put(e, true);
				trace.add(e.from);
				trace.add(e.to);
				modification = false;
			}else {
				verification.put(e, false);
			}
		}
		modification = true;
		Set<Edge> iterator = verification.keySet();
		while(modification) {
			modification = false;
			for(Edge e2: iterator) {
				if(! verification.get(e2)) {
					if(trace.contains(e2.from) || trace.contains(e2.to) ) {
						trace.add(e2.from);
						trace.add(e2.to);
						verification.put(e2, true);
						modification = true;
					}
				}
			}
		}
		if(trace.contains(ed.from) && trace.contains(ed.to)) {		
			return false;
		}
		if((trace.contains(ed.from) && ! trace.contains(ed.to)) || (!trace.contains(ed.from) && trace.contains(ed.to))) {		
			return true;
		}else{
			return false;
		}
	}*/

	public static boolean verifierSeparation(Edge ed, ArrayList<Edge> arbre) {
		HashMap<Edge,Boolean> verification = new HashMap<Edge,Boolean >();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> stock = new ArrayList<ArrayList<Integer>>();
		boolean modification = true;
		int nombreVal = 0;

		for (Edge e : arbre) {
			nombreVal += 1;
			verification.put(e, false);
		}

		Set<Edge> iterator = verification.keySet();

		while(nombreVal > 0) {
			trace = new ArrayList<Integer>();
			modification = true;

			while(modification) {
				for(Edge e2: iterator) {
					if(! verification.get(e2) && modification) {
						trace.add(e2.from);
						trace.add(e2.to);
						verification.put(e2, true);
						modification = false;
						nombreVal -= 1;
					}
				}
			}

			modification = true;
			while(modification) {
				modification = false;
				for(Edge e2: iterator) {
					if(! verification.get(e2)) {
						if(trace.contains(e2.from) || trace.contains(e2.to) ) {
							trace.add(e2.from);
							trace.add(e2.to);
							verification.put(e2, true);
							modification = true;
							nombreVal -= 1;
						}
					}
				}
			}
			stock.add(trace);
		}
		for(ArrayList<Integer> t: stock) {
			if(t.contains(ed.from) && t.contains(ed.to)) {		
				return false;
			}
		}
		return true;
	}

	public static int kruskalHashCode(Graph g){
		ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		HashMap<Edge,Integer> edgeId = new HashMap<Edge,Integer>();
		boolean ajouter;
		int origine, destination;
		int premiersId = -1;
		int hashCode = 1;

		//On associe a chaque arete un nombre premier.
		//L'arbre couvrant sera donc un produit de nombre premiers, donc l'ordre d'apparition des Edge ne changera pas le resultat,
		//mais deux arbres differents ne pourront jamais donner le meme hashcode.
		for(Edge e : list){
			premiersId++;
			edgeId.put(e, premiers[premiersId]);
		}

		Collections.shuffle(list);

		for(Edge e : list){


			origine = e.from;
			destination = e.to;
			ajouter = true;
			//System.out.println("Origine "+origine +" | Destination "+destination);
			for(Edge e2: arbre) {
				trace.add(e2.from);
				trace.add(e2.to);	
			}
			if(trace.contains(origine) && trace.contains(destination)) {
				if(!verifierSeparation(e, arbre)) {
					ajouter = false;
					//System.out.println("cut de "+e.from+"---"+e.to);
				}
				//System.out.println("cut de "+e.from+"---"+e.to);
			}	
			if(ajouter) {
				//	System.out.println("ajout de "+e.from+"---"+e.to);
				arbre.add(e);
				hashCode = hashCode * edgeId.get(e);
			}
		}
		//System.out.println(hashCode);
		return hashCode;
		//return arbre;
	}


	public static void kruskalXFois(int nb){
		HashMap<Integer,Integer> stats = new HashMap<Integer,Integer>();
		Integer resultat;
		for(int i = 0; i < nb; i++){
			resultat = kruskalHashCode(Graph.example());
			if(stats.containsKey(resultat)){
				stats.put(resultat, stats.get(resultat) +1);
			}else{
				stats.put(resultat, 1);
			}

		}
		for(Integer i : stats.keySet()){
			System.out.println("arbre de code "+i+" : "+stats.get(i));
		}
	}



}
