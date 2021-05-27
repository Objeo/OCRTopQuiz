package com.example.topquiz.model;

import java.util.Collections;
import java.util.List;

// Dans l'idéal, à chaque nouvelle partie, il faudrait que les questions soient différentes, et affichées dans un ordre aléatoire.
// Pour ce faire, nous allons créer une classe Java spécifique nommée QuestionBank, qui va gérer cette liste de questions pour nous.

public class QuestionBank {
    private List<Question> mQuestionList;//liste de questions
    private int mNextQuestionIndex;//numero prohcain index ?

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        // Shuffle the question list
        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }

    public Question getQuestion() {
        // Ensure we loop over the questions
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
