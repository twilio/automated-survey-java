## Display survey results
To view the results, the completed surveys are serialized to 
[JSON](http://json.org), which is used to populate the dashboard. The dashboard 
is served on the root (```/```) URL of the application. We use [jQuery](http://jquery.com) 
to call this route via Ajax, and display the results in charts on the home page.

---
**See also:**
- [Google Gson: Overview](https://sites.google.com/site/gson/gson-user-guide#TOC-Overview)
- [AJAX requests in jQuery](http://api.jquery.com/jQuery.ajax/)
