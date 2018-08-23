package jdbcTest.sqlTest.mscallable;

public class SqlCollection {
  public static String sqlSrtarted = "                            SELECT schedule_id\n" +
      "                                 --, schedule_uid\n" +
      "                                 , originating_server_id\n" +
      "                                 , name\n" +
      "                                 --, owner_sid\n" +
      "                                 , enabled\n" +
      "                                 , freq_type\n" +
      "                                 , freq_interval\n" +
      "                                 , freq_subday_type\n" +
      "                                 , freq_subday_interval\n" +
      "                                 , freq_relative_interval\n" +
      "                                 , freq_recurrence_factor\n" +
      "                                 , active_start_date\n" +
      "                                 , active_end_date\n" +
      "                                 , active_start_time\n" +
      "                                 , active_end_time\n" +
      "                              FROM msdb.dbo.sysschedules";

  public static String sql_v01 = "DECLARE @tab TABLE(\n" +
      "\t[schedule_id] [int] NOT NULL,\n" +
      "\t[schedule_uid] [uniqueidentifier] NOT NULL,\n" +
      "\t[schedule_name] [sysname] NOT NULL,\n" +
      "\t[enabled] [int] NOT NULL,\n" +
      "\t[freq_type] [int] NOT NULL,\n" +
      "\t[freq_interval] [int] NOT NULL,\n" +
      "\t[freq_subday_type] [int] NOT NULL,\n" +
      "\t[freq_subday_interval] [int] NOT NULL,\n" +
      "\t[freq_relative_interval] [int] NOT NULL,\n" +
      "\t[freq_recurrence_factor] [int] NOT NULL,\n" +
      "\t[active_start_date] [int] NOT NULL,\n" +
      "\t[active_end_date] [int] NOT NULL,\n" +
      "\t[active_start_time] [int] NOT NULL,\n" +
      "\t[active_end_time] [int] NOT NULL,\n" +
      "\t[date_created] [datetime] NOT NULL,\n" +
      "\t[schedule_description] [nvarchar](4000) NULL,\n" +
      "\t[job_count] [int] NULL\n" +
      ");\n" +
      "INSERT INTO @tab EXEC msdb.dbo.sp_help_schedule;\n" +
      "SELECT schedule_id\n" +
      "     --, schedule_uid\n" +
      "     , 0 as originating_server_id\n" +
      "     , schedule_name as name\n" +
      "     --, owner_sid\n" +
      "     , [enabled]\n" +
      "     , freq_type\n" +
      "     , freq_interval\n" +
      "     , freq_subday_type\n" +
      "     , freq_subday_interval\n" +
      "     , freq_relative_interval\n" +
      "     , freq_recurrence_factor\n" +
      "     , active_start_date\n" +
      "     , active_end_date\n" +
      "     , active_start_time\n" +
      "     , active_end_time\n" +
      "  FROM @tab;";



  public static String sql_v02 =
      "SET NOCOUNT ON; \n" +
      "DECLARE @tab TABLE(\n" +
      "\t[schedule_id] [int] NOT NULL,\n" +
      "\t[schedule_uid] [uniqueidentifier] NOT NULL,\n" +
      "\t[schedule_name] [sysname] NOT NULL,\n" +
      "\t[enabled] [int] NOT NULL,\n" +
      "\t[freq_type] [int] NOT NULL,\n" +
      "\t[freq_interval] [int] NOT NULL,\n" +
      "\t[freq_subday_type] [int] NOT NULL,\n" +
      "\t[freq_subday_interval] [int] NOT NULL,\n" +
      "\t[freq_relative_interval] [int] NOT NULL,\n" +
      "\t[freq_recurrence_factor] [int] NOT NULL,\n" +
      "\t[active_start_date] [int] NOT NULL,\n" +
      "\t[active_end_date] [int] NOT NULL,\n" +
      "\t[active_start_time] [int] NOT NULL,\n" +
      "\t[active_end_time] [int] NOT NULL,\n" +
      "\t[date_created] [datetime] NOT NULL,\n" +
      "\t[schedule_description] [nvarchar](4000) NULL,\n" +
      "\t[job_count] [int] NULL\n" +
      ");\n" +


         "BEGIN\n"+
          " INSERT INTO @tab EXECUTE (N'EXEC msdb.dbo.sp_help_schedule');\n" +
          "END\n"+



