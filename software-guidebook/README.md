# Software Guidebook Triptop

## 1. Introduction
Dit software guidebook geeft een overzicht van het bestelsysteem van Smakelijke Schotel. Het bevat een samenvatting van het volgende: 
1. De vereisten, beperkingen en principes. 
1. De software-architectuur, met inbegrip van de technologiekeuzes op hoog niveau en de structuur van de software. 
1. De ontwerp- en codebeslissingen die zijn genomen om de software te realiseren.
1. De architectuur van de infrastructuur en hoe de software kan worden geinstalleerd. 

## 2. Context

> [!IMPORTANT]
> Werk zelf dit hoofdstuk uit met context diagrammen en een beschrijving van de context van de software.

Toelichting op de context van de software inclusief System Context Diagram:
* Functionaliteit
* Gebruikers
* Externe systemen

## 3. Functional Overview

Om de belangrijkste features toe te lichten zijn er twee domain stories gemaakt, een verdieping in user stories en een overzicht van het domein in de vorm van een domeinmodel. Op deze plek staat typisch een user story map maar die ontbreekt in dit voorbeeld.

### 3.1 Plaatsen bestelling

Bij het scenario: `Als een gast wil ik eten kunnen bestellen zodat ik het gerecht naar wens krijg opgediend`
is in een domain storytelling sessie een fine-grained domain story opgesteld.

![fine-grained domain story - bestelling plaatsen](../opdracht-diagrammen/bestelling%20plaatsen%20digitalized%20to-be%20fine-grained.egn.svg)

We kunnen op basis hiervan user stories opstellen bij de verschillende activiteiten:
1. Als gast wil ik inloggen met mijn tafelnummer zodat mijn bestellingen op de juiste plek terecht zullen komen
2. Als gast wil ik de menukaart kunnen bekijken zodat ik een gerecht naar wens kan uitkiezen
3. Als gast wil ik details van een gerecht kunnen bekijken zodat ik weet wat voor ingrediënten daarin zitten
4. Als gast wil ik een gekozen gerecht van de menukaart toevoegen aan de winkelmand zodat deze besteld kan worden
5. Als gast wil ik een winkelmand kunnen omzetten naar bestelling zodat ik deze geserveerd krijg

> Alternatieven die ter sprake kwamen tijdens de sessie:
>
> - Als gast wil ik de menukaart kunnen filteren op basis van voedingsrestricties zodat ik alleen gerechten zie met die criteria
> 
> - Als gast wil ik het formaat van een gerecht kunnen kiezen zodat ik een portie van gewenst formaat krijg
>
> - Als gast wil ik een gerecht kunnen verwijderen uit mijn winkelwagen zodat ik een fout kan corrigeren
>
> - Als gast wil ik een bestelling kunnen annuleren zodat ik andere gerechten kan bestellen
> 
> - Als gast wil ik kunnen zien wat de verwachte levertijd is van mijn bestelling zodat ik mijn verwachting daarop kan bijstellen
>
> - Als gast wil ik het maandmenu kunnen bekijken
> 
> - Als gast wil ik reviews van een gerecht kunnen bekijken zodat ik weet wat andere gasten daarvan vonden

### 3.2 Betalen rekening

Bij het scenario: `Als gast wil ik de rekening contant betalen zodat ik kan vertrekken zonder openstaande schuld`
is in een domain storytelling sessie een fine-grained domain story opgesteld.

![fine-grained domain story - rekening contant betalen](../opdracht-diagrammen/rekening%20betalen%20met%20contant%20geld%20digitalized%20to-be%20fine-grained.egn.svg)

1. Als gast wil ik inloggen met mijn tafelnummer zodat ik de juiste rekening kan gaan betalen
2. Als gast wil ik de rekening kunnen bekijken zodat ik weet hoeveel geld ik tot nu toe kwijt ben
3. Als gast wil ik de rekening kunnen betalen met contant geld zodat ik mijn schuld op die manier kan vereffenen
4. Als gast wil ik het teveel betaalde geld in de vorm van wisselgeld terug krijgen zodat ik niet te veel betaal

> Alternatieven die ter sprake kwamen tijdens de sessie:
>
> - Als gast wil ik de rekening kunnen splitsen zodat ik alleen de zelf genuttigde gerechten hoef te betalen
>
> - Als gast wil ik een fooi kunnen geven zodat ik mijn dankbaarheid in geld kan uitdrukken

