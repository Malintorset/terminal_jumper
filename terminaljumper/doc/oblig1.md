# Oblig 1 - Hackermens

## A0

Gruppenavn: Hackermens

Kanal på Discord: check ✅

GitLab-gruppe: check ✅

### Kompetanse:

#### Malin:
- Har kodet i java i inf101 og inf102. Har ikke kompetanse fra spilldesign. 
- Har en tendens til å overkomplisere problemet. Sitter ofte fast i koden min.
- Har ikke kodet noen fritidsprosjekter før :’)

#### Lars Magnus:
- Har kodet java i inf101 og inf102. 
- Har prøvd meg på eget prosjekt, men det stoppet opp når jeg ikke fikk rettet opp i feilene. 
- Jeg sliter av og til med å overkomplisere koden eller problemet. 
- Har ikke erfaring med design. 

#### Viljar:
- Har kodet Java i inf 101, gikk FHS for spilldesign
- Komfortabel med git og Linux
- Kodet en del små fritidsprosjekter, men ikke i Java spesifikt, komfortabel med koding, comfy med Python 
- Også tendens til å overkomplisere :^)
- Meh på grafikk, bais på musikk
- Hatt tredjeårsfagene 

#### Petter:
- har kodet i inf101, inf102, inf140, inf161
- Mest erfaring med Java, noe Python. 
- Lineær algebra, diskret matte, statistikk

#### Aleksander:
- God kompetanse i Python (INF100)
- En del kompetanse i JavaScript/TypeScript/HTML/CSS
- Lærer Java, SQL og PHP (og GIT) nå (INF101, INF112)
- Noe erfaring med R (fra statistikk)
- Noe erfaring med Linux, Bash og PowerShell (INF140)
- Noe erfaring med Figma (INFO162)
- God matematisk kompetanse (utdannet lektor i matematikk)
- Erfaring fra organisasjonsarbeid gjennom ulike roller i studentorganisasjon

---

## A1

### Rollefordeling

#### *Team lead* - Malin
- Viktig for organisasjonen av gruppen. Ordfører på møter og tar referat av samlingene med gruppen.

#### *Tech lead* - Viljar 
- Viktig å ha noen med generelt overblikk over teknologien i bruk samt oversikt over arkitekturen.

#### *Head of consultant* - Lars Magnus
- Denne rollen er viktig å ha da man som Head of consultant kan være med å plassere riktig arbeidskompetanse til riktige prosjekt. Dette sparer bedriften både for tid, penger og sløsing av ressurser. 

#### *Software engineer and head of project optimizing* - Aleksander
- Som programvareutvikler er den primære rollen å implementere ideene og kravene til funksjonell kode i henhold til god kode praksis. Som leder for prosjekt optimalisering er oppgaven å sikre optimal ytelse i koden, samt at den er slank, gjennbrukbar, kan ekspanderes og mulig å vedlikeholde.

#### *Head of client contact* - Petter
- Viktig for kontakt med kunden. I forhold til om noe endrer seg i oppgaven. Hovedperson for tolking av informasjon mellom kunden og utviklerene.


Vi tenker at disse rollene vil være med å dekke de behovene som vil oppstå i løpet av et prosjekt. 

--- 

## A2

#### Oppgave A2 - Konsept 
- Inspirasjon: Icy Tower (2001) (Terminal edition)

#### Spillet skal inneholde følgende: 
- Spillet har en forside 
- En spillfigur som kan styres, gå til venstre og høyre, hoppe opp.
- Vegger, som spilleren ikke kan gå gjennom.
- Små platformer som spilleren kan hoppe på. 
- Lyder og grafikk

#### Design:
- Terminal / ASCII, med terminal-kommandoer som platformer 
- Score display 

#### Gameplay-loop:
- Spilleren må hoppe opp en terminal ved bruk av plattformer (kommandoer), samtidig som kameraet hele tiden scroller opp.
- Spillet er over hvis man faller ned, eller skjermen «løper» fra deg.
- Underveis kan spilleren plukke opp «power ups», men også «debuffs» , med positive og negative effekter
- Økt hastighet, dobbeltrom, lavere hastighet, ekstrapoeng osv

#### Nice to haves:
- Leaderboard med high scores 
- Pause og start på nytt knapp
- At noen “plattformer” er svakere? 

---

## A3.1

### Oppgave A3.1: Velg og tilpass en prosess for teamet.

Vi kommer til å bruke Trello for å ha en oversikt over hvordan vi ligger an med de ulike arbeidsoppgavene. Discord vil bli brukt som kommunikasjonsplattform. Vi kommer til å ha ukentlige møter, hvor vi skal presentere hva man har gjort siden sist og hvilke utfordringer man står ovenfor. Dette vil bidra til en felles forståelse for hvordan prosjektet utvikler seg og prosjektmedlemmene kan gi konstruktiv tilbakemelding til hverandre. 

Vi velger scrum som prosjektmetodikk.

### Her er noen grunner til at SCRUM kan være det rette valget for dette prosjektet:

#### Fleksibilitet: 

SCRUM er designet for å være en svært fleksibel ramme som kan tilpasse seg endrede krav og uventede hindringer. Dette gjør det ideelt for utviklingen av et spill som "Terminal Tower", hvor krav og gameplay-mekanikker kan endre seg ofte mens prosjektet fremmer seg.

#### Samarbeid: 

SCRUM vektlegger samarbeid og teamwork, som er avgjørende for suksess i et spillutviklingsprosjekt. Med SCRUM kan utviklingsteamet jobbe tett sammen for å sikre at alle er på samme side, og at prosjektet beveger seg i riktig retning.

