package services

import com.github.tototoshi.csv.CSVWriter
import com.sendgrid._
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.{Attachments, Content, Email}
import models.ReportModel

import java.io.File
import java.nio.file.{Files, Paths}
import java.util
import java.util.Base64

object AttendanceReportService {
  def sendFileEmail(): Unit = {

    var sg = new SendGrid(System.getenv("SENGRID_KEY"));
    val from = new Email(System.getenv("FROM_EMAIL"))
    val to = new Email(System.getenv("TO_EMAIL"))
    val subject = "Door Access Report "
    val content = new Content("text/plain", "Hi Team, \n Please find the door access report attached ");
    val mail = new Mail(from, subject, to, content)

    val file = Paths.get("out.csv");
    val attachments = new Attachments();
    attachments.setFilename("door_access_report.csv");
    attachments.setType("application/csv");
    attachments.setDisposition("attachment");
    val attachmentContentBytes = Files.readAllBytes(file)
    val attachmentContent = Base64.getEncoder.encodeToString(attachmentContentBytes)
    attachments.setContent(attachmentContent)
    mail.addAttachments(attachments)

    val request = new Request
    request.setMethod(Method.POST)
    request.setEndpoint("mail/send")
    request.setBody(mail.build())
    val res = sg.api(request);
    print(res.getBody)

  }

  def createReportsCSV(reports: util.ArrayList[ReportModel]): Unit = {
    val csvFile = new File("out.csv")
    val writer = CSVWriter.open(csvFile)
    writer.writeRow(List("Name", "Out Time", "Total Hours In"))
    reports.forEach(report => {
      writer.writeRow(List(report.employeeName, report.outTime, report.totalTime))
    })
  }

}
