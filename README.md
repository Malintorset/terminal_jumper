# INF112 Project – *Terminaljumper*

![TERMINALJUMPER logo](./doc/img/terminaljumper-logo.png)

- Team: *Hackermens* (Gruppe 7):
  - *Petter Næsset Borja, Lars Magnus Longva, Prem Eide, Viljar Slettli, Aleksander Ljosdal Fedøy, Malin Torset Sivertstøl*
- https://git.app.uib.no/hackermens1/terminaljumper
- https://trello.com/b/3IqzkDAD/hackermens

## Om spillet

*«"Terminal Jumper" is a fast-paced, retro-style arcade game set in the iconic world of the Windows command prompt. Players must navigate their way up an ever-ascending tower, jumping from platform to platform and avoiding obstacles along the way. With its classic graphics, simple controls, and addictive gameplay, Terminal Jumper will take players back to the golden age of gaming, while adding its own unique twists and challenges to keep things fresh and exciting.»*

Kanskje litt pompøst, men kort fortalt er spillet en utfordrende plattformer, hvor du må klatre et tårn i et kappløp mot tiden. Samtidig må du unngå feller, samle ekstrapoeng og powerup, og ikke minst unngå musepekeren som gjerne vil slette deg!

![Skjermbilde som viser TERMINALJUMPER ved siste innlevering](doc/img/oblig4/terminaljumper-final-gamescreen.png)

## Kjøre spillet

### Enkelt (anbefalt)

Krever Java 19 eller høyere. 
1. Last ned [terminaljumper-1.0.0.jar](doc/binaries/terminaljumper-1.0.0.jar), som du finner i dette prosjektet under mappen `doc/binaries` 
2. Dobbeltrykk filen for å starte spillet!

Får du problemer, sjekk først at Java er riktig installert:
1. På Windows, åpne "PowerShell", for Linux og macOS, åpne "Terminal".
2. Lim inn: `java -version` og trykk Enter.
3. Dersom den svarer noe som `Java version 19.02`, hvor tallet er 19 eller høyere har du det installert.

Hvis det ikke fungerer å dobbeltrykke spillet direkte, kan du via en terminal (PowerShell i Windows), i samme mappe som filen ligger i, lime inn:
- `java -jar terminaljumper-1.0.0.jar`
  
### Avansert

Hvis du ønsker å kompilere spillet selv, trenger du Java og Maven satt opp.
- Kompileres med `mvn package`
- Kjøres med `java -jar terminaljumper-1.0.0-fat.jar` fra mappen filen ligger i. Det kan hende den ligger i mappen `target`.

Hvis alt annet feiler, og du har erfaring med utvikling, åpne prosjektet i en IDE som IntelliJ og start spillet fra Main.java direkte.

## Hvordan spille / kontroller

Målet er å komme seg så høyt som mulig i tårnet, uten å falle, bli tatt av musepekeren eller at kameraet henter deg igjen. Underveis vil du møte på power-ups og power-downs som vil påvirke spillet.

Under er det beskrevet hva de ulike knappene gjør:
- For å bevege spilleren (høyre og vestre): `A, D / eller piltastene`
- For å hoppe: `SPACE`
- For å lukke spillet: `ESC`

## Kjente feil (Bugs)

- Dobbelthopp på MacOS føles enda ikke helt bra. 
  - Spesifikt vil det andre hoppet noen ganger ikke registreres når du trykker SPACE, dette er knyttet til "isKeyJustPressed" i LibGDX. 
  - Dette var også et problem ved forrige innlevering, men da risikerte man å ikke få hoppet i det hele tatt. Dette er blitt kraftig forbedret og denne bugen gjelder nå bare det andre hoppet. 
- Noen power-ups og bomber i spillet kan ha en feilformet hitbox, slik at den blir større enn spriten og gjerne trapesformet. Spilleren kan da treffe disse uten at det visuelt gir mening.
    - Dette er strengt tatt ikke en bug, men hitbox til musepeker-fienden er heller ikke optimal. Per nå dreper den bare spilleren om den får tak i føttene hans.
- Resizing eller fullscreen på Windows og Linux kan medføre at UI-elementer og grafikk deformeres. Kan "fikses" ved å gå litt ut og inn av fullscreen, eller bytte "skjermer" i spillet.
- Krasj ved resizing av vindu på macOS
  - Kjent problem i LibGDX som kan være spesifikk til M1-prosessorer

Pga de to sistnevnte feilene med resizing er denne funksjolatiteten foreløpig slått av.

## Credits

Spillet er inspirert av: https://en.wikipedia.org/wiki/Icy_Tower

Under er credits gitt og lisenser spesifisert for eksterne ressurser vi har brukt. Til slutt er lisenser for ressurser vi selv har laget beskrevet.

### Design

#### Startskjerm

*Closebutton*
- By: Material Design
- Source: https://www.flaticon.com/free-icon/close-button_61155
- License: https://creativecommons.org/licenses/by/3.0/

