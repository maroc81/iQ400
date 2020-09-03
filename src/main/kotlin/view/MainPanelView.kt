package view

import controller.MainPanelController
import javafx.geometry.Orientation
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import tornadofx.*

typealias Row = MutableMap<String, String>

class MainPanelView : View("Dynamic Table View") {
    private val controller: MainPanelController by inject()
    var table: TableView<Row> by singleAssign()
    val query = stringProperty("select * from CDCART00")

    override val root = borderpane {
        top = label("File")

        center = splitpane(Orientation.VERTICAL) {
            textarea {
                textProperty().bind(query)
            }
            table = tableview {
            }
        }

        bottom = hbox {
            label(controller.statusToView)
        }
    }

    init {
        shortcut("Meta+Enter") {
            runAsync { controller.loadQueryToTableView(query.value) }
        }
    }
}
