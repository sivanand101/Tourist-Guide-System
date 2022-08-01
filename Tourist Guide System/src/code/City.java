package code;
import java.util.*;
import code.Edge;
import code.Node;
public class City {
	Node place[];
	Edge path[][];
	public City() {}
	public City(String s) {
		createPlaces();
		createPaths();
	}
	public void createPlaces() {
		place = new Node[12];
		place[0] = new Node("Baga Beach");
		place[1] = new Node("Vasco Da Gama");
		place[2] = new Node("Dudhsagar Falls");
		place[3] = new Node("Latin Quarter");
		place[4] = new Node("Chapora Falls");
		place[5] = new Node("Amboli Waterfalls");
		place[6] = new Node("Palolem Beach");
		place[7] = new Node("Agonda Beach");
		place[8] = new Node("Corjuem Fort");
		place[9] = new Node("Velsao Beach");
		place[10] = new Node("Immaculate Conception Church");
		place[11] = new Node("Escobar");
	}
	public void createPaths() {
		path = new Edge[12][12];
		int i,j;
	    for(i=0;i<12;i++)
	    {
	        for(j=0;j<12;j++)
	        {
	            if(i==j)
	            {
	              path[i][j] = new Edge(0,0,0,0.0F,0.0F,0.0F);
	            }
	            else if(i<j)
	            {
	              int wd=(int)(Math.random()%40);
	              int dd=(int)(Math.random()%40);
	              int cd=path[i][j].walk_dist;
	              float wt=wd/5.0F;
	              float ct=cd/25.0F;
	              float dt=dd/60.0F;
	              path[i][j]=new Edge(wd,dd,cd,wt,dt,ct);
	            }
	        }
	    }

	    for(i=0;i<12;i++) {
	    	for(j=0;j<12;j++){
		    	if(i>j){
		         path[i][j]= new Edge(path[j][j]);
		        }
		    }
	    }    
	}
	public void walk_info()
	{
	    int i,j;
	    for(i=0;i<12;i++) {
	    	for(j=0;j<12;j++){
	    		if(path[i][j].walk_dist!=0)
		   	        System.out.println(place[i]+ " to "+place[j]+" = "+path[j][j].walk_dist+" Km. and "+path[i][j].walk_time+" Hr.");
	    	}
	    }   
	}
	public void driving_info()
	{
	    int i,j;
	    for(i=0;i<12;i++) {
	    	for(j=0;j<12;j++){
	    		if(path[i][j].driving_dist!=0)
		   	        System.out.println(place[i]+ " to "+place[j]+" = "+path[j][j].driving_dist+" Km. and "+path[i][j].driving_time+" Hr.");
	    	}
	    }   
	}
	public void cycling_info()
	{
	    int i,j;
	    for(i=0;i<12;i++) {
	    	for(j=0;j<12;j++){
	    		if(path[i][j].cycling_dist!=0)
		   	        System.out.println(place[i]+ " to "+place[j]+" = "+path[j][j].cycling_dist+" Km. and "+path[i][j].cycling_time+" Hr.");
	    	}
	    }   
	}
	private int min_dist(int cost[],int vis[])
	{
		  int i,ind,min;
		  for(i=0;i<12;i++)
		  {
		        if(vis[i]==0)
		            break;
		  }
		  ind=i;
		  min=cost[i];
		  i += 1;
		  for(;i<12;i++)
		  {
		      if(vis[i]==0)
		      {
		          if(cost[i]<min)
		          {
		              ind=i;
		              min=cost[i];
		          }

		      }
		  }
		return (ind);
	}
	public void shortest_walking(int s,int d)
	{
	  int c[]=new int[12],vis[]=new int[12],i,u,j,p[]=new int[12];

	  for(i=0;i<12;i++)
	  {
	    if(i==s)
	        c[i]=0;
	    else
	        c[i]=Integer.MAX_VALUE;
	    vis[i]=0;
	  }

	  p[s]=-1;

	  for(i=0;i<12-1;i++)
	  {
	     u=min_dist(c,vis);  //Selecting Nearest vertex

	     vis[u]=1;

	     for(j=0;j<12;j++)   //Relaxation
	     {
	         if(path[u][j].walk_dist != 0 && vis[j] == 0)
	         {
	             if(path[u][j].walk_dist + c[u] < c[j])
	             {
	                c[j] = c[u] + path[u][j].walk_dist;
	                p[j]=u;
	             }
	         }
	     }
	  }
	  int fn=11,ar[]=new int[12],fnf=c[d];
	  ar[fn]=d;
	  do
	  {
	      ar[--fn]=p[d];
	      d=p[d];
	  }while(p[d]!=-1);
	  for(;fn<11;fn++)
	    System.out.print(place[ar[fn]].name+"--->");
	  System.out.println(place[ar[11]].name);
	  System.out.println("Total walking time ="+fnf+" hr");
	}
	public void shortest_driving(int s,int d)
	{
	  int c[]=new int[12],vis[]=new int[12],i,u,j,p[]=new int[12];

	  for(i=0;i<12;i++)
	  {
	    if(i==s)
	        c[i]=0;
	    else
	        c[i]=Integer.MAX_VALUE;
	    vis[i]=0;
	  }

	  p[s]=-1;

	  for(i=0;i<12-1;i++)
	  {
	     u=min_dist(c,vis);  //Selecting Nearest vertex

	     vis[u]=1;

	     for(j=0;j<12;j++)   //Relaxation
	     {
	         if(path[u][j].driving_dist != 0 && vis[j] == 0)
	         {
	             if(path[u][j].driving_dist + c[u] < c[j])
	             {
	                c[j] = c[u] + path[u][j].driving_dist;
	                p[j]=u;
	             }
	         }
	     }
	  }
	  int fn=11,ar[]=new int[12],fnf=c[d];
	  ar[fn]=d;
	  do
	  {
	      ar[--fn]=p[d];
	      d=p[d];
	  }while(p[d]!=-1);
	  for(;fn<11;fn++)
	    System.out.print(place[ar[fn]].name+"--->");
	  System.out.println(place[ar[11]].name);
	  System.out.println("Total Driving time ="+fnf+" hr");
	}
	public void shortest_cycling(int s,int d)
	{
	  int c[]=new int[12],vis[]=new int[12],i,u,j,p[]=new int[12];

	  for(i=0;i<12;i++)
	  {
	    if(i==s)
	        c[i]=0;
	    else
	        c[i]=Integer.MAX_VALUE;
	    vis[i]=0;
	  }

	  p[s]=-1;

	  for(i=0;i<12-1;i++)
	  {
	     u=min_dist(c,vis);  //Selecting Nearest vertex

	     vis[u]=1;

	     for(j=0;j<12;j++)   //Relaxation
	     {
	         if(path[u][j].cycling_dist != 0 && vis[j] == 0)
	         {
	             if(path[u][j].cycling_dist + c[u] < c[j])
	             {
	                c[j] = c[u] + path[u][j].cycling_dist;
	                p[j]=u;
	             }
	         }
	     }
	  }
	  int fn=11,ar[]=new int[12],fnf=c[d];
	  ar[fn]=d;
	  do
	  {
	      ar[--fn]=p[d];
	      d=p[d];
	  }while(p[d]!=-1);
	  for(;fn<11;fn++)
	    System.out.print(place[ar[fn]].name+"--->");
	  System.out.println(place[ar[11]].name);
	  System.out.println("Total Cycling time ="+fnf+" hr");
	}
}
