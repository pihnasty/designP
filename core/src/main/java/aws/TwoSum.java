package aws;

import java.util.LinkedList;

public class TwoSum {
    public static void main(String[] args) {

    }

}
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            for (int j=(i+1); j<nums.length; j++) {
                if(target == (nums[j]+nums[i])) {
                    return new int [] {i,j};
                }
            }
        }
        return new int [] {};
    }
}