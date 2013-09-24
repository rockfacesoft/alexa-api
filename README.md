alexa-api
=========

Further implemention of alexa api

Provides an easy way to send queries to Alexa for any action/response group.
Visit Alexa for further information on the query parameters for Alexa Topsites and Alexa Web Information Service. 
Get the data back in XML or CSV format.

You will need to have an Alexa Access Code and Secret Access Code to run the query.

Current Issue

conversion of diacritic vowels cause the Alexa query to fail and so are currently ignored, unfortunately this means that
Category Paths that contain diacritic vowels are inaccessible
