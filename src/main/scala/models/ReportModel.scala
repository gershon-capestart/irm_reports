package models

import java.sql.ResultSet
import java.text.SimpleDateFormat

class ReportModel(name: String, in: String, out: String, total: String) {

  var employeeName: String = name
  var inTime: String = in
  var outTime: String = out
  var totalTime: String = total


  def this() = this("", "", "", "")

  def createReportFromDBResult(result: ResultSet): ReportModel = {
    val employeeName = result.getString("name")
    val outTime = result.getString("in_time")
    val inTime = result.getString("out_time")
    val totalTimeSpent = subtractDate(outTime, inTime)
    var report =  new ReportModel(employeeName, outTime, inTime, totalTimeSpent)
    report
  }


  def subtractDate(outTime: String, inTime: String): String = {
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    val min = format.parse(inTime).getTime
    val max = format.parse(outTime).getTime
    String.format("%1$tH:%1$tM:%1$tS", max-min)
  }

}
