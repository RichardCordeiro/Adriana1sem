package concord.tarefa3.controllers;

import concord.tarefa3.dao.TenisDAO;
import concord.tarefa3.models.Tenis;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class TenisController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtMaterial;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Tenis> tableTenis;
    @FXML private TableColumn<Tenis, Integer> colId;
    @FXML private TableColumn<Tenis, String> colCor;
    @FXML private TableColumn<Tenis, String> colTamanho;
    @FXML private TableColumn<Tenis, String> colMaterial;
    @FXML private TableColumn<Tenis, String> colMarca;

    private TenisDAO tenisDAO;
    private ObservableList<Tenis> tenisList;

    @FXML
    public void initialize() {
        tenisDAO = new TenisDAO();
        tenisList = FXCollections.observableArrayList();
        
        // Configurar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableTenis.setItems(tenisList);
        
        tableTenis.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarTenis() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Tenis tenis = new Tenis();
            tenis.setCor(txtCor.getText());
            tenis.setTamanho(txtTamanho.getText());
            tenis.setMaterial(txtMaterial.getText());
            tenis.setMarca(txtMarca.getText());

            tenisDAO.criar(tenis);
            mostrarMensagem("Tênis criado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar tênis: " + e.getMessage());
        }
    }

    @FXML
    private void listarTenis() {
        atualizarLista();
        mostrarMensagem("Lista de tênis atualizada!");
    }

    @FXML
    private void atualizarTenis() {
        Tenis tenisSelecionado = tableTenis.getSelectionModel().getSelectedItem();
        if (tenisSelecionado == null) {
            mostrarMensagem("Por favor, selecione um tênis para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            tenisSelecionado.setCor(txtCor.getText());
            tenisSelecionado.setTamanho(txtTamanho.getText());
            tenisSelecionado.setMaterial(txtMaterial.getText());
            tenisSelecionado.setMarca(txtMarca.getText());

            tenisDAO.atualizar(tenisSelecionado);
            mostrarMensagem("Tênis atualizado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar tênis: " + e.getMessage());
        }
    }

    @FXML
    private void deletarTenis() {
        Tenis tenisSelecionado = tableTenis.getSelectionModel().getSelectedItem();
        if (tenisSelecionado == null) {
            mostrarMensagem("Por favor, selecione um tênis para deletar.");
            return;
        }

        try {
            tenisDAO.deletar(tenisSelecionado.getId());
            mostrarMensagem("Tênis deletado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar tênis: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        tenisList.clear();
        tenisList.addAll(tenisDAO.listarTodos());
    }

    private void preencherCampos(Tenis tenis) {
        txtCor.setText(tenis.getCor());
        txtTamanho.setText(tenis.getTamanho());
        txtMaterial.setText(tenis.getMaterial());
        txtMarca.setText(tenis.getMarca());
        txtResultado.setText(formatarTenis(tenis));
    }

    private String formatarTenis(Tenis tenis) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nMaterial: %s\nMarca: %s",
            tenis.getId(),
            tenis.getCor(),
            tenis.getTamanho(),
            tenis.getMaterial(),
            tenis.getMarca()
        );
    }

    private boolean camposVazios() {
        return txtCor.getText().isEmpty() ||
               txtTamanho.getText().isEmpty() ||
               txtMaterial.getText().isEmpty() ||
               txtMarca.getText().isEmpty();
    }

    private void limparCampos() {
        txtCor.clear();
        txtTamanho.clear();
        txtMaterial.clear();
        txtMarca.clear();
        txtResultado.clear();
        tableTenis.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}

