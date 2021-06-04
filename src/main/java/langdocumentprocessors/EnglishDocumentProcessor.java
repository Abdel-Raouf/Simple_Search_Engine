package langdocumentprocessors;

import java.util.*;

public class EnglishDocumentProcessor implements DocumentProcessor {

    @Override
    public Map<String, HashMap<String, Float>> processDocument(List<String> docs) {
        // TODO: process the document text and handle language-specific issues (stop words, synonyms, stems, etc) => Extra functionality.

        // core
        Map<String, HashMap<String, Float>> termFreq = termFrequency(invertedIndex(docs));


        // extras
        // this.handleStopWords(word);
        // this.handleSynonyms(word);

        return tfMultiplyIdf( termFreq, inverseDocFrequency(docs, termFreq) );
    }

    private Map<HashMap<String, Integer> , HashMap<String, Integer>> invertedIndex(List<String> docs) {
        Map<HashMap<String, Integer> , HashMap<String, Integer>> inverted_index = new HashMap<>();

        for (int i=1; i < docs.size()+1; i++) {
            HashMap<String, Integer> wordsCounterMap = new HashMap<>();
            HashMap<String, Integer> docAndDocLength = new HashMap<>();

            String doc = String.format("document %s", i);
            int doc_text_length = (docs.get(i-1).length() - docs.get(i-1).replaceAll(" ", "").length()) + 1;
            String[] document_words = docs.get(i-1).toLowerCase().split(" ");

            for (String word : document_words){
                if (wordsCounterMap.containsKey(word)){
                    wordsCounterMap.put(word, wordsCounterMap.get(word) + 1);
                }else{
                    wordsCounterMap.put(word, 1);
                }
            }
            docAndDocLength.put(doc, doc_text_length);
            inverted_index.put(docAndDocLength, wordsCounterMap);
        }
            return inverted_index;
    };

    private Map<String, HashMap<String, Float>> termFrequency(Map<HashMap<String, Integer>,
                                                                HashMap<String, Integer>> invertedIndex)
    {
        Map<String, HashMap<String, Float>> termFreq = new HashMap<>();

        for (int i=0; i < invertedIndex.keySet().size(); i++) {
            HashMap<String, Float> innerTermFreq = new HashMap<>();

            Set<String> words = invertedIndex.get(invertedIndex.keySet().toArray()[i]).keySet();

            for (String word : words) {
                int wordValue = invertedIndex.get(invertedIndex.keySet().toArray()[i]).get(word);
                String doc = ((HashMap<String, Integer>) invertedIndex.keySet().toArray()[i]).keySet().toArray()[0].toString();
                int doc_text_length = ((HashMap<String, Integer>) invertedIndex.keySet().toArray()[i]).get(doc);
                float termFreqCal = (float) wordValue / doc_text_length;

                innerTermFreq.put(word, termFreqCal);
                termFreq.put(doc, innerTermFreq);
            }
        }
        return termFreq;
    };

    private Map<String, Float> inverseDocFrequency(List<String> docs, Map<String, HashMap<String, Float>> termFreq) {

        Map<String, Float> invDocFreq = new HashMap<>();

        for(int j=0; j < termFreq.size(); j++) {
            int termFreqLength = termFreq.get( (termFreq.keySet().toArray())[j] ).size();
            for (int x = 0; x < termFreqLength; x++) {
                String word = String.valueOf(termFreq.get( (termFreq.keySet().toArray())[j] ).keySet().toArray()[x]);
                int counterOfWordPerDoc = 0;
                for (int i = 0; i < docs.size(); i++) {
                    if (docs.get(i).contains(word)) {
                        counterOfWordPerDoc++;
                    }
                }

                float invDocFreqCal =  (float) (Math.log10(docs.size() / counterOfWordPerDoc) + 1);

                invDocFreq.put(word, invDocFreqCal);
                counterOfWordPerDoc = 0;
            }
        }

        return invDocFreq;
    };

    private Map<String, HashMap<String, Float>> tfMultiplyIdf(Map<String, HashMap<String, Float>> termFreq,
                                                              Map<String, Float> invDocFreq) {

        Map<String, HashMap<String, Float>> tfIdfSorting = new HashMap<>();

        for(int j=0; j < termFreq.size(); j++) {
            int termFreqLength = termFreq.get((termFreq.keySet().toArray())[j]).size();
            String doc = (String) termFreq.keySet().toArray()[j];
            HashMap<String, Float> innerSortingTfIdf = new HashMap<>();
            for (int i = 0; i < termFreqLength; i++) {
                String word = String.valueOf(termFreq.get((termFreq.keySet().toArray())[j]).keySet().toArray()[i]);
                for (String idfKey : invDocFreq.keySet()) {
                    if (word.equals(idfKey)) {
                        float tfMultiplyIdf = termFreq.get((termFreq.keySet().toArray())[j]).get(word) * invDocFreq.get(idfKey);
                        innerSortingTfIdf.put(word, tfMultiplyIdf);
                        tfIdfSorting.put(doc , innerSortingTfIdf);
                        break;
                    }

                }
            }
        }


        return tfIdfSorting;
    };

    private void handleStopWords(String word) {
        // TODO
    }

    private void handleSynonyms(String word) {
        // TODO
    }
}