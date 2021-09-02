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

        for (int y = 0; y < elevationMap.length; y++) {
            for (int x = 0; x < elevationMap[y].length; x++) {
                if (y - 1 >= 0 && x - 1 >= 0) { //middle
                    int yElevationDifference = elevationMap[y][x] - elevationMap[y - 1][x];
                    if (yElevationDifference < 0) { //we do not care about downhill
                        yElevationDifference = 0;
                    }

                    int xElevationDifference = elevationMap[y][x] - elevationMap[y][x - 1];
                    if (xElevationDifference < 0) { //we do not care about downhill
                        xElevationDifference = 0;
                    }

                    if ((costMap[y - 1][x] + yElevationDifference) < (costMap[y][x - 1] + xElevationDifference)) { //came from y
                        costMap[y][x] = costMap[y - 1][x] + yElevationDifference;
                    } else if ((costMap[y][x - 1] + xElevationDifference) < (costMap[y - 1][x] + yElevationDifference)) { //came from x
                        costMap[y][x] = costMap[y][x - 1] + yElevationDifference;
                    } else { //doesn't matter, come from the least expensive path
                        costMap[y][x] = Math.min(costMap[y - 1][x], costMap[y][x - 1]);
                    }
                } else if (y - 1 >= 0) { //y edge
                    int yElevationDifference = elevationMap[y][x] - elevationMap[y - 1][x];
                    if (yElevationDifference < 0) { //we do not care about downhill
                        yElevationDifference = 0;
                    }
                    costMap[y][x] = costMap[y - 1][x] + yElevationDifference;
                } else if (x - 1 >= 0) { //x edge
                    int xElevationDifference = elevationMap[y][x] - elevationMap[y][x - 1];
                    if (xElevationDifference < 0) { //we do not care about downhill
                        xElevationDifference = 0;
                    }
                    costMap[y][x] = costMap[y][x - 1] + xElevationDifference;
                }
            }
        }

        System.out.println("COST MAP");
        printElevationMap(costMap);

        return costMap[elevationMap.length - 1][elevationMap[0].length - 1];
    }

}

