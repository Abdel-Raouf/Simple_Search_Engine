package langdocumentprocessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DocumentProcessor {
    public  Map<String, HashMap<String, Float>> processDocument(List<String> docs);
}
