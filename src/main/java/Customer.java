import java.util.UUID;
public class Customer {

    private String name;
    private final UUID id;
    private Double ptsFidelite;

    public Customer(String name){
        this.name = name;
        this.id = UUID.randomUUID();
        this.ptsFidelite = ptsFidelite;
    }

    public String getName(){
        return name;
    }

    public Double getPoints(){
        return ptsFidelite;
    }

    public UUID getId(){
        return id;
    }

}
