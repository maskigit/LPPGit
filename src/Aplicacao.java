import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Aplicacao {
    private JFrame frame;
    private JButton adicionarVeiculoButton;
    private JButton verVeiculosButton;
    private JButton alugarVeiculoButton;
    private List<Veiculo> armazem;

    private List<Veiculo> veiculosAlugados;
    private  JButton devolverVeiculoButton;
    private JButton historicoVeiculosButton;
    private List<RegitoAluguer> registosAluguer;
    private JButton guardarDadosButton;
    private JButton carregarDadosButton;
    public Aplicacao() {
        armazem = new ArrayList<>();
        veiculosAlugados = new ArrayList<>();
        registosAluguer = new ArrayList<>();


        frame = new JFrame("Minha Aplicação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        adicionarVeiculoButton = new JButton("Adicionar Veículo");
        adicionarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoAdicionarVeiculo();
            }
        });

        alugarVeiculoButton = new JButton("Alugar Veículo");
        alugarVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alugarVeiculo();
            }
        });

        frame.add(alugarVeiculoButton);

        frame.add(adicionarVeiculoButton);
        frame.setVisible(true);

        verVeiculosButton = new JButton("Ver Veículos");
        verVeiculosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirVeiculosArmazenados();
            }
        });

        frame.add(verVeiculosButton);

        devolverVeiculoButton = new JButton("Devolver Veículo");
        devolverVeiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverVeiculo();
            }
        });

        frame.add(devolverVeiculoButton);

        historicoVeiculosButton = new JButton("Histórico de Veículos Alugados");
        historicoVeiculosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirHistoricoVeiculosAlugados();
            }
        });

        frame.add(historicoVeiculosButton);
        guardarDadosButton = new JButton("Guardar Dados");
        guardarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDados();
            }
        });
        frame.add(guardarDadosButton);

        carregarDadosButton = new JButton("Carregar Dados");
        carregarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });
        frame.add(carregarDadosButton);
    }



    private void guardarDados() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Dados");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheiros Bitcode (*.bit)", "bit"));
        int userSelection = fileChooser.showSaveDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(armazem);
                objectOutputStream.writeObject(veiculosAlugados);
                objectOutputStream.writeObject(registosAluguer);
                JOptionPane.showMessageDialog(frame, "Dados guardados com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao guardar os dados: " + ex.getMessage());
            }
        }
    }

    private void carregarDados() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar Dados");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Ficheiros Bitcode (*.bit)", "bit"));
        int userSelection = fileChooser.showOpenDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (FileInputStream fileInputStream = new FileInputStream(fileToOpen);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                armazem = (List<Veiculo>) objectInputStream.readObject();
                veiculosAlugados = (List<Veiculo>) objectInputStream.readObject();
                registosAluguer = (List<RegitoAluguer>) objectInputStream.readObject();
                JOptionPane.showMessageDialog(frame, "Dados carregados com sucesso!");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao carregar os dados: " + ex.getMessage());
            }
        }
    }
    private void exibirHistoricoVeiculosAlugados() {
        if (registosAluguer.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há veículos alugados para mostrar o histórico.");
            return;
        }

        StringBuilder mensagem = new StringBuilder("Histórico de Veículos Alugados:\n");
        for (RegitoAluguer registro : registosAluguer) {
            mensagem.append("Veículo: ").append(registro.getVeiculo().toString()).append("\n");
            mensagem.append("Cliente: ").append(registro.getCliente()).append("\n");
            mensagem.append("Data de Aluguer: ");
            if (registro.getDataAluguel() != null) {
                mensagem.append(registro.getDataAluguel()).append("\n");
            } else {
                mensagem.append("Sem dados de inicio de aluguer \n");
            }
            mensagem.append("Data de Devolução: ");

            if (registro.getDataDevolucao() != null) {
                mensagem.append(registro.getDataDevolucao());
            } else {
                mensagem.append("Em uso");
            }

            mensagem.append("\n\n");
        }
        JOptionPane.showMessageDialog(frame, mensagem.toString());
    }

    private void devolverVeiculo() {
        if (veiculosAlugados.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há veículos alugados para devolver.");
            return;
        }


        Veiculo veiculoSelecionado = (Veiculo) JOptionPane.showInputDialog(frame, "Selecione um veículo para devolver:",
                "Devolver Veículo", JOptionPane.PLAIN_MESSAGE, null, veiculosAlugados.toArray(), veiculosAlugados.get(0));


        if (veiculoSelecionado != null) {
            String dataDevolucao = JOptionPane.showInputDialog(frame, "Digite a data de devolução:");
            //Confirmar se falta ler dados do ecrã
            //veiculoSelecionado.setDataDevolucao(new Date());
            veiculosAlugados.remove(veiculoSelecionado);  // Remove o veículo da lista de veículos alugados

            armazem.add(veiculoSelecionado);  // Adiciona o veículo de volta à lista de armazém

            for (RegitoAluguer registo : registosAluguer) {
                if ( registo.getDataDevolucao() == null) {
                    registo.setDataDevolucao(dataDevolucao);
                    break;
                }
            }

            JOptionPane.showMessageDialog(frame, "Veículo devolvido com sucesso!");
        }
    }


    private void alugarVeiculo() {
        if (armazem.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Não há veículos disponíveis para alugar.");
            return;
        }

        Veiculo veiculoSelecionado = (Veiculo) JOptionPane.showInputDialog(frame, "Selecione um veículo para alugar:",
                "Alugar Veículo", JOptionPane.PLAIN_MESSAGE, null, armazem.toArray(), armazem.get(0));

        if (veiculoSelecionado != null) {
            String nomeCliente = JOptionPane.showInputDialog(frame, "Digite o nome do cliente:");
            String datainicio = JOptionPane.showInputDialog(frame, "Digite a data do inicio de aluguer :");

            RegitoAluguer valugado = new RegitoAluguer(veiculoSelecionado,nomeCliente,datainicio, null);

            armazem.remove(veiculoSelecionado);  // Remove o veículo da lista de armazém

            veiculosAlugados.add(veiculoSelecionado);  // Adiciona o veículo à lista de veículos alugados

            RegitoAluguer registro = new RegitoAluguer(veiculoSelecionado, nomeCliente, datainicio, null);
            registosAluguer.add(registro);

            JOptionPane.showMessageDialog(frame, "Veículo alugado com sucesso!");
        }
    }

    private void exibirVeiculosArmazenados() {
        StringBuilder mensagem = new StringBuilder("Veículos Armazenados:\n");
        for (Veiculo veiculo : armazem) {
            mensagem.append(veiculo.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, mensagem.toString());
    }



    private void abrirDialogoAdicionarVeiculo() {
        String[] opcoes = {"Carro", "Mota", "Mota Desportiva"};
        String tipoSelecionado = (String) JOptionPane.showInputDialog(frame, "Selecione o tipo de veículo:",
                "Adicionar Veículo", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (tipoSelecionado != null) {
            switch (tipoSelecionado) {
                case "Carro":
                    adicionarCarro();
                    break;
                case "Mota":
                    adicionarMota();
                    break;
                case "Mota Desportiva":
                    adicionarMotaDesportiva();
                    break;
                default:
                    break;
            }
        }
    }

    private void adicionarCarro() {
        String marca = JOptionPane.showInputDialog(frame, "Marca do Carro:");
        String modelo = JOptionPane.showInputDialog(frame, "Modelo do Carro:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ano do Carro:"));
        int numPortas = Integer.parseInt(JOptionPane.showInputDialog(frame, "Número de Portas do Carro:"));

        Carro carro = new Carro(marca, modelo, ano, numPortas);
        armazem.add(carro);
    }

    private void adicionarMota() {
        String marca = JOptionPane.showInputDialog(frame, "Marca da Mota:");
        String modelo = JOptionPane.showInputDialog(frame, "Modelo da Mota:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ano da Mota:"));
        String tipo = JOptionPane.showInputDialog(frame, "Tipo da Mota:");

        Mota mota = new Mota(marca, modelo, ano, tipo);
        armazem.add(mota);
    }

    private void adicionarMotaDesportiva() {
        String marca = JOptionPane.showInputDialog(frame, "Marca da Mota Desportiva:");
        String modelo = JOptionPane.showInputDialog(frame, "Modelo da Mota Desportiva:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ano da Mota Desportiva:"));
        String tipo = JOptionPane.showInputDialog(frame, "Tipo da Mota Desportiva:");
        boolean temTurbo = Boolean.parseBoolean(JOptionPane.showInputDialog(frame, "Tem Turbo (true/false):"));

        MotaDesportiva motaDesportiva = new MotaDesportiva(marca, modelo, ano, tipo, temTurbo);
        armazem.add(motaDesportiva);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Aplicacao();
            }
        });
    }
}