import edu.duke.*;
import java.io.File;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
  public int howMany(String stringa, String stringb){
    int count= 0; 
    int occ = 0; 
    occ = stringb.indexOf(stringa);
     
     while(occ >= 0){ 
     occ = stringb.indexOf(stringa,(occ+stringa.length()));
     count++; 
    }
     return count;
  }
  
  public void testHowMany() {
   String stringa = "GAA";
   String stringb = "ATGAACGAATTGAATC";
   int counts = howMany(stringa, stringb);
   System.out.println("Number of occurences " + counts);
   
   stringa = "AA";
   stringb = "ATGAACGAATTGAATC";
   counts = howMany(stringa, stringb);
   System.out.println("Number of occurences " + counts);
  }
}

