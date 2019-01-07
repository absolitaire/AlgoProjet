package modelisation;

import java.util.Scanner;

public class Partie2Q15 {
	public int nbCombinaisons = 0;

	//ArrayList<Integer>

	public static void main(String[] args){
		Partie2Q15 p = new Partie2Q15();
		Scanner sc = new Scanner(System.in);
		System.out.println("K?");
		int k = sc.nextInt();
		System.out.println("N?");
		int n = sc.nextInt();
		System.out.println("B?");
		int b = sc.nextInt();
		System.out.println("M?");
		int m = sc.nextInt();
		
		
		//p.evaluation(4, 4, 0, 4));
		System.out.println("C("+k+","+n+","+b+","+m+")="+p.evaluation(k, n, b, m));
		sc.close();
	}

	public int evaluation(int k, int n, int b, int m){
		nbCombinaisons = 0;
		//System.out.println((k-n)+" "+b+" "+m+" "+(n-b-m));
		parcours(k-n, n, b, m, n-b-m, 0, 0);

		return nbCombinaisons;
	}


	public void parcours(int couleursInutilisees, int casesRestantes, int bonnes, int mauvaises, int absentes, int mauvaisesGauche, int mauvaisesDroite){
		//ArrayList<Integer> cases;
		if(bonnes > 0){
			//System.out.println("b "+casesRestantes);
			//couleur bonne
			parcours(couleursInutilisees, casesRestantes -1, bonnes-1, mauvaises, absentes, mauvaisesGauche, mauvaisesDroite);
		}
		if(mauvaises > 0){
			//mauvaise vers la droite, nouvelle absente
			for(int i = 0; i < couleursInutilisees; i++){
				//System.out.println("md a "+casesRestantes);
				parcours(couleursInutilisees-1, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche, mauvaisesDroite +1);

			}
			//mauvaise vers la droite, nouvelle vient de gauche
			for(int i = 0; i < mauvaisesDroite; i++){
				//System.out.println("md ng "+casesRestantes);
				parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche, mauvaisesDroite +1-1);

			}
			//mauvaise vers la droite, nouvelle vient de droite
			//for(int i = 0; i < mauvaisesGauche; i++){
			//System.out.println("md nd "+casesRestantes);
			parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche+1, mauvaisesDroite +1);
			//}

			//mauvaises vers la gauche
			for(int j = 0; j < mauvaisesGauche; j++){
				//mauvaise vers la gauche, nouvelle absente
				for(int i = 0; i < couleursInutilisees; i++){
					//System.out.println("mg a "+casesRestantes);
					parcours(couleursInutilisees-1, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche-1, mauvaisesDroite);

				}
				//mauvaise vers la gauche, nouvelle vient de gauche
				for(int i = 0; i < mauvaisesDroite; i++){
					//System.out.println("mg ng "+casesRestantes);
					parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche-1, mauvaisesDroite -1);

				}
				//mauvaise vers la gauche, nouvelle vient de droite
				for(int i = 0; i < mauvaisesGauche; i++){
					//System.out.println("mg nd "+casesRestantes);
					parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises-1, absentes, mauvaisesGauche+1-1, mauvaisesDroite );
				}
			}

		}

		if(absentes > 0){

			//absente, nouvelle absente
			for(int i = 0; i < couleursInutilisees; i++){
				//System.out.println("a a "+casesRestantes);
				parcours(couleursInutilisees-1, casesRestantes -1, bonnes, mauvaises, absentes-1, mauvaisesGauche, mauvaisesDroite);

			}
			//absente, nouvelle vient de gauche
			for(int i = 0; i < mauvaisesDroite; i++){
				//System.out.println("a ng "+casesRestantes);
				parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises, absentes-1, mauvaisesGauche, mauvaisesDroite -1);

			}
			//absente, nouvelle vient de droite
			//for(int i = 0; i < mauvaisesGauche; i++){
			//System.out.println("a nd "+casesRestantes);
			parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises, absentes-1, mauvaisesGauche+1, mauvaisesDroite );
			//}



		}
		//Si la combinaison est correcte (condition d'arret)
		if(bonnes == 0 && mauvaises == 0 && absentes ==0 &&  mauvaisesGauche == 0 && mauvaisesDroite == 0){
			//System.out.println("solution");
			nbCombinaisons ++;
		}
	}
	

	/*public void parcours(int couleursInutilisees, int casesRestantes, int bonnes, int mauvaises, int absentes){
		//ArrayList<Integer> cases;
		if(bonnes > 0){
			parcours(couleursInutilisees, casesRestantes -1, bonnes-1, mauvaises, absentes);
		}
		if(mauvaises > 0){
			for(int i = 0; i < casesRestantes; i++){
				parcours(couleursInutilisees, casesRestantes -1, bonnes, mauvaises-1, absentes);
			}

		}
		if(absentes > 0){
			for(int i = 0; i < couleursInutilisees; i++){
				parcours(couleursInutilisees-1, casesRestantes -1, bonnes, mauvaises, absentes-1);
			}
		}
		if(bonnes == 0 && mauvaises == 0 && absentes ==0){
			nbCombinaisons ++;
		}
	}*/


	/*
	public void parcours(int couleursInutilisees, int casesRestantes, int bonnes, int mauvaises, int absentes){
		//ArrayList<Integer> cases;
		if(bonnes > 0){
			System.out.println("b "+casesRestantes);
			parcours(couleursInutilisees, casesRestantes -1, bonnes-1, mauvaises, absentes);
			//System.out.println("ayy");
		}
		if(mauvaises >= 2){
			for(int i = 0; i < casesRestantes-1; i++){
				System.out.println("m2 "+casesRestantes);
				parcours(couleursInutilisees, casesRestantes -2, bonnes, mauvaises-2, absentes);

			}

		}
		if(mauvaises >= 1 && absentes >= 1){
			//System.out.println("ldldl"+((casesRestantes-1) * couleursInutilisees));
			for(int i = 0; i < (casesRestantes-1) * couleursInutilisees; i++){
				System.out.println("ma "+casesRestantes);
				parcours(couleursInutilisees-1, casesRestantes -2, bonnes, mauvaises-1, absentes-1);
				System.out.println("am "+casesRestantes);
				parcours(couleursInutilisees-1, casesRestantes -2, bonnes, mauvaises-1, absentes-1);
			}

		}
		if(absentes > 0){
			for(int i = 0; i < couleursInutilisees; i++){
				System.out.println("a "+casesRestantes);
				parcours(couleursInutilisees-1, casesRestantes -1, bonnes, mauvaises, absentes-1);
			}
		}
		if(bonnes == 0 && mauvaises == 0 && absentes ==0){
			System.out.println("solution");
			nbCombinaisons ++;
		}
	}
	 */
}
