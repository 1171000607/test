/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;
import org.junit.Test;

import java.time.Instant;
import java.util.*;

public class SocialNetworkTest {

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
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "jeanOrlando5", "RT @RealJamesWoods: The feminists got what they asked for, and what they deserved. All future \\u201cwomen\\u2019s\\u201d Olympics events will be tarnished w\\u2026?", d3);
    private static final Tweet tweet4 = new Tweet(4, "exit___strategy", "RT @swiftcashcc: Geographical locations of SwiftNodes! Current ROI for running a SwiftNode is more than 100% per annum! Each SwiftNode requ\\u2026", d6);
    private static final Tweet tweet5 = new Tweet(5, "sfoxtrading", "$56.5 arb between bittrex and coinbase #bitcoin #bitcoinprice", d4);
    private static final Tweet tweet6 = new Tweet(6, "DerWinky", "https://t.co/Wyqq9LyYwC\\n\\nMario Party mit @cyberdusttv @Glockehara @Lukbertbert", d5);
    private static final Tweet tweet7 = new Tweet(7, "alyssa", "is it reasonable to talk about rivest so much?", d7);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6,tweet7));
        Map<String, Set<String>> Test = new HashMap<> ();
        
        Set<String> next = new HashSet<> ();
        next.add("realjameswoods");
        Test.put(tweet3.getAuthor().toLowerCase(),next);
        //System.out.println(Test);
        
        next = new HashSet<> ();
        next.add("swiftcashcc");
        Test.put(tweet4.getAuthor().toLowerCase(),next);
        //System.out.println(Test);
        
        next = new HashSet<> ();
        next.add("cyberdusttv");
        next.add("glockehara");
        next.add("lukbertbert");
        Test.put(tweet6.getAuthor().toLowerCase(),next);
        //System.out.println(Test);
        
        assertFalse("expected empty graph", followsGraph.isEmpty());
        assertEquals(followsGraph,Test);
        
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6,tweet7));
        List<String> Test = new ArrayList<> ();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        Test.add("cyberdusttv");
        Test.add("glockehara");
        Test.add("lukbertbert");
        Test.add("realjameswoods");
        Test.add("swiftcashcc");
        
        assertEquals(Test,influencers);
        assertFalse("expected empty list", influencers.isEmpty());
        
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
