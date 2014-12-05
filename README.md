Sudoku
======

Sudoku lahendamise ülesanne MTAT.06.008

Lahendaja käivitamiseks tuleb projekt Eclipse'i importida. Main meetod asub klassis `ee.tkasekamp.sudoku.App`. Programm suudab: 

* lahendada suvalist sudokut
* genereerida sudoku
* lahendada erikujulise sudoku

## Kasutamine
* `Test sudoku` laeb sisse näidissudoku
* `Test Jigsaw sudoku` laeb sisse näidis erikujulise sudoku koos regioonifailiga
* `Genereeri sudoku` genereerib suvalise lahenduse
* `Lae tavaline sudoku` laeb sisse sudoku
* `Lae Jigsaw sudoku` laeb sisse erikujulise sudoku ning küsib ka regioonifaili

Lahendamiseks tuleb vajutada `Lahenda sudoku`.

Iga regioon on tähistatud erineva värviga.

## Algoritmi kirjeldus
Algoritm on suhteliselt lihtne. Lahendamist alustatakse kõige ülemisest vasakpoolsemast nurgast. Igasse tühja ruutu pannakse arv, kui see arv ei esine samas reas, veerus ega regioonis (ehk siis toimub valikute kitsendamine). Kui arv on valitud, siis toimub süvitsiotsing, kuni kõik ruudud on täidetud või ei leia enam ühtegi sobivat paigutust. Vea korral kustutatakse proovitud arv ning minnakse ülemisele tasemele, kus jätkatakse arvude proovimist.

Suvalise lahenduse genereerija alustab tühja tabeliga ning paneb esimese arvu suvalisse ruutu. 

