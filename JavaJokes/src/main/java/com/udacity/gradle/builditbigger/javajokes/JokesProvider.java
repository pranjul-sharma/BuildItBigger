package com.udacity.gradle.builditbigger.javajokes;

import java.util.Random;

public class JokesProvider {

    public String getJoke() {
        String[] jokes = {
                "A family of mice were surprised by a big cat. Father Mouse jumped and and said, \"Bow-wow!\" The cat ran away. \"What was that, Father?\" asked Baby Mouse. \"Well, son, that's why it's important to learn a second language.\" ",
                "My friend said he knew a man with a wooden leg named Smith. \n" +
                        "So I asked him \"What was the name of his other leg?\"\n",
                "PUPIL: \"Would you punish me for something I didn`t do?\" \n" +
                        "TEACHER:\" Of course not.\" \n" +
                        "PUPIL: \"Good, because I haven`t done my homework.\"",
                "A snail walks into a bar and the barman tells him there's a strict policy about having snails in the bar and so kicks him out. A year later the same snail re-enters the bar and asks the barman \"What did you do that for?\" \n",
                "A: I have the perfect son. \n" +
                        "B: Does he smoke? \n" +
                        "A: No, he doesn't. \n" +
                        "B: Does he drink whiskey? \n" +
                        "A: No, he doesn't. \n" +
                        "B: Does he ever come home late? \n" +
                        "A: No, he doesn't. \n" +
                        "B: I guess you really do have the perfect son. How old is he? \n" +
                        "A: He will be six months old next Wednesday."

        };

        int num = new Random().nextInt(5);
        return jokes[num];
    }
}
