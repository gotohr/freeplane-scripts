
import org.freeplane.plugin.script.proxy.Proxy.Node

def Node root = node
def List<Node> nodes = root.getChildren()

def Integer timedTasksCount = 0
def Integer doneTasksCount = 0
def Float doneTimeCount = 0

def countTotal
countTotal = { List<Node> nds ->
    def Float total = 0
    for (Node n : nds) {
        if (n.getAt("time")) {
            total += n.getAt("time").toFloat()
            timedTasksCount += 1
//            def i = n.getIcons().iterator()
//            while (i.hasNext()) {
//                def x = i.next()
//                def sub = n.createChild()
//                sub.text = x
//            }
            if (n.getIcons().contains("button_ok") && n.getAt("time")) {
                doneTasksCount += 1
                doneTimeCount += n["time"].toFloat()
            }
        }
        if (!n.getChildren().empty) {
            def innerTotal = countTotal(n.getChildren())
            if (innerTotal != 0) n["total"] = innerTotal
            total += innerTotal
        }
    }
    return total
}

root["totalTime"] = countTotal(nodes)
root["tasks"] = timedTasksCount
root["doneTasks"] = doneTasksCount
root["done%"] = doneTimeCount / root["totalTime"].toFloat() * 100
root["doneTime"] = doneTimeCount