package com.mindhub.skywalkies.Utilities;

public final class BillUtilities {
  public BillUtilities() {
  }
  public static int randomNumberTicket(int min, int max){
    return (int)((Math.random() * (max - min)) + min);
  }
}
