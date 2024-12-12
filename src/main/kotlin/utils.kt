import java.io.File

fun getInputLines(fileName: String): List<String> {
    return object {}.javaClass.getResource(fileName)?.let { url ->
        File(url.toURI()).readLines()
    }.orEmpty()
}

fun getInputText(fileName: String): String {
    return object {}.javaClass.getResource(fileName)?.let { url ->
        File(url.toURI()).readText()
    }.orEmpty()
}

data class Point(val x: Int, val y: Int)