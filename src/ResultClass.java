import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResultClass implements Result{
    public   String Query;
    public Map<String, Set<String>> Answer;

    public ResultClass() {
         Answer = new HashMap<>();

    }

    @Override
    public String getQuery() {

        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public void setAnswer(Map<String, Set<String>> answer) {
        Answer = answer;
    }

    @Override
    public Map<String, Set<String>> getAnswer() {
        return Answer;
    }
}
