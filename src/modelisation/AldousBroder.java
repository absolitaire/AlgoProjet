package modelisation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AldousBroder {
	
	public static final int[] premiers = {2  , 3  , 5  , 7 , 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41, 
			43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101, 103, 107, 109, 113,
			127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229};

	public static void main(String[] args){
		//new AldousBroder().aldousBroder(Graph.example());
		aldousBroderXFois(1000000);
	}
	
	public static void aldousBroder(Graph g) {
		//ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		
		int actuel = (int)Math.random() * (g.getV() + 1);
		trace.add(actuel);
		ArrayList<Edge> adja;
		while(trace.size() != g.getV()) {
			adja = g.adj(actuel);
			Collections.shuffle(adja);
			if(adja.get(0).from == actuel) {
				if(! trace.contains(adja.get(0).to)){
					trace.add(adja.get(0).to);
					arbre.add(adja.get(0));
					System.out.println("ajout de "+adja.get(0).from+"---"+adja.get(0).to);
				}
				actuel = adja.get(0).to;
			}else {
				if(! trace.contains(adja.get(0).from)){
					trace.add(adja.get(0).from);
					arbre.add(adja.get(0));
					System.out.println("ajout de "+adja.get(0).from+"---"+adja.get(0).to);
				}
				actuel = adja.get(0).from;
			}
		}
	}
	
	
	public static int aldousBroderHashCode(Graph g) {
		ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Integer> trace = new ArrayList<Integer>();

		HashMap<Edge,Integer> edgeId = new HashMap<Edge,Integer>();
		int premiersId = -1;
		int hashCode = 1;

		for(Edge e : list){
			premiersId++;
			edgeId.put(e, premiers[premiersId]);
		}
		
		int actuel = (int)Math.random() * (g.getV() + 1);
		trace.add(actuel);
		ArrayList<Edge> adja;
		while(trace.size() != g.getV()) {
			adja = g.adj(actuel);
			Collections.shuffle(adja);
			if(adja.get(0).from == actuel) {
				if(! trace.contains(adja.get(0).to)){
					trace.add(adja.get(0).to);
					arbre.add(adja.get(0));
					//System.out.println("ajout de "+adja.get(0).from+"---"+adja.get(0).to);
					
					hashCode = hashCode * edgeId.get(adja.get(0));
				}
				actuel = adja.get(0).to;
			}else {
				if(! trace.contains(adja.get(0).from)){
					trace.add(adja.get(0).from);
					arbre.add(adja.get(0));
					//System.out.println("ajout de "+adja.get(0).from+"---"+adja.get(0).to);

					hashCode = hashCode * edgeId.get(adja.get(0));
					
				}
				actuel = adja.get(0).from;
			}
		}
		return hashCode;
	}
	
	public static void aldousBroderXFois(int nb){
		HashMap<Integer,Integer> stats = new HashMap<Integer,Integer>();
		Integer resultat;
		for(int i = 0; i < nb; i++){
			resultat = aldousBroderHashCode(Graph.example());
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
