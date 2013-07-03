#Usage

Run `make` and then run the app with `java -classpath lib\rsyntaxtextarea.jar;bin. Jot`

The additional scripts serve no real purpose atm unless on Windows where you can use `jot.bat` to run after compile or slap together a portable jar with `make exec`.


#Misc

I *know* what you are thinking.. another Java Text Editor.. yep. :)

Jot is not the average `JTextArea` however and even though I can't dedicate much time to it atm, I plan to make this evolve into something useful eventually while learning Java.

It is still very, very and I still cannot stress enough, *very* experimental. Around **v0.0.4** I will start to shape it up.
Alot of it so far has just been hacked together, I sort of brainstorm and code at the same time.
Currently, it is very limited in functionality, most of what is present is constant and there is no way yet to change it,
the syntax, font etc. All will be addressed in due time.

I will try to make it as stable as possible each commit so it can be used.
I have been using a slightly *ahead* version for the past few weeks as my core editor to see what I normally use most and trying to break it.

Currently, most of the buttons are broken.. ;) and it is limited to one open tab at the moment, but you can open and save files.

Jot makes use of the excellent [RSyntaxTextArea](http://fifesoft.com/rsyntaxtextarea/) for highlighting amongst other things, these will be listed in detail soon.
It is under `/lib` along with its LICENSE.

A few of the features that I have been working on that are coming along nicely are:
- <del>A mini prompt for typing commands *(I don't like mice..)*</del>
- <del>Full screen view</del>
- <del>Autodetect opened/saved syntax</del>
- Theme switching
- Font changer
- Autocomplete
- (Find) and Replace

and several other key features that any modern text editor has..

I have never written an editor before, and to undertake such a project in a relatively new language is a bit much, I will try to update whenever I can.

Earlier Days (pre v0.0.1)
-
![jot!](http://f.cl.ly/items/1y430J3E3T2T243E3705/jotgui.png)

v0.0.1 Release
-
![jot!](http://cl.ly/image/1y1P3q3n0U0f/gui-2.png)

v0.0.2 Release
-
![jot!](http://cl.ly/image/2H2w3q2m1O0N/Jot0.0.2.png)
