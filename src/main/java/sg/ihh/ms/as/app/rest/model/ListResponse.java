package sg.ihh.ms.as.app.rest.model;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class ListResponse extends ServiceResponse {

    @JsonProperty("data")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Map<String, Object>> dataList;

    @JsonProperty("count")
    private int count;

    public ListResponse(List<Map<String, Object>> dataList, int count) {
        super(HttpStatus.OK);
        this.dataList = dataList;
        this.count = count;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