      "SELECT schedule_id\n" +
      "     --, schedule_uid\n" +
      "     , 0 as originating_server_id\n" +
      "     , schedule_name as name\n" +
      "     --, owner_sid\n" +
      "     , [enabled]\n" +
      "     , freq_type\n" +
      "     , freq_interval\n" +
      "     , freq_subday_type\n" +
      "     , freq_subday_interval\n" +
      "     , freq_relative_interval\n" +
      "     , freq_recurrence_factor\n" +
      "     , active_start_date\n" +
      "     , active_end_date\n" +
      "     , active_start_time\n" +
      "     , active_end_time\n" +
      "  FROM @tab;";


  public static String sql_v03 =
      "SET NOCOUNT ON; \n" +
          "DECLARE @tab TABLE(\n" +
          "\t[schedule_id] [int] NOT NULL,\n" +
          "\t[schedule_uid] [uniqueidentifier] NOT NULL,\n" +
          "\t[schedule_name] [sysname] NOT NULL,\n" +
          "\t[enabled] [int] NOT NULL,\n" +
          "\t[freq_type] [int] NOT NULL,\n" +
          "\t[freq_interval] [int] NOT NULL,\n" +
          "\t[freq_subday_type] [int] NOT NULL,\n" +
          "\t[freq_subday_interval] [int] NOT NULL,\n" +
          "\t[freq_relative_interval] [int] NOT NULL,\n" +
          "\t[freq_recurrence_factor] [int] NOT NULL,\n" +
          "\t[active_start_date] [int] NOT NULL,\n" +
          "\t[active_end_date] [int] NOT NULL,\n" +
          "\t[active_start_time] [int] NOT NULL,\n" +
          "\t[active_end_time] [int] NOT NULL,\n" +
          "\t[date_created] [datetime] NOT NULL,\n" +
          "\t[schedule_description] [nvarchar](4000) NULL,\n" +
          "\t[job_count] [int] NULL\n" +
          ");\n" +


          " INSERT INTO @tab EXEC msdb.dbo.sp_help_schedule;\n" +




          "SELECT schedule_id\n" +
          "     --, schedule_uid\n" +
          "     , 0 as originating_server_id\n" +
          "     , schedule_name as name\n" +
          "     --, owner_sid\n" +
          "     , [enabled]\n" +
          "     , freq_type\n" +
          "     , freq_interval\n" +
          "     , freq_subday_type\n" +
          "     , freq_subday_interval\n" +
          "     , freq_relative_interval\n" +
          "     , freq_recurrence_factor\n" +
          "     , active_start_date\n" +
          "     , active_end_date\n" +
          "     , active_start_time\n" +
          "     , active_end_time\n" +
          "  FROM @tab;";





