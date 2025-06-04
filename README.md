# SOEX Beroepsproduct

Casusbeschrijving voor SOEX: Solution Exploration

## Restaurant applicatie: De Smakelijke Schotel

Het Restaurant "De Smakelijke Schotel" is een drukbezochte eetgelegenheid in het hartje van de stad. Het restaurant heeft een uitgebreid menu met diverse gerechten, variërend van traditionele gerechten tot fusionkeukencreaties. De eigenaar, Jan, beheert de voorraad zorgvuldig om ervoor te zorgen dat alle ingrediënten voorhanden zijn voor de kok om de gerechten te bereiden.

De voorraad van het restaurant wordt beheerd door Jan en zijn team. Ze houden bij hoeveel van elk ingrediënt beschikbaar is. Bijvoorbeeld, er zijn 20 kilogram tomaten, 10 kilogram uien, enzovoort. Deze voorraad wordt aangevuld door leveranciers op basis van bestellingen die het restaurant plaatst.

Het menu van De Smakelijke Schotel wordt regelmatig bijgewerkt en bevat een breed scala aan gerechten, waaronder voorgerechten, hoofdgerechten, bijgerechten en desserts. Elk gerecht op het menu heeft een naam, categorie en prijs. Bijvoorbeeld, het hoofdgerecht "Gegrilde Zalm" behoort tot de categorie "Visgerechten" en kost € 18,95.

Elk gerecht op het menu bestaat uit een combinatie van ingrediënten, waarvan sommige specifieke doseringen vereisen. Zo kan het gerecht "Pasta Carbonara" bijvoorbeeld bestaan uit spaghetti, eieren, pancetta, Parmezaanse kaas en zwarte peper, elk met hun eigen doseringen.

Naast het reguliere menu houdt het restaurant ook rekening met voedingsrestricties van klanten, zoals vegetarisch, veganistisch, glutenvrij, enzovoort. Dit wordt gedaan door middel van voedingsrestricties die aan bepaalde ingrediënten zijn gekoppeld. Bijvoorbeeld, het gerecht "Groentenquiche" is geschikt voor vegetariërs en bevat geen vleesproducten.

Klanten kunnen geen tafel reserveren bij De Smakelijke Schotel, maar bij aankomst krijgen ze de keuze uit beschikbare tafels door de gastvrouw. Elke tafel heeft een uniek tafelnummer en kan één of meerdere bestellingen plaatsen tijdens hun verblijf. Een bestelling kan meerdere gerechten bevatten, afhankelijk van de voorkeuren van de klanten aan die tafel. Na het diner ontvangen klanten een rekening met de kosten van hun bestellingen en kunnen deze alleen contant betalen, eventueel krijgen ze nog wisselgeld terug.

### Verloop van een restaurant bezoek (as-is)

Als een klant, bijvoorbeeld Anna, het gezellige restaurant "De Smakelijke Schotel" binnenkomt, wordt ze vriendelijk begroet door de gastvrouw, Sara genaamd.
Sara geeft Anna de keuze uit beschikbare tafels in de tafelindeling, legt de keuze vast in de tafelindeling en leidt Anna naar haar gekozen tafel.
Ze neemt plaats aan tafel nummer 5 en Sara vertelt welke gerechten vandaag op het menu staan op basis van de beschikbare voorraad.
Na een paar minuten van overwegen maakt Anna haar keuze: ze besluit de vegetarische lasagne en een glas huisgemaakte ice tea te bestellen.

De ober, Peter genaamd, komt naar haar tafel en vraagt vriendelijk naar haar bestelling.
Anna glimlacht en geeft haar keuze door.
Peter noteert de bestelling op zijn blok.

Nadat Peter de bestelling heeft gecontroleerd geeft hij deze door naar de keuken.
De chef-kok, Sarah, ontvangt de bestelling en begint onmiddellijk met de voorbereiding van de vegetarische lasagne.
Ondertussen geniet Anna van de gezellige sfeer in het restaurant en kijkt ze tevreden naar de andere gasten die genieten van hun maaltijden.

Na een korte wachttijd brengt een andere ober, Lisa genaamd, de dampende vegetarische lasagne naar Anna's tafel.
Anna bedankt haar vriendelijk en begint met smaak te eten van haar heerlijke maaltijd.
De lasagne is perfect gebakken en de smaken zijn verrukkelijk.

