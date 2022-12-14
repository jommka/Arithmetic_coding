import java.math.BigDecimal;
import java.util.Objects;

public class Cymbol {
    public String c;
    public BigDecimal p;

    public Cymbol(String c, double p) {
        this.c = c;
        this.p = BigDecimal.valueOf(p);
    }

    @Override
    public String toString() {
        return "'" + c +" = "+p+"'";
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, p);
    }
}
