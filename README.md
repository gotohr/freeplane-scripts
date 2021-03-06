# Freeplane scripts

see about [Freeplane](http://www.freeplane.org/wiki/index.php/Main_Page) and [download](http://sourceforge.net/projects/freeplane) it

checkout this repo in your ~/.config/freeplane/1.3.x/scripts
```bash
git clone git@github.com:gotohr/freeplane-scripts.git scripts
```
In Freeplane settings (Tools -> Preferences) open Plugins configuration. Enable "Permit file/read operations" and add Script classpath: scripts/lib/

After changing preferences restart Freeplane. Scripts are available under Tools -> Scripts. Each script is made to be executed on node level. It is best to wireup some hotkeys to "Execute [script] on one node" on every script.
To do that just hover mouse over any menu item, press control key and select menu item and Freeplane will ask you to provide shortcut key for it.

## Project management scripts

### Count Time Hours
Goes through all nodes recursively and checks all those that have "estimatedTime" attribute. 
Adds "totalTime" attribute to currently selected node. Calculates project spentTime from all timetables on task nodes.
Best to be executed on root project node.

### Add Time To Task
just slaps attribute "estimatedTime" to current node (and popups dialog to enter time!

### Toggle Task Timer
toggles active state on task (node with "estimatedTime" attribute). Active state means adding specific icon ("25%" icon) to node. Crates child node timetable where script writes times. Timetable child node is not meant to be used directly, but if you need so - can be changed "by hand".

## TODO
* Jira synchronization
  * authentication via creds in external config
  * Jira project node - node with "jiraProjectId" attribute
  * upsert all Jira defined tasks per project
  * upsert all local freeplane defined tasks
  * upsert individual tasks
  * pull defined project members for assigning task to member
* Gantt chart - project tasks in timeline with members on vertical axis
* export filtered project/branch/tasks to HTML with attributes
  * publish report to server/email?
* script that assigns hot keys for pm scripts (is this possible?)
 
