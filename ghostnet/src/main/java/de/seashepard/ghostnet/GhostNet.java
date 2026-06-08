src/main/java/de/seashepard/ghostnet/entity/GhostNet.java
@ManyToOne
private Rescuer rescuer;
public Rescuer getRescuer() {
    return rescuer;
}

public void setRescuer(Rescuer rescuer) {
    this.rescuer = rescuer;
}
