package integrations.queries

object HrReportQueries {
  val GET_ALL_USER_LIST_TODAY = "select  ( select min(event_time) from acc_transaction at2  where at2.pin = act.pin) as in_time, ( select max(event_time) from acc_transaction act3 where act3.pin = act.pin) as out_time, ( select min(name) from acc_transaction at4  where at4.pin = act.pin) as name from acc_transaction act group by act.pin;"
}
