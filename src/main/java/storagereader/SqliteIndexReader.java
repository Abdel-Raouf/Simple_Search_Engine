package storagereader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SqliteIndexReader implements IndexReader {
    private final String sqliteDatabasePath;

    public SqliteIndexReader(String sqliteDatabasePath) {
        this.sqliteDatabasePath = sqliteDatabasePath;
    }

    @Override
    public List<String> read(String word) {
        List<String> docsList = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        String selectFromSQLTable = String.format("SELECT docs FROM search_engine WHERE word=='%s' ORDER BY sorting_score DESC", word);

        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(sqliteDatabasePath);

            conn.setAutoCommit(false);

            System.out.println("Opened database successfully");

            statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(selectFromSQLTable);

            while (resultSet.next()) {
                String doc = resultSet.getString("docs");
                docsList.add(doc);
            }

            resultSet.close();
            statement.close();
            conn.close();

            System.out.println("word have been fetched successfully");

        } catch (Exception e) {
            System.out.println(e);
        }

        return docsList;
    }
}
