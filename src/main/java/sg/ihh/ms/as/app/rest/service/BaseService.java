package sg.ihh.ms.as.app.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sg.ihh.ms.as.app.util.json.JsonUtil;

public class BaseService {

    protected Logger log;

    public BaseService() {
        // Empty Constructor
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

    protected void logRequest(String methodName, Object obj) {
        String json = JsonUtil.toJson(obj);
        log.debug("[{}] Request : {}", methodName, json);
    }

}
