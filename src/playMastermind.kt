package mastermind

import kotlin.random.Random

// instead of colors we are using A..F letters

val ALPHABET = 'A'..'F'
const val CODE_LENGTH = 4

fun main() {
    // for secret letter
    val differentLetters = false
    playMastermind(differentLetters)
}

/**
 * @param differentLetters: if true> a latter can be repeat in the secret string   else not
 * @param secret: generated random secret string
 *
 * */
fun playMastermind(
    differentLetters: Boolean,
    secret: String = generateSecret(differentLetters)
) {
    var evaluation: Evaluation

    do {
        print("Your guess: ")
        var guess = readLine()!! // player 2 guess string
        while (hasErrorsInInput(guess)) {
            println(
                "Incorrect input: $guess. " +
                        "It should consist of $CODE_LENGTH characters from $ALPHABET. " +
                        "Please try again."
            )
            guess = readLine()!!
        }
        // evaluated result
        evaluation = evaluateGuess(secret, guess)
        if (evaluation.isComplete()) {
            println("The guess is correct!")
        } else {
            println(
                "Right positions: ${evaluation.rightPosition}; " +
                        "wrong positions: ${evaluation.wrongPosition}."
            )
        }

    } while (!evaluation.isComplete())
}

fun Evaluation.isComplete(): Boolean = rightPosition == CODE_LENGTH
// validation
fun hasErrorsInInput(guess: String): Boolean {
    val possibleLetters = ALPHABET.toSet()
    return guess.length != CODE_LENGTH || guess.any { it !in possibleLetters }
}

/**
 * Generating a random secret string   using Random class
 * */
fun generateSecret(differentLetters: Boolean): String {
    val chars = ALPHABET.toMutableList()
    return buildString {
        for (i in 1..CODE_LENGTH) {
            val letter = chars[Random.nextInt(chars.size)]
            append(letter)
            if (differentLetters) {
                chars.remove(letter)
            }
        }
    }
}
