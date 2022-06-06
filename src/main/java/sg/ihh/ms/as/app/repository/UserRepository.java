package sg.ihh.ms.as.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository {

    public UserRepository() {
        log = getLogger(this.getClass());
    }

    public List<Map<String, Object>> searchUserById(String id) {
        final String methodName = "searchUserById";
        final String sql = "SELECT user_id, cn,sn,active,email FROM vUser WHERE user_id = :id;";
        List<Map<String, Object>> userList = new ArrayList<>();
        start(methodName);
        try (Handle h = getHandle(); Query query = h.createQuery(sql)) {
            query.bind("id", id);
            userList = query.map(getResultMapper()).list();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return userList;
    }

    public List<Map<String, Object>> searchUserByEmail(String email) {
        final String methodName = "searchUserByEmail";
        final String sql = "SELECT user_id, cn,sn,active,email FROM vUser WHERE email = :email;";
        List<Map<String, Object>> userList = new ArrayList<>();
        start(methodName);
        try (Handle h = getHandle(); Query query = h.createQuery(sql)) {
            query.bind("email", email);
            userList = query.map(getResultMapper()).list();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return userList;
    }
}
