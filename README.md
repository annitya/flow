flow
====

A Intellij plugin

Displays an interactive map of caller/callee-functions for your current context.
Allows to focus on the one thing that matters: the code.

TODO:

Validate the scope/performance of find-reference.
Design/UX.
Some unit-tests would be fun.

DONE:

Convert GUI to a tool-window.
Bind to a more sensible event(s): Only update if:
    1. The "flow" of the application has changed.
    2. The cursor is now in a different method than before.
