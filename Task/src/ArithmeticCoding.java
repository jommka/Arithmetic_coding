import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticCoding {
    public List<Cymbol> symbols;
    public List<Segment> segments;
    public BigDecimal G;
    public List<BigDecimal> q;
    public List<String> decode;
    public ArithmeticCoding(List<Cymbol> symbols) {
        this.symbols = symbols;
        this.segments = createNewSegments(new Segment(new BigDecimal(0), new BigDecimal(1)));
    }

    public void getQ(){
        q = new ArrayList<>();
        q.add(BigDecimal.valueOf(0));
        for (int i = 1; i < symbols.size(); i++){
            q.add(q.get(i - 1).add(symbols.get(i - 1).p));
        }
    }

    public void decoding(BigDecimal F, BigDecimal G){
        decode = new ArrayList<>();
        for (BigDecimal q: q){
            //for att
//            decode.add(F.add(q.multiply(G).multiply(F)).add(q.multiply(G)).setScale(10, RoundingMode.CEILING).stripTrailingZeros().toPlainString());
            //for dz
            decode.add(F.add(q.multiply(G)).setScale(10, RoundingMode.CEILING).stripTrailingZeros().toPlainString());

        }
    }

    public List<Segment> createNewSegments(Segment s){
        List<Segment> newSegments = new ArrayList<>();
        newSegments.add(new Segment(s.l, (symbols.get(0).p).multiply(s.size()).add(s.l)));
        for (int i = 1; i < symbols.size(); i++) {
            newSegments.add(new Segment(newSegments.get(i-1).r, (symbols.get(i).p).multiply(s.size()).add(newSegments.get(i-1).r)));
        }
        this.segments = newSegments;
        G = newSegments.get(newSegments.size() - 1).r.subtract(newSegments.get(0).l);

        System.out.println("F = " + newSegments.get(0).l + "; G = "
                + G.stripTrailingZeros().toPlainString());

        getQ();
        decoding(newSegments.get(0).l, G);
        System.out.println("decode: " + decode);
        System.out.println();
        return newSegments;
    }

    public int getCodeLength(BigDecimal value){
        return (int) (-Math.log(value.doubleValue())/Math.log(2) + 2);
    }

    public BigDecimal getCode(){
        return segments.get(0).l.add(G.divide(BigDecimal.valueOf(2), 6, RoundingMode.DOWN));
    }

    public String getBinaryCode(BigDecimal value, int length){
        StringBuilder binaryCode = new StringBuilder();
        BigDecimal num;
        num = value.remainder(BigDecimal.ONE).setScale(5, RoundingMode.HALF_UP);

        for (int l = 0; l < length; l++){
            num = num.multiply(new BigDecimal(2));
            BigDecimal intNum = num.setScale(0, RoundingMode.DOWN);
            binaryCode.append(intNum);
            num = num.remainder(BigDecimal.ONE).setScale(6, RoundingMode.HALF_UP);
        }
        return String.valueOf(binaryCode);
    }

    public void getResult(List<String> symbols){
        calculate(symbols);
        int codeLength = getCodeLength(segments.get(segments.size() - 1).r.subtract(segments.get(0).l));
        System.out.println("---------------------------------------------------");
        System.out.println("Length: " + codeLength);
        System.out.println("X = " + getCode().stripTrailingZeros().toPlainString());
        System.out.println("Binary X = " + getBinaryCode(getCode(), codeLength));
        System.out.println("q = " + q);

    }


    public void calculate(List<String> symbols){
        for (String c:symbols) {
            System.out.print(c + ": ");
            createNewSegments(segments.get(index(c)));
        }
    }

    private int index(String symbol){
        for (int i = 0; i < symbols.size(); i++) {
            if(symbol.equals(symbols.get(i).c))
                return i;
        }
        return -1;
    }
}
