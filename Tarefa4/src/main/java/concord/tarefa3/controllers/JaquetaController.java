package concord.tarefa3.controllers;

import concord.tarefa3.dao.JaquetaDAO;
import concord.tarefa3.models.Jaqueta;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class JaquetaController {
    @FXML private TextField txtCor;
    @FXML private TextField txtTamanho;
    @FXML private TextField txtTecido;
    @FXML private TextField txtMarca;
    @FXML private TextArea txtResultado;
    @FXML private TableView<Jaqueta> tableJaquetas;
    @FXML private TableColumn<Jaqueta, Integer> colId;
    @FXML private TableColumn<Jaqueta, String> colCor;
    @FXML private TableColumn<Jaqueta, String> colTamanho;
    @FXML private TableColumn<Jaqueta, String> colTecido;
    @FXML private TableColumn<Jaqueta, String> colMarca;
    
    private JaquetaDAO jaquetaDAO;
    private ObservableList<Jaqueta> jaquetasList;
    
    @FXML
    public void initialize() {
        jaquetaDAO = new JaquetaDAO();
        jaquetasList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colTamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colTecido.setCellValueFactory(new PropertyValueFactory<>("tecido"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        tableJaquetas.setItems(jaquetasList);
        
        tableJaquetas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                preencherCampos(newSelection);
            }
        });
        
        atualizarLista();
    }
    
    @FXML
    private void criarJaqueta() {
        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            Jaqueta jaqueta = new Jaqueta();
            jaqueta.setCor(txtCor.getText());
            jaqueta.setTamanho(txtTamanho.getText());
            jaqueta.setTecido(txtTecido.getText());
            jaqueta.setMarca(txtMarca.getText());

            jaquetaDAO.criar(jaqueta);
            mostrarMensagem("Jaqueta cadastrada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao criar jaqueta: " + e.getMessage());
        }
    }
    
    @FXML
    private void listarJaquetas() {
        atualizarLista();
        mostrarMensagem("Lista de jaquetas atualizada!");
    }

    @FXML
    private void atualizarJaqueta() {
        Jaqueta jaquetaSelecionada = tableJaquetas.getSelectionModel().getSelectedItem();
        if (jaquetaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma jaqueta para atualizar.");
            return;
        }

        if (camposVazios()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        try {
            jaquetaSelecionada.setCor(txtCor.getText());
            jaquetaSelecionada.setTamanho(txtTamanho.getText());
            jaquetaSelecionada.setTecido(txtTecido.getText());
            jaquetaSelecionada.setMarca(txtMarca.getText());

            jaquetaDAO.atualizar(jaquetaSelecionada);
            mostrarMensagem("Jaqueta atualizada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao atualizar jaqueta: " + e.getMessage());
        }
    }

    @FXML
    private void deletarJaqueta() {
        Jaqueta jaquetaSelecionada = tableJaquetas.getSelectionModel().getSelectedItem();
        if (jaquetaSelecionada == null) {
            mostrarMensagem("Por favor, selecione uma jaqueta para deletar.");
            return;
        }

        try {
            jaquetaDAO.deletar(jaquetaSelecionada.getId());
            mostrarMensagem("Jaqueta deletada com sucesso!");
            limparCampos();
            atualizarLista();
        } catch (Exception e) {
            mostrarMensagem("Erro ao deletar jaqueta: " + e.getMessage());
        }
    }

    private void atualizarLista() {
        jaquetasList.clear();
        jaquetasList.addAll(jaquetaDAO.listarTodos());
    }

    private void preencherCampos(Jaqueta jaqueta) {
        txtCor.setText(jaqueta.getCor());
        txtTamanho.setText(jaqueta.getTamanho());
        txtTecido.setText(jaqueta.getTecido());
        txtMarca.setText(jaqueta.getMarca());
        txtResultado.setText(formatarJaqueta(jaqueta));
    }

    private String formatarJaqueta(Jaqueta jaqueta) {
        return String.format("ID: %d\nCor: %s\nTamanho: %s\nTecido: %s\nMarca: %s",
            jaqueta.getId(),
            jaqueta.getCor(),
            jaqueta.getTamanho(),
            jaqueta.getTecido(),
            jaqueta.getMarca()
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
        tableJaquetas.getSelectionModel().clearSelection();
    }

    private void mostrarMensagem(String mensagem) {
        txtResultado.setText(mensagem);
    }
}