  public static final String SELECT_DATABASES = "                            SET NOCOUNT ON;\n" +
      "                            DECLARE @tab TABLE([name] SYSNAME COLLATE database_default NOT NULL, [description] NVARCHAR(1000));\n" +
      "                            DECLARE @obj_cnt TABLE([db_name] SYSNAME, [cnt] INT);\n" +
      "                            DECLARE @type_cnt TABLE([db_name] SYSNAME, [cnt] INT);\n" +
      "                            DECLARE @sys_databases TABLE([db_name] SYSNAME);\n" +
      "\n" +
      "                            INSERT INTO @tab([name], [description])\n" +
      "                            SELECT [name]\n" +
      "                                 , [description]\n" +
      "                              FROM fn_helpcollations();\n" +
      "\n" +
      "                            INSERT INTO @sys_databases ([db_name])\n" +
      "                            VALUES ('master'),('model'),('msdb'),('tempdb'),('distribution'),('ReportServer'),('ReportServerTempDB');\n" +
      "\n" +
      "                            DECLARE DBNameCursor CURSOR\n" +
      "                            FOR SELECT Name\n" +
      "                                  FROM sys.databases\n" +
      "                                 WHERE Name NOT IN (SELECT [db_name] FROM @sys_databases)\n" +
      "                                   AND HAS_PERMS_BY_NAME([name], 'DATABASE', 'VIEW DEFINITION') = 1\n" +
      "                                 ORDER BY Name;\n" +
      "\n" +
      "                            DECLARE @DBName NVARCHAR(128);\n" +
      "                            DECLARE @cmd VARCHAR(4000);\n" +
      "                            OPEN DBNameCursor\n" +
      "                            FETCH NEXT FROM DBNameCursor INTO @DBName\n" +
      "                            WHILE @@fetch_status = 0\n" +
      "                            BEGIN\n" +
      "                              SELECT @cmd = 'Use ' + @DBName + '; '\n" +
      "                              SELECT @cmd = 'SELECT ''' + @DBName + ''' AS [db_name], COUNT(1) AS [cnt] FROM [' + @DBName + '].sys.objects WHERE is_ms_shipped = 0';\n" +
      "                              BEGIN TRY\n" +
      "                                INSERT INTO @obj_cnt ([db_name], [cnt])\n" +
      "                                EXECUTE (@cmd);\n" +
      "                              END TRY\n" +
      "                              BEGIN CATCH\n" +
      "                                INSERT INTO @obj_cnt ([db_name], [cnt]) VALUES (@DBName, -1);\n" +
      "                              END CATCH;\n" +
      "\n" +
      "                              SELECT @cmd = 'Use ' + @DBName + '; '\n" +
      "                              SELECT @cmd = 'SELECT ''' + @DBName + ''' AS [db_name], COUNT(1) AS [cnt] FROM [' + @DBName + '].sys.types WHERE is_user_defined = 1';\n" +
      "                              BEGIN TRY\n" +
      "                                INSERT INTO @type_cnt ([db_name], [cnt])\n" +
      "                                EXECUTE (@cmd);\n" +
      "                              END TRY\n" +
      "                              BEGIN CATCH\n" +
      "                                INSERT INTO @type_cnt ([db_name], [cnt]) VALUES (@DBName, -1);\n" +
      "                              END CATCH;\n" +
      "\n" +
      "                              FETCH NEXT FROM DBNameCursor INTO @DBName\n" +
      "                            END\n" +
      "                            CLOSE DBNameCursor;\n" +
      "                            DEALLOCATE DBNameCursor;\n" +
      "\n" +
      "                            SELECT dbs.[database_id]\n" +
      "                                 , dbs.[name]\n" +
      "                                 , CASE\n" +
      "                                     WHEN hcs.[description] IS NULL THEN NULL\n" +
      "                                     ELSE\n" +
      "                                       CASE\n" +
      "                                         WHEN PATINDEX('%case[- ]sensitive%', LOWER(hcs.[description])) <> 0 THEN CAST(1 AS BIT)\n" +
      "                                         ELSE CAST(0 AS BIT)\n" +
      "                                       END\n" +
      "                                     END AS [case_sensitive_db]\n" +
      "                                 , dbs.[create_date]\n" +
      "                                 , dbs.[compatibility_level]\n" +
      "                                 , dbs.[collation_name]\n" +
      "                                 , CASE\n" +
      "                                     WHEN dbs.[name] IN (SELECT [db_name] FROM @sys_databases) THEN 1\n" +
      "                                     ELSE 0\n" +
      "                                   END AS [is_ms_shipped]\n" +
      "                                 , CASE\n" +
      "                                     WHEN COALESCE(cnt.[cnt],0) = 0 AND COALESCE(type_cnt.[cnt],0) = 0 AND dbs.[name] NOT IN (SELECT [db_name] FROM @sys_databases) THEN 1\n" +
      "                                     ELSE 0\n" +
      "                                   END AS [is_empty]\n" +
      "                                 , COALESCE(cnt.[cnt],0) + COALESCE(type_cnt.[cnt],0) AS [user_obj_cnt]\n" +
      "                              FROM [master].[sys].[databases] dbs\n" +
      "                              LEFT JOIN @tab hcs ON hcs.[name] = dbs.[collation_name] COLLATE database_default\n" +
      "                              LEFT JOIN @obj_cnt cnt ON cnt.[db_name] = dbs.[name]\n" +
      "                              LEFT JOIN @type_cnt type_cnt ON type_cnt.[db_name] = dbs.[name]\n" +
      "                             WHERE dbs.[state] = 0\n" +
      "                               --AND dbs.[name] NOT IN (SELECT [db_name] FROM @sys_databases)\n" +
      "                               AND HAS_PERMS_BY_NAME(dbs.[name], 'DATABASE', 'VIEW DEFINITION') = 1\n" +
      "                             ORDER BY dbs.[name];";
}