#### Fokus på å levere verdi: 

SCRUM legger sterkt vekt på å levere virkende programvare som gir verdi til sluttbrukeren. Dette er spesielt viktig for et spill som "Terminal Tower", hvor spillere leter etter en engasjerende og morsom opplevelse.

#### Omfavner endring:

 SCRUM anerkjenner at endring er en naturlig del av programvareutvikling, og det gir en ramme for å omfavne og inkorporere endringer i utviklingsprosessen. Dette betyr at teamet kan reagere raskt på nye ideer og tilbakemeldinger, og kan fortsette å forbedre spillet gjennom hele utviklingsprosessen.

Konklusjonen er at SCRUMs fleksible og samarbeidende tilnærming, fokus på å levere verdi, og evne til å omfavne endring, gjør det til en ideell ramme for utviklingen av "Terminal Tower". Ved å bruke SCRUM kan teamet sikre at spillet utvikles effektivt og effektivt, og at det gir en engasjerende og morsom opplevelse for spillerne.

---

## A3.2

Oppgave A3.2: Få oversikt over forventet produkt

En kort beskrivelse av det overordnede målet for applikasjonen:
Et spill som kan kjøres på flere plattformer med minimalt bugs.


### MVP:

1. Vise startskjerm.
2. Ha fungerende knapper/funksjoner til startskjermen. 
3. Vis spiller på spillebrettet. 
4. Flytt spiller (inklusiv tyngdekraft, kunne hoppe)
5. Spiller interagerar med terrenget. 
6. Dynamisk grafikk avhengig av hvor spilleren befinner seg. 
7. Spiller kan dø (dersom man faller utenfor skjermen eller at skjermen tar den igjen).
8. Mål for spillbrett (oppnå høyest mulig poengscore, overleve lengst mulig eller hoppe høyeste mulig).
9. Nytt spillbrett når forrige er ferdig.
10. Game over skjer (viser statistikk for runden, start på nytt knapp). 

### Brukerhistorier:

#### Prioriterte:

- Som spiller trenger jeg å kunne skille trygge plattformer og å falle i avgrunnen, slik at jeg kan navigere spilleren rett. (Samt feller og power-ups.)
    - Akseptkriterie:
        - Grafiske figurer (sprites) osv burde være lett å skille fra hverandre (for svaksynte)
    - Arbeidsoppgave:
        - Lage kontrastrik grafikk

- Som spiller trenger jeg at spillet er lett å forstå og interagere med.
    - Akseptkriterie:
        - Et intuitivt interface / UX, med lettforståelige kontroller
    - Arbeidsoppgave:
        - Teste hvordan spillet er for nye brukere

- Som spiller ønsker å spille et spill som fungerer, og ikke leder til frustrasjoner ved at det ikke fungerer. 
    - Akseptkriterie:
        - Ingen kritiske bugs (game breaking) som ødelegger for å kunne spille
    - Arbeidsoppgave:
        - Skrive god og oversiktlig kode. Teste koden etterhvert.
        - Ha med enhetstester og eller inklusiv men ikke eksklusiv og eller utelukkende funksjonalitetstester 


#### Andre:

- Som en nostalgisk spillentusiast, vil jeg gjenoppleve minnene fra klassiske arcade-spill som Icy Tower, men med en moderne vri, slik at jeg kan nyte en ny versjon av en kjent klassiker.

- Som en casual spillentusiast, vil jeg ha et spill som er enkelt å forstå, men fortsatt gir en utfordrende opplevelse, slik at jeg kan ha det gøy og føle en følelse av prestasjon mens jeg fremmer meg.

- Som en konkurransedyktig spiller, vil jeg følge med på mine høye poengsummer og sammenligne dem med andre spillere, slik at jeg kan bevise mine ferdigheter og strebe etter å bli den beste i verden.

- Som en fan av Windows-kommandoprompten, vil jeg se min favoritt datamiljø bli gjort til live på en ny og spennende måte, slik at jeg kan stupe inn i en verden som er både kjent og ny.

- Som markedsfører trenger jeg et spill som er enkelt å markedsføre, dette omhandler blant annet godt design og brukervennlighet.

- Spillet må være lett å spille, på tvers av aldersgrupper.
 
- Som programmerer trenger jeg å ha en testgruppe, dette er for å finne ut om vi må gjøre endringer i spillet eller om det finnes bugs vi ikke har oppdaget. 
 
- Som kunde vil jeg ha et underholdende spill som tilfredsstiller kravene mine

- Som programmerer vil jeg ha en overordnet og organisert kodebase og jobbe med

- Som programmer trenger jeg at spillet appellerer til en stor markedsandel 

---

## A5 

### Oppgave A5: Oppsummering

Vi føler både at kommunikasjonen og samarbeidet har vært godt. I startfasen var arbeidsoppgavene litt uklare, men dette er noe som vil endre seg med tiden og noe som avhenger av prosjektets utvikling. 

Til nå har vi hatt ukentlige møter hver onsdag og den siste uken har vi sett behov for å øke dette. Tiden vi har satt av til møtene har vært brukt svært effektivt. I forkant har vi sendt ut saksliste, som har fungert som et godt utgangspunkt for møtene. Det har vært skrevet referat for å dokumentere prosjektets utvikling.

Videre vil vi fortsette å utvikle spillet. For å gjøre dette mest mulig effektivt skal vi bli bedre kjent med libGDX, Git og Scrum. Vi har også planer om å fordele arbeidsoppgaver, som gruppemedlemmene kan utføre frem til neste møte. 
