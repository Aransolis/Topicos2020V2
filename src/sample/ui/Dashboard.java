package sample.ui;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;

public class Dashboard extends Stage {

    //private TableView<PlatillosDAO>;

    public Dashboard(){
        CrearUI();
        this.setTitle("Panel de administracion del Restaurante El Antojito:)");
        this.show();
        new PlatilloCRUD();
    }

    private void CrearUI() {
    }
}
