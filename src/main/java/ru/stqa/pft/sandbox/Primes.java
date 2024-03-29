package ru.stqa.pft.sandbox;

public class Primes {
  public static boolean isPrime (int n){
    for (int i=2;i < n; i ++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
  public static boolean isPrimeWhile (int n){
    int i =2;
    while (i < n && n % 1 !=0) {

      i++;
    }
    return i == n;
  }
  public static boolean isPrimeFast (long n){
    int m = (int) Math.sqrt(n);
    for (long i=2;i < m/2; i ++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
