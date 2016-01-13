package pm

import groovy.time.TimeCategory
import groovy.time.TimeDuration
import org.freeplane.plugin.script.proxy.Proxy.Node

import java.text.DateFormat
import java.text.SimpleDateFormat

class Task {
    Node node

    Task(Node node) {
        this.node = node
    }

    public static Task getTask(node) {
        node.getAt("estimatedTime") ? new Task(node) : null
    }

    def setEstimatedTime (Float time) { this.node["estimatedTime"] = time }
    Float getEstimatedTime() { this.node.getAt("estimatedTime").toFloat() }

    def printIcons() {
        def i = this.getIcons().iterator()
        while (i.hasNext()) {
            def x = i.next()
            def sub = this.node.createChild()
            sub.text = x
        }
    }

    TimeTable getTimeTable() {
        TimeTable.getInstance(this)
    }

    def startTimer() {
//        this.getNode().children.contains(it.)
//        this.node.createChild()
    }

    def stopTimer() {

    }

    Boolean isDone() { getEstimatedTime() && this.node.getIcons().contains("button_ok") }

}

class TimeTable {
    Task task
    Node node

    def DateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy")

    public static TimeTable getInstance(Task task) {
        def n = task.node.children.find { it.text == "timetable" }
        def tt
        if (n) {
            tt = new TimeTable()
            tt.setTask(task)
            tt.setNode(n)
            return tt
        } else tt = null
        tt
    }

    public def Float getSpentTime() {
        def Integer h = 0
        def Float m = 0
        this.node.children.each {
            if (it.children.size() > 1) {
                def diff = TimeCategory.minus(
                    formatter.parse(it.children.getAt(1).text),
                    formatter.parse(it.children.getAt(0).text)
                )
                h += diff.getHours()
                m += diff.getMinutes()
            }
        }
        h + m / 60
    }
}