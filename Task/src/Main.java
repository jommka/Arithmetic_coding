import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cymbol> symbols = List.of(
                new Cymbol("s1", 0.1),
                new Cymbol("s2", 0.3),
                new Cymbol("s3", 0.4),
                new Cymbol("s4", 0.2)
        );
        ArithmeticCoding ac = new ArithmeticCoding(symbols);
        List<String> l =List.of(
                "s3",
                "s2",
                "s4",
                "s4",
                "s2",
                "s1",
                "s2"
        );
        ac.getResult(l);
    }
}