Zodra Anna haar maaltijd heeft voltooid, vraagt Peter vriendelijk of ze nog iets wilt bestellen of dat ze klaar is voor de rekening.
Anna besluit om nog een dessert te bestellen, een stukje huisgemaakte appeltaart met een bolletje vanille-ijs.

Peter noteert haar dessertbestelling en regelt dat deze naar de keuken wordt gestuurd.
Binnen enkele minuten wordt het dessert geserveerd, en Anna geniet van de heerlijke combinatie van warme appeltaart en romig ijs.

Nadat Anna van haar maaltijd heeft genoten betaalt ze de rekening bij Peter.
Ze bedankt het personeel voor de uitstekende service en verlaat het restaurant met een voldaan gevoel,
klaar om haar avond voort te zetten.

![Restaurant bezoek as-is Coarse-Grained](./opdracht-diagrammen/Restaurant-bezoek-pure-as-is-coarse-grained.egn.svg)

### Gewenste verbeteringen aan dit proces (to-be)

Het restaurant draait erg goed en Jan is van plan om uit te breiden naar meer tafels, maar wil in de toekomst ook meerdere restaurants gaan uitbuiten.
Om dat te kunnen doen zijn er wel een aantal uitdagingen, want het is niet meer haalbaar voor één gastvrouw om te weten hoe het met tafelindeling en de voorraad gesteld is.

Daarvoor is het gewenst dat de gastvrouw een digitale tafelindeling met reserveringen kan inzien en bijhouden zodat meerdere personen parallel suggesties kunnen doen aan de klant.
Daarnaast is het gewenst dat er voor de klant een digitale menukaart in te zien is van alle gerechten die beschikbaar zijn op basis van de voorraad van dit restaurant.
Hiervoor wil Jan dat klanten gebruik kunnen maken van hun telefoon om de menukaart in te zien en bestellingen door te kunnen geven aan de keuken.
Hiermee wil Jan ook kosten besparen door de ober niet meer bestellingen te laten opnemen, maar alleen nog gerechten te laten bezorgen aan de tafel.

Een bestelling moet gedaan kunnen worden op basis van gerechten geplaatst in een winkelmand, door deze in één keer om te zetten naar een bestelling.
Hierbij mag het alleen mogelijk zijn om gerechten in de winkelmand te plaatsen waarvoor genoeg ingrediënten op voorraad zijn.
Bij het toevoegen van gerechten aan de winkelmand moet de voorraad alvast gereduceerd worden in het systeem.
Zodra een bestelling is opgegeven komt deze in het keukenmanagement systeem terecht zodat de keuken hiermee aan de gang kan gaan en uitserveren aan de ober.

Een bijkomstigheid is dat de lopende rekening te allen tijde inzichtelijk is voor de klant en de wens is dat deze ook direct te betalen is bij een geldautomaat naast de ingang.
In de toekomst wil Jan ook met zijn tijd meegaan en digitale betaalmiddelen ondersteunen, maar hij vindt de bijkomende transactiekosten te hoog om nu te realiseren.

![Restaurant bezoek na reservering digitalized to-be coarse-grained](./opdracht-diagrammen/Restaurant-bezoek-pure-as-is-coarse-grained.egn.svg)

Vervolgens is men tot de conclusie gekomen dat het meest ingewikkelde systeem voor nu het bestelsysteem is. Daarbij spelen eigenlijk twee aspecten een rol: plaatsen bestelling en betalen rekening.

## Opdracht in hoofdlijnen

1. Ontwerp maken waarbij gebruik gemaakt moet worden van C4. Je werkt een of meerdere ontwerpvragen uit die niet mogen dubbelen met andere studenten, overleg de ontwerpvragen dus eerst met je docent.
2. Design patterns bestuderen en toepassen. 
3. PoC-jes (prototypes) maken van communicatie met één, of twee externe services.
4. PoC-jes integreren in de bestaande back-end waarbij de al eerder gekozen principes gehandhaafd blijven, zoals de DDD-aanpak van het domein, het gebruik van services, repositories en DTOs. 

Essentieel in deze casus is dat je niet alleen bedenkt en visualiseert hoe de software eruit moet zien, maar deze plaatjes ook voorziet van tekst waaruit keuzes blijken die je hebt gemaakt en het geheel (een software guidebook) voor een andere ontwikkelaar begrijpelijk is. Daarnaast werk je enkele keuzes uit in de vorm van werkende prototypes en integreert in de bestaande applicatie. 

