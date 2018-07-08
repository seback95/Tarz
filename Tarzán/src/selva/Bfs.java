package selva;

import java.util.ArrayList;
import java.util.LinkedList;

public class Bfs {
	private Punto grafo[][];
	public Bfs(Selva sel)
	{
		grafo=new Punto[sel.getSize()][sel.getSize()];
		for(int i=0;i<sel.getSize();i++)
		{
			for(int j=0;j<sel.getSize();j++)
			{
				grafo[i][j]=sel.getPunto(i, j);
				grafo[j][i]=sel.getPunto(i, j);
			}
		
			}
			
	}

	
	public void bfs(int []actualizados,ArrayList<Arista> aristaProhibida)
	{
		LinkedList<Integer> cola=new LinkedList<Integer>();
		boolean[] visit=new boolean[grafo.length];
		int [] actualizado=new int [grafo.length];
		actualizado[0]=0;
		visit[0]=true;
		cola.add(0);
		while(!cola.isEmpty())
		{
			int actual=cola.removeFirst();
			for(int i=0; i< grafo.length ; i++)
			{
				if(grafo[actual][i]!=null && !visit[i]&& !esAristaProhibida(actual,i,aristaProhibida) )
				{
					visit[i]=true;
					actualizados[i]=actual;
					cola.add(i);
					
				}
				
					
			}
		}
	}

public boolean esAristaProhibida(int act,int destino,ArrayList<Arista>  prh)
{	
	if(prh.size()==0)
		return false;
	else
	{
		for(int i=0;i<prh.size();i++)
		{
			if(prh.get(i).getnDest()==destino&&prh.get(i).getnOrig()==act)
				return true;
		}
	}
	return false;
}











}