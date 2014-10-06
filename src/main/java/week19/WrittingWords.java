package week19;

public class WrittingWords {
    
    public int write(String word) {
        
        int taps = 0;
        
         for (int i = 0; i < word.length(); i++) {
            taps += ((int) word.toUpperCase().charAt(i)) - 64;
        }
        
        return taps;
    }   
}
