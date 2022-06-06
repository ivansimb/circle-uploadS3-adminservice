package sg.ihh.ms.as.app.repository;

import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sg.ihh.ms.as.app.repository.model.Pagination;
import sg.ihh.ms.as.app.repository.model.Sort;
import sg.ihh.ms.as.app.util.date.DateUtil;

public class BaseRepository {

    @Autowired
    protected Jdbi jdbi;

    protected Logger log;

    public BaseRepository() {
        // Empty Constructor
    }

    protected Handle getHandle() {
        return jdbi.open();
    }

    protected boolean executeUpdate(Update update) {
        return update.execute() >= 1;
    }

    protected int executeCount(Query query) {
        return query.mapTo(Integer.class).one();
    }

    protected Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    protected void start(String methodName) {
        log.debug("[{}] Start", methodName);
    }

    protected void completed(String methodName) {
        log.debug("[{}] Completed", methodName);
    }

    protected boolean isValid(List<?> list) {
        return list != null && !list.isEmpty();
    }

    protected boolean isValid(String str) {
        return str != null && !str.isEmpty();
    }

    protected boolean isValid(int value, int min) {
        return value >= min;
    }

    protected boolean isValid(int value, int min, int max) {
        return value >= min && value <= max;
    }

    protected boolean isValid(LocalDate value) {
        return value != null;
    }

    protected boolean isValid(Pagination pagination) {
        return pagination != null && pagination.getOffset() > -1 && pagination.getPageSize() > 0;
    }

    protected String generateSort(List<Sort> sortList) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!sortList.isEmpty()) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(sortList.stream().map(sort -> sort.getField() + " " + sort.getModifier())
                    .collect(Collectors.joining(", ")));
        }

        return stringBuilder.toString();
    }

    protected String generatePagination(Pagination pagination) {
        String statement = "";
        if (isValid(pagination)) {
            statement = "LIMIT " + pagination.getPageSize() + " OFFSET " + pagination.getOffset();
        }
        return statement;
    }

    protected RowMapper<Map<String, Object>> getResultMapper() {
        return (rs, ctx) -> {
            Map<String, Object> map = new HashMap<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String key = rsmd.getColumnName(i);
                int columnType = rsmd.getColumnType(i);

                // TIMESTAMP
                if (columnType == 93 && rs.getObject(key) != null) {
                    map.put(key, DateUtil.formatLocalDateTime(rs.getTimestamp(key).toLocalDateTime()));
                }
                // DATE
                else if (columnType == 91 && rs.getObject(key) != null) {
                    map.put(key, DateUtil.formatLocalDate(rs.getTimestamp(key).toLocalDateTime().toLocalDate()));
                } else {
                    map.put(key, rs.getObject(key));
                }
            }
            return map;

        };
    }
}
