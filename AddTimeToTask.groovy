import org.freeplane.plugin.script.proxy.Proxy.Node
import groovy.swing.SwingBuilder
import pm.Task

swing = new SwingBuilder()
def Node selectedNode = node

def String result
swing.edt {
    result = optionPane().showInputDialog(null, "Time?", "1")
    def task = new Task(selectedNode)
    task.setEstimatedTime(result.toFloat())
}

