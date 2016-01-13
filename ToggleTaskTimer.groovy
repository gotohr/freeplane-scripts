
import org.freeplane.plugin.script.proxy.Proxy.Node
import groovy.swing.SwingBuilder
import pm.*

swing = new SwingBuilder()

def Node selectedNode = node
def task = new Task(selectedNode)

def iter = selectedNode.children.iterator()

def Node cn = null

while(iter.hasNext()) {
    def n = iter.next()
    if (n.text == "timetable") {
        cn = n
        break
    }
}

if (!cn) {
    cn = selectedNode.createChild()
    cn.text = "timetable"
}

if (!cn.children) {
    def entry = cn.createChild()
    entry.text = "1"
    def start = entry.createChild()
    start.text = new Date().toString()
    selectedNode.icons.add("25%")
} else {
    def last = cn.children.last()
    if (last.children.size() > 1) {
        def lastIndex = last.text.toInteger()
        def entry = last.parent.createChild()
        entry.text = lastIndex + 1
        def start = entry.createChild()
        start.text = new Date().toString()
        selectedNode.icons.add("25%")
    } else {
        def stop = last.createChild()
        stop.text = new Date().toString()
        selectedNode.icons.remove("25%")
    }
}

cn.setFolded(true)
