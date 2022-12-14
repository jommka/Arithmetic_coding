import java.math.BigDecimal;

public class Segment {
    public BigDecimal l;
    public BigDecimal r;

    public Segment(BigDecimal l, BigDecimal r) {
        this.l = l;
        this.r = r;
    }

    public BigDecimal size(){
        return r.subtract(l);
    }

    @Override
    public String toString() {
        return "{" + l + " : " + r + "}";
    }
}
