package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160804;

final class TableStatistics {
    private final String schema;
    private final String table;


    private final long rowCount;
    private final long size;
    private final long countInBatch;
    private final long batchCount;
    private final long countRowRoundError;


    TableStatistics(String schema, String table, long rowCount, long size, long countInBatch, long batchCount, long countRowRoundError) {
        this.schema = schema;
        this.table = table;
        this.rowCount = rowCount;
        this.size = size;
        this.countInBatch = countInBatch;
        this.batchCount = batchCount;
        this.countRowRoundError = countRowRoundError;
    }

    public String getSchema() {
        return schema;
    }

    public String getTable() {
        return table;
    }

    public long getRowCount() {
        return rowCount;
    }

    public long getSize() {
        return size;
    }

    public long getCountInBatch() {
        return countInBatch;
    }

    public long getBatchCount() {
        return batchCount;
    }

    public long getCountRowRoundError() {
        return countRowRoundError;
    }

    @Override
    public String toString() {
        return "TableStatistics{" +
                "schema='" + schema + '\'' +
                ", table='" + table + '\'' +
                ", rowCount=" + rowCount +
                ", size=" + size +
                ", countInBatch=" + countInBatch +
                ", batchCount=" + batchCount +
                ", countRowRoundError=" + countRowRoundError +
                '}';
    }
}
