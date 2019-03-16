/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.time.Instant;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        int n,i;
        Instant min_time,max_time,now;
        n=tweets.size();
        if (n>0) {
            min_time=tweets.get(0).getTimestamp();
            max_time=tweets.get(0).getTimestamp();
        }
        else {
            throw new RuntimeException("not implemented");
        }
        for (i=1;i<n;i++) {
            now=tweets.get(i).getTimestamp();
            if (now.isAfter(max_time)) {
                max_time=now;
                //max_index=i;
            }
            if (min_time.isAfter(now)) {
                min_time=now;
                //max_index=i;
            }
        }
        Timespan ans=new Timespan(min_time,max_time);
        return ans;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> ans = new HashSet<String> ();
        String s=new String();
        int n,len,i,j,k;
        char c;
        String now;
        n=tweets.size();
        if (n==0) {
            return ans;
        }
        for (i=0;i<n;i++) {
            now=tweets.get(i).getText();
            len=now.length();
            for (j=0;j<len;) {
                if (now.charAt(j)=='@' && j<len-1) {
                    if (j==0 || !((now.charAt(j-1)>='0' && now.charAt(j-1)<='9') || (now.charAt(j-1)>='a' && now.charAt(j-1)<='z') || (now.charAt(j-1)>='A' && now.charAt(j-1)<='Z') || (now.charAt(j-1)=='-') || (now.charAt(j-1)=='_'))) {
                        k=j;
                        j++;
                        c=now.charAt(j);
                        while (j<len && ((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z') || (c=='-') || (c=='_'))) {
                            j++;
                            if (j<len) c=now.charAt(j);
                        }
                        s=now.substring(k,j);
                        ans.add(s);
                    }
                    else j++;
                }
                else j++;
            }
        }
        return ans;
    }
}
