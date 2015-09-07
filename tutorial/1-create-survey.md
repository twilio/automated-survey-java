## CREATE A SURVEY
Before we get responses, let's specify the questions we would like to ask.

Each question is created as a [JSON object](http://json.org) with two attributes: ```text```, which holds the question we'd like to ask, and ```type```, which describes the kind of input we expect a caller to provide when asked that question.

Our app reads this JSON into a Java object using Google's [Gson](https://sites.google.com/site/gson/gson-user-guide#TOC-Overview) library.
