/*
{{1, 3, 1, 4},
 {4, 0, 2, 0},
 {0, 3, 2, 1},
 {3, 0, 0, 1}}

 Part 2: Minimum Elevation Gain
  * Path from top-left to bottom-right corner of the map
  * Only move right or down from each position (never left or up)
  * Elevation Gain: total amount of *uphill* climb (and uphill only) over Path

Example:
1 3 1
4 0 2
0 3 2

Moving along top and right edges of the map, path is 1, 3, 1, 2, 2
Elevation Gain
1 <- no step yet, gain=0
1,3 <- 1st step from 1 to 3 is +2 feet uphill. gain=2
1,3,1 <- 2nd step is from 3 to 1, downhill so does not affect gain=2
1,3,1,2 <- 1 to 2 is +1 feet uphill, gain=3
1,3,1,2,2 <- 2 to 2, no elevation change, gain=3

Goal: Find the minimum elevation gain (do not need the path itself)

 */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Solution {
    public static void main(String[] args) {

        // List<List> elevationMap = new ArrayList<List<Int>>();
        int[][] elevationMap =
            {
                {1, 3, 1, 4},
                {4, 0, 2, 0},
                {0, 3, 2, 1},
                {3, 0, 0, 1}
            };

        System.out.println("ELEVATION MAP");
        printElevationMap(elevationMap);

        System.out.println("minimum cost was: " + getMinimumCost(elevationMap));
    }

    public static void printElevationMap(int[][] elevationMap) {
        for (int x = 0; x < elevationMap.length; x++) {
            for (int y = 0; y < elevationMap[x].length; y++) {
                System.out.print(elevationMap[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static int getMinimumCost(int[][] elevationMap) {
        int[][] costMap = new int[elevationMap.length][elevationMap[0].length];

        for (int x = 0; x < elevationMap.length; x++) {
            for (int y = 0; y < elevationMap[x].length; y++) {
                if (x - 1 >= 0 && y - 1 >= 0) { //middle
                    int xElevationDifference = elevationMap[x][y] - elevationMap[x - 1][y];
                    if (xElevationDifference < 0) { //we do not care about downhill
                        xElevationDifference = 0;
                    }

                    int yElevationDifference = elevationMap[x][y] - elevationMap[x][y - 1];
                    if (yElevationDifference < 0) { //we do not care about downhill
                        yElevationDifference = 0;
                    }

                    if (xElevationDifference < yElevationDifference) { //came from x
                        costMap[x][y] = costMap[x - 1][y] + xElevationDifference;
                    } else if (yElevationDifference < xElevationDifference) { //came from y
                        costMap[x][y] = costMap[x][y - 1] + xElevationDifference;
                    } else { //doesn't matter, come from the least expensive path
                        costMap[x][y] = Math.min(costMap[x - 1][y], costMap[x][y - 1]);
                    }
                } else if (x - 1 >= 0) { //x edge
                    int xElevationDifference = elevationMap[x][y] - elevationMap[x - 1][y];
                    if (xElevationDifference < 0) { //we do not care about downhill
                        xElevationDifference = 0;
                    }
                    costMap[x][y] = costMap[x - 1][y] + xElevationDifference;
                } else if (y - 1 >= 0) { //y edge
                    int yElevationDifference = elevationMap[x][y] - elevationMap[x][y - 1];
                    if (yElevationDifference < 0) { //we do not care about downhill
                        yElevationDifference = 0;
                    }
                    costMap[x][y] = costMap[x][y - 1] + yElevationDifference;
                }
            }
        }

        System.out.println("COST MAP");
        printElevationMap(costMap);

        return costMap[elevationMap.length - 1][elevationMap[0].length - 1];
    }

}

