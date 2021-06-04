package storagewriter;

import java.util.HashMap;
import java.util.Map;

public interface IndexWriter {
    public boolean write(Map<String, HashMap<String, Float>> words);
}
