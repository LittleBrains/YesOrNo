package ru.littlebrains.yesorno;

import java.io.Serializable;

/**
 * Created by Evgeny on 22.05.2016.
 */
public class QuestionModel implements Serializable {
    public String question;
    public int answer;
    public int timestamp;
}
