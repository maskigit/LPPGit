import java.io.Serializable;

public class RegitoAluguer implements Serializable {
    private Veiculo veiculo;
    private String cliente;
    private String dataAluguel;
    //Era do tipo date mas devido a um erro agora dificil de corrigir mudei
    private String dataDevolucao;

    public void setDataAluguel(String dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public RegitoAluguer(Veiculo veiculo, String cliente, String dataAluguel, String dataDevolucao) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDataAluguel() {
        return dataAluguel;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String date) {
        this.dataDevolucao=date;

    }
}

