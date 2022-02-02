If you want to change our script and compile again, please use these source 
code files. Otherwise you might run into crashes due to misinterpreted/unknown
opcode definitions.

Here's the best practice for compiling the changes:
- Make sure that Sanny Builder's mode is set to Vice City and you have the 
  mod's folder set under GTA VC directory in Sanny Builder's general options.
- Under Editor, I recommend to also configure to load last closed file at 
  startup and load all closed files. This is a personal preference.
- After making changes to the script, always open main.sc and use the 
  Compile + copy (F7) function. This will compile the whole script together
  with the other files, as {$INCLUDE statements add them into the necessairy 
  locations when compiling. Also, Sanny Builder will then copy the compiled 
  file right into the game's data directory, so no need to copy anything 
  manually or even adding main.txt or any other source files into the data 
  directory.
- At around line 360 in main.sc you'll find memory addresses noted in comments
  to the variables. Try to avoid removing or adding any variables above this 
  point, otherwise you'll need to change the memory addresses in the C++
  plugin, too.

P.S.: If you do any changes, please share them with us on the forums :-)!

~ Mini-Me
12.11.2015