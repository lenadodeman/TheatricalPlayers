public class Comedy extends Play{

    public Comedy(String name){
        setName(name);
    }
    
    @Override
    public Double calculOfPrice(Integer audience){
        Double amount = 300.0;
        if (audience > 20) {
            amount += 100 + 5 * (audience - 20);
        }
        amount += 3 * audience;

        return amount;
    }

    @Override
    public Double calculOfFidelity(Integer audience) {
        return Double.valueOf(Math.max(audience - 30, 0))+Math.floor(audience / 5);
    }
}