### 3.3 Domain Model

![Domain Model](../opdracht-diagrammen/Domain%20Model.png)

Wat hierin te zien is dat het team besloten heeft om de voedingsrestricties buiten scope te houden, hiervoor zal een bestaand extern systeem gebruikt worden.

Aangezien de voorraad van wezenlijk belang is bij het kiezen van de juiste aggregate grenzen is voorraad wel meegenomen in het ontwerp, maar nog niet in-scope van de eerste iteratie.

### 3.4 System Sequence Diagram 

Er is een high-level sequence diagram opgesteld van een [restaurant bezoek](../opdracht-diagrammen/SSD%20-%20restaurantbezoek.png).

Vervolgens kan voor elke methode bepaald worden wat de pre- en postcondities zijn. Zoals hieronder vast weergegeven voor de methode `Bestelling plaatsen`.

## bestelling plaatsen

Methode: `plaatsBestelling(restaurant, tafelnummer, winkelmand)`

Korte beschrijving:
`Een nieuwe bestelling wordt ingevoerd in het systeem door een winkelmand om te zetten naar een bestelling.`

### Precondities

* Er bestaat een `tafel` T met opgegeven `tafelnummer`.
* De `winkelmand` W bevat minstens één `regel`.
* Er bestaan gerechten die overeenkomen met de opgegeven `gerecht`:`id`.

### Postcondities

* Er is een `bestelling` B aangemaakt
* Voor elk uniek gerecht uit de `winkelmand` is een `regel` R binnen de `bestelling` B aangemaakt met het aantal keer dat deze voorkomt in de `winkelmand` W
* voor elke `regel` R is er een associatie met de `bestelling` B
* De `bestelling` heeft een associatie met `tafel` T
* De `winkelmand` W is verwijderd

## 4. Quality Attributes

Voordat deze casusomschrijving tot stand kwam, heeft de opdrachtgever de volgende ISO 25010 kwaliteitsattributen benoemd als belangrijk:
* Compatibility -> Interoperability (Degree to which a system, product or component can exchange information with other products and mutually use the information that has been exchanged)
* Reliability -> Fault Tolerance (Degree to which a system or component operates as intended despite the presence of hardware or software faults)
* Maintainability -> Modularity (Degree to which a system or computer program is composed of discrete components such that a change to one component has minimal impact on other components)
* Maintainability -> Modifiability (Degree to which a product or system can be effectively and efficiently modified without introducing defects or degrading existing product quality)
* Security -> Integrity (Degree to which a system, product or component ensures that the state of its system and data are protected from unauthorized modification or deletion either by malicious action or computer error)
* Security -> Confidentiality (Degree to which a system, product or component ensures that data are accessible only to those authorized to have access)

## 5. Constraints

> [!IMPORTANT]
> Beschrijf zelf de beperkingen die op voorhand bekend zijn die invloed hebben op keuzes die wel of niet gemaakt kunnen of mogen worden.

## 6. Principles

> [!IMPORTANT]
> Beschrijf zelf de belangrijkste architecturele en design principes die zijn toegepast in de software.

## 7. Software Architecture

###     7.1. Containers

> [!IMPORTANT]
> Voeg toe: Container Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

###     7.2. Components

> [!IMPORTANT]
> Voeg toe: Component Diagram plus een Dynamic Diagram van een aantal scenario's inclusief begeleidende tekst.

###     7.3. Design & Code

> [!IMPORTANT]
> Voeg toe: Per ontwerpvraag een Class Diagram plus een Sequence Diagram van een aantal scenario's inclusief begeleidende tekst.

## 8. Architectural Decision Records

> [!IMPORTANT]
> Voeg toe: 3 tot 5 ADR's die beslissingen beschrijven die zijn genomen tijdens het ontwerpen en bouwen van de software.

### 8.1. ADR-001 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Context 

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

## Considered Options

