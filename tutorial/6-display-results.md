## DISPLAY RESULTS
To view the results, the completed surveys are serialized to [JSON objects](http://json.org), which are used to populate the dashboard. Here, the dashboard is served as HTML, CSS, and JavaScript files, on the root (```/```) route of the application.  Open your application's root in a web browser to view the dashboard.

The results dashboard for this example application uses [jQuery](http://jquery.com) to dynamically update the response data, but, because the response data is exposed by the application as JSON, you could use any library you like ([or no library at all](https://en.wikipedia.org/wiki/XMLHttpRequest)) to pull in the data from your app with an AJAX request, and use it to populate a dashboard of your own design.

---
**See also:**
- [Google Gson: Overview](https://sites.google.com/site/gson/gson-user-guide#TOC-Overview)
- [AJAX requests in jQuery](http://api.jquery.com/jQuery.ajax/)
