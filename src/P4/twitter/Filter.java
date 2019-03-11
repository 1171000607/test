/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.*;
import java.time.Instant;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        int n,i;
        List<Tweet> ans = new ArrayList<Tweet> ();
        String now;
        n=tweets.size();
        if (n==0) {
            //throw new RuntimeException("not implemented");
            return ans;
        }
        for (i=0;i<n;i++) {
            now=tweets.get(i).getAuthor();
            if (now==username) ans.add(tweets.get(i));
        }
        return ans;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> ans = new ArrayList<Tweet> ();
        int n,i;
        Instant Start,End,now;
        n=tweets.size();
        Start=timespan.getStart();
        End=timespan.getEnd();
        if (n==0) {
            //throw new RuntimeException("not implemented");
            return ans;
            
        }
        for (i=0;i<n;i++) {
            now=tweets.get(i).getTimestamp();
            if (now.isAfter(Start) && End.isAfter(now))
                ans.add(tweets.get(i));
        }
        return ans;
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> ans = new ArrayList<Tweet> ();
        int n,i,j,len;
        String now;
        String[] now_split=new String[] {};
        n=tweets.size();
        if (n==0) {
            //throw new RuntimeException("not implemented");
            return ans;
        }
        for (i=0;i<n;i++) {
            now=tweets.get(i).getText();
            now_split=now.split(" ");
            len=now_split.length;
            for (j=0;j<len;j++) {
                if (words.contains(now_split[j])) {
                    ans.add(tweets.get(i));
                    break;
                }
            }
        }
        return ans;
    }

}
