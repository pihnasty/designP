package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160831;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaroshenko.y on 8/2/2016.
 */
public class App {

    private final static Map<DriverType,ExtractingRequest> tableQueries;
    static {
        Map<DriverType,ExtractingRequest> aMap = new HashMap<DriverType,ExtractingRequest>(){
            {
                put(DriverType.ORACLE,  new ExtractingRequest("SILIN", "LINEORDER_PART" ,
                        "SELECT lo_orderkey,\n" +
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
                        "FROM (\n" +
                        "         SELECT lo_orderkey,\n" +
                        "                lo_linenumber,\n" +
                        "                lo_custkey,\n" +
                        "                lo_partkey,\n" +
                        "                lo_suppkey,\n" +
                        "                lo_orderdate,\n" +
                        "                lo_orderpriority,\n" +
                        "                lo_shippriority,\n" +
                        "                lo_quantity,\n" +
                        "                lo_extendedprice,\n" +
                        "                lo_ordertotalprice,\n" +
                        "                lo_discount,\n" +
                        "                lo_revenue,\n" +
                        "                lo_supplycost,\n" +
                        "                lo_tax,\n" +
                        "                lo_commitdate,\n" +
                        "                lo_shipmode,\n" +
                        "                ROW_NUMBER() OVER (ORDER BY NULL) rn\n" +
                        "         FROM silin.lineorder_part lp\n" +
                        "     )\n" +
                        "WHERE rn BETWEEN ? AND ?"));
                put(DriverType.TERADATA,  new ExtractingRequest("loaddb","lineorder", "SELECT lo_orderkey,\n" +
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
                        "       lo_shipmode,\n" +
                        "       ROW_NUMBER() OVER (ORDER BY lo_orderkey ASC) AS rn\n" +
                        "FROM loaddb.lineorder t\n" +
                        "QUALIFY rn BETWEEN ? and ?"));
            }
        };
        tableQueries = Collections.unmodifiableMap(aMap);
    }



    private static void printUsage() {
        System.err.println("Incorrect parameters...");
        System.err.println();
        System.err.println("Usage: java -jar DWMS-1.1.jar com.amazon.dwms.App <args>");
        System.err.println("Args:");
        System.err.println("    -driver<DriverType> (Oracle|Teradata)");
        System.err.println("    -host<URL>");
        System.err.println("    -port<PortNumber>");
        System.err.println("    -sid<OracleServiceName>");
        System.err.println("    -user<UserName>");
        System.err.println("    -pwd<Password>");
        System.err.println("    -out<OutFolder>");
        System.err.println("    -log<LogFolder>");
        System.err.println("    -compression<CompressionType> gzip|bzip2|lzop|NoCompression(default)");
        System.err.println("    -thread<MaxActiveThread> (default=8)");
        System.err.println("    -connection<MaxConnections> (default=32)");
        System.err.println("    -fetch<FetchSize> (default=200000)");
        System.err.println("    -slices<SliceCount> (default=8)");
        System.err.println("    -verify (to verify compressed stream)");
        System.err.println("-----");
        System.err.println(" Example: java -jar DWMS-1.1.jar com.amazon.dwms.App -driverORACLE -Host\"127.0.0.1\" -port1521 -sidORCL -userORA_USER -PWd\"***\" -thread8 -fetch200000 -out\"D:\\DWMS.out\" -log\"D:\\DWMS.log\" -slices16");
        System.err.println("-----");
        System.err.println();

    }

    public static void main(String[] args) {

        String host = null;
        int    port = -1;
        String sid = null;
        String user = null;
        String password = null;
        String outFolder = null;
        String logFolder = null;
        String compression = "nocompression";
        CompressMethod compressMethod = CompressMethod.NO_COMPRESSION;
        int fetchSize = 200000;
        int threadCount = 8;
        int sliceCount = 8;
        int connectionCount = 32;
        DriverType driver = null;
        boolean verifyCompressedStream = false;

        for(int i=0 ; i<args.length; i++) {
            //Log.write("[%d]=%s",i,args[i]);
            String argValue = args[i].toLowerCase();
            if (argValue.startsWith("-host"))
                host = args[i].substring(5);
            else if (argValue.startsWith("-port"))
                port = Integer.parseInt(args[i].substring(5));
            else if (argValue.startsWith("-sid"))
                sid = args[i].substring(4);
            else if (argValue.startsWith("-user"))
                user = args[i].substring(5);
            else if (argValue.startsWith("-pwd"))
                password = args[i].substring(4);
            else if (argValue.startsWith("-out"))
                outFolder = args[i].substring(4);
            else if (argValue.startsWith("-verify"))
                verifyCompressedStream = true;
            else if (argValue.startsWith("-log"))
                logFolder = args[i].substring(4);
            else if (argValue.startsWith("-thread"))
                threadCount =  Integer.parseInt(args[i].substring(7));
            else if (argValue.startsWith("-connection"))
                connectionCount =  Integer.parseInt(args[i].substring(11));
            else if (argValue.startsWith("-fetch"))
                fetchSize =  Integer.parseInt(args[i].substring(6));
            else if (argValue.startsWith("-slices"))
                sliceCount =  Integer.parseInt(args[i].substring(7));
            else if (argValue.startsWith("-driver")) {
                try {
                    driver = DriverType.valueOfIgnoreCase(args[i].substring(7));
                } catch (Throwable e) {
                    printUsage();
                    throw new IllegalArgumentException("The specified driver is not supported");
                }
            } else if (argValue.startsWith("-compression")) {
                compression = args[i].substring(12);
                try {
                    compressMethod = CompressMethod.valueOfByString(compression);
                } catch (IllegalArgumentException e) {
                    printUsage();
                    throw new IllegalArgumentException(String.format("The specified compression method (%s) is not supported. Use gzip, bzip2, lzop or nocompression.",compression));
                }
            }

        }

        if ((host==null) || (user==null) || (password==null) || (outFolder==null) || (logFolder==null)
                || (driver==null)  || (port == -1) ) {
            printUsage();
            throw new IllegalArgumentException();
        }

        DbExtractor dbExtractor = null;
        try {
            Log.init(Paths.get(logFolder));
            String url=null;
            switch (driver) {
                case ORACLE:
                    default:
                    url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);
                    break;
                case TERADATA:
                    url = String.format("jdbc:teradata://%s/dbs_port=%d,charset=UTF8,tmode=ANSI", host, port);
                    break;
//                case PROGRESS:
//                    url = String.format("jdbc:datadirect:oracle:/%s:%d;ServiceName=%s", host, port, sid);
//                    break;
            }

            dbExtractor = new DbExtractor(driver, url, user, password, connectionCount);

            dbExtractor.extractData(tableQueries.get(driver).getQueryText(), tableQueries.get(driver).getSchema(), tableQueries.get(driver).getTable(), outFolder, compressMethod, fetchSize, threadCount, sliceCount, verifyCompressedStream);

        } catch (Throwable e) {
            Log.write(e);
        } finally {
            if (dbExtractor != null)
                dbExtractor.close();

            Log.close();
        }
    }



}
