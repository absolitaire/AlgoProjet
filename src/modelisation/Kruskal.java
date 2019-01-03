package modelisation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class Kruskal {

	public static void main(String[] args){
		new Kruskal().kruskal2(Graph.example());
	}

	public void kruskal(Graph g){
		ArrayList<Edge> list = g.edges();
		ArrayList<Edge> arbre = new ArrayList<Edge>();
		ArrayList<Integer> trace = new ArrayList<Integer>();
		ArrayList<Integer> trace2 = new ArrayList<Integer>();
		boolean ajouter;
		int origine, destination;

		Collections.shuffle(list);
		
		for(Edge e : list){
			origine = e.from;
			destination = e.to; //System.out.println(destination);
			ajouter = true;
			int point;

			trace2.clear();
			trace.clear(); //g.adj(origine)
			for(Edge edg : arbre){
				if(edg.from == origine){
					//System.out.println(edg.to);
					trace.add(edg.to);
				}else{
					//System.out.println(edg.from);
					trace.add(edg.from);					
				}
			}

			while(!trace.isEmpty() && ajouter == true){
				point = trace.remove(0);
				if(point == destination){
					ajouter = false;
					trace.clear();
					System.out.println("cut from"+e.from+" to "+e.to+"  origine "+origine+" destination  "+destination);
					
				}else{//g.adj(point)
					for(Edge edg : arbre){
						if(edg.from == point){
							if(!trace2.contains(edg.to)){
								trace.add(edg.to);
								trace2.add(edg.to);
								if(point == edg.to){
									ajouter = false;
									trace.clear();
									System.out.println("cut2");

								}
							}

						}else{
							if(!trace2.contains(edg.from)){
								trace.add(edg.from);
								trace2.add(edg.from);	
								if(point == edg.from){
									ajouter = false;
									trace.clear();
									System.out.println("cut3");

								}
							}
						}
					}
				}

			}
			if(ajouter){
				arbre.add(e);
				System.out.println("-------------"+e.from+" "+e.to);
			}
		}

	}
	
	public ArrayList<Edge> kruskal2(Graph g){
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
			System.out.println("Origine "+origine +" | Destination "+destination);
			for(Edge e2: arbre) {
				trace.add(e2.from);
				trace.add(e2.to);	
			}
			if(trace.contains(origine) && trace.contains(destination)) {
				if(!verifierSeparation(e, arbre)) {
					ajouter = false;
					System.out.println("cut de "+e.from+"---"+e.to);
				}
				
			}	
			if(ajouter) {
				System.out.println("ajout de "+e.from+"---"+e.to);
				arbre.add(e);
			}
		}
		return arbre;
	}
	
	public boolean verifierSeparation(Edge ed, ArrayList<Edge> arbre) {
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
		}else {
			return true;
		}
	}
}






