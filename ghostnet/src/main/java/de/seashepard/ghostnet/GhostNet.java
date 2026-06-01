src/main/java/de/sheasepherd/ghostnet/entity/GhostNet.java
@ManyToOne
private Rescuer rescuer;
public Rescuer getRescuer() {
    return rescuer;
}

public void setRescuer(Rescuer rescuer) {
    this.rescuer = rescuer;
}