> [!TIP]
> This section mentions the possible options as headers in the second, third, etc. column. The forces in the left column are criteria (requirements, quality attributes, other forces like costs, learning curve, cultural, political or ethical factors that we can use to make the decision. 

| Forces                                        |  Aspect 1 << decided >>      |  Aspect 2 << discarded >>             |
|-----------------------------------------------|----------------------------|--------------------------------------|
| Force 1 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++ (incl. bron)          |
| Force 2 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 3 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 4 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |


#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We decided to use option X because of …"

#### Consequences 

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.

### 8.2. ADR-002 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

## Considered Options

> [!TIP]
> This section mentions the possible options as headers in the second, third, etc. column. The forces in the left column are criteria (requirements, quality attributes, other forces like costs, learning curve, cultural, political or ethical factors that we can use to make the decision.

| Forces                                        |  Aspect 1 << decided >>      |  Aspect 2 << discarded >>             |
|-----------------------------------------------|----------------------------|--------------------------------------|
| Force 1 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++ (incl. bron)          |
| Force 2 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 3 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 4 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |


#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We decided to use option X because of …"

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.

### 8.3. ADR-003 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

## Considered Options

> [!TIP]
> This section mentions the possible options as headers in the second, third, etc. column. The forces in the left column are criteria (requirements, quality attributes, other forces like costs, learning curve, cultural, political or ethical factors that we can use to make the decision.

| Forces                                        |  Aspect 1 << decided >>      |  Aspect 2 << discarded >>             |
|-----------------------------------------------|----------------------------|--------------------------------------|
| Force 1 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++ (incl. bron)          |
| Force 2 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 3 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 4 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |


#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We decided to use option X because of …"

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.

### 8.4. ADR-004 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

## Considered Options

> [!TIP]
> This section mentions the possible options as headers in the second, third, etc. column. The forces in the left column are criteria (requirements, quality attributes, other forces like costs, learning curve, cultural, political or ethical factors that we can use to make the decision.

| Forces                                        |  Aspect 1 << decided >>      |  Aspect 2 << discarded >>             |
|-----------------------------------------------|----------------------------|--------------------------------------|
| Force 1 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++ (incl. bron)          |
| Force 2 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 3 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 4 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |



#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We decided to use option X because of …"

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.

### 8.5. ADR-005 TITLE

> [!TIP]
> These documents have names that are short noun phrases. For example, "ADR 1: Deployment on Ruby on Rails 3.0.10" or "ADR 9: LDAP for Multitenant Integration". The whole ADR should be one or two pages long. We will write each ADR as if it is a conversation with a future developer. This requires good writing style, with full sentences organized into paragraphs. Bullets are acceptable only for visual style, not as an excuse for writing sentence fragments. (Bullets kill people, even PowerPoint bullets.)

#### Status

> [!TIP]
> A decision may be "proposed" if the project stakeholders haven't agreed with it yet, or "accepted" once it is agreed. If a later ADR changes or reverses a decision, it may be marked as "deprecated" or "superseded" with a reference to its replacement.

#### Context

> [!TIP]
> This section describes the forces at play, including technological, political, social, and project local. These forces are probably in tension, and should be called out as such. The language in this section is value-neutral. It is simply describing facts about the problem we're facing and points out factors to take into account or to weigh when making the final decision.

## Considered Options

> [!TIP]
> This section mentions the possible options as headers in the second, third, etc. column. The forces in the left column are criteria (requirements, quality attributes, other forces like costs, learning curve, cultural, political or ethical factors that we can use to make the decision.

| Forces                                        |  Aspect 1 << decided >>      |  Aspect 2 << discarded >>             |
|-----------------------------------------------|----------------------------|--------------------------------------|
| Force 1 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++ (incl. bron)          |
| Force 2 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 3 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |
| Force 4 (ASR of aanvullende force)            |   --/-/?/+/++ (incl. bron) |    --/-/?/+/++  (incl. bron)         |



#### Decision

> [!TIP]
> This section describes our response to the forces/problem. It is stated in full sentences, with active voice. "We decided to use option X because of …"

#### Consequences

> [!TIP]
> This section describes the resulting context, after applying the decision. All consequences should be listed here, not just the "positive" ones. A particular decision may have positive, negative, and neutral consequences, but all of them affect the team and project in the future.
## 9. Deployment, Operation and Support

> [!TIP]
> Zelf aanvullen van wat je moet doen om de software te installeren en te kunnen runnen.

In de map restaurant-app staat een Maven project dat met Java 21 of hoger gebuild en gerund kan worden:

```mvn package``` compileert de applicatie, ```mvn spring-boot:run``` start de applicatie.

Voor een goede samenwerking tussen de front-end en back-end zijn er afspraken gemaakt over de gewenste API in een [Open API specificatie](../restaurant-app/src/main/resources/openapi.json). Hierbij is met name gekeken naar het high-level sequence diagram uit hoofdstuk 3.