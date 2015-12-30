
import org.freeplane.plugin.script.proxy.Proxy.Node
import groovy.swing.SwingBuilder

swing = new SwingBuilder()

def result
swing.edt {
    result = optionPane().showInputDialog(null, "Time?", "1")
}
def Node root = node
root["time"] = result