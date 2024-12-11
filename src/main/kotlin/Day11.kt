object Day11 {

    fun countStones(inputFileName: String, blinks: Int): Long {
        val input = getInputLines(inputFileName)
            .first()
            .split(' ')
            .map(String::toLong)

        var allNumbers = HashMap<Long, Long>(
            input.groupingBy { it }
                .eachCount()
                .mapValues { it.value.toLong() })

        var currentNumbers = HashMap<Long, Long>(
            input.groupingBy { it }
                .eachCount()
                .mapValues { it.value.toLong() })

        fun add(number: Long, count: Long) {
            currentNumbers[number] = currentNumbers.getOrDefault(number, 0) + count
        }

        repeat(blinks) { _ ->
            currentNumbers.clear()

            for ((num, count) in allNumbers) {
                if (num == 0L) {
                    add(1, count)
                    continue
                }

                // count digits by comparing to '10' pows
                var numberDigits = 1
                var pow = 1L
                while (num >= pow * 10) {
                    pow *= 10
                    numberDigits++
                }

                if (numberDigits % 2 == 0) {
                    var pow2 = 1L

                    // count a half of digits
                    repeat(numberDigits / 2) { pow2 *= 10 }

                    val first = num / pow2
                    val second = num % pow2

                    add(first, count)
                    add(second, count)
                } else {
                    add(num * 2024, count)
                }
            }

            currentNumbers = allNumbers.also {
                allNumbers = currentNumbers
            }
        }

        return allNumbers.values.sum()
    }
}