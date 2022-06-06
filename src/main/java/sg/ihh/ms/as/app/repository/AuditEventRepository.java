package sg.ihh.ms.as.app.repository;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.springframework.stereotype.Repository;
import sg.ihh.ms.as.app.model.AuditEvent;
import sg.ihh.ms.as.app.rest.model.AuditEventRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AuditEventRepository extends BaseRepository {

    public AuditEventRepository() {
        log = getLogger(this.getClass());
    }

    public List<AuditEvent> searchAuditEvents(AuditEventRequest request) {
        final String methodName = "searchAuditEvents";
        final String sql = "SELECT id, endtime, result, sso_user AS ssouser, app_name AS appname, ipaddr , type, user_agent AS userAgent  " +
                "FROM SSOEvents s " +
                "WHERE s.starttime >= :from AND s.endtime <= :to ";

        List<AuditEvent> eventList = new ArrayList<>();
        start(methodName);
        try (Handle h = getHandle(); Query query = h.createQuery(sql)) {
            query.bind("from", request.getFrom());
            query.bind("to", request.getTo());
            eventList = query.mapToBean(AuditEvent.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return eventList;
    }
}
