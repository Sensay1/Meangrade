package ui;

import core.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

public class LogginController extends Controller {

  @FXML
  public TextField usernameTextField;

  @FXML
  public TextField passwordTextField;

  @FXML
  public TextField passwordRepeatField;

  @FXML
  private Button btreg;

  @FXML
  private Button btlog;

  @FXML
  public Label userstar;

  @FXML
  public Label passtar;

  @FXML
  public Label repasstar;

  @FXML
  private Label usermsg;

  @FXML
  private Label pasmsg;

  @FXML
  private Label repasmsg;

  @FXML
  private ImageView img;

  @FXML
  void handleBtnReg(ActionEvent e) {
    Validator validator = click();

    if (core.getuser(usernameTextField.getText()) == null) {
      if (validator.register()) {
        // Creates the new profile before logginn:
        core.newProfile(usernameTextField.getText(), passwordTextField.getText());
        core.logginn(usernameTextField.getText(), passwordTextField.getText());
        openDash();
      } else {
        show(validator);
      }
    } else {
      usermsg.setText("Username is already taken!");
    }
  }

  @FXML
  void handleBtnLog(ActionEvent e) {
    Validator validator = click();

    if (validator.logginn()) {
      if (core.logginn(usernameTextField.getText(), passwordTextField.getText())) {
        openDash();
      } else {
        usermsg.setText("Username or passowrd wrong!");
      }
    } else {
      show(validator);
    }
  }

  Validator click() {
    clear();
    return new Validator(usernameTextField.getText(), passwordTextField.getText(),
        passwordRepeatField.getText());
  }

  void openDash() {
    DashboardController dash = new DashboardController();
    openFXML(dash, "Dashboard.fxml");
    // dash.poppulateListView();
    // dash.initClickActions();
  }

  void clear() {
    Validator clear = new Validator("", "", "");
    show(clear);
  }

  void show(Validator v) {
    // Error messages:
    String[] msgArr = v.getMsg();
    usermsg.setText(msgArr[0]);
    pasmsg.setText(msgArr[1]);
    repasmsg.setText(msgArr[2]);
    // Error stars:
    userstar.setVisible(usermsg.getText() != "");
    passtar.setVisible(pasmsg.getText() != "");
    repasstar.setVisible(repasmsg.getText() != "");
  }

  @Override
  void Poppulate() {
  }
}
