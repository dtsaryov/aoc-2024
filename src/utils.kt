import java.io.File

fun getInput(fileName: String): List<String> {
    return object {}.javaClass.getResource(fileName)?.let { url ->
        File(url.toURI()).readLines()
    }.orEmpty()
}