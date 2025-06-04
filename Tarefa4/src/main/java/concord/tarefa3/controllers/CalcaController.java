package concord.tarefa3.controllers;

import concord.tarefa3.dao.CalcaDAO;
import concord.tarefa3.models.Calca;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalcaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Calca> tableCalcas;
    @FXML private TableColumn<Calca, Integer> colId;
    @FXML private TableColumn<Calca, String> colCor;
    @FXML private TableColumn<Calca, String> colTamanho;
    @FXML private TableColumn<Calca, String> colTecido;
    @FXML private TableColumn<Calca, String> colMarca;

    private CalcaDAO calcaDAO;
    private ObservableList<Calca> calcasList;

    @FXML
    public void initialize() {
        calcaDAO = new CalcaDAO();
        calcasList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableCalcas.setItems(calcasList);
        
        tableCalcas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarCalca() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Calca calca = new Calca();
            calca.setCor(txtCor.getText());
            calca.setTamanho(txtTamanho.getText());
            calca.setTecido(txtTecido.getText());
            calca.setMarca(txtMarca.getText());

            calcaDAO.criar(calca);
            mostrarMensagem("Calça criada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar calça: " + e.getMessage());
        }
    }

    @FXML
    private void listarCalcas() {
        atualizarLista();
        mostrarMensagem("Lista de calças atualizada!");
    }

    @FXML
    private void atualizarCalca() {
        Calca calcaSelecionada = tableCalcas.getSelectionModel().getSelectedItem();
        if (calcaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma calça para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            calcaSelecionada.setCor(txtCor.getText());
            calcaSelecionada.setTamanho(txtTamanho.getText());
            calcaSelecionada.setTecido(txtTecido.getText());
            calcaSelecionada.setMarca(txtMarca.getText());

            calcaDAO.atualizar(calcaSelecionada);
            mostrarMensagem("Calça atualizada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar calça: " + e.getMessage());
        }
    }

    @FXML
    private void deletarCalca() {
        Calca calcaSelecionada = tableCalcas.getSelectionModel().getSelectedItem();
        if (calcaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma calça para deletar.");
            return;
        }

        try {
            calcaDAO.deletar(calcaSelecionada.getId());
            mostrarMensagem("Calça deletada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar calça: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        calcasList.clear();
        calcasList.addAll(calcaDAO.listarTodos());
    }

    private void preencherCampos(Calca calca) {
        txtCor.setText(calca.getCor());
        txtTamanho.setText(calca.getTamanho());
        txtTecido.setText(calca.getTecido());
        txtMarca.setText(calca.getMarca());
        txtResultado.setText(formatarCalca(calca));
    }

    private String formatarCalca(Calca calca) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nTecido: %s\nMarca: %s",
            calca.getId(),
            calca.getCor(),
            calca.getTamanho(),
            calca.getTecido(),
            calca.getMarca()
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
        tableCalcas.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}
