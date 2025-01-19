import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Programowanie funkcyjne, strumienie, lambdy
 */
public class Strumienie {


    public static void main(String[] args) {
        Strumienie s = new Strumienie();

        //List<Integer> lista = List.of(1, 2, 3, 4, 5);
        List<Integer> lista = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));


        // opis jak cos zrobic - np. petla
        for(Integer i : lista) {
            System.out.println(lista);
        }

        // opis co zrobic
        // klasa anonimowa
        lista.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        // lambda
        lista.forEach(i -> System.out.println(i)); // nazwa zmiennej w lambdzie jest dowolna, nie podajemy typu

        lista.forEach(i -> {
            System.out.println(i);
            System.out.println("kolejna operacja dla " + i);
        });


        // referencja do metody
        lista.forEach(System.out::println);


        s.f1(10);

        Consumer<Integer> println = System.out::println;
        lista.forEach(println);

        println.accept(15);

        lista.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        });

        System.out.println();

        // do przetwarzania danych najlepiej uzyc strumienia:
        Stream<Integer> stream = lista.stream();
        stream = stream.map(liczba -> liczba*2);

        System.out.println(stream.count());

        // jedno dlugie wywolanie - preferowany sposob
        lista.stream()
                .map(e -> e*2) // zmiana elementu w strumieniu na inny (tutaj mnozenie go przez 2)
                .filter( e -> e < 5)
            .count();


        // funkcje konczace/terminujace - po nich nie mozna juz uzyc danego strumienia
        // count()

        // collect zbiera elementy strumienia do nowej kolekcji
        List<Integer> collect = lista.stream()
                //.collect(Collectors.toList());
                //.collect(Collectors.toSet())
                .toList();

        lista.stream()
            .forEach(System.out::println);

        lista.stream()
            .min(Integer::compareTo);

        boolean wszystkieMniejszeNiz12 = lista.stream()
                .allMatch(i -> i < 12);

        Optional<Integer> optionalElement = lista.stream()
                .findFirst();

        if (optionalElement.isPresent()) {
            Integer i = optionalElement.get();
            System.out.println(i);
        }

        // funkcyjny odpowiednik
        optionalElement.ifPresent(System.out::println);
    }


    int a = 1;

    public void f1(int a) {
        this.a = a;
        int b = a;
        System.out.println(b);
    }

    public void f2(int a) {
        int b = a;
        System.out.println(b);
    }


}
