val data0 = sc.textFile("parking/2016.csv")

/*
Summons Number, 0
Plate ID, 1
 * Registration State,2
 * Plate Type,3
 * Issue Date,4
 * Violation Code,5
 * Vehicle Body Type,6
 * Vehicle Make,7
 * Issuing Agency,8
Street Code1,9
Street Code2,10
Street Code3,11
Vehicle Expiration Date,12
Violation Location,13
Violation Precinct,14
Issuer Precinct,15
Issuer Code,16
Issuer Command, 17
Issuer Squad,18
 * Violation Time,19
Time First Observed,20
Violation County,21
Violation In Front Of Or Opposite,22
House Number,23
Street Name,24
Intersecting Street,25
Date First Observed,26
Law Section,27
Sub Division,28
Violation Legal Code,29
Days Parking In Effect,30
From Hours In Effect,31
To Hours In Effect,32
 * Vehicle Color,33
Unregistered Vehicle?,34
Vehicle Year,35
Meter Number,36
Feet From Curb,37
------------------------
Violation Post Code,38
Violation Description,39
No Standing or Stopping Violation,40
Hydrant Violation,41
Double Parking Violation,42
Latitude,43
Longitude,44
Community Board,45
Community Council, 46
Census Tract,47
BIN,48
BBL,49
NTA 50
*/

val data1 = data0.filter(_.split(",").length > 33).filter(!_.split(",")(0).equals("Summons Number"))

//state, plate, date, code, body, make, agency, color
val data2 = data1.map(line => Tuple9(line.split(",")(2), line.split(",")(3), line.split(",")(4), line.split(",")(5), line.split(",")(6), line.split(",")(7), line.split(",")(8), line.split(",")(19), line.split(",")(33)))

val state = data2.map(_._1)
val plate = data2.map(_._2)
val code = data2.map(_._4)
val body = data2.map(_._5)
val make = data2.map(_._6)
val agency = data2.map(_._7)
val color = data2.map(_._9)

val state_count = state.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(_._1.length == 2).filter(!_._1.contains("\"")).map(tuple => tuple._1 + "," + tuple._2)
val plate_count = plate.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(_._2 > 100).map(tuple => tuple._1 + "," + tuple._2)
val code_count = code.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(!_._1.contains("/")).map(tuple => tuple._1 + "," + tuple._2)
val body_count = body.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(_._2 > 100).map(tuple => tuple._1 + "," + tuple._2)
val make_count = make.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(_._2 > 1000).map(tuple => tuple._1 + "," + tuple._2)
val agency_count = agency.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(!_._1.contains("\"")).filter(_._1.length == 1).map(tuple => tuple._1 + "," + tuple._2)

//val color_count = color.map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")) //useless

for(line <- plate_count.take(212)) println(line)

val date = data2.map(_._3)
val time = data2.map(_._8)

def isAllDigits(x: String) = x forall Character.isDigit
val month = date.map(_.split("/")(0)).map(line => (line, 1)).reduceByKey(_ + _).sortBy(_._2, false).filter(!_._1.equals("")).filter(pair => isAllDigits(pair._1)).map(tuple => tuple._1 + "," + tuple._2)

def isTime(s: String): Boolean =
{
  if(s.length != 5) return false
  if(!s.charAt(0).isDigit || !s.charAt(1).isDigit || !s.charAt(2).isDigit || !s.charAt(3).isDigit) return false
  if(s.charAt(0) != '0' && s.charAt(0) != '1') return false
  if(s.charAt(2) > '5') return false
  if(s.charAt(4) != 'P' && s.charAt(4) != 'A') return false
  if(s.charAt(0) == '1' && s.charAt(1) > '2') return false
  if(s.charAt(0) == '0' && s.charAt(1) == '0' && s.charAt(4) == 'P') return false;
  if(s.charAt(0) == '1' && s.charAt(1) == '2' && s.charAt(4) == 'A') return false;

  return true
}

val time_count = time.map(line => (line, 1)).filter(!_._1.equals("")).reduceByKey(_ + _).sortBy(_._2, false).filter(pair => isTime(pair._1))

def to24Hour(s: String): String =
{
  if(s.charAt(2) == 'A') return s.substring(0,2)

  if(s.substring(0,2).equals("12")) return "12"

  (s.substring(0,2).toInt + 12).toString
}

val time_count2 = time_count.map(tuple => Tuple3(tuple._1.substring(0,2) + tuple._1.charAt(4), tuple._1.substring(2,4), tuple._2)).filter(tuple => !tuple._1.equals("") && !tuple._2.equals("")).map(tuple => to24Hour(tuple._1) + "," + tuple._2 + "," + tuple._3)


