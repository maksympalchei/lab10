package lab10;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lab10 extends Application {
    private Stage currentStage;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-alignment: center;");
        Button btn1 = new Button("Розклад на понеділок");
        btn1.setOnAction(e -> openWindow(MondayWindow.getInstance()));

        Button btn2 = new Button("Розклад на вівторок");
        btn2.setOnAction(e -> openWindow(TuesdayWindow.getInstance()));

        Button btn3 = new Button("Розклад на середу");
        btn3.setOnAction(e -> openWindow(WednesdayWindow.getInstance()));

        Button btn4 = new Button("Розклад на четвер");
        btn4.setOnAction(e -> openWindow(ThursdayWindow.getInstance()));

        Button btn5 = new Button("Розклад на п'ятницю");
        btn5.setOnAction(e -> openWindow(FridayWindow.getInstance()));

        root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Головне вікно");
        primaryStage.show();
    }

    private void openWindow(ScheduleWindow window) {
        if (currentStage != null && currentStage.isShowing() && currentStage != window.getStage()) {
            currentStage.close();
        }
        currentStage = window.getStage();
        currentStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static class ScheduleEntry {
        private String time;
        private String activity;

        public ScheduleEntry(String time, String activity) {
            this.time = time;
            this.activity = activity;
        }

        public String getTime() {
            return time;
        }

        public String getActivity() {
            return activity;
        }
    }
    public static class ScheduleWindow {
        protected Stage stage;
        protected TableView<ScheduleEntry> tableView;

        protected ScheduleWindow(String title) {
            stage = new Stage();
            stage.setTitle(title);

            tableView = new TableView<>();
            TableColumn<ScheduleEntry, String> timeColumn = new TableColumn<>("Час");
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

            TableColumn<ScheduleEntry, String> activityColumn = new TableColumn<>("Заняття");
            activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

            tableView.getColumns().add(timeColumn);
            tableView.getColumns().add(activityColumn);

            StackPane root = new StackPane();
            root.getChildren().add(tableView);

            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);
        }

        protected void setScheduleData(ObservableList<ScheduleEntry> data) {
            tableView.setItems(data);
        }

        public Stage getStage() {
            return stage;
        }
    }

    public static class MondayWindow extends ScheduleWindow {
        private static MondayWindow instance;

        private MondayWindow() {
            super("Розклад на понеділок");
            setScheduleData(FXCollections.observableArrayList(
                new ScheduleEntry("08:00", "Зустріч"),
                new ScheduleEntry("10:00", "Робота"),
                new ScheduleEntry("12:00", "Обід"),
                new ScheduleEntry("14:00", "Обговорення проекту"),
                new ScheduleEntry("16:00", "Огляд")
            ));
        }

        public static MondayWindow getInstance() {
            if (instance == null) {
                instance = new MondayWindow();
            }
            return instance;
        }
    }

    public static class TuesdayWindow extends ScheduleWindow {
        private static TuesdayWindow instance;

        private TuesdayWindow() {
            super("Розклад на вівторок");
            setScheduleData(FXCollections.observableArrayList(
                new ScheduleEntry("08:00", "Воркшоп"),
                new ScheduleEntry("10:00", "Емейли"),
                new ScheduleEntry("12:00", "Обід"),
                new ScheduleEntry("14:00", "Командна зустріч"),
                new ScheduleEntry("16:00", "Розробка")
            ));
        }

        public static TuesdayWindow getInstance() {
            if (instance == null) {
                instance = new TuesdayWindow();
            }
            return instance;
        }
    }

    public static class WednesdayWindow extends ScheduleWindow {
        private static WednesdayWindow instance;

        private WednesdayWindow() {
            super("Розклад на середу");
            setScheduleData(FXCollections.observableArrayList(
                new ScheduleEntry("08:00", "Планування"),
                new ScheduleEntry("10:00", "Розробка"),
                new ScheduleEntry("12:00", "Обід"),
                new ScheduleEntry("14:00", "Дзвінок з клієнтом"),
                new ScheduleEntry("16:00", "Тестування")
            ));
        }

        public static WednesdayWindow getInstance() {
            if (instance == null) {
                instance = new WednesdayWindow();
            }
            return instance;
        }
    }

    public static class ThursdayWindow extends ScheduleWindow {
        private static ThursdayWindow instance;

        private ThursdayWindow() {
            super("Розклад на четвер");
            setScheduleData(FXCollections.observableArrayList(
                new ScheduleEntry("08:00", "Огляд коду"),
                new ScheduleEntry("10:00", "Розробка"),
                new ScheduleEntry("12:00", "Обід"),
                new ScheduleEntry("14:00", "Синхронізація команди"),
                new ScheduleEntry("16:00", "Підготовка до демонстрації")
            ));
        }

        public static ThursdayWindow getInstance() {
            if (instance == null) {
                instance = new ThursdayWindow();
            }
            return instance;
        }
    }

    public static class FridayWindow extends ScheduleWindow {
        private static FridayWindow instance;

        private FridayWindow() {
            super("Розклад на п'ятницю");
            setScheduleData(FXCollections.observableArrayList(
                new ScheduleEntry("08:00", "Ретроспектива"),
                new ScheduleEntry("10:00", "Емейли"),
                new ScheduleEntry("12:00", "Обід"),
                new ScheduleEntry("14:00", "Розробка"),
                new ScheduleEntry("16:00", "Завершення робіт")
            ));
        }

        public static FridayWindow getInstance() {
            if (instance == null) {
                instance = new FridayWindow();
            }
            return instance;
        }
    }
}
