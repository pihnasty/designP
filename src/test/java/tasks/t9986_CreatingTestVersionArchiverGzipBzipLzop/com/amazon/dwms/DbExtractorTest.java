package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms;

import org.testng.annotations.Test;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */
public class DbExtractorTest extends BaseTest {

    @Test
    public void initialization() {
        DbExtractor dbExtractor = null;
        try {
            System.out.println("Start test");
            dbExtractor = new DbExtractor(DriverType.ORACLE,"jdbc:oracle:thin:@52.36.56.194:1521:ORA12C01", "ORA_DWH", "ORA_DWH99",32);
            System.out.println("Get TableStatistics...");
            TableStatistics tableStatistics =dbExtractor.getTableStatistics("SILIN", "LINEORDER_PART", 16);
            System.out.println(tableStatistics);

        } finally {
            if (dbExtractor != null)
                dbExtractor.close();
        }
    }

    @Test
    public void dataExtraction() {
        DbExtractor dbExtractor = null;
        try {
            dbExtractor = new DbExtractor(DriverType.ORACLE,"jdbc:oracle:thin:@52.36.56.194:1521:ORA12C01", "ORA_DWH", "ORA_DWH99",32);

            String query = "SELECT lo_orderkey,\n" +
                    "       lo_linenumber,\n" +
                    "       lo_custkey,\n" +
                    "       lo_partkey,\n" +
                    "       lo_suppkey,\n" +
                    "       lo_orderdate,\n" +
                    "       lo_orderpriority,\n" +
                    "       lo_shippriority,\n" +
                    "       lo_quantity,\n" +
                    "       lo_extendedprice,\n" +
                    "       lo_ordertotalprice,\n" +
                    "       lo_discount,\n" +
                    "       lo_revenue,\n" +
                    "       lo_supplycost,\n" +
                    "       lo_tax,\n" +
                    "       lo_commitdate,\n" +
                    "       lo_shipmode\n" +
                    "  FROM (SELECT lo_orderkey,\n" +
                    "               lo_linenumber,\n" +
                    "               lo_custkey,\n" +
                    "               lo_partkey,\n" +
                    "               lo_suppkey,\n" +
                    "               lo_orderdate,\n" +
                    "               lo_orderpriority,\n" +
                    "               lo_shippriority,\n" +
                    "               lo_quantity,\n" +
                    "               lo_extendedprice,\n" +
                    "               lo_ordertotalprice,\n" +
                    "               lo_discount,\n" +
                    "               lo_revenue,\n" +
                    "               lo_supplycost,\n" +
                    "               lo_tax,\n" +
                    "               lo_commitdate,\n" +
                    "               lo_shipmode,\n" +
                    "               ROWNUM rn\n" +
                    "          FROM silin.lineorder_part lp\n" +
                    "         WHERE ROWNUM <= ?)\n" +
                    "WHERE rn >= ?";


            dbExtractor.extractData(query, "SILIN", "LINEORDER_PART", "D:\\DWMS.out", "gzip", 200000, 8, 16);
        } finally {
            if (dbExtractor != null)
                dbExtractor.close();
        }
    }




}
