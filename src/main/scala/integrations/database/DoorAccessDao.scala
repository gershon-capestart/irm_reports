package integrations.database

import integrations.queries.HrReportQueries
import models.ReportModel

import java.sql.Connection
import java.text.SimpleDateFormat
import java.util
import java.util.Date


class DoorAccessDao {

  val dbConnection: Connection = persistence.Data_Store.connectionPool.getConnection()

  def getUsersWhoAccessedDoorToday(): util.ArrayList[ReportModel] = {
    val query = dbConnection.createStatement();
    val result = query.executeQuery(String.format(HrReportQueries.GET_ALL_USER_LIST_TODAY));
    val reports = new util.ArrayList[ReportModel]()
    while (result.next()) {
      val report = new ReportModel().createReportFromDBResult(result);
      if(!report.employeeName.isEmpty){
        reports.add(report)
      }
    }
    reports
  }
}
