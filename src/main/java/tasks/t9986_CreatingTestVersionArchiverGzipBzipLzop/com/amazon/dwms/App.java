package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms;

import java.nio.file.Paths;

/**
 * Created by yaroshenko.y on 8/2/2016.
 */
public class App {

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
        int fetchSize = 200000;
        int threadCount = 8;
        int sliceCount = 8;
        int connectionCount = 32;
        DriverType driver = null;

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
            else if (argValue.startsWith("-compression"))
                compression = args[i].substring(12);
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

            }
        }

        if ((host==null) || (user==null) || (password==null) || (outFolder==null) || (logFolder==null)
                || (driver==null)  || (port == -1) ) {
            printUsage();
            throw new IllegalArgumentException();
        }

        if ((!compression.equalsIgnoreCase("gzip")) &&
            (!compression.equalsIgnoreCase("bzip2")) &&
            (!compression.equalsIgnoreCase("lzop")) &&
            (!compression.equalsIgnoreCase("nocompression")) ) {
            printUsage();
            throw new IllegalArgumentException("The specified compression method is not supported. Use gzip, bzip2, lzop or nocompression.");
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
//                case PROGRESS:
//                    url = String.format("jdbc:datadirect:oracle:/%s:%d;ServiceName=%s", host, port, sid);
//                    break;
            }

            dbExtractor = new DbExtractor(driver, url, user, password, connectionCount);

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


            dbExtractor.extractData(query, "SILIN", "LINEORDER_PART", outFolder, compression, fetchSize, threadCount, sliceCount);

        } catch (Throwable e) {
            Log.write(e);
        } finally {
            if (dbExtractor != null)
                dbExtractor.close();

            Log.close();
        }
    }



}
