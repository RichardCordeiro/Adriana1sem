package concord.tarefa3.controllers;

import concord.tarefa3.models.Bone;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class BoneController {
    @FXML private TextField txtCor;
    @FXML private TextField txtEstilo;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtMarca;
    @FXML private TextArea textResultado;

    private List<Bone> listaBones = new ArrayList<>();

    @FXML
    public void criarBone() {
        String cor = txtCor.getText();
        String estilo = txtEstilo.getText();
        String tamanho = txtTamanho.getText();
        String marca = txtMarca.getText();

        if (cor.isEmpty() || estilo.isEmpty() || tamanho.isEmpty() || marca.isEmpty()) {
            textResultado.setText("Todos os campos devem ser preenchidos.");
            return;
        }

        Bone boneAtual = new Bone(cor, estilo, tamanho, marca);
        listaBones.add(boneAtual); 

        textResultado.setText("Boné cadastrado:\n" + formatarBone(boneAtual));
    }

    @FXML
    public void lavarBone() {
        if (listaBones.isEmpty()) {
            textResultado.setText("Nenhum boné foi cadastrado ainda.");
            return;
        }

    
        Bone boneAtual = listaBones.get(listaBones.size() - 1);
        textResultado.setText("Boné lavado:\n" + formatarBone(boneAtual));
    }

    @FXML
    public void vestirBone() {
        if (listaBones.isEmpty()) {
            textResultado.setText("Nenhum boné foi cadastrado ainda.");
            return;
        }

        Bone boneAtual = listaBones.get(listaBones.size() - 1);
        textResultado.setText("Boné vestido:\n" + formatarBone(boneAtual));
    }

    private String formatarBone(Bone bone) {
        return "Cor: " + bone.getCor() + "\n" +
                "Estilo: " + bone.getEstilo() + "\n" +
                "Tamanho: " + bone.getTamanho() + "\n" +
                "Marca: " + bone.getMarca();
    }
}
