package jetbrains.kotlin.course.last.push

// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }
            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }
            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun fillPatternRow(patternRow: String, patternWidth: Int) = if (patternRow.length <= patternWidth) {
    val filledSpace = "$separator".repeat(patternWidth - patternRow.length)
    "$patternRow$filledSpace"
} else {
    error("patternRow length > patternWidth, please check the input!")
}

fun getPatternHeight(pattern: String) = pattern.lines().size

fun repeatHorizontally(pattern: String, n: Int, patternWidth: Int): String {
    val pictureRows = pattern.lines()
    val sb = StringBuilder()
    for (row in pictureRows) {
        val currentRow = fillPatternRow(row, patternWidth)
        sb.append(currentRow.repeat(n))
        sb.append(newLineSymbol)
    }
    return sb.toString()
}

fun dropTopLine(image: String, width: Int, patternHeight: Int, patternWidth: Int): String =
    if (patternHeight > 1) {
        val lines = image.lines()
        lines.drop(1).joinToString(newLineSymbol)
    } else image

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    var currentPattern = pattern
    val patternWidth = getPatternWidth(pattern)
    val patternHeight = getPatternHeight(pattern)
    val sb = StringBuilder()

    repeat(height) { i ->
        if (i == 1) {
            currentPattern = dropTopLine(currentPattern, width, patternHeight, patternWidth)
            sb.append(repeatHorizontally(currentPattern, width, patternWidth))
        }else(sb.append(repeatHorizontally(currentPattern, width, patternWidth)))
    }
    return sb.toString()
}

fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    val patternLines = pattern.lines()
    val patternHeight = patternLines.size
    val patternWidth = patternLines.maxOfOrNull { it.length } ?: 0
    val gapLines = List(patternHeight) { " ".repeat(patternWidth) }

    val sb = StringBuilder()

    for (canvasLine in 0 until height * patternHeight) {
        val blockRow = canvasLine / patternHeight
        val lineInsideBlock = canvasLine % patternHeight

        for (blockCol in 0 until width) {
            val isGap = if (width > 1) {
                if (blockRow % 2 == 0) {
                    blockCol % 2 == 1
                } else {
                    blockCol % 2 == 0
                }
            } else {
                false
            }

            if (isGap) {
                sb.append(gapLines[lineInsideBlock])
            } else {
                val line = patternLines[lineInsideBlock]
                sb.append(line.padEnd(patternWidth, ' '))
            }
        }
        sb.append('\n')
    }

    return sb.toString()
}

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    val trimmedPattern = pattern.trimEnd()
    return when (generatorName) {
        "canvas" -> canvasGenerator(trimmedPattern, width, height)
        "canvasGaps" -> canvasWithGapsGenerator(trimmedPattern, width, height)
        else -> throw IllegalArgumentException("Unexpected filter name: $generatorName")
    }
}

fun main() {
    val pattern = getPattern()
    val generatorName = chooseGenerator()
    println(message = "Please input the width of the resulting picture:")
    val width = safeReadLine().toInt()
    println("Please input the height of the resulting picture:")
    val height = safeReadLine().toInt()

    println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    println("The generated image:")
    println(applyGenerator(pattern, generatorName, width, height))
}
