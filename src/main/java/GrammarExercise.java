import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class GrammarExercise {
    public static void main(String[] args) throws RuntimeException{

    	Scanner scan =new Scanner(System.in);
    	while (scan.hasNext()) {
    		 String firstWordList =scan.next();
    	     String secondWordList =scan.next();
    	     if(isMatches(firstWordList) && isMatches(secondWordList)){
				 List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
				 if(result.size()>0) {
					 for(int i=0; i<result.size(); i++) {
						 System.out.print(result.get(i)+" ");
					 }
				 }
			 }else{
				 throw new RuntimeException("input not valid");
			 }
    	}
    	scan.close();

   }


    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) throws RuntimeException{
        firstWordList = firstWordList.toLowerCase();
    	secondWordList = secondWordList.toLowerCase();
    	List<String> firstList=Arrays.asList(firstWordList.split(","));
    	List<String> asList=Arrays.asList(secondWordList.split(","));
		List<String> resultList3 = null;
    	int cout1=0;
    	int coun2=0;
    	for(int i=0;i<firstList.size();i++){
    		if(!isMatches(firstList.get(i))){
				cout1=cout1+1;
			}
		}
		for(int i=0;i<asList.size();i++){
			if(!isMatches(asList.get(i))){
				coun2=coun2+1;
			}
		}
		if( cout1==0 && coun2==0 ){
			List<String> resultList1=(List)firstList.stream().filter(it->asList.contains(it)).collect(Collectors.toList());

			List<String> resultList2=(List)resultList1.parallelStream().distinct().collect(Collectors.toList());

			resultList3=(List)resultList2.stream().sorted().collect(Collectors.toList());
		}else{
			throw new RuntimeException("input not valid");
		}
		return resultList3;
    }


    public static boolean isMatches(String wordList){
		Pattern patt=Pattern.compile("^[A-Za-z]*([A-Za-z]+[,])*([A-Za-z]+)$");
		//Pattern patt=Pattern.compile("[^a-z]");
		Matcher match=patt.matcher(wordList);
		return match.matches();
	}
}
