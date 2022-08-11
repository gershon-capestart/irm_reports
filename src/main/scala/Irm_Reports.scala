import integrations.database.DoorAccessDao
import persistence.Data_Store
import services.AttendanceReportService

object Irm_Reports {

  def main(args: Array[String]): Unit = {
    print("Getting db connection")
    val reports = new DoorAccessDao().getUsersWhoAccessedDoorToday();
    AttendanceReportService.createReportsCSV(reports)
    AttendanceReportService.sendFileEmail()
  }

}
