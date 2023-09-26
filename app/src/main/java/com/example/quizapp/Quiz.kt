package com.example.quizapp

class Quiz(val questions: List<Question>) {
    // vars to track score, quesiton index
    var score = 0
    private var index = 0
    private var alreadyAsked = (mutableListOf <Int>())

    // gets the current question
    private fun getCurrentQuesiton() : String {
        questionBeGone(index)
        return questions[index].question
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
        else if(index != questions.lastIndex && index == alreadyAsked[alreadyAsked.lastIndex]) {
            index++
            return true
        }
        return false
    }

    // loads the questions if there's more questions to load
    fun loadNextQuestion() : String{
        if (moreQuesitons()) {
            return getCurrentQuesiton()
        }
        return "End of Quiz"
    }

    // checks answer
    fun checkAnswer(string: String) {
        if(questions[index].answer == string)
            score++
    }

    // functions
    // checking answer
    // get current quesiton

    // optional functions:
    // shuffle remaining quesitons
    // reset quiz
}