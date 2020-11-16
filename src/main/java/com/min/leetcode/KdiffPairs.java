package main.java.com.min.leetcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class KdiffPairs {
    class Solution {
        public int findPairs(int[] nums, int k) {
            Set<Pair> pairs = new HashSet<>();

            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (Math.abs(nums[i] - nums[j]) == k) {
                        pairs.add(new Pair(nums[i], nums[j]));
                    }
                }
            }

            return pairs.size();
        }

        private class Pair {
            int l;
            int r;

            public Pair(final int l, final int r) {
                if (l > r) {
                    this.l = r;
                    this.r = l;
                } else {

                    this.l = l;
                    this.r = r;
                }
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) return true;
                if (!(o instanceof Pair)) return false;
                Pair pair = (Pair) o;
                return l == pair.l &&
                        r == pair.r;
            }

            @Override
            public int hashCode() {
                return Objects.hash(l, r);
            }
        }
    }
}
