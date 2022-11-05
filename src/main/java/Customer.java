import java.util.UUID;
public class Customer {

    private String name;
    private final UUID id;
    private Double ptsFidelity = 0.0;

    public Customer(String name){
        this.name = name;
        this.id = UUID.randomUUID();
        this.ptsFidelity = ptsFidelity;
    }

    public String getName(){
        return name;
    }
    public UUID getId(){
        return id;
    }

    // Concerne les points que le client a déjà
    public Double getFidelity(){
        return ptsFidelity;
    }

    // Update des points du clients en fonction de ceux gagnés par la performance
    public void updateFidelity(Performance perf) {
        this.ptsFidelity += perf.getFidelity();
    }

    public void updateFidelity(int ptsfidelity) {
        this.ptsFidelity += ptsFidelity;
    }

    public Boolean possibleDiscount() {
        if (getFidelity() >= 150) {
            updateFidelity(-150);
            return true;
        }
        return false;
    }

}
