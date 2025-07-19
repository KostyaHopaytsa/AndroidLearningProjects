package jetbrains.kotlin.course.hangman

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int) = "Welcome to the game!$newLineSymbol$newLineSymbol" +
        "In this game, you need to guess the word made by the computer.$newLineSymbol" +
        "The hidden word will appear as a sequence of underscores, one underscore means one letter.$newLineSymbol" +
        "You have $maxAttemptsCount attempts to guess the word.$newLineSymbol" +
        "All words are English words, consisting of $wordLength letters.$newLineSymbol" +
        "Each attempt you should enter any one letter,$newLineSymbol" +
        "if it is in the hidden word, all matches will be guessed.$newLineSymbol$newLineSymbol" +
        "" +
        "For example, if the word \"CAT\" was guessed, \"_ _ _\" will be displayed first, " +
        "since the word has 3 letters.$newLineSymbol" +
        "If you enter the letter A, you will see \"_ A _\" and so on.$newLineSymbol$newLineSymbol" +
        "" +
        "Good luck in the game!"

// You will use this function later
fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

// You will use this function later
fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun isComplete(secret: String,  currentGuess: String): Boolean {
    val normalizedGuess = currentGuess.replace(separator, "")
    return secret == normalizedGuess
}

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    val currentChars = currentUserWord.split(separator)
    val result = buildString {
        for(i in secret.indices) {
            if(secret[i] == guess) {
                append(guess)
            }else{
                append(currentChars[i])
            }
            append(separator)
        }
    }
    return result.removeSuffix(separator)
}

fun generateSecret(): String = words.random()

fun getHiddenSecret(wordLength: Int): String =
    List(wordLength) { underscore }.joinToString(separator)

fun isCorrectInput(userInput: String): Boolean {
    if(userInput.length != 1){
        println("The length of your guess should be 1! Try again!")
        return false
    }
    val ch = userInput[0]
    if (!ch.isLetter() || ch !in 'A'..'Z' && ch !in 'a'..'z') {
        println("You should input only English letters! Try again!")
        return false
    }
    return true
}

fun safeUserInput(): Char {
    println("Please input your guess.")
    while(true) {
        val userInput = safeReadLine()
        if (isCorrectInput(userInput)){
            return userInput[0].uppercaseChar()
        }
    }
}

fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String{
    return if(guess !in secret){
        println("Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord")
        currentUserWord
    }else{
        val newUserWord = generateNewUserWord(secret, guess, currentUserWord)
        println("Great! This letter is in the word! The current word is $newUserWord")
        newUserWord
    }
}

fun playGame(secret: String, maxAttemptsCount: Int): Unit {
    val wordLength = secret.length
    var currentUserWord = getHiddenSecret(wordLength)
    var attempts = 0
    var complete = false

    do {
        val guess = safeUserInput()
        currentUserWord = getRoundResults(secret, guess, currentUserWord)
        complete = isComplete(secret, currentUserWord)
        attempts++
        if (isWon(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
            return
        }
        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! My word is $secret")
            return
        }
    } while (!complete)
}

fun main() {
    // Uncomment this code on the last step of the game

    println(getGameRules(wordLength, maxAttemptsCount))
    playGame(generateSecret(), maxAttemptsCount)
}
