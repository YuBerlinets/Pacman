# Pacman
## Game Pacman, 2nd project for PJATK GUI course
The application represents the default Pacman game with Pacman itself and ghosts. 
Map based on JTable, which creates based on AbstractTableModel. 
The map is generated from the array of integers with different values, representing other cells in our app's GUI. 
Every 5 sec there is a possibility to spawn a boost.
After finishing the game, the user is provided with a Frame of the saving score, where the user should enter their name.
All scores are saved in the Score menu, scores based on the JList component. All data are being saved using Serializable interface
