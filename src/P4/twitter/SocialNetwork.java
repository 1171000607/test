/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    public static Map<String, Set<String>> hashtag = new HashMap<>();
    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        /*
         * defind variables
         */
        int n,m,i,j,k;
        char c;
        Tweet now;
        String now_text,now_author,now_follow,now_hashtag;
        Map<String, Set<String>> ans = new HashMap<>();
        
        n=tweets.size();
        if (n==0) {
            return ans;
        }
        
        for (i=0;i<n;i++) {
            now=tweets.get(i);
            now_author=now.getAuthor().toLowerCase();
            now_text=now.getText().toLowerCase();
            m=now_text.length();
            if (!ans.containsKey(now_author)) {
                Set<String> follow = new HashSet<> ();
                ans.put(now_author, follow);
            }
            
            if (m==0) continue;
            for (j=0;j<m;) {
                /*
                 * find the users mentioned
                 */
                if (j<m-1 && now_text.charAt(j)=='@') {
                    if (j==0 || !((now_text.charAt(j-1)>='0' && now_text.charAt(j-1)<='9') || (now_text.charAt(j-1)>='a' && now_text.charAt(j-1)<='z') || (now_text.charAt(j-1)=='-') || (now_text.charAt(j-1)=='_'))) {
                        j++;
                        k=j;
                        c=now_text.charAt(j);
                        while (j<m && ((c>='0' && c<='9') || (c>='a' && c<='z') || (c=='-') || (c=='_'))) {
                            j++;
                            if (j<m) c=now_text.charAt(j);
                        }
                        now_follow=now_text.substring(k,j);
                        ans.get(now_author).add(now_follow);
                    }
                    else j++;
                }
                
                /*
                 * find the hashtags
                 */
                if (j<m-1 && now_text.charAt(j)=='#') {
                    if (j==0 || !((now_text.charAt(j-1)>='0' && now_text.charAt(j-1)<='9') || (now_text.charAt(j-1)>='a' && now_text.charAt(j-1)<='z') || (now_text.charAt(j-1)=='-') || (now_text.charAt(j-1)=='_'))) {
                        j++;
                        k=j;
                        c=now_text.charAt(j);
                        while (j<m && ((c>='0' && c<='9') || (c>='a' && c<='z') || (c=='-') || (c=='_'))) {
                            j++;
                            if (j<m) c=now_text.charAt(j);
                        }
                        now_hashtag=now_text.substring(k,j);
                        if (!hashtag.containsKey(now_hashtag)) {
                            Set<String> follow = new HashSet<> ();
                            hashtag.put(now_hashtag, follow);
                        }
                        hashtag.get(now_hashtag).add(now_author);
                    }
                    else j++;
                }
                else j++;
            }
            if (ans.get(now_author).isEmpty()) ans.remove(now_author);
        }
        
        return ans;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        int n,i,num;
        Map<String,Integer> ans_map=new LinkedHashMap<> ();
        List<String> ans=new ArrayList<> ();
        Set<String> nowset;
        String nowstring;
        for (Map.Entry<String, Set<String>> visitmap : followsGraph.entrySet()) {
            nowset=visitmap.getValue();
            Iterator<String> visitset = nowset.iterator();
            
            while (visitset.hasNext()) {
                nowstring=visitset.next();
                if (!ans_map.containsKey(nowstring)) {
                    num=1;
                    ans_map.put(nowstring,num);
                }
                else{
                    num=ans_map.get(nowstring);
                    num++;
                    ans_map.remove(nowstring);
                    ans_map.put(nowstring, num);
                }
            }
        }
        
        List<Map.Entry<String, Integer>> ans_list = new ArrayList<Map.Entry<String, Integer>>(ans_map.entrySet()); //转换为list
        ans_list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().compareTo(o1.getValue())==0)
                    return o1.getKey().compareTo(o2.getKey());
                else return o2.getValue().compareTo(o1.getValue());
            }
        });
        n=ans_list.size();
        for (i=0;i<n;i++) {
            nowstring=ans_list.get(i).getKey();
            ans.add(nowstring);
        }
        return ans;
        //throw new RuntimeException("not implemented");
    }
    
    /**
     * use the idea 1--Common hashtags to optimize the social network
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     *         if have the same follower count, in
     *         increasing order of username.
     */
    public static List<String> MySocialNetwork(Map<String, Set<String>> followsGraph) {
        int n,i,num;
        Map<String,Integer> ans_map=new LinkedHashMap<> ();
        List<String> ans=new ArrayList<> ();
        Set<String> nowset,hashtag_follow;
        String nowstring,now_follow,now_follow1;
        
        /*
         * statistics about people on the same hashtag
         */
        for (Map.Entry<String, Set<String>> vis_hashtag : hashtag.entrySet()) {
            hashtag_follow=vis_hashtag.getValue();
            Iterator<String> vis_hashtag_value = hashtag_follow.iterator();
            
            while (vis_hashtag_value.hasNext()) {
                now_follow=vis_hashtag_value.next();
                if (!followsGraph.containsKey(now_follow)) {
                    Set<String> follow = new HashSet<> ();
                    followsGraph.put(now_follow, follow);
                }
                
                Iterator<String> vis_hashtag_value1 = hashtag_follow.iterator();
                while (vis_hashtag_value1.hasNext()) {
                    now_follow1=vis_hashtag_value1.next();
                    if (now_follow==now_follow1) continue;
                    followsGraph.get(now_follow).add(now_follow1);
                }
                if (followsGraph.get(now_follow).isEmpty()) followsGraph.remove(now_follow);
            }
        }
        
        for (Map.Entry<String, Set<String>> visitmap : followsGraph.entrySet()) {
            nowset=visitmap.getValue();
            Iterator<String> visitset = nowset.iterator();
            
            while (visitset.hasNext()) {
                nowstring=visitset.next();
                if (!ans_map.containsKey(nowstring)) {
                    num=1;
                    ans_map.put(nowstring,num);
                }
                else{
                    num=ans_map.get(nowstring);
                    num++;
                    ans_map.remove(nowstring);
                    ans_map.put(nowstring, num);
                }
            }
        }
        
        List<Map.Entry<String, Integer>> ans_list = new ArrayList<Map.Entry<String, Integer>>(ans_map.entrySet()); //转换为list
        ans_list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().compareTo(o1.getValue())==0)
                    return o1.getKey().compareTo(o2.getKey());
                else return o2.getValue().compareTo(o1.getValue());
            }
        });
        n=ans_list.size();
        for (i=0;i<n;i++) {
            nowstring=ans_list.get(i).getKey();
            ans.add(nowstring);
        }
        return ans;
        //throw new RuntimeException("not implemented");
    }


}
