import scala.io.Source
import scala.collection.mutable
import java.io._
import scala.math._


object hw1{
    def main(args: Array[String]){
       // var posFileName = "";
       // var negFileName = "";
        var negTerm = mutable.Map[String,Double]().withDefault(x=>0)
        var posTerm = mutable.Map[String,Double]().withDefault(x=>0)
        var negWordCountsTotal = 0.0;
        var posWordCountsTotal = 0.0;
        //var freq = ListMap[String, Double]().withDefault(x=>0)
        //var freq = mutable.Map[String, Double]().withDefault(x=>0)
        val unnecWords = Array[String](".", "&", ":", "\"", ",", "?","(", "movie", "film", "is", "was", ")", 
    "been","a", "about", "above", "across", "after", "afterwards",
    "again", "against", "all", "almost", "alone", "along",
    "already", "also", "although", "always", "am", "among",
    "amongst", "amoungst", "amount", "an", "and", "another",
    "any", "anyhow", "anyone", "anything", "anyway", "anywhere",
    "are", "around", "as", "at", "back", "be",
    "became", "because", "become", "becomes", "becoming", "been",
    "before", "beforehand", "behind", "being", "below", "beside",
    "besides", "between", "beyond", "bill", "both", "bottom",
    "but", "by", "call", "can", "cannot", "cant", "dont",
    "co", "computer", "con", "could", "couldnt", "cry",
    "de", "describe", "detail", "do", "done", "down",
    "due", "during", "each", "eg", "eight", "either",
    "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
    "everyone", "everything", "everywhere", "except", "few", "fifteen",
    "fify", "fill", "find", "fire", "first", "five",
    "for", "former", "formerly", "forty", "found", "four",
    "from", "front", "full", "further", "get", "give",
    "go", "had", "has", "hasnt", "have", "he",
    "hence", "her", "here", "hereafter", "hereby", "herein",
    "hereupon", "hers", "herself", "him", "himself", "his",
    "how", "however", "hundred", "i", "ie", "if",
    "in", "inc", "indeed", "interest", "into", "is",
    "it", "its", "itself", "keep", "last", "latter",
    "latterly", "least", "less", "ltd", "made", "many",
    "may", "me", "meanwhile", "might", "mill", "mine",
    "more", "moreover", "most", "mostly", "move", "much",
    "must", "my", "myself", "name", "namely", "neither",
    "never", "nevertheless", "next", "nine", "no", "nobody",
    "none", "noone", "nor", "not", "nothing", "now",
    "nowhere", "of", "off", "often", "on", "once",
    "one", "only", "onto", "or", "other", "others",
    "otherwise", "our", "ours", "ourselves", "out", "over",
    "own", "part", "per", "perhaps", "please", "put",
    "rather", "re", "same", "see", "seem", "seemed",
    "seeming", "seems", "serious", "several", "she", "should",
    "show", "side", "since", "sincere", "six", "sixty",
    "so", "some", "somehow", "someone", "something", "sometime",
    "sometimes", "somewhere", "still", "such", "system", "take",
    "ten", "than", "that", "the", "their", "them",
    "themselves", "then", "thence", "there", "thereafter", "thereby",
    "therefore", "therein", "thereupon", "these", "they", "thick",
    "thin", "third", "this", "those", "though", "three",
    "through", "throughout", "thru", "thus", "to", "together",
    "too", "top", "toward", "towards", "twelve", "twenty",
    "two", "un", "under", "until", "up", "upon",
    "us", "very", "via", "was", "we", "well",
    "were", "what", "whatever", "when", "whence", "whenever",
    "where", "whereafter", "whereas", "whereby", "wherein", "whereupon",
    "wherever", "whether", "which", "while", "whither", "who",
    "whoever", "whole", "whom", "whose", "why", "will",
    "with", "within", "without", "would", "yet", "you", "your", "yours",
    "yourself", "yourselves", "just", "it's", "character", "people", "man", "movies",
    "films", "characters", "director", "work", "plot", "performance", "world", "years",
    "gets", "get", "things", "audience", "takes", "role", "he's", "plays", "fact", "cast",
    "look", "!", "thing", "played", "there's", "screen", "place", "that's", "actors", "acting",
    "-", "times", "job", "did", "do", "done", "john", "year", "film's", "day", "bit", "probably",
    "script", "wants", "works", "i'm", "men", "woman", "including", "certainly", "finally", "i've", 
    "david", "hand", "said", "doing", "production", "knows", "moment", "days", "michael", "sets", "roles",
    "particularly", "city", "title", "they're", "act", "screenplay", "robert", "aren't", "who's", "somewhat")
    
    val path = "/Users/MsJ/Documents/CAL/CS294/txt_sentoken";
        for(negFileName <- new File(path, "negtrain8").listFiles()){
           
            val negText = Source.fromFile(negFileName).getLines.toList.flatMap(x=> x.split(" ")) 
            for(word <- negText){
              if(!(unnecWords contains word)) {
                 negTerm(word) += 1.0;
                 negWordCountsTotal += 1.0;
              }
            }  

        }


        for(posFileName <- new File(path,"postrain8").listFiles()){
            val posText = Source.fromFile(posFileName).getLines.toList.flatMap(x=> x.split(" ")) 
            for(word <- posText){
              if(!(unnecWords contains word)) {
                 posTerm(word) += 1.0;
                 posWordCountsTotal += 1.0;
              }
            }
        }

        val negB = negTerm.size;
        val posB = posTerm.size;
        val laplace = 1.0;
        var negPrior = mutable.Map[String,Double]().withDefault(x=>0)
        var posPrior = mutable.Map[String,Double]().withDefault(x=>0)
        for((k,v) <- negTerm){
            negPrior(k) = log((v+laplace)/(negWordCountsTotal+negB))
        }

        for((k,v) <- posTerm){
            posPrior(k) = log((v+laplace)/(posWordCountsTotal+posB))
        }
        
        //for((k,v) <- posPrior) println(k+ " -> " + v)

        //run classification
       // val testfile ="/Users/MsJ/Documents/CAL/CS294/txt_sentoken/neg/cv000_29416.txt"
       val negdumb = log(laplace/(negWordCountsTotal+laplace*negB));
       val posdumb = log(laplace/(posWordCountsTotal+laplace*posB));

        var negcount = 0;
        var poscount = 0;      
        
        for(postestFileName <- new File(path,"postest8").listFiles()){
            val test = Source.fromFile(postestFileName).getLines.toList.flatMap(x=> x.split(" "));
            var negLogPosterior = 0.0;
            var posLogPosterior = 0.0;
            for(word <- test){
              if(!unnecWords.contains(word)){
                  var logprob = 0.0;
          
                  if(negPrior.contains(word)){
                    logprob = negPrior(word);
                  }
                  else{
                    logprob = negdumb;
                  }
                  negLogPosterior += logprob;
                  
                  if(posPrior.contains(word)){
                    logprob = posPrior(word);
                  }
                  else{
                    logprob = posdumb;
                  }
                  posLogPosterior += logprob;
                }
            }

            
            if(posLogPosterior> negLogPosterior){
                poscount += 1;
            }

        }
        for(negtestFileName <- new File(path, "negtest8").listFiles()){
            val test = Source.fromFile(negtestFileName).getLines.toList.flatMap(x=> x.split(" "));
            var negLogPosterior = 0.0;
            var posLogPosterior = 0.0;
            for(word <- test){
                  if(!unnecWords.contains(word)){
                  var logprob = 0.0;
          
                  if(negPrior.contains(word)){
                    logprob = negPrior(word);
                  }
                  else{
                    logprob = negdumb;
                  }
                  negLogPosterior += logprob;
                  
                  if(posPrior.contains(word)){
                    logprob = posPrior(word);
                  }
                  else{
                    logprob = posdumb;
                  }
                  posLogPosterior += logprob;

                }
            }

            
            if(negLogPosterior> posLogPosterior){
                negcount += 1;
            }

        }
        // compute statistics
        var tp = poscount;
        var fp = 100.0 - negcount;
        var fn = 100.0 - poscount;
        var precision = tp/(tp + fp);
        var recall = tp/(tp + fn);
        
        var F1 = 2*precision*recall/(precision+recall);
        println("tp: "+ poscount)
        println("tn: "+ negcount)
        println(F1);
     
        }
}


