package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

import core.Grade;

public class DashboardController extends Controller {

  private ObservableList<Grade> gradeCollection = FXCollections.observableArrayList();
  @FXML
  private ListView<Grade> gradeList;

  @FXML
  private void handleOpenNewCourse(ActionEvent event) {
    NewCourseController newCourse = new NewCourseController();
    openFXML(newCourse, "NewCourse.fxml");
  }

  @FXML
  private void handleOpenNewGrade(ActionEvent event) {
    NewGradeController newGrade = new NewGradeController();
    openFXML(newGrade, "NewGrade.fxml");
  }

  @FXML
  void handleLoggOut(ActionEvent event) throws FileNotFoundException {
    LogginController loggin = new LogginController();
    core.loggOut();
    openFXML(loggin, "Loggin.fxml");
  }

  void poppulateListView() {
    for (Grade grade : core.getActiveUser().getGrades()) {
      gradeCollection.add(grade);
    }
    gradeList.setItems(gradeCollection);
  }

  public void initClickActions() {
    // Detecting mouse clicked on ListView
    gradeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        if (gradeList.getSelectionModel().getSelectedItem() != null) {
          // System.out.println(gradeList.getSelectionModel().getSelectedItem().getCode());
          // Open gradeview :)
          OpenGrade(gradeList.getSelectionModel().getSelectedItem());
        }
      }
    });
  }

  private void OpenGrade(Grade g) {
    ViewGradeController viewGrade = new ViewGradeController();
    viewGrade.SendGrade(g);
    openFXML(viewGrade, "ViewGrade.fxml");
    // viewGrade.Poppulate();
  }

  @Override
  void Poppulate() {
    poppulateListView();
    initClickActions();
  }

}