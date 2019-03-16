package P3;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.LinkedList;

public class FriendshipGraph {
    /*
     * Vertex to save the graph
     * use the adjacency list
     */
    public Map<Person, List<Person>> Vertex = new HashMap<> ();
    
    /*
     * add a vertex to the graph
     */
    public void addVertex(Person vertex) throws Exception{
        String name_new,name_now;
        name_new=vertex.getName();
        for (Map.Entry<Person, List<Person> > visit_Vertex : Vertex.entrySet()) {
            name_now=visit_Vertex.getKey().getName();
            if (name_new==name_now) {   //throw an exception if there are two identical points
                throw new Exception("Error:The same name!");
            }
        }
        List<Person> next = new ArrayList<> ();   //initialize the vertex
        Vertex.put(vertex,next);
    }
    
    /*
     * add a vertex to the graph
     */
    public void addEdge(Person vertex1,Person vertex2) throws Exception{
        int n,i,flag;
        if (vertex1==vertex2) {
            throw new Exception("Error:Self-loop!");   //throw an exception if there is a self-loop
        }
        if ((!Vertex.containsKey(vertex1)) || (!Vertex.containsKey(vertex2))) {
            throw new Exception("Error:Vertex not in graph!");   //throw an exception if vertex not add to the graph
        }
        flag=0;
        n=Vertex.get(vertex1).size();
        for (i=0;i<n;i++) {   //add edges to the graph and judge the repeating edges
            if (Vertex.get(vertex1).get(i)==vertex2) {
                flag=1;
                break;
            }
        }
        if (flag==0) Vertex.get(vertex1).add(vertex2);
    }
    
    /*
     * calculate the shortest distance between two vertices
     */
    public int getDistance(Person vertex1,Person vertex2) {
        int i,step=0;
        Person top,now;
        Queue<Person> Q=new LinkedList<> ();
        Queue<Integer> S=new LinkedList<> ();
        List<Person> vis=new ArrayList<> ();
        
        if (vertex1==vertex2) return 0;
        
        Q.add(vertex1);   //start from the starting point
        S.add(step);
        vis.add(vertex1);
        while(!Q.isEmpty()) {   //BFS
            top=Q.poll();
            step=S.poll();
            for (i=0;i<Vertex.get(top).size();i++) {
                now=Vertex.get(top).get(i);
                if (now==vertex2) return step+1;   //reach the destination
                if (!vis.contains(now)) {
                    Q.add(now);
                    vis.add(now);
                    S.add(step+1);   //save the distance
                }
            }
        }
        return -1;
    }
    
    /*
     * use the client implementation to complete main() method
     */
    public static void main(String[] args) throws Exception{
        FriendshipGraph graph = new FriendshipGraph();
        
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        
        System.out.println(graph.getDistance(rachel, ross));
        System.out.println(graph.getDistance(rachel, ben));
        System.out.println(graph.getDistance(rachel, rachel));
        System.out.println(graph.getDistance(rachel, kramer));
        
        
    }
}
