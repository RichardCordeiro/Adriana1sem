package concord.tarefa3.controllers;

import concord.tarefa3.models.Calca;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class CalcaController {
    @FXML private TextField txtTipo;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtCor;
    @FXML private TextField txtTecido;
    @FXML private TextArea textResultado;

    private List<Calca> listaCalcas = new ArrayList<>();

    @FXML
    public void criarCalca() {
        String tipo = txtTipo.getText();
        String tamanho = txtTamanho.getText();
        String cor = txtCor.getText();
        String tecido = txtTecido.getText();

        if (tipo.isEmpty() || tamanho.isEmpty() || cor.isEmpty() || tecido.isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Calca calcaAtual = new Calca(tipo, tamanho, cor, tecido);
        listaCalcas.add(calcaAtual);  
        textResultado.setText("Calça criada:\n" + formatarCalca(calcaAtual));
    }

    @FXML
    public void dobrarCalca() {
        if (listaCalcas.isEmpty()) {
            textResultado.setText("Nenhuma calça foi criada ainda.");
            return;
        }

        
        Calca calcaAtual = listaCalcas.get(listaCalcas.size() - 1);
        textResultado.setText("Calça dobrada:\n" + formatarCalca(calcaAtual));
    }

    @FXML
    public void vestirCalca() {
        if (listaCalcas.isEmpty()) {
            textResultado.setText("Nenhuma calça foi criada ainda.");
            return;
        }

        
        Calca calcaAtual = listaCalcas.get(listaCalcas.size() - 1);
        textResultado.setText("Calça vestida:\n" + formatarCalca(calcaAtual));
    }

    private String formatarCalca(Calca calca) {
        return "Tipo: " + calca.getTipo() + "\n" +
                "Tamanho: " + calca.getTamanho() + "\n" +
                "Cor: " + calca.getCor() + "\n" +
                "Tecido: " + calca.getTecido();
    }
}
