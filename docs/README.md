# Tilo User Guide

Tilo is a simple, command-line task management application that helps you keep track of your todos, deadlines, and events. It stores your tasks locally and provides an intuitive interface for managing your daily activities.

## Quick Start

1. Run the application: `java Tilo`
2. You'll see the Tilo welcome screen with ASCII art
3. Start adding tasks using the commands below
4. Type `bye` to exit and save your tasks

---

## Adding a todo: `todo`

Adds a simple todo task to your task list.

Format: `todo DESCRIPTION`

Example:
- `todo read book`

Expected output:
```
Got it. I've added this task:
 [T][ ] read book
Now you have 1 task in the list
```

💡 **Tip:** Todo tasks are the simplest type - they only need a description and can be marked as done or not done.

---

## Adding deadlines: `deadline`

Adds a task with a specific deadline to your task list.

Format: `deadline DESCRIPTION /by DATE_TIME`

Example:
- `deadline submit report /by 2024-10-15 2359`

Expected output:
```
Got it. I've added this task:
 [D][ ] submit report (by: 2024-10-15 2359)
Now you have 2 tasks in the list
```

💡 **Tip:** The `/by` keyword is required to separate the description from the deadline. You can use any date format that makes sense to you.

---

## Adding events: `event`

Adds an event with start and end times to your task list.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example:
- `event team meeting /from 2024-10-15 1400 /to 2024-10-15 1600`

Expected output:
```
Got it. I've added this task:
 [E][ ] team meeting (from: 2024-10-15 1400 to: 2024-10-15 1600)
Now you have 3 tasks in the list
```

💡 **Tip:** Both `/from` and `/to` keywords are required. Events are perfect for meetings, appointments, and activities with specific time periods.

---

## Listing all tasks: `list`

Displays all tasks in your task list with their completion status.

Format: `list`

Example output:
```
1. [T][ ] read book
2. [D][ ] submit report (by: 2024-10-15 2359)
3. [E][ ] team meeting (from: 2024-10-15 1400 to: 2024-10-15 1600)
```

💡 **Tip:** Tasks are numbered starting from 1. The letters [T], [D], [E] indicate Todo, Deadline, and Event respectively. [ ] means not done, [X] means completed.

---

## Marking tasks as done: `mark`

Marks a specific task as completed.

Format: `mark TASK_NUMBER`

Example:
- `mark 1`

Expected output:
```
Nice! I've marked this task as done:
 [T][X] read book
```

💡 **Tip:** Use the task numbers shown in the `list` command. Completed tasks will show [X] instead of [ ].

---

## Unmarking tasks: `unmark`

Marks a completed task as not done.

Format: `unmark TASK_NUMBER`

Example:
- `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
 [T][ ] read book
```

💡 **Tip:** Useful when you accidentally mark a task as done or need to work on it again.

---

## Deleting tasks: `delete`

Removes a task permanently from your task list.

Format: `delete TASK_NUMBER`

Example:
- `delete 1`

Expected output:
```
Got it. I've deleted this task:
 [T][ ] read book
Now you have 2 tasks in the list
```

⚠️ **Warning:** Deleted tasks cannot be recovered. Make sure you want to permanently remove the task.

---

## Finding tasks: `find`

Searches for tasks containing a specific keyword in their description.

Format: `find KEYWORD`

Example:
- `find report`

Expected output:
```
Here are the matching tasks in your list:
1. [D][ ] submit report (by: 2024-10-15 2359)
```

💡 **Tip:** Search is case-insensitive and matches partial words. For example, searching "meet" will find "meeting".

---

## Exiting the application: `bye`

Saves your tasks and exits the application.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```

💡 **Tip:** Your tasks are automatically saved to `./data/tilo.txt` when you exit properly using the `bye` command.

---

## Task Status Icons

| Icon | Task Type | Status |
|------|-----------|---------|
| [T][ ] | Todo | Not completed |
| [T][X] | Todo | Completed |
| [D][ ] | Deadline | Not completed |
| [D][X] | Deadline | Completed |
| [E][ ] | Event | Not completed |
| [E][X] | Event | Completed |

---

## Error Handling

Tilo provides helpful error messages when something goes wrong:

- **Empty commands**: "Please enter a command."
- **Invalid commands**: Suggests valid commands to try
- **Invalid task numbers**: "Task number must be between 1 and X"
- **Empty task list**: "No tasks available. Add some tasks first."
- **Missing required fields**: Tells you which field is missing

---

## Data Storage

- Tasks are automatically saved to `./data/tilo.txt`
- The data folder is created automatically if it doesn't exist
- Tasks are loaded when you start Tilo
- If the save file is corrupted, Tilo will start with an empty task list

---

## Tips for Effective Task Management

1. **Use descriptive names**: Instead of "homework", use "complete math assignment chapter 5"
2. **Be specific with deadlines**: Include both date and time when possible
3. **Regular reviews**: Use `list` frequently to see what needs to be done
4. **Clean up**: Delete completed tasks that you no longer need to track
5. **Search efficiently**: Use `find` to quickly locate specific tasks in large lists

---

## Troubleshooting

**Q: My tasks disappeared!**
A: Check if the `./data/tilo.txt` file exists. If it's corrupted, Tilo starts with an empty list.

**Q: I get "command not found" errors**
A: Make sure you're using the exact command names: `todo`, `deadline`, `event`, `list`, `mark`, `unmark`, `delete`, `find`, `bye`.

**Q: Task numbers seem wrong**
A: Task numbers change when you delete tasks. Always check with `list` first to see the current numbering.

**Q: Can I edit existing tasks?**
A: Currently, you need to delete the old task and create a new one. Direct editing is not supported.

---

Enjoy using Tilo for your task management needs! 🎯
