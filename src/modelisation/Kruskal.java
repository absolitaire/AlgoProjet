package modelisation;

import java.util.ArrayList;
import java.util.Collections;

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
				ajouter = false;
				System.out.println("cut de "+e.from+"---"+e.to);
			}	
			if(ajouter) {
				System.out.println("ajout de "+e.from+"---"+e.to);
				arbre.add(e);
			}
		}
		return arbre;
	}
}
