package com.example.topquiz.model;

import java.util.List;

public class Question {

    //classe Java nommée Question.
    // L'instance de cette classe contiendra une question, avec les quatre réponses associées et la bonne réponse correspondante.
    // De ce fait, la classe va contenir les trois attributs suivants :

    private String mQuestion; //Texte de la question.
    private List<String> mChoiceList; // liste des reponses proposées.
    private int mAnswerIndex; //Index de la réponse dans la liste précédente.

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    // vérifiez que la liste des réponses contienne au moins une entrée.
    public void setChoiceList(List<String> choiceList) {
        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null"); //permet de déclencher une erreur
        }

        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    //vérifiez que l'index de réponse soit compris entre [0; nombre de réponses possible[.
    public void setAnswerIndex(int answerIndex) {
        if (answerIndex < 0 || answerIndex >= mChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = answerIndex;

    }
}