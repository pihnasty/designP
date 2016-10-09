package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160831;

/**
 * Created by yaroshenko.y on 8/5/2016.
 */
final class ExtractingRequest {
    final private String schema;
    final private String table;
    final private String queryText;

    ExtractingRequest(String schema, String table, String queryText) {
        this.schema = schema;
        this.table = table;
        this.queryText = queryText;
    }

    String getQueryText() {
        return queryText;
    }

    String getSchema() {
        return schema;
    }

    String getTable() {
        return table;
    }
}
