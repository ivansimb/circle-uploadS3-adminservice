package sg.ihh.ms.as.app.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.ihh.ms.as.app.model.AuditEvent;
import sg.ihh.ms.as.app.repository.AuditEventRepository;
import sg.ihh.ms.as.app.rest.model.AuditEventRequest;
import sg.ihh.ms.as.app.rest.model.AuditEventResponse;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "audit-events", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuditEventService extends BaseService {

    @Autowired
    private AuditEventRepository auditEventRepository;

    public AuditEventService() {
        log = getLogger(this.getClass());
    }

    @PostMapping("search")
    public AuditEventResponse search(@RequestBody @Valid AuditEventRequest request) {
        final String methodName = "search";
        start(methodName);
        logRequest(methodName, request);
        List<AuditEvent> list = new ArrayList<>();
        list = auditEventRepository.searchAuditEvents(request);

        AuditEventResponse response = new AuditEventResponse(list, list.size());

        completed(methodName);
        return response;
    }
}
