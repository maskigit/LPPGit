import java.io.Serializable;
import java.util.Date;

public class Veiculo implements Serializable {
    private String marca;
    private String modelo;
    private int ano;
    private String clienteAluguel;
    private Date dataAluguel;
    private String dataDevolucao;

    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }


    public Veiculo(String marca, String modelo, int ano, String clienteAluguel, Date dataAluguel, String dataDevolucao) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.clienteAluguel = clienteAluguel;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }
    public String getInformacoes() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + ano;
    }


    public String getClienteAluguel() {
        return clienteAluguel;
    }

    public void setClienteAluguel(String clienteAluguel) {
        this.clienteAluguel = clienteAluguel;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Veiculo (){

    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Veiculo [marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Veiculo other = (Veiculo) obj;
        return marca.equals(other.marca) && modelo.equals(other.modelo) && ano == other.ano;
    }

    @Override
    public int hashCode() {
        int result = marca.hashCode();
        result = 31 * result + modelo.hashCode();
        result = 31 * result + ano;
        return result;
    }

}
