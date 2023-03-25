package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Collections {
  public static void main (String[] args)
  {
    String [] langs = {"Java","C4","Python","PHP"};
    List<String> languages = Arrays.asList("Java","C4","Python","PHP");

    for (String l: languages ){
      System.out.println("Я хочу выучить " + l);
    }

  }
}
