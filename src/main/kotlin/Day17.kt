import kotlin.math.pow
import kotlin.math.truncate

object Day17 {

    fun part1(inputFileName: String): String {
        val input = getInputLines(inputFileName)

        var a = input[0].substringAfterLast(':').trim().toDouble()
        var b = input[1].substringAfterLast(':').trim().toDouble()
        var c = input[2].substringAfterLast(':').trim().toDouble()

        fun getComboOperand(op: Double): Double {
            return when {
                op in 0.0..3.0 -> op
                op == 4.0 -> a
                op == 5.0 -> b
                op == 6.0 -> c
                else -> error("")
            }
        }

        val program = input[4].substringAfterLast(':')
            .trim()
            .split(',')
            .windowed(2, 2)
            .map { it[0].toInt() to it[1].toDouble() }

        var i = 0
        val out = StringBuilder()

        while (i < program.size) {
            val (cmd, op) = program[i]

            when (cmd) {
                // adv
                0 -> {
                    a = truncate(a / 2.0.pow(getComboOperand(op)))
                }
                // bxl
                1 -> {
                    b = truncate((b.toLong() xor op.toLong()).toDouble())
                }
                // bst
                2 -> {
                    b = getComboOperand(op) % 8
                }
                // jnz
                3 -> {
                    if (a != 0.0) {
                        i = op.toInt()
                        continue
                    }
                }
                // bxc
                4 -> {
                    b = truncate((b.toLong() xor c.toLong()).toDouble())
                }
                // out
                5 -> {
                    out.append("${(getComboOperand(op) % 8).toLong()},")
                }
                // bdv
                6 -> {
                    b = truncate(a / 2.0.pow(getComboOperand(op)))
                }
                // cdv
                7 -> {
                    c = truncate(a / 2.0.pow(getComboOperand(op)))
                }
            }

            i++
        }

        return out.toString().trimEnd(',')
    }
}