<!DOCTYPE html>
<head>
</head>
<body>
	<h1>${ greeting }</h1>
	<p>With this demo application, you can access the following endpoints</p>
	<ul>
		<li><code>/</code> : This homepage</li>
		<li><code>/:number</code> : Put your favorite number after the slash, and the template will affirm your choice.</li>
		<li><code>/new/:phone</code> : Place a phone number in for ":phone", and that phone number will be saved to the database.</li>
		<li><code>/id/:id</code> : Put the ID you received from the previous endpoint in place of ":id", the database will retrieve the phone number.</li>
	</ul>
</body>