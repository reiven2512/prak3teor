import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static String firstLetter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    static String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789";
    static String digit = "abcdefABCDEF0123456789";
    static String firstDigit = "0123456789";
    static String action = "+-/*()";

    public static void main(String[] args) {
        method("ret23 / line := (text * 12 - 1B) * 8A;23ret := (12 - 1B) * 8A;ret23 := (12 - 1B:) * 8A;");
    }

    public static void method(String str) {
        String[] arr = str.split(";");
        for (int i = 0; i < arr.length; i++) {
            test(i, arr[i]);
        }
    }

    public static void test(int i, String s){
        String cur = "";
        List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if(c == ' '){
                if(!cur.equals("")){
                    if(firstLetter.contains(cur.charAt(0) + "")){
                        list.add(new Pair<String, String>(cur, "Идентификатор"));
                    } else {
                        list.add(new Pair<String, String>(cur, "16-чное число"));
                    }
                    cur = "";
                }
            } else {
                if(c == ':' && j+1 < s.length()){
                    if(s.charAt(j+1) == '='){
                        if(!cur.equals("")) {
                            list.add(new Pair<String, String>(cur, "Идентификатор"));
                        }
                        list.add(new Pair<String, String>(":=", "Знак присваивания"));
                        cur = "";
                        j++;
                    } else {
                        System.out.println((i+1) + " арифметическое выражение не прошло лексический анализ. Ошибка в " + (j+1) + " символе");
                        return;
                    }
                } else if (action.contains(c + "")) {
                    if(!cur.equals("")) {
                        if(firstLetter.contains(cur.charAt(0) + "")){
                            list.add(new Pair<String, String>(cur, "Идентификатор"));
                        } else {
                            list.add(new Pair<String, String>(cur, "16-чное число"));
                        }
                        cur = "";
                    }
                    list.add(new Pair<String, String>(c + "",  getWord(c)));
                } else if(word.contains(c + "")){
                    if(!cur.equals("")) {
                        if(firstDigit.contains(cur.charAt(0) + "")){
                            if (!digit.contains(c + "")) {
                                System.out.println((i+1) + " арифметическое выражение не прошло лексический анализ. Ошибка в " + (j+1) + " символе");
                                return;
                            }
                        }
                    }
                    cur += c;
                } else {
                    System.out.println((i+1) + " арифметическое выражение не прошло лексический анализ. Ошибка в " + (j+1) + " символе");
                    return;
                }
            }
        }
        System.out.println((i+1) + " арифметическое выражение");
        for(Pair<String, String> pair:list){
            System.out.printf("%-20s",pair.getKey());
            System.out.printf("%-20s",pair.getValue());
            System.out.println();
        }
    }

    public static String getWord(char c){
        switch (c){
            case '+': return "Знак операции 'сложение'";
            case '-': return "Знак операции 'вычитание'";
            case '/': return "Знак операции 'деление'";
            case '*': return "Знак операции 'умножение'";
            case '(': return "Открывающая скобка";
            case ')': return "Закрывающая скобка";
        }
        return "";
    }
}

