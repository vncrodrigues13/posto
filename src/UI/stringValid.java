package UI;

public class stringValid {
    public static boolean temLetras(String util){
        for (int i = 0; i < util.length(); i++) {
          if (Character.isLetter(util.charAt(i))){
              return true;
          }
        }
        return false;
    }
}
