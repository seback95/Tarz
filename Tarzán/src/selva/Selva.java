package selva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class Selva {
	private Punto mat[][];
	private int ini, fin;
	private ArrayList<Punto> puntos;

	public Selva(String name) {
		int x, y;
		puntos = new ArrayList<Punto>();
		String linea;
		String datos[];
		try {
			File f = new File(name);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((linea = br.readLine()) != null) {
				datos = linea.split(" ");
				x = Integer.parseInt(datos[0]);
				y = Integer.parseInt(datos[1]);
				puntos.add(new Punto(x, y));
			}
			ini = 0;
			fin = puntos.size() - 1;

			mat = new Punto[puntos.size()][puntos.size()];
			for (int i = 0; i < puntos.size(); i++) {
				for (int j = 0; j < puntos.size(); j++)
					mat[i][j] = null;
			}
			int cont = 0;
			double dis;
			for(int j=0;j < puntos.size()-1;j++)
			{
				Punto nod = puntos.get(cont);
				for (int i = j +1; i < puntos.size(); i++) {
					if ((dis = distancia(nod, puntos.get(i))) <= 100)// descarto una distancia mayor a 100
					{
						mat[cont][i] = new Punto(puntos.get(i), dis);
						mat[i][cont] = new Punto(puntos.get(i), dis);
					}
				}
				cont++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static double distancia(Punto p1, Punto p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}

	public void resolver(String name) {
		ArrayList<Arista> aristasProhibidas=new ArrayList<Arista>(); 
		ArrayList<Integer> camino = new ArrayList<Integer>();
		Bfs saltos = new Bfs(this);
		int[] actualizados = new int[mat.length];
		Arista arista = null;
		saltos.bfs(actualizados, aristasProhibidas);

		try {
			FileWriter fw = new FileWriter(name);
			PrintWriter pw = new PrintWriter(fw);
			
			while ((arista = esCaminoErroneo(actualizados)) != null) {
				aristasProhibidas.add(arista);
				setearVectorAcero(actualizados); // seteo todo el vector en cero para despues corroborar que pudo llegar al nodofinal(osea si el nodo final esta en cero quiere decir que no encontro camino)
				saltos.bfs(actualizados, aristasProhibidas);
				if (actualizados[fin] == 0)
					pw.println("No existe camino");
			}
			int origen = actualizados[fin];
			int destino = fin;
			obtenerCamino(camino,actualizados,ini,fin);		
			for (int i = camino.size()-1; i>= 0; i--)
				pw.println(puntos.get(camino.get(i)).getX()+" "+puntos.get(camino.get(i)).getY());
			
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void obtenerCamino(ArrayList<Integer> camino,int[]actualizados,int ini,int fin)
	{
		int origen=actualizados[fin],destino=fin;
		while (origen != destino) {
			camino.add(destino);
			destino = origen;
			origen = actualizados[origen];
		}
		camino.add(0);
	}
public static void setearVectorAcero(int []vec)
{
	for(int i=0;i<vec.length;i++)
		vec[i]=0;
}
	private Arista esCaminoErroneo(int[] actualizados) {
		
		Arista aristaProhibida=null;
		int cont = 000000000000000000000000000000000; // necesito muchos ceros para mas placer :)
		int origen = actualizados[fin];
		int destino = fin;
		while (origen != destino) {

			if (mat[origen][destino].getDistancia() > 50) {
				cont++;
				aristaProhibida = new Arista(origen, destino, mat[origen][destino].getDistancia());
			}
			destino = origen;
			origen = actualizados[origen];
		}
		if (cont > 1)
			return aristaProhibida;
		else
			return null;
	}

	public int getSize() {
		return mat.length;
	}

	public Punto getPunto(int i, int j) {
		return mat[i][j];
	}

	

}
