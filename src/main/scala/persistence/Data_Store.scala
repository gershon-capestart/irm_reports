package persistence

import org.apache.commons.dbcp2._

object Data_Store {
  val dbUrl = System.getenv("JDBC_URL")
  val connectionPool = new BasicDataSource()
  connectionPool.setUrl(dbUrl)
  connectionPool.setInitialSize(3)
}
