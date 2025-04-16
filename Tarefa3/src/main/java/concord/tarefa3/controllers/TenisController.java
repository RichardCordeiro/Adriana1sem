package concord.tarefa3.controllers;

import concord.tarefa3.models.Tenis;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class TenisController {
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtTamanho;
    @FXML
    private TextField txtCor;
    @FXML
    private CheckBox chkEsportivo;
    @FXML
    private TextArea textResultado;

    private Tenis tenisAtual;
    private ArrayList<Tenis> listaDeTenis = new ArrayList<>(); 

    @FXML
    public void criarTenis() {
        if (txtMarca.getText().isEmpty() || txtTamanho.getText().isEmpty() || txtCor.getText().isEmpty()) {
            textResultado.setText("Preencha todos os campos.");
            return;
        }

        try {
            int tamanho = Integer.parseInt(txtTamanho.getText());
            tenisAtual = new Tenis(txtMarca.getText(), tamanho, txtCor.getText(), chkEsportivo.isSelected());
            listaDeTenis.add(tenisAtual); 
            textResultado.setText("Tênis criado:\n" + formatarTenis(tenisAtual));
        } catch (NumberFormatException e) {
            textResultado.setText("Digite um número válido para o tamanho.");
        }
    }

    @FXML
    public void limparTenis() {
        if (tenisAtual == null) {
            textResultado.setText("Nenhum tênis foi criado ainda para limpar.");
            return;
        }

        
        tenisAtual = null;
        textResultado.setText("Tênis limpo. Todos os dados foram removidos.");
    }

    @FXML
    public void calcarTenis() {
        if (tenisAtual == null) {
            textResultado.setText("Nenhum tênis foi criado ainda.");
            return;
        }

        textResultado.setText("Tênis calçado:\n" + formatarTenis(tenisAtual));
    }

    private String formatarTenis(Tenis tenis) {
        return "Marca: " + tenis.getMarca() + "\n" +
                "Tamanho: " + tenis.getTamanho() + "\n" +
                "Cor: " + tenis.getCor() + "\n" +
                "Esportivo: " + (tenis.isEsportivo() ? "Sim" : "Não");
    }
}
