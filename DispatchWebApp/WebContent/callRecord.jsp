<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Call Record</title>
<link rel="stylesheet" href="./css/jquery-ui-1.10.4.css">
<link rel="stylesheet" type="text/css" href="./css/templateStyle.css">
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.10.0/dojo/dojo.js"
            data-dojo-config="async: true"></script>
</head>
<body>
    <h2>Call Record</h2>
    <form id="callRecordForm" class="form" method="POST" action="saveCall.do">
        <div class="ui-widget">
            <label for="timeOfCall">Time of Call: </label>
            <input id="timeOfCall" type="text" style="width: 50px" name="timeOfCall" />
        </div>
        <div class="ui-widget">
            <label for="callerFirstName">Caller First Name: </label>
            <input id="callerFirstName" type="text" style="width: 150px" name="callerFirstName" />
        </div>
        <div class="ui-widget">
            <label for="callerLastName">Caller Last Name: </label>
            <input id="callerLastName" type="text" style="width: 150px" name="callerLastName" />
        </div>
        <div data-dojo-type="dijit.form.Button" data-dojo-props="label:'Click Me!'"></div>
    </form>
</body>
</html>