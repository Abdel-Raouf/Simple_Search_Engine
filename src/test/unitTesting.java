import langdocumentprocessors.EnglishDocumentProcessor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// unit testing using Junit.
public class unitTesting {

    String doc_1 = "the brown fox jumped over the brown dog";
    String doc_2 = "the lazy brown dog sat in the corner";
    String doc_3 = "the red fox bit the lazy dog";
    List<String> docs = new ArrayList<>();


    @Test
    public void englishDocumentProcessorTest() {
        docs.add(doc_1);
        docs.add(doc_2);
        docs.add(doc_3);

        EnglishDocumentProcessor englishDocumentProcessor = new EnglishDocumentProcessor();

        final Map<String, HashMap<String, Float>> result = englishDocumentProcessor.processDocument(docs);

        final HashMap<String, Float> innerSortingTfIdf_1 = new HashMap<String, Float>() {
            {
                put("the", 0.25F); //TODO: see the problem of 'double' here and why we need to cast to 'float'
                put("over", 0.18464015F);
                put("jumped", 0.18464015F);
                put("brown", 0.25F);
                put("dog", 0.125F);
                put("fox", 0.125F);
            }
        };

        final HashMap<String, Float> innerSortingTfIdf_2 = new HashMap<String, Float>() {
            {
                put("the", 0.25F); //TODO: see the problem of 'double' here and why we need to cast to 'float'
                put("corner",0.18464015F);
                put("in", 0.18464015F);
                put("lazy", 0.125F);
                put("sat", 0.18464015F);
                put("brown", 0.125F);
                put("dog", 0.125F);
            }
        };

        final HashMap<String, Float> innerSortingTfIdf_3 = new HashMap<String, Float>() {
            {
                put("the", 0.2857143F); //TODO: see the problem of 'double' here and why we need to cast to 'float'
                put("red", 0.21101733F);
                put("lazy", 0.14285715F);
                put("bit", 0.21101733F);
                put("dog", 0.14285715F);
                put("fox", 0.14285715F);
            }
        };
        final Map<String, HashMap<String, Float>> expected = new HashMap<String , HashMap<String, Float>>() {
            {
                put("document 1", innerSortingTfIdf_1);
                put("document 2", innerSortingTfIdf_2);
                put("document 3", innerSortingTfIdf_3);
            }
        };

        Assert.assertEquals(expected, result);
        System.out.println("Test Passed");

    }

}
