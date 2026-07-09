package com.leetcode;


/**
 * CountServersThatCommunicate
 */
public class CountServersThatCommunicate {


    public static void main(String[] args) {
        final CountServersThatCommunicate serversThatCommunicate = new CountServersThatCommunicate();

        int[][] grid = {{}};
        System.out.println(serversThatCommunicate.countServers(grid));
    }

	private int countServers(int[][] grid) {

        int[] row = new int[grid.length];
        int[] col = new int[grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                
                int x = grid[i][j];
                if (x == 1) {
                    row[i] += 1;
                    col[j] += 1;
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                int x = grid[i][j];
                if (x == 1 && (row[i] > 1 || col[j] > 1)) {
                    count++;
                }
            }
        }
        return count;
	}


}
