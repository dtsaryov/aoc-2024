object Day12 {

    fun getFencePriceByPerimeter(inputFileName: String): Long {
        val map = getPlantsMap(inputFileName)
        val visited = mutableSetOf<Point>()

        var price = 0L

        for (y in map.indices) for (x in map[y].indices) {
            if (!visited.add(Point(x, y))) continue

            val region = PlantRegion(map[y][x], 0, 0, mutableListOf())
            countRegion(Point(x, y), region, map, visited)
            price += region.area * region.perimeter
        }

        return price
    }

    fun getFencePriceBySides(inputFileName: String): Long {
        val map = getPlantsMap(inputFileName)
        val visited = mutableSetOf<Point>()

        var price = 0L

        for (y in map.indices) for (x in map[y].indices) {
            if (!visited.add(Point(x, y))) continue

            val region = PlantRegion(map[y][x], 0, 0, mutableListOf())
            countRegion(Point(x, y), region, map, visited)

            price += region.area * countCorners(map, region)
        }

        return price
    }

    private fun countCorners(
        map: Array<CharArray>,
        region: PlantRegion
    ): Int {
        var corners = 0
        for (y in map.indices) for (x in map[y].indices) {
            if (leftTopInner(Point(x, y), region))
                corners++
            if (leftBottomInner(Point(x, y), region))
                corners++
            if (rightTopInner(Point(x, y), region))
                corners++
            if (rightBottomInner(Point(x, y), region))
                corners++

            if (leftTopOuter(Point(x, y), region, map))
                corners++
            if (leftBottomOuter(Point(x, y), region, map))
                corners++
            if (rightTopOuter(Point(x, y), region, map))
                corners++
            if (rightBottomOuter(Point(x, y), region, map))
                corners++
        }
        return corners
    }

    private fun leftTopInner(p: Point, region: PlantRegion): Boolean {
        val fences = region.fences.filter {
            it.first.x == p.x && it.first.y == p.y
        }
        if (fences.size < 2) return false
        return fences.any { it.second.x == p.x - 1 } &&
                fences.any { it.second.y == p.y - 1 }

    }

    private fun leftBottomInner(p: Point, region: PlantRegion): Boolean {
        val fences = region.fences.filter {
            it.first.x == p.x && it.first.y == p.y
        }
        if (fences.size < 2) return false
        return fences.any { it.second.x == p.x - 1 } &&
                fences.any { it.second.y == p.y + 1 }
    }

    private fun rightTopInner(p: Point, region: PlantRegion): Boolean {
        val fences = region.fences.filter {
            it.first.x == p.x && it.first.y == p.y
        }
        if (fences.size < 2) return false
        return fences.any { it.second.x == p.x + 1 } &&
                fences.any { it.second.y == p.y - 1 }

    }

    private fun rightBottomInner(p: Point, region: PlantRegion): Boolean {
        val fences = region.fences.filter {
            it.first.x == p.x && it.first.y == p.y
        }
        if (fences.size < 2) return false
        return fences.any { it.second.x == p.x + 1 } &&
                fences.any { it.second.y == p.y + 1 }
    }

    private fun leftTopOuter(p: Point, region: PlantRegion, map: Array<CharArray>): Boolean {
        if (!region.plants.contains(p)) return false

        if (p.x - 1 < 0 ||
            p.y - 1 < 0 ||
            map[p.y - 1][p.x] != region.plant ||
            map[p.y][p.x - 1] != region.plant
        ) return false

        val fences = region.fences.filter {
            it.second == Point(p.x - 1, p.y - 1)
        }
        if (fences.size < 2) return false
        return fences.any { it.first.x == p.x - 1 } &&
                fences.any { it.first.y == p.y - 1 }

    }

    private fun leftBottomOuter(p: Point, region: PlantRegion, map: Array<CharArray>): Boolean {
        if (!region.plants.contains(p)) return false

        if (p.x - 1 < 0 ||
            p.y + 1 > map.lastIndex ||
            map[p.y + 1][p.x] != region.plant ||
            map[p.y][p.x - 1] != region.plant
        ) return false

        val fences = region.fences.filter {
            it.second == Point(p.x - 1, p.y + 1)
        }
        if (fences.size < 2) return false
        return fences.any { it.first.x == p.x - 1 } &&
                fences.any { it.first.y == p.y + 1 }
    }

    private fun rightTopOuter(p: Point, region: PlantRegion, map: Array<CharArray>): Boolean {
        if (!region.plants.contains(p)) return false

        if (p.x + 1 > map[0].lastIndex ||
            p.y - 1 < 0 ||
            map[p.y - 1][p.x] != region.plant ||
            map[p.y][p.x + 1] != region.plant
        ) return false

        val fences = region.fences.filter {
            it.second == Point(p.x + 1, p.y - 1)
        }
        if (fences.size < 2) return false
        return fences.any { it.first.x == p.x + 1 } &&
                fences.any { it.first.y == p.y - 1 }

    }

    private fun rightBottomOuter(p: Point, region: PlantRegion, map: Array<CharArray>): Boolean {
        if (!region.plants.contains(p)) return false

        if (p.x + 1 > map[0].lastIndex ||
            p.y + 1 > map.lastIndex ||
            map[p.y + 1][p.x] != region.plant ||
            map[p.y][p.x + 1] != region.plant
        ) return false

        val fences = region.fences.filter {
            it.second == Point(p.x + 1, p.y + 1)
        }
        if (fences.size < 2) return false
        return fences.any { it.first.x == p.x + 1 } &&
                fences.any { it.first.y == p.y + 1 }
    }

    @Suppress("unused")
    private fun countWalls() {
//        for (y in map.indices) for (x in map[y].indices) {
//            if (!visited.add(Point(x, y))) continue
//
//            val region = PlantRegion(map[y][x], 0, 0, mutableListOf())
//            countRegion(Point(x, y), region, map, visited)
//
//            var sides = 0
//
//            // check horizonal fences
//            for (yy in 0..map.size) {
//                // filter fences from bottom to up
//                val hFencesUp = region.fences.filter {
//                    it.first.y == yy && it.second.y == yy - 1
//                }.sortedBy { it.first.x }
//
//                if (!hFencesUp.isEmpty()) {
//                    // increase side count if at least of fence exists
//                    sides++
//
//                    var currentX = hFencesUp.first().first.x
//                    var idx = 0
//                    while (idx < hFencesUp.size) {
//                        val cFence = hFencesUp[idx]
//                        if (cFence.first.x == currentX) {
//                            currentX++
//                        } else {
//                            sides++
//                        }
//                        idx++
//                    }
//                }
//                // filter fences from up to bottom
//                val hFencesDown = region.fences.filter {
//                    it.first.y == yy && it.second.y == yy + 1
//                }.sortedBy { it.first.x }
//
//                if (!hFencesDown.isEmpty()) {
//                    // increase side count if at least of fence exists
//                    sides++
//
//                    var currentX = hFencesDown.first().first.x
//                    var idx = 0
//                    while (idx < hFencesDown.size) {
//                        val cFence = hFencesDown[idx]
//                        if (cFence.first.x == currentX) {
//                            currentX++
//                        } else {
//                            sides++
//                        }
//                        idx++
//                    }
//                }
//            }
//
//            // check vertical fences
//            for (xx in 0..map.size) {
//                // filter fences from right to left
//                val vFencesLeft = region.fences.filter {
//                    it.first.x == xx && it.second.x == xx - 1
//                }.sortedBy { it.first.y }
//
//                if (!vFencesLeft.isEmpty()) {
//                    // increase side count if at least of fence exists
//                    sides++
//
//                    var currentY = vFencesLeft.first().first.y
//                    var idx = 0
//                    while (idx < vFencesLeft.size) {
//                        val cFence = vFencesLeft[idx]
//                        if (cFence.first.y == currentY) {
//                            currentY++
//                        } else {
//                            sides++
//                        }
//                        idx++
//                    }
//                }
//
//                // filter fences from left to right
//                val hFencesRight = region.fences.filter {
//                    it.first.x == xx && it.second.x == xx + 1
//                }.sortedBy { it.first.y }
//
//                if (!hFencesRight.isEmpty()) {
//                    // increase side count if at least of fence exists
//                    sides++
//
//                    var currentY = hFencesRight.first().first.y
//                    var idx = 0
//                    while (idx < hFencesRight.size) {
//                        val cFence = hFencesRight[idx]
//                        if (cFence.first.y == currentY) {
//                            currentY++
//                        } else {
//                            sides++
//                        }
//                        idx++
//                    }
//                }
//            }
//
//            price += region.area * sides
//        }
    }

    private fun countRegion(
        pos: Point,
        region: PlantRegion,
        map: Array<CharArray>,
        visited: MutableSet<Point>
    ) {
        region.area++
        region.plants += pos

        for (step in getNextSteps(pos)) {
            if (!checkPos(step, map)) {
                region.fences += pos to step
                region.perimeter++
                continue
            }

            if (map[step.y][step.x] != region.plant) {
                region.fences += pos to step
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
    var perimeter: Long = 0,
    var fences: MutableList<Pair<Point, Point>>,
    val plants: MutableSet<Point> = mutableSetOf()
)