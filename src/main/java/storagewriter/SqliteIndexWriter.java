package storagewriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class SqliteIndexWriter implements IndexWriter {
    private final String sqliteDatabasePath;

    public SqliteIndexWriter(String sqliteDatabasePath) {
        this.sqliteDatabasePath = sqliteDatabasePath;
    }

    @Override
    public boolean write(Map<String, HashMap<String, Float>> words) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String insertIntoSQLTable = "INSERT INTO SEARCH_ENGINE"
                + "(word, docs, sorting_score)"
                + "VALUES (?,?,?)";

        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(sqliteDatabasePath);

            conn.setAutoCommit(false);

            System.out.println("Opened database successfully");

            preparedStatement = conn.prepareStatement(insertIntoSQLTable);

            for (String doc : words.keySet()) {
                int wordsLength = words.get( doc ).size();
                for(int x=0; x < wordsLength; x++) {
                    preparedStatement.setString(1, String.valueOf(words.get( doc ).keySet().toArray()[x])); // getting words.
                    preparedStatement.setString(2, doc); // getting documents.
                    preparedStatement.setFloat(3, words.get( doc ).get(words.get( doc ).keySet().toArray()[x]) ); // getting sorting score.

                    preparedStatement.executeUpdate();
                    conn.commit();
                }
            }

            preparedStatement.close();
            conn.close();
            System.out.println("all words have been inserted successfully.");

        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }


}
