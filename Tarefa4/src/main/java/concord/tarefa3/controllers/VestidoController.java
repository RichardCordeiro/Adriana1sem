package concord.tarefa3.controllers;

import concord.tarefa3.dao.VestidoDAO;
import concord.tarefa3.models.Vestido;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class VestidoController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Vestido> tableVestidos;
    @FXML private TableColumn<Vestido, Integer> colId;
    @FXML private TableColumn<Vestido, String> colCor;
    @FXML private TableColumn<Vestido, String> colTamanho;
    @FXML private TableColumn<Vestido, String> colTecido;
    @FXML private TableColumn<Vestido, String> colMarca;

    private VestidoDAO vestidoDAO;
    private ObservableList<Vestido> vestidosList;

    @FXML
    public void initialize() {
        vestidoDAO = new VestidoDAO();
        vestidosList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableVestidos.setItems(vestidosList);
        
        tableVestidos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }

    @FXML
    private void criarVestido() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Vestido vestido = new Vestido();
            vestido.setCor(txtCor.getText());
            vestido.setTamanho(txtTamanho.getText());
            vestido.setTecido(txtTecido.getText());
            vestido.setMarca(txtMarca.getText());

            vestidoDAO.criar(vestido);
            mostrarMensagem("Vestido criado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar vestido: " + e.getMessage());
        }
    }

    @FXML
    private void listarVestidos() {
        atualizarLista();
        mostrarMensagem("Lista de vestidos atualizada!");
    }

    @FXML
    private void atualizarVestido() {
        Vestido vestidoSelecionado = tableVestidos.getSelectionModel().getSelectedItem();
        if (vestidoSelecionado == null) {
            mostrarMensagem("Por favor, selecione um vestido para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            vestidoSelecionado.setCor(txtCor.getText());
            vestidoSelecionado.setTamanho(txtTamanho.getText());
            vestidoSelecionado.setTecido(txtTecido.getText());
            vestidoSelecionado.setMarca(txtMarca.getText());

            vestidoDAO.atualizar(vestidoSelecionado);
            mostrarMensagem("Vestido atualizado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar vestido: " + e.getMessage());
        }
    }

    @FXML
    private void deletarVestido() {
        Vestido vestidoSelecionado = tableVestidos.getSelectionModel().getSelectedItem();
        if (vestidoSelecionado == null) {
            mostrarMensagem("Por favor, selecione um vestido para deletar.");
            return;
        }

        try {
            vestidoDAO.deletar(vestidoSelecionado.getId());
            mostrarMensagem("Vestido deletado com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar vestido: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        vestidosList.clear();
        vestidosList.addAll(vestidoDAO.listarTodos());
    }

    private void preencherCampos(Vestido vestido) {
        txtCor.setText(vestido.getCor());
        txtTamanho.setText(vestido.getTamanho());
        txtTecido.setText(vestido.getTecido());
        txtMarca.setText(vestido.getMarca());
        txtResultado.setText(formatarVestido(vestido));
    }

    private String formatarVestido(Vestido vestido) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nTecido: %s\nMarca: %s",
            vestido.getId(),
            vestido.getCor(),
            vestido.getTamanho(),
            vestido.getTecido(),
            vestido.getMarca()
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
        tableVestidos.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}
