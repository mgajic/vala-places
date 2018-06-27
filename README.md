# vala-places

Vala places application is hosted on aws and this is the host URL: **ValaPlaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com**

These are the two requested deployed endpoints: 

**Find places endpoint**

**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/country/{countryCode}/text/{searchTerm}**


**Get metric:**

**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/metric**

Example URL requests:

Find places:

This endpoint will for given input country code ( rs,fr...) and input text ( name of the town, street ...) return predicted results, by contacting Google API places in the backend.

**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/country/rs/text/gandijeva**
**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/country/fr/text/paris**
**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/country/rs/text/beograd**
**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/country/rs/text/paris**



Get metric:

This endpoint will return information of how many requests was sent to the defined endpoints, and how many requests per given country in the Find places endpoint.

**http://valaplaces-env.h8wmjqhenx.us-east-2.elasticbeanstalk.com/api/places/metric**

MySQL server has been hosted at:
**sql3.freemysqlhosting.net**

This is the GitHub url: **https://github.com/mgajic/vala-places**
Branch is the **development**

Application was tested with Postman REST client 


