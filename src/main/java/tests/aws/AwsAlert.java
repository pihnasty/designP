//package tests.aws;
//
//import com.amazon.sct.AppInitializer;
//import com.amazon.sct.logger.Logger;
//import com.amazon.sct.util.StringUtils;
//import com.amazon.sct.settings.Settings;
//import com.amazon.sct.settings.SettingsProvider;
//import com.amazon.sct.util.CrossPlatformUtils;
//import com.amazon.sct.view.control.HyperlinkAws;
//import com.amazon.sct.view.util.GuiUtils;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Window;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.MessageFormat;
//import java.util.Arrays;
//
//
///**
// * AwsAlert
// * Created by Meleshko.A on 30.03.2017.
// */
//public class AwsAlert extends Alert {
//
//    private static final String ALERT_INFO_ICON = "com/amazon/sct/view/alert/alert_info_icon.png";
//    private static final String ALERT_WARNING_ICON = "com/amazon/sct/view/alert/alert_warning_icon.png";
//    private static final String ALERT_ERROR_ICON = "com/amazon/sct/view/alert/alert_error_icon.png";
//    private static final String FXML = "/com/amazon/sct/view/alert/alertView.fxml";
//
//    @FXML
//    private HyperlinkAws showLogLink;
//
//    @FXML
//    private Label header;
//
//    @FXML
//    private Label text;
//
//    @FXML
//    private ImageView imageView;
//
//    @FXML
//    private TextArea textArea;
//
//    @FXML
//    private ButtonBar buttonBar;
//
//    @FXML
//    private VBox vBox;
//
//    public AwsAlert(AlertType alertType) {
//        super(alertType);
//    }
//
//    public AwsAlert(AlertType alertType, Window owner) {
//        super(alertType);
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML), AppInitializer.resourceBundle);
//            loader.setController(this);
//            loader.setRoot(this);
//            loader.load();
//        } catch (Exception e) {
//            Logger.UI.writeError(e, AppInitializer.resourceBundle.getString("ui.error.create.fxml") + " " + FXML);
//        }
//        setGraphic(null);
//        setContentText(null);
//        setHeaderText(null);
//        getDialogPane().setExpandableContent(null);
//        getDialogPane().setHeader(null);
//        buttonBar = (ButtonBar) getDialogPane().getChildren().stream().filter(p -> p instanceof ButtonBar).findFirst().orElse(null);
//        getButtonBar().getButtons().add(showLogLink);
//        if (alertType.equals(AlertType.CONFIRMATION)) {
//            getButtonTypes().add(new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
//        }
//        GuiUtils.initTitleDialogIcon(this);
////         J8 lambda response
////         showAndWait().filter(resp -> resp == ButtonType.OK).ifPresent(resp -> System.out.println("OK"));
//        if (owner != null) {
//            initOwner(owner);
//        }
//        imageView.setImage(chooseImageForAlert(alertType));
//    }
//
//    public AwsAlert(AlertType alertType, String title, String header, String message, Window owner) {
//        this(alertType, owner);
//        getText().heightProperty().addListener((observable, oldValue, newValue) -> {
//                    if ((double) newValue > 300) {
//                        setActive(getText(), false);
//                        setActive(getTextArea(), true);
//                        getHeader().setPadding(new Insets(0, 0, 0, 8));
//                    }
//                }
//        );
//        getTextArea().textProperty().bind(getText().textProperty());
//        setTitle(title);
//        if (StringUtils.isNullOrEmpty(message)) {
//            getText().setText(header);
//        } else {
//            getHeader().setText(header);
//            getText().setText(message);
//        }
//        if (StringUtils.isNullOrEmpty(getHeader().getText())) {
//            setActive(getHeader(), false);
//        }
//    }
//
//    public AwsAlert(AlertType alertType, String title, String header, String message, Window owner, boolean isShowLogLink, Node... contentNodes) {
//        this(alertType, title, header, message, owner, isShowLogLink);
//        if (contentNodes != null) {
//            Arrays.stream(contentNodes).filter(n -> n != null).forEach(n ->
//                    vBox.getChildren().add(n));
//        }
//    }
//
//    public AwsAlert(AlertType alertType, String title, String header, String message, Window owner, String description) {
//        this(alertType, owner);
//        getText().heightProperty().addListener((observable, oldValue, newValue) -> {
//                    if ((double) newValue > 300) {
//                        setActive(getText(), false);
//                        setActive(getTextArea(), true);
//                        getHeader().setPadding(new Insets(0, 0, 0, 8));
//                    }
//                }
//        );
//        getTextArea().textProperty().bind(getText().textProperty());
//        setTitle(title);
//        if (StringUtils.isNullOrEmpty(message)) {
//            getText().setText(header);
//        } else {
//            getHeader().setText(header);
//            Label ltext = new Label(message);
//            ltext.setWrapText(true);
//            ltext.setMaxWidth(getText().getMaxWidth());
//            ScrollPane sp = new ScrollPane(ltext);
//            getText().setMaxHeight(100);
//            getText().setGraphic(sp);
//        }
//        if (StringUtils.isNullOrEmpty(getHeader().getText())) {
//            setActive(getHeader(), false);
//        }
//    }
//
//    public AwsAlert(AlertType alertType, String title, String header, String message, Window owner, boolean isShowLogLink) {
//        this(alertType, title, header, message, owner);
//        setActive(showLogLink, isShowLogLink);
//    }
//
//    public void setActive(Node node, boolean isActive) {
//        node.setVisible(isActive);
//        node.setManaged(isActive);
//    }
//
//    @FXML
//    void onShowLog() {
//        try {
//            CrossPlatformUtils.openFileIfItPossible(new File(SettingsProvider.getGlobalSettings().get(Settings.Keys.LOG_FOLDER_UI)));
//        } catch (IOException e) {
//            String message = MessageFormat.format(AppInitializer.resourceBundle.getString("ui.error.alert.show.log"), SettingsProvider
//                    .getGlobalSettings().get(Settings.Keys.LOG_FOLDER_UI));
//            Logger.UI.writeError(e, message);
//            GuiUtils.createAlert(AlertType.ERROR, AppInitializer.resourceBundle.getString("ui.error.alert.show.log.error"), message,
//                    null, this.getOwner(), false).show();
//        }
//    }
//
//    @FXML
//    void onClose() {
//        this.close();
//    }
//
//    private static Image chooseImageForAlert(Alert.AlertType type) {
//        Image iv = null;
//        switch (type) {
//            case INFORMATION:
//                iv = new Image(ALERT_INFO_ICON);
//                break;
//            case ERROR:
//                iv = new Image(ALERT_ERROR_ICON);
//                break;
//            case WARNING:
//            case CONFIRMATION:
//                iv = new Image(ALERT_WARNING_ICON);
//                break;
//            default:
//                break;
//        }
//        return iv;
//    }
//
//    public Label getHeader() {
//        return header;
//    }
//
//    public void setHeader(Label header) {
//        this.header = header;
//    }
//
//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public void setImageView(ImageView imageView) {
//        this.imageView = imageView;
//    }
//
//    public ButtonBar getButtonBar() {
//        return buttonBar;
//    }
//
//    public void setButtonBar(ButtonBar buttonBar) {
//        this.buttonBar = buttonBar;
//    }
//
//    public TextArea getTextArea() {
//        return textArea;
//    }
//
//    public void setTextArea(TextArea textArea) {
//        this.textArea = textArea;
//    }
//
//    public Label getText() {
//        return text;
//    }
//
//    public void setText(Label text) {
//        this.text = text;
//    }
//
//    public VBox getvBox() {
//        return vBox;
//    }
//
//    public void setvBox(VBox vBox) {
//        this.vBox = vBox;
//    }
//}
