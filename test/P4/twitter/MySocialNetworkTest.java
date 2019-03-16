package P4.twitter;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MySocialNetworkTest {
    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T06:13:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T23:59:00Z");
    private static final Instant d5 = Instant.parse("2016-02-18T00:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-18T14:28:00Z");
    private static final Instant d7 = Instant.parse("2016-02-17T05:37:42Z");
    
    private static final Tweet tweet1 = new Tweet(1, "cyberdusttv", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #bitcoin price", d2);
    private static final Tweet tweet3 = new Tweet(3, "jeanOrlando5", "RT @RealJamesWoods: The feminists got what they asked for, and what they deserved. All future \\u201cwomen\\u2019s\\u201d Olympics events will be tarnished w\\u2026?", d3);
    private static final Tweet tweet4 = new Tweet(4, "exit___strategy", "RT @swiftcashcc: Geographical locations of SwiftNodes! Current ROI for running a SwiftNode is more than 100% per annum! Each SwiftNode requ\\u2026", d6);
    private static final Tweet tweet5 = new Tweet(5, "Glockehara", "$56.5 arb between bittrex and coinbase #bitcoin #bitcoinprice", d4);
    private static final Tweet tweet6 = new Tweet(6, "DerWinky", "https://t.co/Wyqq9LyYwC\\n\\nMario Party mit @cyberdusttv @Glockehara @Lukbertbert", d5);
    private static final Tweet tweet7 = new Tweet(7, "alyssa", "is it reasonable to talk about rivest so much?", d7);
    private static final Tweet tweet8 = new Tweet(8, "Calzinirossi23","RT @lesbianoutsider: Yes, Ladies, go see #CaptainMarvel #bitcoin. Women can be Super Heroes too. But don't forget, Biological Males can be Faux Fema\u2026", d3);
    private static final Tweet tweet9 = new Tweet(9, "lesbianoutsider","Yes, Ladies, go see #CaptainMarvel. Women can be Super Heroes too. But don't forget, Biological Males can be Faux F\\u2026 https://t.co/mLi5bZzgGc",d6);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6,tweet7,tweet8,tweet9));
        Map<String, Set<String>> Test = new HashMap<> ();
        
        Set<String> next = new HashSet<> ();
        next.add("realjameswoods");
        Test.put("jeanorlando5",next);
        //System.out.println(Test);
        
        next = new HashSet<> ();
        next.add("swiftcashcc");
        Test.put("exit___strategy",next);
        //System.out.println(Test);
        
        next = new HashSet<> ();
        next.add("cyberdusttv");
        next.add("glockehara");
        next.add("lukbertbert");
        Test.put("derwinky",next);
        //System.out.println(Test);
        
        next = new HashSet<> ();
        next.add("lesbianoutsider");
        Test.put("calzinirossi23",next);
        
        assertEquals(followsGraph,Test);
        
    }
    
    @Test
    public void testMySocialNetwork() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6,tweet7,tweet8,tweet9));
        List<String> Test = new ArrayList<> ();
        List<String> influencers = SocialNetwork.MySocialNetwork(followsGraph);
        
        Test.add("calzinirossi23");
        Test.add("glockehara");
        Test.add("bbitdiddle");
        Test.add("cyberdusttv");
        Test.add("lesbianoutsider");
        Test.add("lukbertbert");
        Test.add("realjameswoods");
        Test.add("swiftcashcc");
        
        assertEquals(Test,influencers);
        assertEquals(8, influencers.size());
        
    }
    
}
