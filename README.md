# Webcrawler

Application for scouting web on given url.

# Parameters

| Name | Required | Description |
| --- | --- | --- |
| mainDomain | yes | Url do domain in format http[s]//xxx.xxx
| maxPages | no | Number of pages that web crawler has to check. Default value set to 100.


# Request and response example

Request:
```
/api/v1/scoutWeb?mainDomain=https://wiprodigital.com&maxPages=2
```

Response:

```
{
    "mainDomain": "https://wiprodigital.com",
    "pageInfos": [
        {
            "url": "https://wiprodigital.com",
            "links": [
                "https://wiprodigital.com/news/wipro-rated-a-leader-in-digital-transformation-in-itsma-report/",
                "https://wiprodigital.com/cases/delivering-an-exceptional-mortgage-customer-experience-for-allied-irish-bank/",
                "https://wiprodigital.com/?s=&post_type[]=cases/",
                "https://wiprodigital.com/events/wipro-digital-designit-at-idc-european-digital-executive-summit-2018/",
                "https://wiprodigital.com/cases/in24/",
                "https://wiprodigital.com/news/pacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance/",
                "https://wiprodigital.com/?s=&post_type[]=post/",
                "https://wiprodigital.com/?s=&post_type[]=events/",
                "https://wiprodigital.com/events/teresa-brazen-senior-director-cooper-at-design-thinking-2018/",
                "https://wiprodigital.com/who-we-are/",
                "https://wiprodigital.com/what-we-think/",
                "https://wiprodigital.com/2019/03/01/the-new-path-to-transformation-unlearn-learn-deliver/",
                "https://wiprodigital.com/news/new-survey-highlights-leadership-crisis-digital-transformation/",
                "https://wiprodigital.com/2018/08/09/turning-data-into-consumer-growth/",
                "https://wiprodigital.com/",
                "https://wiprodigital.com/2018/10/04/three-ways-to-navigate-your-data-science-journey/",
                "https://wiprodigital.com/events/meet-wipro-digital-at-pega-customer-engagement-summit-2018/",
                "https://wiprodigital.com/news/wipro-named-global-leader-digital-transformation-strategy-consulting/",
                "https://wiprodigital.com/cases/case-study-speaking-from-the-heart/",
                "https://wiprodigital.com/news/wipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities/",
                "https://wiprodigital.com/cases/a-design-system-to-enable-future-growth/",
                "https://wiprodigital.com/cases/progressive-metering-a-utilitys-strategic-move-into-predictive-planning/",
                "https://wiprodigital.com/events/wipro-digital-is-a-platinum-sponsor-at-springone-platform-2018/",
                "https://wiprodigital.com/who-we-are/",
                "https://wiprodigital.com/2019/03/11/cmos-swiping-left-on-technology-and-data/",
                "https://wiprodigital.com/events/nate-clinton-managing-director-cooper-at-sf-design-week-2018/",
                "https://wiprodigital.com/get-in-touch/",
                "https://wiprodigital.com/?s=&post_type[]=news/",
                "https://wiprodigital.com/what-we-do/",
                "https://wiprodigital.com/join-our-team/",
                "https://wiprodigital.com/case-study-redefining-newborn-care-for-volusense/",
                "https://wiprodigital.com/designit-approach/",
                "https://wiprodigital.com/2019/04/12/wipro-clients-must-improve-their-understanding-of-digital-outcomes-to-leverage-the-firms-growing-capabilities/",
                "https://wiprodigital.com/2019/02/04/hiring-for-mindset/",
                "https://wiprodigital.com/designit-approach/",
                "https://wiprodigital.com/2019/03/04/talent-and-new-ways-of-working-in-the-age-of-digital-transformation/",
                "https://wiprodigital.com/join-our-team/",
                "https://wiprodigital.com/events/join-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef/",
                "https://wiprodigital.com/news/the-state-of-martech-study/",
                "https://wiprodigital.com/privacy-policy/"
            ],
            "externalLinks": [
                "https://dc.ads.linkedin.com/collect/?pid=225025&fmt=gif",
                "https://twitter.com/wiprodigital",
                "https://www.linkedin.com/company/wipro-digital",
                "https://www.facebook.com/WiproDigital/"
            ],
            "staticContentLinks": [
                "https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png",
                "https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo-dk-2X.png"
            ],
            "subdomains": []
        },
        {
            "url": "https://wiprodigital.com/news/wipro-rated-a-leader-in-digital-transformation-in-itsma-report/",
            "links": [
                "https://wiprodigital.com/who-we-are/",
                "https://wiprodigital.com/?s=&post_type[]=cases/",
                "https://wiprodigital.com/get-in-touch/",
                "https://wiprodigital.com/?s=&post_type[]=news/",
                "https://wiprodigital.com/what-we-do/",
                "https://wiprodigital.com/?s=&post_type[]=post/",
                "https://wiprodigital.com/?s=&post_type[]=events/",
                "https://wiprodigital.com/join-our-team/",
                "https://wiprodigital.com/who-we-are/",
                "https://wiprodigital.com/what-we-think/",
                "https://wiprodigital.com/",
                "https://wiprodigital.com/join-our-team/",
                "https://wiprodigital.com/privacy-policy/"
            ],
            "externalLinks": [
                "http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-rated-a-leader-in-digital-transformation-in-itsma-report%2F&hashtags=wiprodigital",
                "https://dc.ads.linkedin.com/collect/?pid=225025&fmt=gif",
                "https://www.facebook.com/WiproDigital/",
                "https://www.itsma.com/",
                "http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-rated-a-leader-in-digital-transformation-in-itsma-report%2F&title=Wipro Rated a Leader in Digital Transformation in ITSMA Report&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-rated-a-leader-in-digital-transformation-in-itsma-report%2F&source=wiprodigital.com",
                "https://apac01.safelinks.protection.outlook.com/?url=https%253A%252F%252Fwww.itsma.com%252F&data=02%257C01%257C%257Cd590b05b790c4b83b74d08d663e3df45%257C258ac4e4146a411e9dc879a9e12fd6da%257C0%257C0%257C636806227682155646&sdata=NAxk7d9Gdzo6Ct1oJ87RnRY4%252BWz3bw3Kp%252FDQI0GTzhk%253D&reserved=0",
                "https://www.itsma.com/research/positioning-for-digital-leadership-2018-digital-transformation-brand-tracking-study/",
                "http://www.sec.gov/",
                "https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-rated-a-leader-in-digital-transformation-in-itsma-report%2F",
                "https://twitter.com/wiprodigital",
                "https://www.linkedin.com/company/wipro-digital",
                "https://twitter.com/ITSMA_B2B"
            ],
            "staticContentLinks": [
                "https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png",
                "https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo-dk-2X.png"
            ],
            "subdomains": []
        }
    ],
    "brokenLinks": []
}
```


# Additional information

A decided to add configurable property 'webcrawler.maxpages' with information how many pages can be checks for given domain.
I added it for large pages to not be scouted in very long time. Client can override this behavior by adding 'maxPages' parameter in request. Thanks to this client can be more knowingly use scouting for large web pages.

For scouting client can only give main domain as url.  
Example of corrent url: https://wiprodigital.com  
Example of incorrect url: https://wiprodigital.com/case/ 

# Technologies

- Java 11
- Spring boot 2.1.4
- Jsoup 1.11.3
- Mockito
- Swagger 2.9.2

# Ideas for improving

- multithreading for checking web page and building page information
- handling full path url etc. https://wiprodigital.com/case/ and not only main domain
- add REST methods for changing configuration without restarting app

## Run app

```
mvn spring-boot:run
```

## Run tests

```
mvn test
```

## Build without tests

```
mvn install -DskipTests
```

## Build with tests

```
mvn install
```
