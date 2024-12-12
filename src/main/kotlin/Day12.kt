object Day12 {

    fun getFencePrice(inputFileName: String): Long {
        val map = getPlantsMap(inputFileName)
        val visited = mutableSetOf<Point>()

        var price = 0L

        for (y in map.indices) for (x in map[y].indices) {
            if (!visited.add(Point(x, y))) continue

            val region = PlantRegion(map[y][x], 0, 0)
            countRegion(Point(x, y), region, map, visited)
            price += region.area * region.perimeter
        }

        return price
    }

    private fun countRegion(
        pos: Point,
        region: PlantRegion,
        map: Array<CharArray>,
        visited: MutableSet<Point>
    ) {
        region.area++

        for (step in getNextSteps(pos)) {
            if (!checkPos(step, map)) {
                region.perimeter++
                continue
            }

            if (map[step.y][step.x] != region.plant) {
                region.perimeter++
                continue
            }

            if (visited.add(step)) {
                countRegion(step, region, map, visited)
            }
        }
    }

    private fun checkPos(pos: Point, grid: Array<CharArray>): Boolean {
        return pos.y in grid.indices && pos.x in grid[0].indices
    }

    private fun getNextSteps(pos: Point): List<Point> {
        val (x, y) = pos
        return listOf(
            Point(x - 1, y),
            Point(x, y + 1),
            Point(x + 1, y),
            Point(x, y - 1)
        )
    }

    private fun getPlantsMap(inputFileName: String): Array<CharArray> {
        val inputLines = getInputLines(inputFileName)

        val map = Array(inputLines.size) { CharArray(inputLines[it].length) }

        inputLines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                map[y][x] = c
            }
        }

        return map
    }
}

private data class PlantRegion(
    var plant: Char = '∆',
    var area: Long = 0,
    var perimeter: Long = 0
)