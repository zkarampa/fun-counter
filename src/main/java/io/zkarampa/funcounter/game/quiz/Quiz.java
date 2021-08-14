package io.zkarampa.funcounter.game.quiz;

import io.zkarampa.funcounter.Player;

import java.util.*;

public class Quiz {

    private List<Entry> entries = new ArrayList<>();

    public Quiz() {
    }

    public Integer addQuestion(Entry entry) {
        this.entries.add(entry);
        return this.entries.size() - 1;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "entries=" + entries +
                '}';
    }

    public static final class Entry {
        private final String question;
        private final String answer;
        private final List<String> possibleAnswers;

        public Entry(String question, String answer, String... possibleAnswers) {
            this.question = question;
            this.answer = answer;
            this.possibleAnswers = Arrays.asList(possibleAnswers);
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getPossibleAnswers() {
            return possibleAnswers;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "question='" + question + '\'' +
                    ", answer='" + answer + '\'' +
                    ", possibleAnswers=" + possibleAnswers +
                    '}';
        }
    }
}
