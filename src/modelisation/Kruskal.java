package modelisation;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

	public static void main(String[] args){
		new Kruskal().kruskal(Graph.example());
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
					System.out.println(edg.to);
					trace.add(edg.to);
				}else{
					System.out.println(edg.from);
					trace.add(edg.from);					
				}
			}

			while(!trace.isEmpty() && ajouter == true){
				point = trace.remove(0);
				if(point == destination){
					ajouter = false;
					trace.clear();
					System.out.println("cut"+e.from+" "+e.to+" "+origine+" "+destination);

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

}
