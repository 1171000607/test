package P3;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class FriendshipGraph {
    private Map<Person, List<Person>> Vertex = new HashMap<> ();
    
    public void addVertex(Person vertex) throws Exception{
        if (Vertex.containsKey(vertex)) {
            throw new Exception("Error:Same Name!");
        }
        else {
            List<Person> next = new ArrayList<> ();
            Vertex.put(vertex,next);
        }
    }
    
    public void addEdge(Person vertex1,Person vertex2) {
        int n,i,flag;
        flag=0;
        n=Vertex.get(vertex1).size();
        for (i=0;i<n;i++) {
            if (Vertex.get(vertex1).get(i)==vertex2) {
                flag=1;
                break;
            }
        }
        if (flag==0) Vertex.get(vertex1).add(vertex2);
    }
    
    public int getDistance(Person vertex1,Person vertex2) {
        int i,step=0;
        Person top,now;
        Queue<Person> Q=new LinkedList<> ();
        Queue<Integer> S=new LinkedList<> ();
        List<Person> vis=new ArrayList<> ();
        
        if (vertex1==vertex2) return 0;
        
        Q.add(vertex1);
        S.add(step);
        vis.add(vertex1);
        while(!Q.isEmpty()) {
            top=Q.poll();
            step=S.poll();
            for (i=0;i<Vertex.get(top).size();i++) {
                now=Vertex.get(top).get(i);
                if (now==vertex2) return step+1;
                if (!vis.contains(now)) {
                    Q.add(now);
                    vis.add(now);
                    S.add(step+1);
                }
            }
        }
        return -1;
    }
    
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
