package concord.tarefa3.controllers;

import concord.tarefa3.models.Chapeu;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class ChapeuController {
    @FXML private TextField txtTipo;
    @FXML private TextField txtCor;
    @FXML private TextField txtMaterial;
    @FXML private TextField txtTamanho;
    @FXML private TextArea textResultado;

    private List<Chapeu> listaChapeus = new ArrayList<>();

    @FXML
    public void criarChapeu() {
        String tipo = txtTipo.getText();
        String cor = txtCor.getText();
        String material = txtMaterial.getText();
        String tamanho = txtTamanho.getText();

        if (tipo.isEmpty() || cor.isEmpty() || material.isEmpty() || tamanho.isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Chapeu chapeuAtual = new Chapeu(tipo, cor, material, tamanho);
        listaChapeus.add(chapeuAtual);  
        textResultado.setText("Chapéu criado:\n" + formatarChapeu(chapeuAtual));
    }

    @FXML
    public void guardarChapeu() {
        if (listaChapeus.isEmpty()) {
            textResultado.setText("Nenhum chapéu foi cadastrado ainda.");
            return;
        }

        
        Chapeu chapeuAtual = listaChapeus.get(listaChapeus.size() - 1);
        textResultado.setText("Chapéu guardado:\n" + formatarChapeu(chapeuAtual));
    }

    @FXML
    public void vestirChapeu() {
        if (listaChapeus.isEmpty()) {
            textResultado.setText("Nenhum chapéu foi cadastrado ainda.");
            return;
        }

        
        Chapeu chapeuAtual = listaChapeus.get(listaChapeus.size() - 1);
        textResultado.setText("Chapéu vestido:\n" + formatarChapeu(chapeuAtual));
    }

    private String formatarChapeu(Chapeu chapeu) {
        return "Tipo: " + chapeu.getTipo() + "\n" +
                "Cor: " + chapeu.getCor() + "\n" +
                "Material: " + chapeu.getMaterial() + "\n" +
                "Tamanho: " + chapeu.getTamanho();
    }
}
