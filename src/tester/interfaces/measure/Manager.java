package tester.interfaces.measure;

public class Manager extends Employee implements Measurable {

    private double bonus;

    public Manager(String name, double bonus) {
        super(name, Math.round(random.nextDouble() * 100000.0 + 50000));
        this.bonus = bonus;
    }

    @Override
    public double getMeasure() {
        return super.getMeasure() + bonus;
    }

    @Override
    public String toString() {
        return super.toString() + " + bonus:" + bonus;
    }
}