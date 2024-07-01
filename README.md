# Project Jujutsu 

A Forge 1.20.1 Minecraft Mod. Based off the popular anime Jujutsu Kaisen.  PROJECT CANCELED BY OWNER



Setup Process:
==============================

Step 1: Open your command-line and browse to the folder where you extracted the zip file.

Step 2: You're left with a choice.
If you prefer to use Eclipse:
1. Run the following command: `gradlew genEclipseRuns` (`./gradlew genEclipseRuns` if you are on Mac/Linux)
2. Open Eclipse, Import > Existing Gradle Project > Select Folder 
   or run `gradlew eclipse` to generate the project.

If you prefer to use IntelliJ:
1. Open IDEA, and import project.
2. Select your build.gradle file and have it import.
3. Run the following command: `gradlew genIntellijRuns` (`./gradlew genIntellijRuns` if you are on Mac/Linux)
4. Refresh the Gradle Project in IDEA if required.

If at any point you are missing libraries in your IDE, or you've run into problems you can 
run `gradlew --refresh-dependencies` to refresh the local cache. `grps://mcforge.readthedocs.io/en/latest/gettingstarted/
                                                                    LexManos' Install Video: https://www.youtube.com/watch?v=8VEdtQLuLO0  adlew clean` to reset everything
{this does not affect your code} and then start the process again.




## Todo List 

 ### Moves 
- [x] Booige Woogie 
- [Wip] Limitless
- [ ] More Coming soon

### Utilty 
- [x] RayCasting
- [X] Keybinds
- [X] HotBar 
- [WiP] HotBar Dsaving
- [ ] More Coming soon
### 




## Custom Utilities 

- Raycasting - Simply use rayTraceEyes From the Raycast file to find if a Entity is looking at another Entity Speficy The Entity you want to start the Raycast From and the end distance. Note The longer the Ray the Lagger it will become. 

- Chat - To show a message in chat Simply use the Chat Utilitie. Simply do Chat("Any message here").

- ShowActionBar - Create a Custom Action bar above the player healthbar displaying text speifcy the Entity and the Text .

- Glow - Simply use setglow and unglow to make a Entity Glow. 

- Custom Keybinds - Create a New Keybind in the file Keybindings by following the intructions then add it to the EN lang file. 

- Custom Commands - Copy and paste the template Code int he Commands folder which will allow you to create a Command. 

## Importent files 

- Server - Anything in the server file is to be exucuted on the server side of things 

- NetWorking - Anything in this File is Client to Server (Packets). 

Every file has a purpose don't create 1 millon files. Please never commit directly to the Repo Create Branches and Test them for bugs before commiting code. 

## Authors

- [@Codezey [Lead Developer]](https://www.github.com/c0dezey)

- [@Kain [Owner]]

- [@LegoMyLegoEgo [Developer]](https://www.github.com/GoodTimeWithMatt)

- [@EpicMidToker [Modler]]
