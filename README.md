# Laboratorium 5, TN 11:00

Podczas laboratorium należy zbudować aplikację, w której dojdzie do synchronizacji wielu wątków. Aplikacja powinna pozwalać na ich uruchamianie (liczba uruchamianych wątków to parametr, który można zmienić programie) i obserwowanie ich stanów.
Zakładamy, że aplikacja będzie pełnić rolę symulatora hali przemysłowej, po której poruszają się roboty mobilne z napędem elektrycznym. Co jakiś czas roboty muszą ładować akumulatory. Mogą to robić w stacji ładowania, która ma kilka stanowisk rozmieszczonych liniowo wzdłuż ściany. Problem w tym, że roboty podczas ładowania blokują stanowiska. Co więcej, blokowanych może być więcej niż jedno stanowisko, jeśli wymusza to rozmiar danego robota. Zakładamy, że rozmiary robotów są dyskretne. Rozmiar 1 może oznaczać, że robot blokuje jedno stanowisko, rozmiar 2 - że dwa stanowiska itd. Zakładamy, że każde stanowisko skrajne ma na tyle dużo przestrzeni po stronie bez innych stanowisk, że takie stanowisko może zablokować robot o dowolnym rozmiarze, nie blokując stanowisk pozostałych. Jednak w przypadku złego dojazdu robot może zablokować również stanowiska sąsiednie. W najgorszym przypadku może zablokować tyle stanowisk (wliczając stanowisko skrajne), ile wynika z jego rozmiaru. Ponadto zakładamy, że częstość, z jaką roboty muszą pojawić się przed stacją ładowania, jest parametryzowana (roboty poruszają się po hali przez jakiś ustalony czas plus losowy margines). Symulator powinien umożliwiać obserwację sytuacji panującej w hali. Niech roboty będą wątkami, zaś stacja dokująca współdzielonym zasobem. Niech największy możliwy rozmiar robota równa się liczbie stanowisk stacji ładowania. Aby sprawę uprościć wizualizacja może być zrobiona za pomocą etykiet tekstowych oraz tabeli. Zanim wystartuje symulacja można przekazać do programu liczbę stanowisk oraz liczbę robotów. Na bazie tego parametru można wyrysować za pomocą etykiet tekstowych taki oto schemat (na stanowiskach pojawiają się literki reprezentujące robota, w tabeli widać parametry robotów oraz ich status):

```txt
Stanowiska     Roboty
_              Nazwa  Rozmiar Status  Czas
_              A      3       działa   40
_              B      4       czeka    20
1:             C      2       działa   30
2:             D      2       ładuje   30
3:
4: D
_  D
_
_
```

Należy zadbać o odpowiednią synchronizację wątków (z wait() i notify()).
Pozostałe szczegóły mają być zgodne z ustaleniami poczynionymi na początku zajęć.
