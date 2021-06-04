import org.junit.Assert;
import org.junit.Test;
import storagereader.SqliteIndexReader;

import java.util.ArrayList;
import java.util.List;

public class FunctionalTesting {

    String sqliteDatabasePath = "jdbc:sqlite:" + String.valueOf(getClass().getResource("/search_engine.db"));

    // TODO:  We will query sqlite here.
    @Test
    public void testQueryWordFromSqlite() {
        SqliteIndexReader readFromSqlite = new SqliteIndexReader(sqliteDatabasePath);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("document 1");
        expectedList.add("document 2");


        Assert.assertEquals(expectedList, readFromSqlite.read("brown"));

    }
}
