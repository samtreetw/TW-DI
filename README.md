# TW-DI
To run the code in local tomecat, please edit your context.xml in /conf 
Line:21 ~ Line:25, change D:\gitProject\DI\TW-DI\allfoot.db to your local path 
{code}
<Context>

    <!-- Default set of monitored resources -->
    <WatchedResource>WEB-INF/web.xml</WatchedResource>

    <!-- Uncomment this to disable session persistence across Tomcat restarts -->
    <!--
    <Manager pathname="" />
    -->

    <!-- Uncomment this to enable Comet connection tacking (provides events
         on session expiration as well as webapp lifecycle) -->
    <!--
    <Valve className="org.apache.catalina.valves.CometConnectionManagerValve" />
    -->   

    <Resource name="jdbc/AllFootSqliteDataSource" auth="Container" type="javax.sql.DataSource"
              maxActive="100" maxIdle="30" maxWait="10000"
              username="" password=""
              driverClassName="org.sqlite.JDBC"
              url="jdbc:sqlite:D:\gitProject\DI\TW-DI\allfoot.db"/>

</Context>
