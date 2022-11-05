public class Tragedy extends Play{

    public Tragedy(String name){
        setName(name);
    }
    @Override
    public Double calculOfPrice(Integer audience){
        Double amount = 400.0;
        if (audience > 30) {
            amount += 10 * (audience - 30);
        }

        return amount;
    }

    @Override
    public Double calculOfFidelity(Integer audience) {
        return Double.valueOf(Math.max(audience - 30, 0));
    }
}
