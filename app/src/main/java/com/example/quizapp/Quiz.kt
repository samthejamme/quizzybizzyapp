package com.example.quizapp

class Quiz(val questions: List<Question>) {
    // vars to track score, quesiton index
    var score = 0
    var index = 0
    var alreadyAsked = (mutableListOf <Int>())

    // gets the current question
    private fun getCurrentQuesiton() : Question {
        questionBeGone(index)
        return questions.get(index)
    }

    // adds the question to the list of questions that were already asked
    private fun questionBeGone(int: Int) {
        alreadyAsked.add(int)
    }

    // checks if more questions
    private fun moreQuesitons() : Boolean {
        if(!alreadyAsked.contains(index)) {
            return true
        }
        else if(index < alreadyAsked.lastIndex) {
            index++
            return true
        }
        return false
    }

    // loads the questions if there's more questions to load
    fun loadQuestion() {
        if (moreQuesitons()) {
            getCurrentQuesiton()
        }
    }

    // checks asnwer
    fun checkAnswer() {
        val string = MainActivity.ANSWER
        if(questions.get(index).answer == string)
            score++
    }

    // functions
    // checking answer
    // get current quesiton

    // optional functions:
    // shuffle remaining quesitons
    // reset quiz
}