Implementarea acestei teme urmareste sa accentueze urmatoarele aspecte:
 - Realizarea unei structuri ce permite modificarea si scalarea acesteia
intrun mod usor accesibil si comphresibil.
 - Implementarea a  3 design pattern-uri: 
    1.Factory Pattern: A fost folosit pentru realizarea a claselor diferite,
precum ce a conturilor(cel clasic si cel de economii),cat si a cardurilor(cel
clasic si cel one time).
    2.Command Pattern: Utilizat pentru genstionarea mai usoara a comenzilor
primite ca si input.
    3.Builder Pattern: Cu ajutorul acestia se construiesc tranzactiile, datorita
faptului ca fiecare tranzactie este semnificativ diferita ca si structura.

Programul este structurat astfel:
    1.Account package:
        Acesta contine clasele si interfata realizarii factory pattern-ului
pentru conturi:
        -Account(interface): Contine metodele necesare pentru utilizarea conturilor
        -AccountFactory(Class): Contine o metoda ce returneaza un cont in functie de
necesitate
        -ClassicAccount(Class): Reprezinta clasa pentru un cont clasic
        -SavingsAccount(Class): Reprezinta clasa pentru un cont de economii

    2.Card package:
        Acesta contine clasele si interfata necesara realizarii factory pattern-ului
pentru carduri:
        -Card(interface): Contine metodele necesare pentru utilizarea cardurilor
        -CardFactory(Class): Contine o metoda ce returneaza un card in functie de
necesitate
        -ClassicCard(Class): Reprezinta clasa pentru un card clasic
        -OneTimeUseCard(Class): Reprezinta clasa pentru un card cu use one time

    3.CommandsCenter Package:
        Acesta contine un package cu toate comenzile posibile, cat si o clasa
ce reprezinta centrul de comenzi care interpretaza toate comenzile primite ca input
si mai apoi acestea sunt executate la run time.
        -Commands(Package): Pentru fiecare comanda exista o clasa definita specific,
care aceasta este creata de catre CommandCenter, si mai apoi executata.Contine o interfata
specifica command pattern-ului cu o metode execute() care are ca rol executarea comenzii.
        -CommandCenter(Class): Este reprezentata de 2 metode principale, una care interpre-
teaza comenzile din input si cea care le pune in aplicare pe datele noastre.

    4.OutputJson Package:
        Contine toate clasele specifice comenzilor pentru a realiza output-ul.Acestea
adauga intr-un ArrayList de ObjectNode care mai apoi la final este parcurs si afiseaza
in fisierele de output rezultatele dupa rularea comenzilor.

    5.Transactions Package:
        Toti userii au un instoric de tranzactii in care sunt adaugate toate tranzactiile si 
modificiarile specifice unui user.
        -Transaction(Package): Contine 2 metode cea de aflare a timestamp-ului specific pentru
fiecare tranzactie cat si una de tip ObjectNode ce returneaza un obiect de tip ObjectNode ce
reprezinta formatul unei tranzactii

    6.Elements Class:
        Reprezinta clasa ce contine userii cat si cursul valutar, acestea fiind astfel o banca.
Fiecare User este tinut in memorie cu ajutorul unui HashMap in functie de adresa de mail asociata.

    7.ExchangeRate Class:
        Reprezinta cursul valutar asociat, acesta este reprezentat de un graf orientat pentru a obtine
intr-un timp optim cursul cu ajutorul parcurgerii DFS a grafului.

    8.User Class:
        Reprezinta clasa specifica unui user, aceatsa contine un HashMap pentru fiecare cont in functie
de IBAN, datele specifice user-ului precum nume, prenume, un HashMap ce retine toate aliasu-urile setate
pentru diferite conturi, cat si un ArrayList de tranzactii ce retin tranzactiile specifice unui User.



    Ca si functionare, in clasa main se instantieaza un obiect de tip CommandCenter, mai apoi ii este oferit
inputul de comenzi asociat, iar la final este parcurs tot ArrayList-ul asociat output-urilor rezultat in urma 
comenzilor si este adaugat in fiserele ascoiate fiecarui test.
    
        