*Resize icon*
- By: BigMug Line
- Source: https://www.flaticon.com/free-icon/two-rounded-equal-squares-outlines-symbol_54725
- License: Free for personal and commercial use with attribution.

*Minimize icon*
- By: Skent weibo
- Source: https://www.veryicon.com/icons/miscellaneous/skent-icon/minimize-8.html
- License: Free for personal and commercial purpose.

*Recyclingbin*
- Source: https://www.pngwing.com/en/free-png-tviug
- License: Non-commercial use, DMCA Contact Us

*Flatfolder*
- By: Pong Man
- Source: https://opengameart.org/content/flat-folder-icon
- License: https://creativecommons.org/publicdomain/zero/1.0/

### Sprites og tileset

*Industrial Zone Tileset*
- By: Craftpix
- Source: https://craftpix.net/freebies/free-industrial-zone-tileset-pixel-art/?utm_campaign=SocialNetwork&utm_source=Dribbble&utm_medium=free-industrial-zone-tileset-pixel-art&affiliate=121692
- License: https://craftpix.net/file-licenses/

### Lydfiler

#### Lydeffekter

button-5.wav, Long Electronic Button Sound 2
- https://www.hooksounds.com/sound-effects/long-electronic-button-sound-2/1604311/
- Copyright © 2023 HookSounds - Royalty Free Music - subscriber at time of download and use

button-3.wav, Electronic Button Sound 16
- https://www.hooksounds.com/sound-effects/electronic-button-sound-16/1604263/
- Copyright © 2023 HookSounds - Royalty Free Music - subscriber at time of download and use

button-11.wav, Arcade Game Jump Button Sound 3
- https://www.hooksounds.com/sound-effects/arcade-game-jump-button-sound-3/1604168/
- Copyright © 2023 HookSounds - Royalty Free Music - subscriber at time of download and use

Filer fra Mixit:
- mixkit-extra-bonus-in-a-video-game-2045.wav
  - https://mixkit.co/free-sound-effects/game/
- mixkit-arcade-retro-game-over-213.wav
  - https://mixkit.co/free-sound-effects/game-over/

- Disse dekkes av lisensen:
  - Video games is listed as an acceptable use of the sounds effect on the website. https://mixkit.co/license/#sfxFree
    - Items under the Mixkit Sound Effects Free License can be used in your commercial and non-commercial projects for free.
    - You are licensed to use the Item to create an End Product that incorporates the Item as well as other things, so that it is larger in scope and different in nature than the Item. You’re permitted to download, copy, modify, distribute and publicly perform the Sound Effect Items on any web or social media platform, in podcasts and in video games, as well as in films and presentations distributed on CDs, DVDs, via TV or radio broadcast or internet based video on demand services.
    - You can’t redistribute the Item on its own, as stock, in a tool or template, or with source files. You’re also not allowed to claim them as your own or register them on any rights management service.
    - There are some important limits to these rights, described in our User Terms.

#### Musikk

Space-Jazz by [Kevin MacLead](https://incompetech.com/)
  - Music promoted by https://www.chosic.com/free-music/all/
  - [Creative Commons CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)

Wallpaper by [Kevin MacLead](https://incompetech.com/)
  - Music promoted by https://www.chosic.com/free-music/all/
  - [Creative Commons CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)

### Misc

UI-Skin for final score - [Terra Mother UI Ver. 1](https://github.com/czyzby/gdx-skins/tree/master/terra-mother)
- Created by Raymond "Raeleus" Buckley
- Visit ray3k.wordpress.com for games, tutorials, and much more!
- © Copyright 2016 Raymond Buckley

Various LibGDX assets 
- Assets included in the library
  - Including `uiskin` in `../src/main/skins`
- Assets released under the Apache 2.0 License. For more information, check the [NOTICE.txt](./NOTICE.txt) file.

## Våre Lisenser

Alt som ligger i mappen ```../src/main/resources/sprites``` er laget av Malin og skal bruke lisensen under:
- <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons-lisens" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />Dette verk er lisensieret under en <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Navngivelse-IkkeKommersiell-DelPåSammeVilkår 4.0 Internasjonal lisens</a>.

I mappen ```../src/main/resources/tilemaps``` er tileMap_1 - 5 og tileMap_START laget av Lars Magnus og skal bruke lisensen:
- <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons-lisens" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png"/></a> <br />Dette verk er lisensieret under en <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Navngivelse-IkkeKommersiell-DelPåSammeVilkår 4.0 Internasjonal lisens</a>.

I mappen ``` ../src/main/resources/ui``` er alt, utenom skin/pakken i mappen ```terra-mother ```, laget av Lars Magnus og Malin. Lisensen under skal brukes til dette.
- <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons-lisens" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png"/></a> <br/>Dette verk er lisensieret under en <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Navngivelse-IkkeKommersiell-DelPåSammeVilkår 4.0 Internasjonal lisens</a>.
