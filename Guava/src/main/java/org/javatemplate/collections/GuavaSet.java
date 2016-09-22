package org.javatemplate.collections;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by arunmohzi on 9/20/16.
 */
public class GuavaSet {
    public Set<String> getSetDifference(Set<String> set1, Set<String> set2){
      return Sets.difference(set1, set2);
    }

    public Set<String> getSymmetricDifference(Set<String> set1, Set<String> set2){
        Sets.SetView<String> setView = Sets.symmetricDifference(set1, set2);
        return setView;
    }

    public Set<String> getSetIntersection(Set<String> set1, Set<String> set2){
        Sets.SetView<String> setView = Sets.intersection(set1, set2);
        return setView;
    }

    public Set<String> getSetUnion(Set<String> set1, Set<String> set2){
        Sets.SetView<String> setView = Sets.union(set1, set2);
        return setView;
    }
}
