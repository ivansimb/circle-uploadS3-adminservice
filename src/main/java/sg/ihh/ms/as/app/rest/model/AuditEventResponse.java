package sg.ihh.ms.as.app.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.springframework.http.HttpStatus;
import sg.ihh.ms.as.app.model.AuditEvent;

import java.util.List;

public class AuditEventResponse extends ServiceResponse {

    @JsonProperty("events")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AuditEvent> eventList;

    @JsonProperty("total")
    private int total;

    public AuditEventResponse(List<AuditEvent> eventList, int total) {
        super(HttpStatus.OK);
        this.eventList = eventList;
        this.total = total;
    }

    public List<AuditEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<AuditEvent> eventList) {
        this.eventList = eventList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
