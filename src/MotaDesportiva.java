public class MotaDesportiva extends Mota {
    private boolean temTurbo;

    public MotaDesportiva(String marca, String modelo, int ano, String tipo, boolean temTurbo) {
        super(marca, modelo, ano, tipo);
        this.temTurbo = temTurbo;
    }

    public MotaDesportiva() {

    }


    public boolean isTemTurbo() {
        return temTurbo;
    }

    public void setTemTurbo(boolean temTurbo) {
        this.temTurbo = temTurbo;
    }

    @Override
    public String toString() {
        return super.toString() + ", temTurbo=" + temTurbo;
    }
}
