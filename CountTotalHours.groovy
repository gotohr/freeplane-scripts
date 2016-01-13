import groovy.time.TimeCategory
import org.freeplane.plugin.script.proxy.Proxy.Node
import pm.*

import java.text.DateFormat
import java.text.SimpleDateFormat

def Node root = node
def List<Node> nodes = root.getChildren()

def Integer timedTasksCount = 0
def Integer doneTasksCount = 0
def Float doneTimeCount = 0
def Float spentTimeCount = 0

def countTotal
countTotal = { List<Node> nds ->
    def Float total = 0
    for (Node n : nds) {
        def t = Task.getTask(n)
        if (t) {
            total += t.getEstimatedTime()
            timedTasksCount += 1

            if (t.isDone()) {
                doneTasksCount += 1
                doneTimeCount += t.getEstimatedTime()
            }

            def tt = t.getTimeTable()
            if (tt) {
                def Float spentTime = tt.getSpentTime()
                t.node["spentTime"] = spentTime
                spentTimeCount += spentTime
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
root["spentTime"] = spentTimeCount