## Ontwerpvragen

Gegroepeerd op quality attribute uit het software guidebook:

### Interoperability

* Hoe kun je externe APIs (zoals allergenen- of calorieëninfo) uniform aanspreken ondanks verschillende datamodellen?
* Hoe kunnen we verschillende identity providers met verschillende interfaces integreren voor het gehele systeem?
* Hoe zorg je dat een wijziging in een of meerdere APIs niet leidt tot een grote wijziging in de applicatie? Specifieker: hoe zorg je ervoor dat een wijziging in de API van een externe service niet leidt tot een wijziging in de front-end maar flexibel kan worden opgevangen door de back-end? 
* Hoe zorg je ervoor dat je bij een wijziging in de datastructuur van een externe service niet de hele applicatie hoeft aan te passen?
* Wie is verantwoordelijk voor het vertalen van een bericht van een externe service naar een aanroep van het domein?
* Wie roept een specifieke externe service aan, gebeurt dat vanuit de front-end of vanuit de back-end? Welke redenen zijn er om voor de ene of de andere aanpak te kiezen?
* Hoe koppel je gerechten automatisch aan relevante allergeneninformatie zonder handmatige mapping?

### Fault Tolerance

* Hoe ga je om met aanroepen van externe services die niet beschikbaar zijn en toch verwacht wordt dat er waardevolle output gegeven wordt? Hoe zorg je dat een gast altijd een consistente beleving heeft, ongeacht welke services tijdelijk onbeschikbaar zijn?
* Wat doe je als je vanuit de applicatie meerdere externe services, of meerdere aanroepen naar dezelfde service, moet aanroepen en de volgorde van aanroepen van belang is?

### Modularity

* Hoe kun je verschillende betaalmethodes (nu contant, later Stripe/PayPal) ondersteunen zonder het betalingssysteem te herschrijven?
* Hoe implementeer je de keuze voor een betaalmethode zonder if-structuren of switch-statements?
* Hoe kun je het gebruik van meerdere receptbronnen standaardiseren voor uitbreidbaarheid?  
* Hoe kun je gerechten filteren op diverse en in de toekomst nog uit te breiden voedingswensen?
* Hoe kun je een dynamisch maandmenu genereren op basis van seizoensgebonden recepten?
* Hoe kun je één bestelling splitsen over meerdere gasten met aparte betaalverzoeken?

### Modifiability

* Hoe zorg je dat bestelstatussen eenvoudig uitgebreid kunnen worden en het systeem adequaat reageert op een wijzing in die status?
* Hoe kun je meerdere varianten van gerechten (formaat/portie) zonder logica-duplicatie modelleren?
* Hoe maak je het systeem voorbereid op meerdere restaurants met verschillende voorraadsystemen?

### Integrity

* Hoe wordt de toestand van de applicatie bijgehouden, is er een centrale toestand of is de toestand van de applicatie verdeeld over de bouwstenen?
* Hoe verwerk je realtime voorraadcontrole bij het toevoegen van gerechten aan de winkelmand?
* Hoe zorg je dat een bestelling pas doorgaat als alle ingrediënten beschikbaar zijn?
* Hoe manage je wijzigingen in API-specificaties zonder dat de hele app breekt?

### Confidentiality

* Hoe beheer je veilig de interactie met verouderde externe APIs die geen moderne beveiligingsprotocollen ondersteunen?
* Hoe implementeer je logging van API-aanroepen op een manier die gevoelige informatie niet blootstelt?
* Hoe waarborg je de integriteit van data die via externe APIs wordt verzonden of ontvangen?
* Hoe zorg je ervoor dat authenticatie en autorisatie consistent worden toegepast bij het communiceren met verschillende externe APIs?

## Potentiële APIs

* Food Nutrition Allergen and Ingredient Database (allergenen voor mensen die allergisch zijn voor iets of liever vegan/vegetarisch eten)
* Voorraadbeheersysteem
* Recepten API (om het systeem mee uit te breiden)
* Calorieen API (om mensen inzicht te geven in de hoeveelheid kcal die ze eten)
* Cocktail API (stappenplan om exact een cocktail te kunnen maken)
* PayPal, Stripe etc. (betalingen)
