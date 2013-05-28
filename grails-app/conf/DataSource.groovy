hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:devDb"
        }
        dataSource_activiti {
            pooled = true
            readonly=true
            driverClassName = "org.h2.Driver" //"com.mysql.jdbc.Driver"
            username = "sa" //"activiti"
            password = "" //"activiti"
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:tcp://localhost/~/activiti" //"jdbc:mysql://localhost:3306/activiti?autoReconnect=true"
        }
    }
    test {
        dataSource_activiti {
            pooled = true
            readonly=true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "activiti"
            password = "activiti"
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/activiti?autoReconnect=true"
        }
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:devDb"
        }
    }
    production {
        dataSource_activiti {
            pooled = true
            readonly=true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "activiti"
            password = "activiti"
            dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/activiti?autoReconnect=true"
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:devDb"
        }
    }
}
