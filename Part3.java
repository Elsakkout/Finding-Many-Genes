import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
  public int findStopCodon(String dna, int startIndex, String stopCodon){
    int currIndex = dna.indexOf(stopCodon,startIndex+3);
    
    while (currIndex != -1){
    int diff = currIndex - startIndex;
        if(diff % 3 == 0){
            return currIndex;
        }else{
            currIndex =dna.indexOf(stopCodon, currIndex + 1);
        }
    
    }
    return dna.length();
  }
    
  public void testFindStopCodon(){
    
    String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
    int dex = findStopCodon(dna, 0, "TAA");
    if (dex != 9) System.out.println("error on 9");
    dex = findStopCodon(dna, 9, "TAA");
    if (dex != 21) System.out.println("error on 21");
    dex = findStopCodon(dna, 1, "TAA");
    if (dex != -1) System.out.println("error on 26");
    dex = findStopCodon(dna, 0, "TAG");
    if (dex != -1) System.out.println("error on 26 TAG");
    System.out.println("tests finished");
  
  }
  
  public String findGene(String dna, int where){
    int startIndex = dna.indexOf("ATG", where);
    if (startIndex == -1){
        return "";
    }
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    int minIndex = 0;
    if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
        minIndex = tgaIndex;
    }else{
        minIndex = taaIndex;
    }
    
    if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
        minIndex = tagIndex;
    }
    
    if (minIndex == dna.length()){
        return "";
    }
    
    return dna.substring(startIndex, minIndex + 3);
  }
  
  public void testFindGene(){
    String dna = "ATGCCCGGGAAATAACCC";
    int where = 0;
    String gene = findGene(dna, where);
    System.out.println("DNA String is " + dna);
    System.out.println("Gene is " + gene);
    
    dna = "ATGCCCGGGAAACCC";
    gene = findGene(dna, where);
    System.out.println("DNA String is " + dna);
    System.out.println("Gene is " + gene);
    
    dna = "ATGCCCGGGAAACCCTAGCCCGGGTGA";
    gene = findGene(dna, where);
    System.out.println("DNA String is " + dna);
    System.out.println("Gene is " + gene);
    
    dna = "ATGCCCGGGAAACCCTGACCCGGGTCA";
    gene = findGene(dna, where);
    System.out.println("DNA String is " + dna);
    System.out.println("Gene is " + gene);
  }
  
  public void printAllGenes(String dna){
    int startIndex = 0;
    while(true) {
        String currentGene = findGene(dna, startIndex);
    if (currentGene.isEmpty()){
        break;
    }
    System.out.println(currentGene);
    startIndex = dna.indexOf(currentGene, startIndex) + 
                 currentGene.length();
    }
  }
  
  public void testOn(String dna){
    System.out.println("Testing printAllGenes on " + dna);
    printAllGenes(dna);
  }
  
  public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
  }
  
  public int countGenes(String dna){
    int startIndex = dna.indexOf("ATG");
    int count = 0;
    while(true) {
        String currentGene = findGene(dna, startIndex);
        
    if (currentGene.isEmpty()){
        break;
    }
    System.out.println(currentGene);
    startIndex = dna.indexOf(currentGene, startIndex) + 
                 currentGene.length();
    count++;
    }
    return count;
  }
  
  public void testCountGenes(){
    String dna = "ATGCCCGGGAAATAACCCTAA";
    int gene = countGenes(dna);
    System.out.println("DNA String is " + dna);
    System.out.println("Number of Genes is " + gene);
    
    dna = "ATGCCCGGGAAACCC";
    gene = countGenes(dna);
    System.out.println("DNA String is " + dna);
    System.out.println("Number of Genes is " + gene);
    
    dna = "ATGCCCGGGAAACCCTAGCCCGGGTGA";
    gene = countGenes(dna);
    System.out.println("DNA String is " + dna);
    System.out.println("Number of Genes is " + gene);

    dna = "ATGCCCGGGAAACCCTGACCCATGGGGTAA";
    gene = countGenes(dna);
    System.out.println("DNA String is " + dna);
    System.out.println("Number of Genes is " + gene);

  }
}