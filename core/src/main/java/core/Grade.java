package core;

import java.util.ArrayList;

public class Grade {

    private String code;
    private char grade;
    private int score;
    private final String comment;

    private final char[] validGrades = { 'A', 'B', 'C', 'D', 'E', 'F' };

    public Grade(String code, char grade, Integer score, String comment) {
        setCode(code);
        setGrade(grade);
        setScore(score);
        this.comment = comment;
    }

    public String getCode() {
        return this.code;
    };

    public char getGrade() {
        return this.grade;
    };

    public Integer getScore() {
        return this.score;
    }

    public String getComment() {
        return this.comment;
    }

    public String toString() {
        return getCode() + "\t\t\t\t\t\t\tGrade: " + getGrade();
    }

    public void setScore(int score) throws IllegalArgumentException {
        if (scoreIsValid(score)) {
            this.score = score;
        } else {
            // System.out.println("Score was " + String.valueOf(score));
            throw new IllegalArgumentException("Score must be an number between 1-5!");
        }
    }

    public Course getCourse(Core core) {
        for (Course singleCourse : core.getCourses()) {
            if (singleCourse.getCode().equals(code)) {
                return singleCourse;
            }
        }
        return new Course("XXX0000", "Course not found!", "404", new ArrayList<>(), new ArrayList<>());
    }

    public void setGrade(char grade) throws IllegalArgumentException {
        if (gradeIsValid(grade)) {
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade must be a character from A-F");
        }
    }

    public void setCode(String code) throws IllegalArgumentException {
        if (codeIsValid(code)) {
            this.code = code;
        } else {
            throw new IllegalArgumentException(
                    "Code must follow the following convention: AAA0000, 3 letter followed by 4 numbers");
        }
    }

    public boolean codeIsValid(String code) {
        return Validator.regex(code, "[A-Z]{3}\\d{4}");
    }

    public boolean scoreIsValid(Integer score) {
        return score <= 5 && score >= 1;
    }

    public boolean gradeIsValid(char grade) {
        for (char validGrade : validGrades) {
            if (validGrade == grade) {
                return true;
            }
        }
        return false;
    }

    public static char toChar(int i) {
        int a = 65;
        return (char) (a + 6 - i);
    }

    public static int toInt(char c) {
        int a = 65;
        return (int) (a + 6 - c);
    }

}
