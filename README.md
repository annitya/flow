flow
====

A Intellij plugin

Displays two interactive windows, one containing the selected caller-method's code, one containing the selected callee's code.
Allows to focus on the one thing that matters: the code, and the flow of it.

TODO:

Allow the user to move between scopes.
Validate the scope/performance of find-reference.
Design/UX.
Some unit-tests would be fun.

DONE:

Convert GUI to a tool-window.
Bind to a more sensible event(s): Only update if:
    1. The "flow" of the application has changed.
    2. The cursor is now in a different method than before.
