## Accept a call
Every time your Twilio number receives a call, Twilio will send an HTTP request 
to the application, asking what to do next. The application responds with 
[TwiML](https://www.twilio.com/docs/api/twiml) that describes the action 
(```<Say>``` a phrase, ```<Gather>``` input, among others).

First, your Twilio number must be configured to make requests to the 
```/interview``` route of this application. Go to your 
[Manage Numbers](https://www.twilio.com/user/account/phone-numbers/) page, and 
set up a new TwiML application using one of your Twilio numbers.

---
**See also:**
- [How do I create a TwiML App?](https://www.twilio.com/help/faq/twilio-client/how-do-i-create-a-twiml-app)
