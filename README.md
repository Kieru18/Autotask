# Skład zespołu:
    Jakub Kieruczenko
    Karol Ziarek
    Szymon Łukawski

# Temat projektu:
    Aplikacja dla pracowników (dla tego projektu, ZOO)
    Automatyzacja procesu prioretyzacji i przydzielania zadań
    Nazwa Aplikacji: AutoTask

# Technologia:
    - Java, Maven
    - Springboot
    - Vaadin
    - Hibernate

# Użycie:
    git clone https://gitlab-stud.elka.pw.edu.pl/jkierucz/pap22z-z21
    cd pap22z-z21/autotask
    mvn clean package
    java -jar ./target/autotask.jar


# Funkcjonalności:
## Panel logowania:
    Podstawowe:
    - możliwość zalogowania przy pomocy loginu i hasła
    - możliwość pokazania hasła przy pomocy checkboxa
    Dodatkowe:
    - przystosowanie okienka do zmiany rozmiaru
    - hasło w bazie danych zahashowane - bezpieczeństwo

## Panel Główny:
    Podstawowe:
    - Wyświelanie uporządkowaniej listy zadań do wykoniania, gdzie struktura zadania to: opis, deadline, pracownicy, lokazlizacja itd.
    - Porządkowanie listy zadań oparte na parametrach: deadline, priorytet zadania. 
    - Wyświetlanie listy pracowników i rozróżnianie ich na W pracy/Poza pracą
    - Możliwość sortowania pracowników w zależności od dostępności 
    Pomysły na dalszy rozwój funkcjonalności:
    - Mapa zoo wyświetlająca zadania
    - Wyświetlanie szczegółów zadania poprzez kliknięcie w odpowiednie miejsce na mapie
    - Mapka wyświetla kolejność wykonania zadań (graficzne przedstawienie listy zadań z podpowiedzią dotyczącą najefektywniejszego przejścia od zadania do zadania)
    - Rozwinięte rozróżnianie pracowników na Dostępny/Zajęty/Niedostępny 

## Panel Generowania Zadań:
    Podstawowe:
    - Możliwość tworzenia zadania przy pomocy formularza: komu, co zlecić, gdzie, do kiedy, opis, priotytet
    - Możliwość tworzenia zadań cyklicznych
    Dodatkowe:
    - Formularz: wybór z listy historycznych wartości
    - przystosowanie okienka do zmiany rozmiaru
