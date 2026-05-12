package com.vev.exemplo;

/*
Given an integer n, return the string form of the number followed by "!".
If the number is divisible by 3 use "Fizz" instead of the number,
and if the number is divisible by 5 use "Buzz" instead of the number,
and if the number is divisible by both 3 and 5, use "FizzBuzz"
*/
public class FizzBuzz {
  public static String fizzbuzz(int valor) {
    if (valor <= 0){
      throw new IllegalArgumentException();
    }
    String resp = "";
    if (valor % 3 == 0){
      resp += "Fizz";
    }
    if (valor % 5 == 0){
      resp += "Buzz";
    }
    if ((valor % 3 != 0) && (valor % 5) != 0){
      resp += valor;
    }
    resp += "!";
    return resp;
  }
}