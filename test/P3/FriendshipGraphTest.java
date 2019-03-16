package P3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FriendshipGraphTest{
    private FriendshipGraph A=new FriendshipGraph();
    private Map<Person, List<Person> > Test = new HashMap<> ();
    private List<Person> next = new ArrayList<> ();
    
    Person rachel = new Person("Rachel");
    Person ross = new Person("Ross");
    Person ben = new Person("Ben");
    Person kramer = new Person("Kramer");
    
    Person a = new Person("a");
    Person b = new Person("b");
    Person c = new Person("c");
    Person d = new Person("d");
    Person e = new Person("e");
    
    @Test
    public void addVertexTest() {
        try{
            A.addVertex(rachel);
            Test.put(rachel, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(ross);
            Test.put(ross, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(ben);
            Test.put(ben, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(kramer);
            Test.put(kramer, next);
            assertEquals(Test,A.Vertex);
            
            
            A.addVertex(a);
            Test.put(a, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(b);
            Test.put(b, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(c);
            Test.put(c, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(d);
            Test.put(d, next);
            assertEquals(Test,A.Vertex);
            
            A.addVertex(e);
            Test.put(e, next);
            assertEquals(Test,A.Vertex);
        }
        catch(Exception E){
            System.out.println(E);
        }
    }
    
    @Test
    public void addEdgeTest() {
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
       
        try{
            A.addVertex(rachel);
            A.addVertex(ross);
            A.addVertex(ben);
            A.addVertex(kramer);
            
            A.addEdge(rachel, ross);
            Test.get(rachel).add(ross);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(ross, rachel);
            Test.get(ross).add(rachel);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(ross, ben);
            Test.get(ross).add(ben);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(ben, ross);
            Test.get(ben).add(ross);
            assertEquals(Test,A.Vertex);
            
            
            A.addEdge(rachel, ben);
            Test.get(rachel).add(ben);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(ben, rachel);
            Test.get(ben).add(rachel);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(rachel, a);
            Test.get(rachel).add(a);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(a, rachel);
            Test.get(a).add(rachel);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(ross, a);
            Test.get(ross).add(a);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(a, ross);
            Test.get(a).add(ross);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(a, b);
            Test.get(a).add(b);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(b, a);
            Test.get(b).add(a);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(kramer, c);
            Test.get(kramer).add(c);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(c, kramer);
            Test.get(c).add(kramer);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(c, d);
            Test.get(c).add(d);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(d, c);
            Test.get(d).add(c);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(rachel, a);
            Test.get(rachel).add(a);
            assertEquals(Test,A.Vertex);
            
            A.addEdge(c, e);
            Test.get(c).add(e);
            assertEquals(Test,A.Vertex);
        }
        catch(Exception E){
            System.out.println(E);
        }
    }
    
    @Test
    public void getDistanceTest() {
        try{
            A.addVertex(rachel);
            A.addVertex(ross);
            A.addVertex(ben);
            A.addVertex(kramer);
            
            A.addVertex(a);
            A.addVertex(b);
            A.addVertex(c);
            A.addVertex(d);
            A.addVertex(e);
            
            A.addEdge(rachel, ross);
            A.addEdge(rachel, ross);
            A.addEdge(ross, rachel);
            A.addEdge(ross, ben);
            A.addEdge(ben, ross);
            
            A.addEdge(rachel, ben);
            A.addEdge(ben, rachel);
            A.addEdge(rachel, a);
            A.addEdge(a, rachel);
            A.addEdge(ross, a);
            A.addEdge(a, ross);
            A.addEdge(a, b);
            A.addEdge(b, a);
            A.addEdge(kramer, c);
            A.addEdge(c, kramer);
            A.addEdge(c, d);
            A.addEdge(d, c);
            A.addEdge(rachel, a);
            A.addEdge(c, e);
        }
        catch(Exception E){}
 
        assertEquals(1, A.getDistance(rachel, ross));
        assertEquals(0, A.getDistance(rachel, rachel));
        assertEquals(-1, A.getDistance(rachel, kramer));
        assertEquals(1, A.getDistance(rachel, ben));
        assertEquals(1, A.getDistance(a, ross));
        assertEquals(3, A.getDistance(ben, b));
        assertEquals(2, A.getDistance(kramer, e));
        assertEquals(-1, A.getDistance(e, kramer));
        assertEquals(2, A.getDistance(ben, a));
        assertEquals(-1, A.getDistance(e, d));
    }
}
