package modelisation;

import java.util.ArrayList;
import java.util.Collections;

public class AldousBroder {
	
	public static void main(String[] args){
		new AldousBroder().aldousBroder(Graph.example());
	}
	
	public void aldousBroder(Graph g) {
		ArrayList<Edge> list = g.edges();
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
}
