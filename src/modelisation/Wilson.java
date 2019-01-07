package modelisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Wilson {

	public static final int[] premiers = {2  , 3  , 5  , 7 , 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41, 
			43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101, 103, 107, 109, 113,
			127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229};

	public static void main(String[] args){
		wilsonXFois(1000000);

	}


	public static int wilsonHashCode(Graph g){
		ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Edge> adj;
		ArrayList<Integer> sommetsVisites = new ArrayList<Integer>();
		ArrayList<Integer> sommetsNonVisites = new ArrayList<Integer>();
		HashMap<Edge,Integer> edgeId = new HashMap<Edge,Integer>();
		LinkedList<Integer> parcours = new LinkedList<Integer>();
		boolean continuer;
		Edge edge;
		Integer sommet;
		int position;
		Integer s1, s2;
		int premiersId = -1;
		int hashCode = 1;

		//On associe a chaque arete un nombre premier.
		//L'arbre couvrant sera donc un produit de nombre premiers, donc l'ordre d'apparition des Edge ne changera pas le resultat,
		//mais deux arbres differents ne pourront jamais donner le meme hashcode.
		for(Edge e : list){
			premiersId++;
			edgeId.put(e, premiers[premiersId]);
		}

		sommetsVisites.add(new Integer(0));
		for(int i = 1; i < g.vertices(); i++){
			sommetsNonVisites.add(new Integer(i));
		}

		while(!sommetsNonVisites.isEmpty()){
			parcours.clear();
			parcours.add(sommetsNonVisites.get(0));

			continuer = true;

			do{
				adj = g.adj(parcours.getLast());
				edge = adj.get((int)(adj.size()*Math.random()));
				if(edge.from == parcours.getLast()){
					sommet = edge.to;
				}else{
					sommet = edge.from;
				}

				if(parcours.contains(sommet)){
					//System.out.println("boucle");
					position = parcours.indexOf(sommet);
					while(parcours.size()>position){
						parcours.removeLast();
					}
				}
				parcours.add(sommet);

				if(sommetsVisites.contains(sommet)){
					continuer = false;
				}

			}while(continuer);

			for(int j = 0; j < parcours.size()-1; j++){

				s1 = parcours.get(j);
				s2 = parcours.get(j+1);

				//System.out.print(s1+" ");

				adj = g.adj(s1);
				for(Edge e : adj){
					if(e.from == s2 || e.to == s2){
						arbre.add(e);
						hashCode = hashCode * edgeId.get(e);
						break;
					}
				}
				sommetsNonVisites.remove(s1);
				sommetsVisites.add(s1);
			}


			//System.out.print(parcours.getLast()+" \n");
			sommetsNonVisites.remove(parcours.getLast());
			sommetsVisites.add(parcours.getLast());


		}
		//System.out.println(">>"+hashCode);
		return hashCode;
	}



	public static ArrayList<Edge> wilson(Graph g){
		//ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Edge> adj;
		ArrayList<Integer> sommetsVisites = new ArrayList<Integer>();
		ArrayList<Integer> sommetsNonVisites = new ArrayList<Integer>();
		//HashMap<Edge,Integer> edgeId = new HashMap<Edge,Integer>();
		LinkedList<Integer> parcours = new LinkedList<Integer>();
		boolean continuer;
		Edge edge;
		Integer sommet;
		int position;
		Integer s1, s2;
		//int premiersId = -1;
		//int hashCode = 1;

		/*for(Edge e : list){
			premiersId++;
			edgeId.put(e, premiers[premiersId]);
		}*/

		sommetsVisites.add(new Integer(0));
		for(int i = 1; i < g.vertices(); i++){
			sommetsNonVisites.add(new Integer(i));
		}

		while(!sommetsNonVisites.isEmpty()){
			parcours.clear();
			parcours.add(sommetsNonVisites.get(0));

			continuer = true;

			do{
				adj = g.adj(parcours.getLast());
				edge = adj.get((int)(adj.size()*Math.random()));
				if(edge.from == parcours.getLast()){
					sommet = edge.to;
				}else{
					sommet = edge.from;
				}

				if(parcours.contains(sommet)){
					//System.out.println("boucle");
					position = parcours.indexOf(sommet);
					while(parcours.size()>position){
						parcours.removeLast();
					}
				}
				parcours.add(sommet);

				if(sommetsVisites.contains(sommet)){
					continuer = false;
				}

			}while(continuer);

			for(int j = 0; j < parcours.size()-1; j++){

				s1 = parcours.get(j);
				s2 = parcours.get(j+1);

				//System.out.print(s1+" ");

				adj = g.adj(s1);
				for(Edge e : adj){
					if(e.from == s2 || e.to == s2){
						arbre.add(e);
						//hashCode = hashCode * edgeId.get(e);
						break;
					}
				}
				sommetsNonVisites.remove(s1);
				sommetsVisites.add(s1);
			}


			//System.out.print(parcours.getLast()+" \n");
			sommetsNonVisites.remove(parcours.getLast());
			sommetsVisites.add(parcours.getLast());


		}
		//System.out.println(">>"+hashCode);
		//return hashCode;
		return arbre;
	}



	public static void wilsonXFois(int nb){
		HashMap<Integer,Integer> stats = new HashMap<Integer,Integer>();
		Integer resultat;
		for(int i = 0; i < nb; i++){
			resultat = wilsonHashCode(Graph.example());
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
