package concord.tarefa3.controllers;

import concord.tarefa3.models.Mochila;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class MochilaController {
    @FXML private TextField txtMarca;
    @FXML private TextField txtLitros;
    @FXML private TextField txtCor;
    @FXML private CheckBox chkCompartimentoNotebook;
    @FXML private TextArea textResultado;

    private List<Mochila> listaMochilas = new ArrayList<>();

    @FXML
    public void criarMochila() {
        if (txtMarca.getText().isEmpty() || txtLitros.getText().isEmpty() || txtCor.getText().isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        try {
            int litros = Integer.parseInt(txtLitros.getText());
            Mochila mochilaAtual = new Mochila(txtMarca.getText(), litros, txtCor.getText(), chkCompartimentoNotebook.isSelected());
            listaMochilas.add(mochilaAtual);  
            textResultado.setText("Mochila criada:\n" + formatarMochila(mochilaAtual));
        } catch (NumberFormatException e) {
            textResultado.setText("Digite um número válido para litros.");
        }
    }

    @FXML
    public void abrirMochila() {
        if (listaMochilas.isEmpty()) {
            textResultado.setText("Nenhuma mochila foi criada ainda.");
            return;
        }

        
        Mochila mochilaAtual = listaMochilas.get(listaMochilas.size() - 1);
        textResultado.setText("Mochila aberta:\n" + formatarMochila(mochilaAtual));
    }

    @FXML
    public void carregarMochila() {
        if (listaMochilas.isEmpty()) {
            textResultado.setText("Nenhuma mochila foi criada ainda.");
            return;
        }

        
        Mochila mochilaAtual = listaMochilas.get(listaMochilas.size() - 1);
        textResultado.setText("Mochila carregada:\n" + formatarMochila(mochilaAtual));
    }

    private String formatarMochila(Mochila mochila) {
        return "Marca: " + mochila.getMarca() + "\n" +
                "Capacidade: " + mochila.getLitros() + "L\n" +
                "Cor: " + mochila.getCor() + "\n" +
                "Compartimento para notebook: " + (mochila.isTemCompartimentoNotebook() ? "Sim" : "Não");
    }
}
