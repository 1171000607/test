package P3;

import static org.junit.Assert.*;
import org.junit.Test;

public class FriendshipGraphTest {
    @Test
    public void getDistanceTest() {
        FriendshipGraph A=new FriendshipGraph();
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
            A.addEdge(ross, rachel);
            A.addEdge(ross, ben);
            A.addEdge(ben, ross);
        }
        catch(Exception e){}
        
        System.out.println(A.getDistance(rachel, ross));
        assertEquals(1, A.getDistance(rachel, ross));
        assertEquals(2, A.getDistance(rachel, ben));
        assertEquals(0, A.getDistance(rachel, rachel));
        assertEquals(-1, A.getDistance(rachel, kramer));
    }
}
