package com.mindhub.skywalkies.Utilities;

public final class AvatarUtilities {
  private AvatarUtilities(){}
  public static int randomHeadAvatar(){
    return (int)((Math.random() * (9 - 1)) + 1);
  }
  public static int randomFaceAvatar(){
    return (int)((Math.random() * (5 - 1)) + 1);
  }
  public static int randomBodyAvatar(){
    return (int)((Math.random() * (5 - 1)) + 1);
  }
  public static int randomBodyColorAvatar(){
    return (int)((Math.random() * (4 - 1)) + 1);
  }
  public static int randomShoesAvatar(){
    return (int)((Math.random() * (5 - 1)) + 1);
  }
}
