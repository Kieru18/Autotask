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
    - możliwość usunięcia wpisanych danych do formularza logowania przy pomocy guzika
    Dodatkowe:
    - przystosowanie okienka do zmiany rozmiaru
    - hasło w bazie danych zahashowane - bezpieczeństwo

## Panel Główny:
    Podstawowe:
    - Wyświelanie uporządkowaniej listy zadań do wykoniania, gdzie struktura zadania to: kto zlecił, co zlecił, gdzie, do kiedy, opis
    - Porządkowanie listy zadań oparte na parametrach: odległość między miejscami wykonania zadań, priorytet zadania. 
    - Wyświetlanie listy pracowników i rozróżnianie ich na W pracy/Poza pracą
    Dodatkowe:
    - Wyświetlanie mapy zoo w centralnej części okienka
    - Wyświetlanie szczegółów zadania poprzez kliknięcie w odpowiednie miejsce na mapie
    - Mapka wyświetla kolejność wykonania zadań (graficzne przedstawienie listy zadań z podpowiedzią dotyczącą najefektywniejszego przejścia od zadania do zadania)
    - rozwinięte rozróżnianie pracowników na Dostępny/Zajęty/Niedostępny 
    - Możliwość sortowania pracowników, alfabetycznie, w zależności od dostępności
    - przystosowanie okienka do zmiany rozmiaru

## Panel Generowania Zadań:
    Podstawowe:
    - Możliwość tworzenia zadania przy pomocy formularza: komu, co zlecić, gdzie, do kiedy, opis, priotytet
    - Możliwość tworzenia zadań cyklicznych
    Dodatkowe:
    - Formularz: wybór z listy historycznych wartości
    - przystosowanie okienka do zmiany rozmiaru

## Panel Zakończenia zadania:
    Podstawowe:
    - możliwość zaznaczenia zadania jako wykonane
    Dodakowe:
    - możliwość wpisania produktów które zostały użyte do wykonania zadania
    - aktualizacja stanu magazynu
    - przystosowanie okienka do zmiany rozmiaru
