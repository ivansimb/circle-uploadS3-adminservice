package sg.ihh.ms.as.app.repository.model;

public class Pagination {

    private int offset;

    private int pageSize;

    public Pagination(int offset, int pageSize) {
        this.offset = offset;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public int getPageSize() {
        return pageSize;
    }
}
