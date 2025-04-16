package concord.tarefa3.controllers;

import concord.tarefa3.models.Camiseta;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class CamisetaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea textResultado;

    private List<Camiseta> listaCamisetas = new ArrayList<>();

    @FXML
    public void criarCamiseta() {
        String cor = txtCor.getText();
        String tamanho = txtTamanho.getText();
        String tecido = txtTecido.getText();
        String marca = txtMarca.getText();

        if (cor.isEmpty() || tamanho.isEmpty() || tecido.isEmpty() || marca.isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        Camiseta camisetaAtual = new Camiseta(cor, tamanho, tecido, marca);
        listaCamisetas.add(camisetaAtual);  
        textResultado.setText("Camiseta criada:\n" + formatarCamiseta(camisetaAtual));
    }

    @FXML
    public void lavarCamiseta() {
        if (listaCamisetas.isEmpty()) {
            textResultado.setText("Nenhuma camiseta foi cadastrada ainda.");
            return;
        }

        
        Camiseta camisetaAtual = listaCamisetas.get(listaCamisetas.size() - 1);
        textResultado.setText("Camiseta lavada:\n" + formatarCamiseta(camisetaAtual));
    }

    @FXML
    public void vestirCamiseta() {
        if (listaCamisetas.isEmpty()) {
            textResultado.setText("Nenhuma camiseta foi cadastrada ainda.");
            return;
        }

        
        Camiseta camisetaAtual = listaCamisetas.get(listaCamisetas.size() - 1);
        textResultado.setText("Camiseta vestida:\n" + formatarCamiseta(camisetaAtual));
    }

    private String formatarCamiseta(Camiseta camiseta) {
        return "Cor: " + camiseta.getCor() + "\n" +
                "Tamanho: " + camiseta.getTamanho() + "\n" +
                "Tecido: " + camiseta.getTecido() + "\n" +
                "Marca: " + camiseta.getMarca();
    }
}
