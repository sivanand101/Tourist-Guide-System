package code;

public class Edge {
	int walk_dist;
	int driving_dist;
	int cycling_dist;
	float walk_time,driving_time,cycling_time;
	public Edge(){};
	public Edge(int wd,int dd,int cd,float wt,float dt,float ct){
		walk_dist=wd;
		driving_dist=dd;
		cycling_dist=cd;
		walk_time=wt;
		driving_time=dt;
		cycling_time=ct;
	}
	public Edge(Edge e) {
		walk_time=e.walk_time;
		walk_dist=e.walk_dist;
		cycling_time=e.cycling_time;
		cycling_dist=e.cycling_dist;
		driving_time=e.driving_time;
		driving_dist=e.driving_dist;
	}
	
}
