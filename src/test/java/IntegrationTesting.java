import langdocumentprocessors.EnglishDocumentProcessor;
import org.junit.Assert;
import org.junit.Test;
import storagewriter.SqliteIndexWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IntegrationTesting {
    String sqliteDatabasePath = "jdbc:sqlite:" + String.valueOf(getClass().getResource("/database/search_engine.db"));

    String doc_1 = "the brown fox jumped over the brown dog";
    String doc_2 = "the lazy brown dog sat in the corner";
    String doc_3 = "the red fox bit the lazy dog";
    List<String> docs = new ArrayList<>();

    @Test
    public void testSimpleSentencesProcessingWithSqlite() { // TODO: we can pass here the TextDocumentReader (if we extend our app functionality).
        docs.add(doc_1);
        docs.add(doc_2);
        docs.add(doc_3);

        EnglishDocumentProcessor englishDocumentProcessor = new EnglishDocumentProcessor();
        SqliteIndexWriter writeToSqlite = new SqliteIndexWriter(sqliteDatabasePath);

        Map<String, HashMap<String, Float>> results = englishDocumentProcessor.processDocument(docs);

        Assert.assertEquals(true, writeToSqlite.write(results));
    }

}
