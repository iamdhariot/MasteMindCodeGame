package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    println(secret)// for checking
    val rightPosition = getGuessInRightPosition(secret, guess)
    val wrongPosition = getGuessInWrongPosition(secret, guess)

    return Evaluation(rightPosition, wrongPosition)
}

fun getGuessInRightPosition(secret: String, guess: String): Int {

    var rightPosition = 0
    for (i in 0 until secret.length) {
        if (secret[i] == guess[i]) {
            rightPosition++  // if guess and secret letters position is same then count it as a correct guess

        }
    }
    return rightPosition
}

fun getGuessInWrongPosition(secret: String, guess: String): Int {
    var wrongPosition = 0
    var newSecret = ""
    var newGuess = ""

    for (i in 0 until secret.length) {
        if (secret[i] != guess[i]) {
            newSecret += secret[i]  // if guess and secret letters position are not same or they are not matched on same position then store guess and secret string in newGuess newSecret
            newGuess += guess[i]
        }
    }
    val evaluatedChar = mutableListOf<Char>()
    if (newSecret.isNotEmpty()) {
        for (i in 0 until secret.length) {
            var char = guess[i] // taking one char/ letter at a time for evaluation
            // check this char is evaluated or not
            if (!evaluatedChar.contains(char)) {
                val totalCharMatchedInSecret = countMatched(newSecret, char) // total number of char matched in newSecret
                val totalCharMatchedInGuess = countMatched(newGuess, char)  // total number of char matched in newGuess
                wrongPosition += if (totalCharMatchedInSecret == totalCharMatchedInGuess || totalCharMatchedInSecret > totalCharMatchedInGuess) totalCharMatchedInGuess else totalCharMatchedInSecret
                evaluatedChar.add(char)
            }

        }
    }
    return wrongPosition

}

// counting the number of char found in this given string
fun countMatched(string: String, char: Char): Int {
    var totalMatches = 0
    for (i in 0 until string.length) {
        if (string[i] == char)
            totalMatches++
    }
    return totalMatches
}
