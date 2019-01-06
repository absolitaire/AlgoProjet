package modelisation;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Labyrinthe {

	public  final static int TAILLE = 20;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.println("1: Labyrinthe 20x20 Kruskal");
		System.out.println("2: Labyrinthe 20x20 Wilson");
		System.out.println("3: Stats de 1000 labyrinthes 20x20 Kruskal");
		System.out.println("4: Stats de 1000 labyrinthes 20x20 Wilson");
		int cmd = -1;
		while(cmd < 1 || cmd > 4){
			cmd = s.nextInt();
		}

		if(cmd == 1 || cmd == 2){
			Graph g =new Graph(TAILLE * TAILLE);
			int n = 0, x = 0, y = 0;
			while(n < TAILLE * TAILLE){
				g.setCoordinate(n, 30+x*40,30+ y*40);
				y++;
				if(y == TAILLE){
					y = 0;
					x++;
				}
				n++;
			}
			if(cmd == 1){
				/*for(Edge e : Kruskal.kruskal(Graph.Grid(TAILLE))){
					g.addEdge(e);
				};*/
				
				int culDeSacs = 0; 
				int sommeCulsDeSacs = 0;
				int sommeDistance = 0;
				ArrayList<Edge> arbre;
				int[] nbAdj;
				Graph gr;

				final int nbFois = 1;

				for(int j = 0; j < nbFois; j++){
					if(cmd ==1){
						arbre = Kruskal.kruskal(Graph.Grid(TAILLE));
					}else{
						arbre = Wilson.wilson(Graph.Grid(TAILLE));
					}
					for(Edge e : arbre){
						g.addEdge(e);
					};
					nbAdj = new int[TAILLE * TAILLE];
					gr = new Graph(TAILLE * TAILLE);

					for(Edge e : arbre){
						nbAdj[e.from]++;
						nbAdj[e.to]++;
						g.addEdge(e);
					};



					//on ne consid�re pas la case d'entr�e et la case de sortie comme des culs de sacs
					for(int i = 1; i < TAILLE * TAILLE -1; i++){
						if(nbAdj[i]==1){
							culDeSacs ++;
						}

					}
					//System.out.println(culDeSacs);
					sommeCulsDeSacs += culDeSacs;

					//System.out.println(parcoursLabyrinthe(g, 0, 0, -1));
					sommeDistance += parcoursLabyrinthe(gr, 0, 0, -1);
				}
				
				
			}else{
				for(Edge e : Wilson.wilson(Graph.Grid(TAILLE))){
					g.addEdge(e);
				};
			}

			Display d = new Display();
			d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			d.setImage(g.toImage());
			System.out.println("Le resultat est affiche");
		}else{
			int culDeSacs = 0; 
			int sommeCulsDeSacs = 0;
			int sommeDistance = 0;
			ArrayList<Edge> arbre;
			int[] nbAdj;
			Graph g;

			final int nbFois = 10;

			for(int j = 0; j < nbFois; j++){
				if(cmd ==3){
					arbre = Kruskal.kruskal(Graph.Grid(TAILLE));
				}else{
					arbre = Wilson.wilson(Graph.Grid(TAILLE));
				}

				nbAdj = new int[TAILLE * TAILLE];
				g = new Graph(TAILLE * TAILLE);

				for(Edge e : arbre){
					nbAdj[e.from]++;
					nbAdj[e.to]++;
					g.addEdge(e);
				};



				//on ne consid�re pas la case d'entr�e et la case de sortie comme des culs de sacs
				for(int i = 1; i < TAILLE * TAILLE -1; i++){
					if(nbAdj[i]==1){
						culDeSacs ++;
					}

				}
				//System.out.println(culDeSacs);
				sommeCulsDeSacs += culDeSacs;

				//System.out.println(parcoursLabyrinthe(g, 0, 0, -1));
				sommeDistance += parcoursLabyrinthe(g, 0, 0, -1);
			}

			System.out.println("Moyenne de culs de sac: "+sommeCulsDeSacs/nbFois);

			System.out.println("Moyenne de distance de l'entree a la sortie: "+sommeDistance/nbFois);

		}


		s.close();
	}



	public static int parcoursLabyrinthe(Graph g, int dist, int position, int precedent){
		//System.out.println(dist+" "+position+" "+precedent);
		if(position == TAILLE * TAILLE -1) return dist;
		int resultat = -1;
		for(Edge e : g.adj(position)){
			if(e.to == position){
				if(e.from != precedent){
					resultat = parcoursLabyrinthe(g, dist+1, e.from, e.to);
				}

			}else{
				if(e.to != precedent){
					resultat = parcoursLabyrinthe(g, dist+1, e.to, e.from);
				}

			}
			if(resultat > -1) return resultat;
		}
		return -1;
	}

}
