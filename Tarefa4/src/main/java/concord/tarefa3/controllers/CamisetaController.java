package concord.tarefa3.controllers;

import concord.tarefa3.dao.CamisetaDAO;
import concord.tarefa3.models.Camiseta;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class CamisetaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Camiseta> tableCamisetas;
    @FXML private TableColumn<Camiseta, Integer> colId;
    @FXML private TableColumn<Camiseta, String> colCor;
    @FXML private TableColumn<Camiseta, String> colTamanho;
    @FXML private TableColumn<Camiseta, String> colTecido;
    @FXML private TableColumn<Camiseta, String> colMarca;
    
    private CamisetaDAO camisetaDAO;
    private ObservableList<Camiseta> camisetasList;
    
    @FXML
    public void initialize() {
        camisetaDAO = new CamisetaDAO();
        camisetasList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableCamisetas.setItems(camisetasList);
        
        tableCamisetas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }
    
    @FXML
    private void criarCamiseta() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Camiseta camiseta = new Camiseta();
            camiseta.setCor(txtCor.getText());
            camiseta.setTamanho(txtTamanho.getText());
            camiseta.setTecido(txtTecido.getText());
            camiseta.setMarca(txtMarca.getText());

            camisetaDAO.criar(camiseta);
            mostrarMensagem("Camiseta cadastrada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar camiseta: " + e.getMessage());
        }
    }
    
    @FXML
    private void listarCamisetas() {
        atualizarLista();
        mostrarMensagem("Lista de camisetas atualizada!");
    }

    @FXML
    private void atualizarCamiseta() {
        Camiseta camisetaSelecionada = tableCamisetas.getSelectionModel().getSelectedItem();
        if (camisetaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma camiseta para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            camisetaSelecionada.setCor(txtCor.getText());
            camisetaSelecionada.setTamanho(txtTamanho.getText());
            camisetaSelecionada.setTecido(txtTecido.getText());
            camisetaSelecionada.setMarca(txtMarca.getText());

            camisetaDAO.atualizar(camisetaSelecionada);
            mostrarMensagem("Camiseta atualizada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar camiseta: " + e.getMessage());
        }
    }

    @FXML
    private void deletarCamiseta() {
        Camiseta camisetaSelecionada = tableCamisetas.getSelectionModel().getSelectedItem();
        if (camisetaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma camiseta para deletar.");
            return;
        }

        try {
            camisetaDAO.deletar(camisetaSelecionada.getId());
            mostrarMensagem("Camiseta deletada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar camiseta: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        camisetasList.clear();
        camisetasList.addAll(camisetaDAO.listarTodos());
    }

    private void preencherCampos(Camiseta camiseta) {
        txtCor.setText(camiseta.getCor());
        txtTamanho.setText(camiseta.getTamanho());
        txtTecido.setText(camiseta.getTecido());
        txtMarca.setText(camiseta.getMarca());
        txtResultado.setText(formatarCamiseta(camiseta));
    }

    private String formatarCamiseta(Camiseta camiseta) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nTecido: %s\nMarca: %s",
            camiseta.getId(),
            camiseta.getCor(),
            camiseta.getTamanho(),
            camiseta.getTecido(),
            camiseta.getMarca()
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtTamanho.getText().isEmpty() ||
               txtTecido.getText().isEmpty() ||
               txtMarca.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtTamanho.clear();
        txtTecido.clear();
        txtMarca.clear();
        txtResultado.clear();
        tableCamisetas.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}
